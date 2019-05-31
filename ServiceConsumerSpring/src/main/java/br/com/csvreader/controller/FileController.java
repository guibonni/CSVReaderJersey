package br.com.csvreader.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.csvreader.model.School;
import br.com.csvreader.util.CSVReader;


@RestController
public class FileController {
	
	// http://localhost:8080/api/readfile
	@GetMapping("api/readfile")
	public String all(){
		
		try {
			int sucessos = 0;
			int falhas = 0;
			
			String file = "/home/gui/Documentos/Faculdade/SD/CSVReaderProject/arquivo_dados.csv";
			
			Reader in = new FileReader(file);
			
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			
			for (CSVRecord record : records) {
				try {
					School school = new School();
					
					school.setId(Integer.parseInt(record.get("ID")));
					school.setSchoolCode(record.get("SchoolCode"));
					school.setSchoolName(record.get("SchoolName"));
					school.setAddress(record.get("Address"));
					school.setCity(record.get("City"));
					school.setStateCode(record.get("StateCode"));
					school.setZipCode(record.get("ZipCode"));
					school.setProvince(record.get("Province"));
					school.setCountry(record.get("Country"));
					school.setPostalCode(record.get("PostalCode"));
					
					if (saveFile(school)) {
						sucessos++;
					} else {
						falhas++;
					}
				} catch (NumberFormatException e) {
					falhas++;
				}
			}
			
			return "Leitura do arquivo finalizada. Sucessos: " + String.valueOf(sucessos) + ", Falhas: " + String.valueOf(falhas);
		} catch(FileNotFoundException e) {
			return "Arquivo não encontrado";
		} catch(IOException e) {
			return "Falha ao ler o arquivo";
		} catch (Exception e) {
			return "Erro desconhecido";
		}
	}
	
	private static boolean saveFile(School school) {
		boolean sucesso;
		
		try {
			URL url = new URL("http://localhost:8080/api/database/save/");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String input = school.toJson();
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				sucesso = false;
			} else {
				sucesso = true;
			}
			
			conn.disconnect();
		} catch (MalformedURLException e) {
			sucesso = false;
		} catch (IOException e) {
			sucesso = false;
		} catch (RuntimeException e) {
			sucesso = false;
		}
		
		return sucesso;
	}
}
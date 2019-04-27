package br.com.csvreader.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.csvreader.model.School;
import br.com.csvreader.util.CSVReader;

@Path("/readFile")
public class ReadFileServlet {
	
	@GET // http://localhost:8080/ServiceConsumer/rest/readFile/getFile
	@Path("/getFile")
	@Produces("application/json")
	public Response getFile() {
		CSVReader reader = new CSVReader();
		String file = "Documentos/Faculdade/SD/CSVReaderProject/arquivo_dados.csv";
		
		if (reader.fileExists(file)) {
			List<List<String>> fileData = reader.readFile(file);
			List<School> schoolsList = new ArrayList<School>();
			
			for (List<String> linha : fileData) {
				try {
					School school = new School();
					
					school.setID(Integer.parseInt(linha.get(0)));
					school.setSchoolCode(linha.get(1));
					school.setSchoolName(linha.get(2));
					school.setAddress(linha.get(3));
					school.setCity(linha.get(4));
					school.setStateCode(linha.get(5));
					school.setZipCode(linha.get(6));
					// school.setProvince(linha.get(7));
					// school.setCountry(linha.get(8));
					// school.setPostalCode(linha.get(9));
					
					schoolsList.add(school);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
			String resposta = "{" + schoolsList.get(0).toJson() + "," + schoolsList.get(1).toJson() + "}";
			
			// return Response.status(200).entity(saveFile(schoolsList)).build();
			return Response.status(200).entity(resposta).build();
		} else {
			return Response.status(200).entity("Arquivo não encontrado").build();
		}
	}
	
	private static String saveFile(List<School> schools) {
		String erro = "";
		
		try {
			URL url = new URL("http://localhost:8080/ServiceConsumer/rest/database/");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String input = "{\"codigo\":2,\"nome\":\"Guilherme\"}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed. HTTP error code: " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			String output;
			String response = "";
			
			while ((output = br.readLine()) != null) {
				response += output;
			}
			
			conn.disconnect();
			
			return response;
		} catch (MalformedURLException e) {
			erro = "MalformedURLException: " + e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			erro = "IOException: " + e.getMessage();
			e.printStackTrace();
		} catch (RuntimeException e) {
			erro = "RuntimeException: " + e.getMessage();
			e.printStackTrace();
		}
		
		return erro;
	}

}

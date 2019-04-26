package br.com.csvreader.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/readFile")
public class ReadFileServlet {
	
	@GET // http://localhost:8080/ServiceConsumer/rest/readFile/getFile
	@Path("/getFile")
	@Produces("application/json")
	public Response getFile() {
		// return "O arquivo foi lido com sucesso.";
		return Response.status(200).entity(saveFile()).build();
	}
	
	private static String saveFile() {
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

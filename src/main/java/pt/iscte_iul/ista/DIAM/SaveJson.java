package pt.iscte_iul.ista.DIAM;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.kohsuke.github.*;

public class SaveJson{
	
	 public static void main(String[] args) throws IOException {
		 
	        InputStreamReader ir = new InputStreamReader(System.in);
	        BufferedReader in = new BufferedReader(ir);
	        String  metodo ,OndeGuardar , nome ,DfilePath , SfilePath,donoR,repositorio,token;
	        
	        System.out.println("Especifique a Directoria do ficheiro Fonte\n");
	         SfilePath=in.readLine();
	         
	         System.out.println("Nome que pretende dar ao ficheiro\n");
	         nome =in.readLine()+".json" ;
	         
	        System.out.println("Porfavor, especifique o metodo que pretende salvar o ficheiro(web ou localmente)\n");
	        metodo = in.readLine();
	        if(metodo.contentEquals("localmente")){
	        	
	        	System.out.println("Directoria onde pretende salvar o ficheiro\n");
	         OndeGuardar = in.readLine();
	         
	         
	         DfilePath=OndeGuardar + nome ;
	         
	        
	        try {
	            // Create a FileReader to read data from the source CSV file
	            FileReader fileReader = new FileReader(SfilePath);
	            // Create a BufferedReader to read data from the FileReader
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            // Create a FileWriter to write data to the destination CSV file
	            FileWriter fileWriter = new FileWriter(DfilePath);
	            // Create a BufferedWriter to write data to the FileWriter
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            String line;
	            // Read each line of the source JSON file and write it to the destination JSON file
	            while ((line = bufferedReader.readLine()) != null) {
	                bufferedWriter.write(line);
	                bufferedWriter.newLine();
	            }
	            // Close the BufferedReader and BufferedWriter
	            bufferedReader.close();
	            bufferedWriter.close();
	            System.out.println("Data written to the destination JSON file successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        }
	        if(metodo.contentEquals("web")){
	        	
	        	// GitHub repository information
	        	System.out.println("GitHub Username:\n");
	            donoR = in.readLine();
	            System.out.println("Repositorio GitHub:\n");
	            repositorio = in.readLine();
	           
	            
	            // GitHub API token for authentication
	            System.out.println("Token de autenticacao GitHub:\n");
	            token = in.readLine();
	            
	            // Read the existing JSON file from disk
	            Path path = Paths.get(SfilePath);
	            byte[] fileContent = Files.readAllBytes(path);
	            
	            // Authenticate with GitHub using the API token
	            GitHub github = new GitHubBuilder().withOAuthToken(token).build();
	            
	            // Get the repository to update the file in
	            GHRepository repository = github.getRepository(donoR + "/" + repositorio);
	            
	            
	            
	         // Create a new file in the repository with the JSON data
	            GHContentBuilder builder = repository.createContent();
	            builder.content(fileContent).message("Added " + nome).path(nome).commit();
	            
	            // Print a message to indicate that the file was uploaded
	            System.out.println("File " + nome + " was uploaded to " + donoR + "/" + repositorio);
	          }
	 }
}
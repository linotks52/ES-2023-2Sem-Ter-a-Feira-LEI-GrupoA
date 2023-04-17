package pt.iscte_iul.ista.DIAM;

import okhttp3.*;
import java.io.*;
import org.kohsuke.github.*;

public class SaveJson{
	
	 public static void main(String[] args) throws IOException {
		 
	        InputStreamReader ir = new InputStreamReader(System.in);
	        BufferedReader in = new BufferedReader(ir);
	        String  metodo ,OndeGuardar , nome ,DfilePath , SfilePath;
	        
	        System.out.println("Especifique a Directoria onde se localiza o ficheiro Fonte\n");
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
	        	
	            String owner = "github_username";
	            String repoName = "github_repository_name";
	            String fileName = "file_name.csv";
	            
	         // GitHub API token for authentication
	            String token = "github_api_token";
	            
	            // Authenticate with GitHub using the API token
	            GitHub github = new GitHubBuilder().withOAuthToken(token).build();
	            
	            // Get the repository to upload the file to
	            GHRepository repository = github.getRepository(owner + "/" + repoName);
	            
	            // Create a new file in the repository with the CSV data
	            GHContentBuilder builder = repository.createContent();
	            builder.content(fileContent).message("Added " + fileName).path(fileName).commit();
	            
	            // Print a message to indicate that the file was uploaded
	            System.out.println("File " + fileName + " was uploaded to " + owner + "/" + repoName);
	          }
	   
	 
	}
}
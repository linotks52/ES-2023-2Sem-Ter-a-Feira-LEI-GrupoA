package Save;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.kohsuke.github.*;

/**
 * Classe que Grava um Ficheiro Csv numa diretoria ou repositorio no GitHub
 * @author JoaoMariaFranco
 *
 */

public class SaveCsv{
	/**
	 * Metodo saveOnline que salva um ficheiro Csv num repositorio GitHub atraves de um token pessoal.
	 * 
	 * @throws IOException
	 */
	public static void saveOnline(String username, String repository, String token, String path, String name) throws IOException{
		name = name + ".csv";
		
		Path file = Paths.get(path);
		byte[] fileContent = Files.readAllBytes(file);

		// Autenticar utilizado o token do API do GitHub
		GitHub github = new GitHubBuilder().withOAuthToken(token).build();

		// Buscar o repositorio
		GHRepository connect = github.getRepository(username + "/" + repository);

		// Criar um ficheiro novo no repositorio com os dados do ficheiro existente
		GHContentBuilder builder = connect.createContent();
		builder.content(fileContent).message("Added " + name).path(name).commit();
		System.out.println("File " + name + " was uploaded to " + username + "/" + repository);
	}


	/**
	 * Metodo saveLocalmente que salva um ficheiro Csv numa diretoria escolhida pelo utilizador.
	 * 
	 * @throws IOException
	 */
	
	
	public static void saveLocalmente(String name,String spath,String dpath){
		
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter= null;
		
		name=name+".csv";
		
		dpath=dpath + name;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(spath));
			bufferedWriter=new BufferedWriter(new FileWriter(dpath));

			String line;
			// Le cada linha do ficheiro json e rescreve-o no ficheiro json destino.
			while ((line = bufferedReader.readLine()) != null) {
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			System.out.println("Data written to the destination json file successfully.");
		} catch (IOException f){
			f.printStackTrace();
		}
		finally{
			if(bufferedReader != null && bufferedWriter != null){

				try{

					// Fecha o bufferedReader e bufferedWriter.
					bufferedReader.close();

					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
	
	
	
	}
}
}
	



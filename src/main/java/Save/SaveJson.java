package Save;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.kohsuke.github.*;

/**
 * Classe que Grava um Ficheiro json numa diretoria ou repositorio no GitHub
 * @author JoaoMariaFranco
 *
 */

public class SaveJson{
	/**
	 * Funcão que vai receber informacao do utilizador a cerca de onde ele pretend gravar um ficheiro (web).
	 * @param username Nome da conta github
	 * @param repository Repositório Gitbhub onde será salvo o ficheiro
	 * @param token Token de autenticação da conta 
	 * @param path Localização do ficheiro em disco
	 * @param name Nome que será dado ao ficheiro dentro do repositório
	 * @throws IOException caso hajam erros de input/output
	 * 
	 */
	
	public static void saveOnline(String username, String repository, String token, String path, String name) throws IOException{
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
	 * Funcão que vai receber informacao do utilizador a cerca de onde ele pretend gravar um ficheiro  (localmente).
	 * @param oldPath Antiga localização do ficheiro em disco
	 * @param newPath Nova localização do ficheiro em disco
	 * @throws IOException caso hajam erros de input/output
	 * 
	 */
	
	public static void saveLocal(String oldPath,String newPath) throws IOException{
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(ir);
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter= null;

		String  metodo ,OndeGuardar , nome ,DfilePath , SfilePath,donoR,repositorio,token;

		//System.out.println("Especifique a Directoria do ficheiro Fonte\n");

		//SfilePath=in.readLine();


		//System.out.println("Nome que pretende dar ao ficheiro\n");
		//nome =in.readLine()+".json" ;

		//System.out.println("Porfavor, especifique o metodo que pretende salvar o ficheiro(web ou localmente)\n");
		//metodo = in.readLine();

			//System.out.println("Directoria onde pretende salvar o ficheiro\n");
			//OndeGuardar = in.readLine();


			//DfilePath=OndeGuardar + nome ;



			try {
				bufferedReader = new BufferedReader(new FileReader(oldPath));
				bufferedWriter=new BufferedWriter(new FileWriter(newPath));

				String line;
				// Le cada linha do ficheiro json e rescreve-o no ficheiro json destino.
				while ((line = bufferedReader.readLine()) != null) {
					bufferedWriter.write(line);
					bufferedWriter.newLine();
				}
				System.out.println("Data written to the destination successfully.");
			} catch (FileNotFoundException f){
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
	
	public static void main(String[] args) throws IOException{
		
	}
}



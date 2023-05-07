package Save;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.kohsuke.github.*;

/**
 * Classe que Grava um Ficheiro csv numa diretoria local ou repositorio no GitHub
 * 
 * @author JoaoMariaFranco
 *
 */

public class SaveCsv{
	/**
	 * Metodo saveOnline que salva um ficheiro csv num repositorio GitHub atraves de um token pessoal.
	 * 
	 * @param username nome do dono do repositorio a que se quer aceder.
	 * @param repository nome do repositorio que se quer aceder.
	 * @param token token de accesso pessoal a um repositorio.
	 * @param path Localizacao do ficheiro localmente , que se quer gravar no Github.
	 * @param name Novo nome a ser dado ao ficheiro csv (e.g. name=ficheiro -> novo ficheiro "ficheiro.csv").
	 * 
	 * @throws IOException
	 * 
	 * @author JoaoMariaFranco
	 */
	public static void saveOnline(String username, String repository, String token, String path, String name) 
						throws IOException{
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
		
	}


	/**
	 * Metodo saveLocalmente que salva um ficheiro Csv numa diretoria escolhida pelo utilizador.
	 * 
	 * @param name Novo nome a ser dado ao ficheiro Json (e.g. name=ficheiro -> novo ficheiro "ficheiro.csv").
	 * @param path Localizacao do ficheiro localmente .
	 * @param dpath Localizacao destino , onde se quer que o ficheiro seja gravado. 
	 * 
	 * @throws IOException
	 * 
	 * @author JoaoMariaFranco
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
			// Le cada linha do ficheiro csv e rescreve-o no ficheiro csv destino.
			while ((line = bufferedReader.readLine()) != null) {
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			
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




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
	 * Funcão main que vai receber informacao do utilizador a cerca de onde ele pretend gravar um ficheiro json (web ou localmente).
	 * 
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException{

		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(ir);
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter= null;

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
				bufferedReader = new BufferedReader(new FileReader(SfilePath));
				bufferedWriter=new BufferedWriter(new FileWriter(DfilePath));

				String line;
				// Le cada linha do ficheiro json e rescreve-o no ficheiro json destino.
				while ((line = bufferedReader.readLine()) != null) {
					bufferedWriter.write(line);
					bufferedWriter.newLine();
				}
				System.out.println("Data written to the destination json file successfully.");
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
			if(metodo.contentEquals("web")){

				// Informacao acerca do GITHUB
				System.out.println("GitHub Username:\n");
				donoR = in.readLine();
				System.out.println("Repositorio GitHub:\n");
				repositorio = in.readLine();


				// GitHub API token que serve da mesma forma que uma palavra-passe
				System.out.println("Token de autenticacao GitHub:\n");
				token = in.readLine();

				// Le o ficheiro json existente
				Path path = Paths.get(SfilePath);
				byte[] fileContent = Files.readAllBytes(path);

				// Autenticar utilizado o token do API do GitHub
				GitHub github = new GitHubBuilder().withOAuthToken(token).build();

				// Buscar o repositorio
				GHRepository repository = github.getRepository(donoR + "/" + repositorio);



				// Criar um ficheiro novo no repositorio com os dados do ficheiro existente
				GHContentBuilder builder = repository.createContent();
				builder.content(fileContent).message("Added " + nome).path(nome).commit();

				// Mensagem para indicar o upload
				System.out.println("File " + nome + " was uploaded to " + donoR + "/" + repositorio);
			}
		}
}



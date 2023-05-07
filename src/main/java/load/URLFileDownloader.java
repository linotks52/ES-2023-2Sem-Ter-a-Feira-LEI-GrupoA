package load;

import java.io.*;
import java.net.URL;

import convert.CsvToJson;
import convert.JsonToCsv;

/**
 * Classe abstrata que contém as funções para importar ficheiros por um url
 * @author tcast
 *
 */
public abstract class URLFileDownloader {
	
	
	  private URLFileDownloader() {
		    throw new IllegalStateException("URLFileDownloader class");
		  }
	/**
	 * Função que recebe um URL (String) e retorna o ficheiro nesse mesmo url
	 * @param url o URL do ficheiro a ser descarregado
	 * @return o ficheiro descarregado
	 * @throws IOException se houver um erro a descarregar o ficheiro
	 */
    public static File downloadFileFromURL(String url) throws IOException {
        // Create URL object
        URL downloadURL = new URL(url);

        // Get file name and extension from URL
        String fileName = downloadURL.getFile().substring(downloadURL.getFile().lastIndexOf('/') + 1);
        // Create file with original file name and extension
        File outputFile = new File(fileName);

        // Download file from URL and save to local file
        InputStream inputStream = downloadURL.openStream();
        OutputStream outputStream = new FileOutputStream(outputFile);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        inputStream.close();
        outputStream.close();
        
        File csv = new File("output.csv");

        
       if(fileName.endsWith("json")) {
        	return JsonToCsv.convert(outputFile);
        }else {
        	return JsonToCsv.convert(CsvToJson.convert(outputFile));
        }
    }
    
}


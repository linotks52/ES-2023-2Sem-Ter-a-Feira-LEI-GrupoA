package pt.iscte_iul.ista.DIAM;

import java.io.*;

public class SaveCsv {
	
	 public static void main(String[] args) {
		 
	        String OndeGuardar = "D:/Users/Utilizador/Desktop/CSVs";
	        String nome = "OLA";
	        String filePath=OndeGuardar + nome + ".csv"; 
	        
	        try {
	            // Create a File object
	            File file = new File(filePath);
	            // Create a FileOutputStream to write to the File object
	            FileOutputStream fileOutputStream = new FileOutputStream(file);
	            // Create a BufferedReader to read the data from the input stream of the file
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	            String line;
	            // Read data from the input stream and write it to the output stream
	            while ((line = bufferedReader.readLine()) != null) {
	                fileOutputStream.write(line.getBytes());
	                fileOutputStream.write('\n');
	            }
	            // Close the input stream and output stream
	            bufferedReader.close();
	            fileOutputStream.close();
	            System.out.println("CSV file saved successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}



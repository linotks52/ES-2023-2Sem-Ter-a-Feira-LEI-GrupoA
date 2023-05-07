package pt.iscte_iul.ista.ES;
import org.junit.jupiter.api.Test;

import convert.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.stream.Stream;

public class ConvertTeste {
    //json to csv
    /**
     *
     */
    @Test
    public void jsonToCsv(){
    JsonToCsv jtc = new JsonToCsv();
    File f = new File("input.json");
    File out = jtc.convert(f);
    try {
        File f1 = new File("default.csv");
    assertArrayEquals(Files.readAllBytes(f1.toPath()),Files.readAllBytes(out.toPath())," nao sao iguais" );
    } catch (FileNotFoundException e) {
        assertFalse(true);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
   
    }
    @Test
    public void csvToJson(){
        CsvToJson ctj  = new CsvToJson();
        File f = new File("input.csv");
        File out = ctj.convert(f);
        try {
            File f1 = new File("default.json");
        assertArrayEquals(Files.readAllBytes(f1.toPath()),Files.readAllBytes(out.toPath())," nao sao iguais" );
        } catch (FileNotFoundException e) {
            assertFalse(true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        }

}
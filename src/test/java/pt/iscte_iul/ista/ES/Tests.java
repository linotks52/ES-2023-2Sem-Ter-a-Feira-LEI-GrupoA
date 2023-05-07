package pt.iscte_iul.ista.ES;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;


import load.URLFileDownloader;
import timetable.showCSV;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author tcast
 * @version 1.0
 * Classe de testes
 */
public class Tests {
    public static void main(String[] args) throws IOException, ParseException {
        showCSV.showHorario(new File("output4.csv"));
    }
    
}
    

    
    
//}


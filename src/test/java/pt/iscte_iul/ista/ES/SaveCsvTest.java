package pt.iscte_iul.ista.ES;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import Save.SaveCsv;





class SaveCsvTest {

	@Test
	public void testSaveOnline() throws IOException {
		SaveCsv.saveOnline("JoaoMariaFranco","TesteES","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" ,"OLA.csv","TESTADO");
		GitHub github = new GitHubBuilder().withOAuthToken("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").build();
		GHRepository connect= github.getRepository("JoaoMariaFranco/TesteES");
		String f=connect.getFileContent("TESTADO.csv").getName();
		assertTrue(f.equals("TESTADO.csv"));
		
	}

	@Test
	public void testSaveLocalmente() throws IOException {
		
		
		String dFile="FicheirosDeTeste/";
		String sFile = "OLA.csv";
		SaveCsv.saveLocalmente("TESTE",sFile,dFile);
		File f = new File("FicheirosDeTeste/TESTE.csv");
		assertTrue(f.exists());
		f.delete();
		

	}

}
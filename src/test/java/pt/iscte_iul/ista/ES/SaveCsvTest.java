package pt.iscte_iul.ista.ES;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import save.SaveCsv;




class SaveCsvTest {

	@Test
	public void testSaveOnline() throws IOException {
		SaveCsv.saveOnline("JoaoMariaFranco","TesteES","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" ,"OLA.csv","TESTADO");
		GitHub github = new GitHubBuilder().withOAuthToken("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").build();
		GHRepository connect= github.getRepository("JoaoMariaFranco/TesteES");
		String f=connect.getFileContent("TESTADO.csv").getName();
		assertTrue(f.equals("TESTADO.csv"));
		connect.getFileContent("TESTADO.csv").delete("remover teste");
		
	}

	@Test
	public void testSaveLocalmente() throws IOException {
		
		
		String dFile="FicheirosDeTeste/TESTE.csv";
		String sFile = "OLA.csv";
		SaveCsv.saveLocalmente(sFile,dFile);
		File f = new File("FicheirosDeTeste/TESTE.csv");
		assertTrue(f.exists());
		f.delete();
		

	}

}
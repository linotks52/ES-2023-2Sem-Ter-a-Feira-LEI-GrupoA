package pt.iscte_iul.ista.ES;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import Save.SaveJson;




class SaveJsonTest {

	@Test
	public void testSaveOnline() throws IOException {
		SaveJson.saveOnline("JoaoMariaFranco","TesteES","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" ,"OLA.json","TESTADO");
		GitHub github = new GitHubBuilder().withOAuthToken("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").build();
		GHRepository connect= github.getRepository("JoaoMariaFranco/TesteES");
		String f=connect.getFileContent("TESTADO.json").getName();
		assertTrue(f.equals("TESTADO.json"));
		
	}

	@Test
	public void testSaveLocalmente() throws IOException {
		
		
		String dFile="FicheirosDeTeste/";
		String sFile = "OLA.csv";
		SaveJson.saveLocalmente("TESTE",sFile,dFile);
		File f = new File("FicheirosDeTeste/TESTE.json");
		assertTrue(f.exists());
		f.delete();
		

	}

}
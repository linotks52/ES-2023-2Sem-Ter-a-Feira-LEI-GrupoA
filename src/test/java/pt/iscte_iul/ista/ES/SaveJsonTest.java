package pt.iscte_iul.ista.ES;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import save.SaveJson;




class SaveJsonTest {

	@Test
	public void testSaveOnline() throws IOException {
		SaveJson.saveOnline("JoaoMariaFranco","TesteES","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" ,"OLA.json","TESTADO");
		GitHub github = new GitHubBuilder().withOAuthToken("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").build();
		GHRepository connect= github.getRepository("JoaoMariaFranco/TesteES");
		String f=connect.getFileContent("TESTADO.json").getName();
		assertTrue(f.equals("TESTADO.json"));
		connect.getFileContent("TESTADO.json").delete("remover teste");
		
	}

	@Test
	public void testSaveLocalmente() throws IOException {
		
		
		String dFile="FicheirosDeTeste/TESTE.json";
		String sFile = "OLA.csv";
		SaveJson.saveLocalmente(sFile,dFile);
		File f = new File("FicheirosDeTeste/TESTE.json");
		assertTrue(f.exists());
		f.delete();
		

	}

}
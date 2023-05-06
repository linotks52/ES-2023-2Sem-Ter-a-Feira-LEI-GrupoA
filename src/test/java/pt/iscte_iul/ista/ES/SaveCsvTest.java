package pt.iscte_iul.ista.ES;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import Save.SaveJson;

class SaveJsonTest {

	@Test
	public void testSaveOnline() {
		
	}

	@Test
	public void testSaveLocalmente() throws IOException {
		Path tempDir = Files.createTempDirectory("test");
		
		String dFile=tempDir.toString();
		String sFile = "/ES/OLA.json";
		SaveJson.saveLocalmente("TESTE",sFile,dFile);
		
		assertTrue(Files.exists(tempDir.resolve("TESTE.csv")));
		
		Files.delete(tempDir.resolve("TESTE.csv"));
	    Files.delete(tempDir);

	}

}
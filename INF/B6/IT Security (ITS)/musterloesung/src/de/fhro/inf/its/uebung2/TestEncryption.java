package de.fhro.inf.its.uebung2;

import junit.framework.TestCase;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Logger;

public class TestEncryption  extends TestCase{
	private static Logger logger = Logger.getLogger(TestEncryption.class.getName());

	private Decryption dec;
	private byte[] fileContent;

    private static final String FILENAME = "test.txt";
    private static final String ENCTEXT = "encTest.txt";
    private static final String KEY = "key.txt";



    public void testEncryption(){
    	encryption();
    	decryption();
    }
    
    public void encryption()
    {
    	try {
	        logger.info("*****************************************************************");
	        logger.info("Encrypt");
	        logger.info("Read Text From File: " + FILENAME);
	        byte[] data = Encryption.readFromFile(FILENAME);
	        logger.info("Generate Key");
	        SecretKey key = Encryption.generateKey();
	        logger.info("encrypting...");
	        byte[] encryptedData = Encryption.encrypt(key, data);
	        logger.info("Write Key and Encrypted Text to File");
	        Encryption.writeToFile(ENCTEXT, encryptedData);
	        Encryption.saveKey(KEY, key);
	    } catch (Exception e){
    		logger.info("Error in encryption");
    		logger.info(e.getMessage());
    		fail();
    	}
    }

    public void decryption()
    {
        byte[] decryptedData = null;
		try {
	        logger.info("***************************************************************");
		    logger.info("Decrypt");
		    logger.info("Load encrypt from File: " + ENCTEXT);
			byte[] encryptedData = Decryption.readFromFileBase64(ENCTEXT);
		    logger.info("Read Key and Text from File");
		    SecretKey key = Decryption.readKey(KEY);
		    logger.info("Decrypting...");
			decryptedData = Decryption.decrypt(key, encryptedData);
			logger.info("Decrypted File: " + new String(decryptedData));
		} catch (Exception e) {
    		logger.info("error in decryption");
    		logger.info(e.getMessage());
    		fail();
		}
       
        // read original file and compare with result of encrypting and decrypting
        if (decryptedData != null) {
	        try {
				fileContent = Files.readAllBytes(Paths.get(FILENAME));
	        } catch (Exception e) {
	        	logger.info(e.getMessage());
	        }
	        assertTrue(Arrays.equals(decryptedData,fileContent));
			assertEquals(new String(decryptedData),new String(fileContent));
        }
    }
}

package de.fhro.inf.its.uebung04;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.security.Key;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.logging.Logger;

public class TestDigitalSignature {
	private static final Logger logger = Logger.getLogger(TestDigitalSignature.class.getName());
    private static final String FILE = "testSignature.txt";  // file to sign
    private static final String SIGFILE = "testSignature.sig.txt";  // file with signature
    private static final String KEYSTORE = "./keyData/mykeystore"; // keystore file
    private static final String CERTIFICATE = "./keyData/Base64.cer";  // certificate file
    private static final String ALIAS = "myKey"; // name (Alias) of signature key

    // Passwords for Keystore and Key
    // ATTENTION: in real application don't write passwords in source code,
    // they must be passed to the application by a secure way
    private static final char[] STOREPASS = { 't','e','s','t','1','2','3', '4'};
    private static final char[] KEYPASS = { 't','e','s','t','1','2','3','4'};


    @Test
    public void testSignature()
    {
        logger.info("Message Signature------------------------------------------------------------");
        logger.info("Reading From: " + FILE);
        logger.info("Signatur writing to: " + SIGFILE);        
        try {
        	// Signing needs private key and certificate		    
		    Key signatureKey = CryptoUtil.getPrivateKey(KEYSTORE, STOREPASS, KEYPASS, ALIAS);
		    Certificate certificate = CryptoUtil.getCertificate(CERTIFICATE);
		    logger.info("Read Key and Certificate successful");
		    byte[] signedData = DigitalSignature.sign(FILE, (PrivateKey) signatureKey);
		    DigitalSignature.saveSignature(SIGFILE, signedData);
		    logger.info("Signatur creation successful");   
	    } catch (Exception e){
    		logger.info("Error at signature creation");
    		logger.info(e.getMessage());
    		fail(); 
    	}
        
        logger.info("Message Verify---------------------------------------------------------------");
        logger.info("Reading From: " + FILE);
        logger.info("Reading Signatur from: " + SIGFILE);
       
        try {
        	// Verification needs certificate with public key
		    Certificate certificate = CryptoUtil.getCertificate(CERTIFICATE);
	        boolean result = DigitalSignature.verify(FILE, SIGFILE, certificate);
	        assertTrue(result);
	        logger.info("Signatur.verify()successful");
	    } catch (Exception e){
    		logger.info("Error at signature verification");
    		logger.info(e.getMessage());
    		fail();
    	}
    }
}
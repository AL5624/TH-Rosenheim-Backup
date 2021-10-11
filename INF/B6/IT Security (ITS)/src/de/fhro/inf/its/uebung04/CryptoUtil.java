package de.fhro.inf.its.uebung04;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;


public class CryptoUtil {
    /**
     * read a certificate from a file
     * @param certificateFile filename of certificate file
     * @throws CertificateException 
     * @throws IOException 
     */
    public static Certificate getCertificate(String certificateFile) throws CertificateException, IOException
    { 
            //read Base64 encoded Certificate from file (format PEM)
            FileInputStream fis = new FileInputStream(certificateFile);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(fis);
            fis.close();
			return cert;
    }
    
    /**
	 * open keystore with a password and read a password protected private key
	 * @throws KeyStoreException 
	 * @throws IOException 
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnrecoverableKeyException
	 * @param alias alias for the private key
	 * @param keypass password for the private key
	 * @param keystore filename of the keystore
	 * @param storepass password for the keystore
	 */
	public static Key getPrivateKey(String keystore, char[] storepass,
			char[] keypass, String alias) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException,
			UnrecoverableKeyException {
	
		//open KeyStore with password and create instance
		KeyStore ks = KeyStore.getInstance("JKS");
		FileInputStream in = new FileInputStream(keystore);
		ks.load(in, storepass);
		in.close();
		//get password protected private key from KeyStore
		Key key = ks.getKey(alias, keypass);
		return key;

	}
}

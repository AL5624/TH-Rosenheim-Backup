package de.fhro.inf.its.uebung2;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.*;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

public class Decryption {

    private static final String cipherAlgorithm = "AES/ECB/PKCS5Padding";
    private static final String algorithm = "AES";

    /**
     * read Base64 encoded file and decode it Base64
     *
     * @param inputFile   filename
     */
    public static byte[] readFromFileBase64(String inputFile)throws Exception
    {
        // TODO
        byte[] data = Files.readAllBytes(Paths.get(inputFile));
        Base64.Decoder decoder = Base64.getDecoder();

        return decoder.decode(data);
    }

    /**
     * read symmetric key from file and decode it Base64
     * @param inputFile  filename
     */
    public static SecretKey readKey(String inputFile) throws Exception
    {
    	// TODO

        return new SecretKeySpec(readFromFileBase64(inputFile), algorithm);
    }

    /**
     *  decrypt data
     *  @pre data are read from file, key is read
     *
     * @return  decrypted data
     */
    public static byte[] decrypt(SecretKey key, byte[] data)throws Exception
    {       
        // TODO

        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    // U3

    public static SecretKey readKey(char[] passwordForKeyCharArray) throws Exception
    {
        KeyStore ks = KeyStore.getInstance("JCEKS");
        String keyFile = "D:\\Dokumente\\TH-Rosenheim-Backup\\INF\\B6\\IT Security (ITS)\\ITS_U3_KEY_FILE.ks";
        InputStream readStream = new FileInputStream(keyFile);
        ks.load(readStream, "password".toCharArray());
        Key key = ks.getKey("keyAlias", passwordForKeyCharArray);
        readStream.close();

        return (SecretKey) key;
    }

    public static String decrypt(byte[] cipherText, SecretKey key, byte[] IV) throws Exception
    {
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(16 * 8, IV);

        // Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Decryption
        byte[] decryptedText = cipher.doFinal(cipherText);

        return new String(decryptedText);
    }
}

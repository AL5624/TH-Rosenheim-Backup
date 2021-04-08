package de.fhro.inf.its.uebung2;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Base64;

import static de.fhro.inf.its.uebung2.Decryption.decrypt;
import static de.fhro.inf.its.uebung2.Decryption.readKey;

public class Encryption {

    private static final String cipherAlgorithm = "AES/ECB/PKCS5Padding";
    private static final String algorithm = "AES";

    public static void main(String[] args) throws Exception
    {
        String plainText = "Hello World!";

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);

        // Generate Key
        SecretKey key = keyGenerator.generateKey();
        byte[] IV = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        System.out.println("Original Text : " + plainText);

        byte[] cipherText = encrypt(plainText.getBytes(), key, IV);
        System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(cipherText));

        String decryptedText = decrypt(cipherText, key, IV);
        System.out.println("DeCrypted Text : " + decryptedText);
    }
    
    /**
     * read data from a file and store it in an internal byte array
     *
     * @param srcFile  Dateiname
     * @throws Exception 
     */
    public static byte[] readFromFile(String srcFile) throws Exception
    {
        return Files.readAllBytes(Paths.get(srcFile));
    }

    /**
     *  Generate a symmetric key according to field algorithm
     *  @pre field algorithm is initialised with cipher algorithm
     */
    public static SecretKey generateKey() throws Exception
    {  
        // TODO

        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        keyGen.init(256, new SecureRandom());
        SecretKey secretKey = keyGen.generateKey();

        return secretKey;
    }

    /**
     *  encrypt data
     *  @pre data is read from file, key is generated
     *  @post result is stored in byte array encodedData
     */
    public static byte[] encrypt(SecretKey key, byte[] data) throws Exception
    {  
        // TODO

        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec IV = new IvParameterSpec(iv); //  initialization vector
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        // cipher.init(Cipher.ENCRYPT_MODE, key, IV);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(data);

        return cipherText;
    }

    /**
     * store encrypted data base64 encoded in a file
     * @param destFile filename
     */
    public static void writeToFile(String destFile, byte[] data) throws Exception
    {
        // TODO

        Files.write(Paths.get(destFile), Base64.getEncoder().encode(data));
    }

    /**
     * store key Base64 encoded in a file
     *
     * @param destFile filename
     */
    public static void saveKey(String destFile, SecretKey key) throws Exception
    {
        // TODO

        Files.write(Paths.get(destFile), Base64.getEncoder().encode(key.getEncoded()));
    }


    // U3

    public static void saveKey(char[] passwordForKeyCharArray) throws Exception
    {
        KeyStore ks = KeyStore.getInstance("JCEKS");
        ks.load(null, "password".toCharArray());
        ks.setKeyEntry("keyAlias", generateKey(), passwordForKeyCharArray, null);
        String keyFile = "D:\\Dokumente\\TH-Rosenheim-Backup\\INF\\B6\\IT Security (ITS)\\ITS_U3_KEY_FILE.ks";
        OutputStream writeStream = new FileOutputStream(keyFile);
        ks.store(writeStream, "password".toCharArray());
        writeStream.close();
    }

    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception
    {
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(16 * 8, IV);

        // Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);

        return cipherText;
    }
}

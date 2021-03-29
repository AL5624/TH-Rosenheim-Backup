package de.fhro.inf.its.uebung2;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.Base64;

public class Encryption {

    private static final String cipherAlgorithm = "AES/ECB/PKCS5Padding";
    private static final String algorithm = "AES";

    public static void main(String[] args) {
        try {
            String src = "D:\\Dokumente\\TH-Rosenheim-Backup\\INF\\B6\\IT Security (ITS)\\ITS_U2_SRC_FILE.txt";
            String des = "D:\\Dokumente\\TH-Rosenheim-Backup\\INF\\B6\\IT Security (ITS)\\ITS_U2_DES_FILE.txt";
            String keyFile = "D:\\Dokumente\\TH-Rosenheim-Backup\\INF\\B6\\IT Security (ITS)\\ITS_U2_KEY_FILE.txt";
            byte[] data = Encryption.readFromFile(src);
            SecretKey key = Encryption.generateKey();
            byte[] encrypt = Encryption.encrypt(key, data);
            Encryption.writeToFile(des, encrypt);
            Encryption.saveKey(keyFile, key);
        } catch (Exception e) {

        }
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
}

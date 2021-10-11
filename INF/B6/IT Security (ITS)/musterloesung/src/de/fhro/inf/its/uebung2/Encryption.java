package de.fhro.inf.its.uebung2;

import javax.crypto.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;


public class Encryption {

    private static final String cipherAlgorithm = "AES/ECB/PKCS5Padding";
    private static final String algorithm = "AES";
    private static final int KEY_SIZE = 256;
    
    /**
     * read data from a file and store it in an internal byte array
     *
     * @param srcFile  Dateiname
     * @throws Exception
     * @return filecontent
     */
    public static byte[] readFromFile(String srcFile) throws Exception
    {
        return Files.readAllBytes(Paths.get(srcFile));
    }

    /**
     *  Generate a symmetric key according to field algorithm
     *  @pre field algorithm is initialised with cipher algorithm
     *  @return symmetric SecretKey
     */
    public static SecretKey generateKey() throws Exception
    {      
        KeyGenerator keygen = KeyGenerator.getInstance(algorithm);  // specify algorithm for key generation
        keygen.init(KEY_SIZE, new SecureRandom());      // set cipher strength
        return keygen.generateKey();    // generate key
    }

    /**
     *  encrypt data
     *  @pre data is read from file, key is generated
     *  @post result is stored in byte array encodedData
     *  @return encrypted data
     */
    public static byte[] encrypt(SecretKey key, byte[] data) throws Exception
    {       
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);  //instantiate a Cipher object with AES
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * store encrypted data base64 encoded in a file
     * @param destFile filename
     */
    public static void writeToFile(String destFile, byte[] data) throws Exception
    {
        Path path = Paths.get(destFile);
        Files.write(path, Base64.getEncoder().encode(data));
    }

    /**
     * store key Base64 encoded in a file
     *
     * @param destFile filename
     */
    public static void saveKey(String destFile, SecretKey key) throws Exception
    {
        Path path = Paths.get(destFile);
        Files.write(path, Base64.getEncoder().encode(key.getEncoded()));
    }
}

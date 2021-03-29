package de.fhro.inf.its.uebung2;

import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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
}

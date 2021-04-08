package de.fhro.inf.its.uebung2;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class Decryption {

    private static final String cipherAlgorithm = "AES/ECB/PKCS5Padding";
    private static final String algorithm = "AES";

    /**
     * read Base64 encoded file and decode it Base64
     *
     * @param inputFile   filename
     * @return decoded filecontent
     */
    public static byte[] readFromFileBase64(String inputFile)throws Exception
    {
        byte [] data = Files.readAllBytes(Paths.get(inputFile));
        return Base64.getDecoder().decode(data);
    }

    /**
     * read symmetric key from file and decode it Base64
     * @param inputFile  filename
     * @return symetric SecretKey
     */
    public static SecretKey readKey(String inputFile) throws Exception
    {
    	byte[] keydata = Files.readAllBytes(Paths.get(inputFile));
        return new SecretKeySpec(Base64.getDecoder().decode(keydata), algorithm);
    }

    /**
     *  decrypt data
     *  @pre data are read from file, key is read
     *  @return  decrypted data
     */
    public static byte[] decrypt(SecretKey key, byte[] data)throws Exception
    {       
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
}

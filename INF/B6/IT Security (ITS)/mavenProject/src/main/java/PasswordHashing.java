/*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
*/

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class PasswordHashing {
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 20000;
    private static final int OUTPUT_SIZE_IN_BITS = 192;
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String HASH_ALGORITHM = "SHA-512";
    private static final String HASH_DELIMITER = ":";

    public static void main(String[] args) throws Exception {
        String password = "Hello World!";

        String hash = hashWithSalt(password);

        String hashPBKDF2 = hashWithPBKDF2(password);

        System.out.println("hash: " + hash);

        System.out.println("hashPBKDF2: " + hashPBKDF2);

        boolean verify = verifyPasswordHash(password, hash);

        boolean verifyPBKDF2 = verifyPasswordPBKDF2(password, hashPBKDF2);

        System.out.println("verify hash: " + verify);

        System.out.println("verify PBKDF2: " + verifyPBKDF2);
    }

    /**
     * generates a random salt
     * @return salt
     */
    public static byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Encodes salt and hash Base64 and concatenates the two String separated by a delimiter
     * @param salt
     * @param hash  hashed password
     * @return  String you need to verify a password
     */
    public static String concatenateSaltAndPassword(byte[] salt, byte[]hash) {
        String saltAsString = Base64.getEncoder().encodeToString(salt);
        String hashAsString = Base64.getEncoder().encodeToString(hash);
        return saltAsString + HASH_DELIMITER + hashAsString;
    }

    /**
     * split stored password in the parts salt and hashed password
     * @param hash
     * @return
     */
    private static List<byte[]> splitHash(String hash) {
        String[] stringParts = hash.split(HASH_DELIMITER);
        List<byte[]> parts = new ArrayList<byte[]>();
        parts.add(Base64.getDecoder().decode(stringParts[0]));
        parts.add(Base64.getDecoder().decode(stringParts[1]));
        return parts;
    }

    /**
     * concatenate password with a salt and then perform hashing
     * @param password  to be hashed
     * @return String containing salt and hashed password, all information need to verify a password
     * @throws Exception
     */
    public static String hashWithSalt(String password) throws Exception {

        // TODO:
        // generate salt, hash password with salt, concatenate salt and hash

        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password, salt);

        return concatenateSaltAndPassword(salt, hash);
    }

    /**
     * hash a password combined with a salt with SHA512
     * @param password  password from user
     * @param salt
     * @return password hash
     * @throws NoSuchAlgorithmException
     */
    public static byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        // TODO: calculate hash value

        MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
        md.update(salt);

        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * verifies if user-password is correct in case of password hashing combined with salt
     * @param password  password input from user
     * @param hashedPassword  password stored in system
     * @return true if password is correct
     * @throws NoSuchAlgorithmException
     */
    public static boolean verifyPasswordHash(String password, String hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {

        // TODO: verify password (recalculate hash and compare with stored hash value

        List<byte[]> list = splitHash(hashedPassword);

        byte[] hashPassword = hashPassword(password, list.get(0));

        return Arrays.toString(hashPassword).equals(Arrays.toString(list.get(1)));
    }

    /**
     * geberate a salt and hash a password with PBKDF2 algorithm
     * @param password password from user
     * @return String containing salt and PBKDF2-hashed password, all information need to verify a password
     * @throws Exception
     */
    public static String hashWithPBKDF2(String password) throws Exception {

        // TODO:
        // generate salt, hash password with PBKDF2 and salt, concatenate salt and hash

        byte[] salt = generateSalt();
        byte[] hash = hashPBKDF2(password, salt);

        return concatenateSaltAndPassword(salt, hash);
    }

    /**
     * hash a password and a salt with PBKDF2
     * @param password password from user
     * @param salt
     * @return  password hash
     * @throws Exception
     */
    public static byte[] hashPBKDF2(String password, byte[] salt) throws Exception {

        // TODO: calculate PBKDF2 hash value

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, OUTPUT_SIZE_IN_BITS);
        SecretKeyFactory f = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);

        return f.generateSecret(spec).getEncoded();
    }

    /**
     * verifies if user-password is correct in case of password hashing performed in PBKDF2
     * @param password  password input from user
     * @param hashedPassword password stored in system
     * @return  true if password is correct
     * @throws Exception
     */
    public static boolean verifyPasswordPBKDF2(String password, String hashedPassword) throws Exception {

        // TODO: verify password (recalculate hash and compare with stored hash value

        List<byte[]> list = splitHash(hashedPassword);

        byte[] hashPassword = hashPBKDF2(password, list.get(0));

        return Arrays.toString(hashPassword).equals(Arrays.toString(list.get(1)));
    }


}
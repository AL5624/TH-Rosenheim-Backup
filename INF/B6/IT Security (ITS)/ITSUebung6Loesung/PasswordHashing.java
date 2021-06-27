import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(password, salt);
        return concatenateSaltAndPassword(salt,hashedPassword);

    }

    /**
     * hash a password combined with a salt with SHA512
     * @param password  password from user
     * @param salt
     * @return password hash
     * @throws NoSuchAlgorithmException
     */
    public static byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
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
    public static boolean verifyPasswordHash(String password, String hashedPassword) throws NoSuchAlgorithmException {

        List<byte[]> parts = splitHash(hashedPassword);
        byte[] salt = parts.get(0);
        byte[] hashValue = hashPassword(password, salt);
        return Arrays.equals(hashValue, parts.get(1));
    }

    /**
     * geberate a salt and hash a password with PBKDF2 algorithm
     * @param password password from user
     * @return String containing salt and PBKDF2-hashed password, all information need to verify a password
     * @throws Exception
     */
    public static String hashWithPBKDF2(String password) throws Exception {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPBKDF2(password, salt);
        return concatenateSaltAndPassword(salt,hashedPassword);
    }

    /**
     * hash a password and a salt with PBKDF2
     * @param password password from user
     * @param salt
     * @return  password hash
     * @throws Exception
     */
    public static byte[] hashPBKDF2(String password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, OUTPUT_SIZE_IN_BITS);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * verifies if user-password is correct in case of password hashing performed in PBKDF2
     * @param password  password input from user
     * @param hashedPassword password stored in system
     * @return  true if password is correct
     * @throws Exception
     */
    public static boolean verifyPasswordPBKDF2(String password, String hashedPassword) throws Exception {
        List<byte[]> parts = splitHash(hashedPassword);
        byte[] salt = parts.get(0);
        byte[] hashValue = hashPBKDF2(password, salt);
        return Arrays.equals(hashValue, parts.get(1));
    }

    /**
     * hash a password with Bcrypt hashing algorithm
     * @param password password from user
     * @return hashed password
     */
    public static String hashBcrypt(String password){
        // possible Parameters: (can be set in  the constructor)
        // strength - the log rounds to use, between 4 and 31
        // random - the secure random instance to use
        PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        return pwdEncoder.encode(password);
    }

    /**
     * verify a password hashed with Bcrypt
     * @param password password input from user
     * @param encodedPassword  password stored in system
     * @return true if password is correct
     */
    public static boolean verifyBcrypt(String password, String encodedPassword){
        PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        return pwdEncoder.matches(password, encodedPassword);
    }

    /**
     * hash a password with Scrypt hashing algorithm
     * @param password  password from user
     * @return hashed password
     */
    public static String hashScrypt(String password){
        // possible Parameters for Scrypt: (can be set in  the constructor)
        // cpuCost - cpu cost of the algorithm (as defined in scrypt this is N). must be power of 2 greater than 1. Default is currently 16,348 or 2^14)
        // memoryCost - memory cost of the algorithm (as defined in scrypt this is r) Default is currently 8.
        // parallelization - the parallelization of the algorithm (as defined in scrypt this is p) Default is currently 1. Note that the implementation does not currently take advantage of parallelization.
        // keyLength - key length for the algorithm (as defined in scrypt this is dkLen). The default is currently 32.
        // saltLength - salt length (as defined in scrypt this is the length of S). The default is currently 64.
        PasswordEncoder pwdEncoder = new SCryptPasswordEncoder();
        return pwdEncoder.encode(password);
    }

    /**
     * verify a password hashed with Bcrypt
     * @param password  password input from user
     * @param encodedPassword  password stored in system
     * @return true if password is correct
     */
    public static boolean verifyScrypt(String password, String encodedPassword){
        PasswordEncoder pwdEncoder = new SCryptPasswordEncoder();
        return pwdEncoder.matches(password, encodedPassword);
    }
}

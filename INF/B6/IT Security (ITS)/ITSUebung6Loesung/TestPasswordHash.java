import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TestPasswordHash {
    @Test
    public void testPasswordHash() throws Exception {
        String hashedPassword = PasswordHashing.hashWithSalt("test1234");
        assertTrue(PasswordHashing.verifyPasswordHash("test1234", hashedPassword));
        System.out.println("Hash verification with salt successful: " + hashedPassword);
    }
    @Test
    public void testPasswordPBKDF2() throws Exception {
        String hashedPassword = PasswordHashing.hashWithPBKDF2("test1234");
        assertTrue(PasswordHashing.verifyPasswordPBKDF2("test1234", hashedPassword));
        System.out.println("Hash verification with PKDF2 successful: " + hashedPassword);
    }
    @Test
    public void testBcrypt(){
        String hashedPassword = PasswordHashing.hashBcrypt(("test1234"));
        assertTrue(PasswordHashing.verifyBcrypt("test1234", hashedPassword));
        System.out.println("Hash verification with Bcrypt successful: " + hashedPassword);
    }
    @Test
    public void testScrypt(){
        String hashedPassword = PasswordHashing.hashScrypt(("test1234"));
        assertTrue(PasswordHashing.verifyScrypt("test1234", hashedPassword));
        System.out.println("Hash verification with Scrypt successful: " + hashedPassword);
    }
}

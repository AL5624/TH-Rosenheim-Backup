import com.google.crypto.tink.*;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AesGcmKeyManager;
import com.google.crypto.tink.mac.HmacKeyManager;
import com.google.crypto.tink.mac.MacConfig;
import com.google.crypto.tink.signature.EcdsaSignKeyManager;
import com.google.crypto.tink.signature.SignatureConfig;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Encrypt {

    public static void main(String[] args) throws Exception {
        Encrypt.encryptMac();
    }

    public static void encryptAead() throws Exception {
        String srcFile = "src/toEncrypt.txt";
        String associatedData = "AntonBertram";
        byte[] plainText = Files.readAllBytes(Paths.get(srcFile));

        AeadConfig.register();

        KeysetHandle keysetHandle = KeysetHandle.generateNew(AesGcmKeyManager.aes256GcmTemplate());
        Aead aead = keysetHandle.getPrimitive(Aead.class);
        byte[] ciphertext = aead.encrypt(plainText, associatedData.getBytes(StandardCharsets.UTF_8));

        String decrypt = new String(aead.decrypt(ciphertext, associatedData.getBytes(StandardCharsets.UTF_8)));

        System.out.println(decrypt);
    }

    public static void encryptMac() throws Exception {
        String srcFile = "src/toEncrypt.txt";
        String associatedData = "AntonBertram";
        byte[] plainText = Files.readAllBytes(Paths.get(srcFile));

        MacConfig.register();
        KeysetHandle keysetHandle = KeysetHandle.generateNew(HmacKeyManager.hmacSha256Template());
        Mac mac = keysetHandle.getPrimitive(Mac.class);
        byte[] tag = mac.computeMac(plainText);

        System.out.println(new String(tag, StandardCharsets.UTF_8));

        mac.verifyMac(tag, plainText);
    }

    /* signature with elliptic curve */
    public static void signEcdsa() throws Exception {
        String srcFile = "src/toEncrypt.txt";
        String associatedData = "AntonBertram";
        byte[] plainText = Files.readAllBytes(Paths.get(srcFile));

        SignatureConfig.register();

        KeysetHandle keysetHandle = KeysetHandle.generateNew(EcdsaSignKeyManager.ecdsaP256Template());

        PublicKeySign signer = keysetHandle.getPrimitive(PublicKeySign.class);

        byte[] signature = signer.sign(plainText);

        PublicKeyVerify verify = keysetHandle.getPrimitive(PublicKeyVerify.class);

        verify.verify(signature, plainText);
    }
}

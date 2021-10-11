package de.fhro.inf.its.uebung04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.*;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class DigitalSignature {

    public static byte[] sign(String filename, PrivateKey signatureKey) throws Exception {
        byte[] data = filename.getBytes("UTF8");

        Signature sig = Signature.getInstance("SHA512withRSA");
        sig.initSign(signatureKey);
        sig.update(data);

        return sig.sign();
    }

    public static void saveSignature(String sigFile, byte[] signedData) throws Exception {
        FileOutputStream keyfiles = new FileOutputStream(sigFile);
        keyfiles.write(signedData);
        keyfiles.close();
    }

    public static boolean verify(String filename, String sigFilename, Certificate cert) throws Exception {
        byte[] data = filename.getBytes("UTF8");
        Signature sig = Signature.getInstance("SHA512withRSA");
        sig.initVerify(cert);
        sig.update(data);

        return sig.verify(Files.readAllBytes(Paths.get(sigFilename)));
    }
}

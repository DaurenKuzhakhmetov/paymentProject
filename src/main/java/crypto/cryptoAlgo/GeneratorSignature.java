package crypto.cryptoAlgo;

/*
  see : https://stackoverflow.com/questions/6840206/sha2-password-hashing-in-java
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneratorSignature {
     public String generateSign(String signature){
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
           // String salt = "secret_word";
            String passWithSalt = signature;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< passHash.length ;i++) {
                sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString();
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }

}

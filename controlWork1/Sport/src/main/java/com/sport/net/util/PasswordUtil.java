package com.sport.net.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class PasswordUtil {


    private static final SecureRandom RAND = new SecureRandom();

    public static Optional generateSalt (final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static Optional hashPassword (String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }


    public static boolean verifyPassword (String password, String key, String salt) {
        Optional optEncrypted = hashPassword(password, salt);
        if (!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);
    }


    public static void main(String[] args) {
        String salt = String.valueOf(PasswordUtil.generateSalt(512).get());
        // salt ==> "DARMFcJcJDeNMmNMLkZN4rSnHV2OQPDd27yi5fYQ77r2vKTa ... Wt9QZog0wtkx8DQYEAOOwQVs="

        String password = "Of Salesmen!";
        // password ==> "Of Salesmen!"

        String key = String.valueOf(PasswordUtil.hashPassword(password, salt).get());
        // key ==> "djaaKTM/+X14XZ6rxjN68l3Zx4+5WGkJo3nAs7KzjISiT6aa ... sN5DcmOeMfhqMGCNxq6TIhg=="

        PasswordUtil.verifyPassword("Of Salesmen!", key, salt);
        // $5 ==> true

        PasswordUtil.verifyPassword("By-Tor! And the Snow Dog!", key, salt);
        // $6 ==> false

    }



}

package fau.HashingStrategies;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingStrategyMD5 implements HashingStrategy {
    @Override
    public String hash(String string) {

        MessageDigest md5Digest;
        String md5Hash;

        try {

            // Create an instance of MessageDigest with the MD5 algorithm
            md5Digest = MessageDigest.getInstance("MD5");

            // Convert the input string to bytes
            byte[] inputBytes = string.getBytes(StandardCharsets.UTF_8);

            // Compute the MD5 hash
            byte[] hashBytes = md5Digest.digest(inputBytes);

            // Convert the hash bytes to a hexadecimal representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            md5Hash = hexString.toString();

            return md5Hash;


        } catch (NoSuchAlgorithmException e) {

        }

        throw new RuntimeException("Something went wrong");

    }
}


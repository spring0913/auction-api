package com.auction.api.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

    private final MessageDigest messageDigest;

    public PasswordEncoder(String algorithm) throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance(algorithm);
    }

    public String encode(String credential){
        messageDigest.reset();
        messageDigest.update(credential.getBytes());
        return String.format("%064x", new BigInteger(1, messageDigest.digest()));
    }
}

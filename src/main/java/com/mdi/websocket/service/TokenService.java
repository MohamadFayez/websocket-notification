/*
 * Copyright (c) 2023.
 */

package com.mdi.websocket.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    @Value("${encryption.aesAlgorithm}")
    private String securityAlgorithmAES;

    public String generateEncryptedToken(String customerId, String clientId, String clientPKey, String commonSecret, long expiresAfterMillis) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKey secretKey = convertStringToSecretKey(commonSecret);
        long now = System.currentTimeMillis();
        Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(clientPKey), SignatureAlgorithm.HS256.getJcaName());
        Map headers = new HashMap<>();
        headers.put("alg", SignatureAlgorithm.HS256.getValue());
        JwtBuilder builder = Jwts.builder().setHeader(headers).setIssuedAt(new Date(now)).setSubject(String.valueOf(customerId)).setExpiration(new Date(now + expiresAfterMillis)).claim("client_id", clientId).claim("scope", Arrays.asList("openid", "protected"));//.signWith(signingKey);
        return symmetricEncrypt(builder.compact(), secretKey);

    }

    private SecretKey convertStringToSecretKey(String serverSecretKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] decodedKey = Base64.getDecoder().decode(serverSecretKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, securityAlgorithmAES);
    }

    private String symmetricEncrypt(String text, SecretKey secretKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        byte[] encryptedByte;
        byte[] encryptText = text.getBytes();
        Cipher cipher;
        cipher = Cipher.getInstance(securityAlgorithmAES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        encryptedByte = cipher.doFinal(encryptText);

        return convertByteArrayToString(encryptedByte);
    }

    public String convertByteArrayToString(byte[] bytes) {
        return clearLineBreaker(Base64.getEncoder().encodeToString(bytes));
    }

    public String clearLineBreaker(String s) {
        s = s.replace("\n", "");
        s = s.replace("\r", "");
        return s;
    }
}

package com.fancy.showmedata.utils;


import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class CryptAES {

    private static final String AES_TYPE = "AES/ECB/PKCS5Padding";

    public static String encrypt(String keyStr, String plainText) {
        byte[] encrypt = null;
        try {
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypt = cipher.doFinal(plainText.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (encrypt == null)
            encrypt = new byte[0];
        return new String(Base64.encode(encrypt, Base64.DEFAULT));
    }

    public static String decrypt(String keyStr, String encryptData) {
        byte[] decrypt = null;
        try {
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypt = cipher.doFinal(Base64.decode(encryptData, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (decrypt == null)
            decrypt = new byte[0];
        return new String(decrypt).trim();
    }

    private static Key generateKey(String key) throws Exception {
        try {
            return new SecretKeySpec(key.getBytes(), "AES");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public static void main(String[] args) {

        String keyStr = "UITN25LMUQC436IM";

        String plainText = "this is a string will be AES_Encrypt";

        String encText = encrypt(keyStr, plainText);
        String decString = decrypt(keyStr, encText);

        System.out.println(encText);
        System.out.println(decString);

    }
}
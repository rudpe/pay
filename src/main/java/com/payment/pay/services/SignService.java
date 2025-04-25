package com.payment.pay.services;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SignService {

    public String generateRequestSign(String mid, String amount, String currencyAlphaCode, String msTxnId, String firstName, String familyName, String timestamp, String key) {
        String message = getRequestMessage(mid, amount, currencyAlphaCode, msTxnId, firstName, familyName, timestamp);
        String iv = getIv(mid);
        return generateSign(message, key, iv);
    }

    public String generateResponseSign(String msTxnId, String amount, String currencyAlphaCode, String result, String key, String mid) {
        String message = getResponseMessage(msTxnId, amount, currencyAlphaCode, result);
        return generateSign(message, key, getIv(mid));
    }

    private String getRequestMessage(String mid, String amount, String currencyAlphaCode, String msTxnId, String firstName, String familyName, String timestamp) {
        return mid + amount + currencyAlphaCode + msTxnId + firstName + familyName + timestamp;
    }

    private String getResponseMessage(String msTxnId, String amount, String currencyAlphaCode, String result) {
        return msTxnId + amount + currencyAlphaCode + result;
    }

    private String getIv(String mid) {
        return mid + new StringBuilder(mid).reverse();
    }

    private String generateSign(String message, String key, String iv) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            byte[] keyBytes = Hex.decodeHex(key.toCharArray());
            byte[] ivBytes = iv.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
            byte[] sha1Hash = DigestUtils.sha1(message);
            byte[] encryptedData = encryptCipher.doFinal(sha1Hash);
            return Hex.encodeHexString(encryptedData).substring(0,32);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.INFO, e.getMessage());
            return null;
        }
    }
}

package com.oss.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

public class EncryptionUtil {
    public EncryptionUtil() {
    }

    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    public static String getSha1(String str) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            byte[] var7 = md;
            int var8 = md.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                byte byte0 = var7[var9];
                buf[k++] = hexDigits[byte0 >>> 4 & 15];
                buf[k++] = hexDigits[byte0 & 15];
            }

            return new String(buf);
        } catch (Exception var11) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getSha1(getSha1("Lxkj@123456")));
    }
}

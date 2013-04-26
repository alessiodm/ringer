package org.alessiodm.ringer.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * Very simple encryption salt + MD5
     * 
     * @param toenc String to encode
     * @param salt  Salt for encoding
     * @return      Encoded String
     */
    public static String encrypt(String toenc, String salt){
        if (salt != null){
            toenc += salt + "!@!" + salt;
        }
        
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(toenc.getBytes());
            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
    public static void main(String[] args){
        System.out.println(MD5Util.encrypt("user3", "user3"));
    }
}

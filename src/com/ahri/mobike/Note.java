package com.ahri.mobike;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zouyingjie on 2017/2/23.
 * Java 加密笔记
 */
public class Note {
    private static String src = "imooc security base64";


    public static void main(String[] args) {
        md();
        sha();

    }

    /**
     * Base64加密
     */
    public static void jdkBase64() {
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(src.getBytes());
            System.out.println(encode);

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(encode);
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void commonsBase64() {
        byte[] bytes = Base64.encodeBase64(src.getBytes());
        System.out.println(new String(bytes));

        byte[] bytes1 = Base64.decodeBase64(bytes);
        System.out.println(new String(bytes1));
    }


    /**
     * 消息摘要算法
     *
     * MD 消息摘要
     * SHA 安全哈希算法
     * MAC 信息验证码
     */

    /**
     * MD2  JDK
     * MD4  Bouncy Castle
     * MD5  JDK
     * 128位摘要信息
     */
    public static void md() {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(src.getBytes());
            System.out.println("JDK MD5:" + Hex.encodeHexString(digest));

            String ccMd5 = DigestUtils.md5Hex(src);
            System.out.println("CC  MD5:" + ccMd5);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全散列算法
     * <p>
     * SHA-1   JDK
     * SHA-224 BC
     * SHA-256 JDK
     * SHA-384 JDK
     * SHA-512 JDK
     */
    public static void sha() {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(src.getBytes());
            String hexString = Hex.encodeHexString(md.digest());
            System.out.println("JDK SHA:" + hexString);


            String ccSha1 = DigestUtils.sha1Hex(src);
            System.out.println("CC  SHA:" + ccSha1);

            System.out.println("CC SHA-256:" + DigestUtils.sha256Hex(src.getBytes()));

            MessageDigest md256 = MessageDigest.getInstance("sha-256");
            md256.update(src.getBytes());
            System.out.println("JDK SHA-256" + Hex.encodeHexString(md256.digest()));

            MessageDigest sha256Digest = DigestUtils.getSha256Digest();
            sha256Digest.update(src.getBytes());
            System.out.println("CC SHA-256:" + Hex.encodeHexString(sha256Digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息认证码
     * 兼容md和sha, 在兼容的基础上添加了密钥
     * HmacMD2/4/5
     * HmacSHA1/256/224/384/512
     */
//    private static void mac(){
//
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
//            SecretKey secretKey = keyGenerator.generateKey();
//            byte[] key = secretKey.getEncoded();
//
//            SecretKey restorekEY =
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
}

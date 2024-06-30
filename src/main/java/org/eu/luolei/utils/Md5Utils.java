package org.eu.luolei.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Md5Utils {

    protected static MessageDigest md = null;

    static {
        // 创建一个MessageDigest实例
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(Md5Utils.class.getName() + "初始化失败");
            e.printStackTrace();
        }
    }

    public static String encode(String s) {
        md.update(s.getBytes(StandardCharsets.UTF_8));

        byte[] result = md.digest(); // 16 bytes
        return HexFormat.of().formatHex(result);
    }
}

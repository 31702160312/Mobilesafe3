package cn.mmvtc.mobilesafe3.chapter02.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2019/3/27.
 */

public class MD5Utils {
    /**
     * md5加密算法
     */
    public static String encode(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            //StringBuilder是可变字符序列
            StringBuilder sb = new StringBuilder();
            for (byte b : result)//byte 8位 int 32位
            {
                int number = b&0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1) {
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
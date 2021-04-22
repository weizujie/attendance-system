package com.weizujie.attendance.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import sun.security.provider.MD5;

import java.util.Random;

/**
 * 生成随机 salt 工具类
 * @author weizujie
 */
public class SaltUtil {

    public static String getSalt(int n) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char a = chars[new Random().nextInt(chars.length)];
            stringBuffer.append(a);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456", "qnhPC6nh", 1024);
        System.out.println(md5Hash.toString());
    }
}
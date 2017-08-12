package com.yznu.jxda.utils;

/**
 * Created by 刘剑银 on 2017/8/9.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Collection;

/**
 * 工具类
 */

public class Utils {
    private static final SecureRandom random = new SecureRandom();

    /**
     * 获取字符串的md5值
     *
     * @param str
     * @return
     */
    public static String getMD5String(String str) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            StringBuffer buf = new StringBuffer();
            for (byte b : md.digest()) {
                buf.append(String.format("%02X", b & 0xff));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();

            return "";
        }

    }

    /*public static void main(String[] args) {
        System.out.println(getMD5String("admin"));
        21232F297A57A5A743894A0E4A801FC3
    }*/

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || str.trim().length() == 0;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.size() == 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        return isNull(array) || array.length == 0;
    }

    /**
     * 生成随机 token
     *
     * @return
     */
    public static String getNextToken() {
        return new BigInteger(130, random).toString(32);
    }

}

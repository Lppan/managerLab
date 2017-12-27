package com.laboratory.utils;/**
 * Created by Administrator on 2017/12/22.
 */

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 生成MD5加密
 * MD5 encode
 * Created by Lpan on 2017/12/22.
 */
public class MD5Utils {
    public  static  String  encode(String  source){
        String hexString = "";
        if(source != null && !"".equals(source)){
            try {
                MessageDigest  messageDigest = MessageDigest.getInstance("MD5");
                BASE64Encoder base64en = new BASE64Encoder();
                hexString = base64en.encode(messageDigest.digest(source.getBytes("UTF-8")));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return hexString;
    }

    public static void main(String args[]){
        String encode = encode("123");
        System.out.println(encode);
    }
}

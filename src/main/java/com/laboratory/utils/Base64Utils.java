package com.laboratory.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * base 64 encode and decode
 * Created by shipan on 2017/12/26.
 */
public class Base64Utils {
    private static final String UTF_8 = "UTF-8";

    /**
     * 对给定的字符串用base64解密
     * @param str
     * @return
     */
    public static String decodeStr(String str){
        String returnStr = null;
        if(null != str && !"".equals(str)){
            try {
                returnStr = new String(Base64.decodeBase64(str.getBytes(UTF_8)), UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return returnStr;
    }

    /**
     * 对给定字符串进行base64加密
     * @param str
     * @return
     */
    public static String encodeStr(String str){
        String returnStr  = null;
        if(null != str && !"".equals(str)){
            try {
                returnStr = new String(Base64.encodeBase64(str.getBytes(UTF_8)), UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return returnStr;
    }
    public static String jdkBase64Encoder(String str)
    {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(str.getBytes());
        return encode;
    }
    /**
     * 使用jdk的base64 解密字符串
     * 返回为null表示解密失败
     * */
    public static String jdkBase64Decoder(String str)
    {
        BASE64Decoder decoder = new BASE64Decoder();
        String decode=null;
        try {
            decode = new String( decoder.decodeBuffer(str));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return decode;
    }



    public static void main(String args[]){
        String shipan = encodeStr("123");
        String shipans = decodeStr("MTIz");
        String encoder = jdkBase64Encoder("123");
        String decoder = jdkBase64Decoder("MTIz");
        System.out.println(shipan);
        System.out.println(shipans);
        System.out.println(encoder);
        System.out.println(decoder);
    }
}

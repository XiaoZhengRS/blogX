package com.xiaozhengkeji.blog.tools;

import java.io.IOException;

public class Base64Tool {
    /**
     * Baser64加密
     *
     * @param content 需要加密的字符串
     */
    public static String Baser64加密(String content) {
        return new sun.misc.BASE64Encoder().encode(content.getBytes());
    }

    /**
     * Baser64解密
     *
     * @param source 需要解密字符串
     */
    public static String Baser64解密(String source) {
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            return new String(decoder.decodeBuffer(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

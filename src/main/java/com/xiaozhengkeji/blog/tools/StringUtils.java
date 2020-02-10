package com.xiaozhengkeji.blog.tools;

import java.io.UnsupportedEncodingException;

public class StringUtils {
    public String 编码转换(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }
}

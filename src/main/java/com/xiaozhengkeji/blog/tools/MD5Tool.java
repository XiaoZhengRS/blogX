package com.xiaozhengkeji.blog.tools;

import org.springframework.util.DigestUtils;

public class MD5Tool {
    public static String MD5加密(String set) {
        return DigestUtils.md5DigestAsHex(set.getBytes());
    }
}

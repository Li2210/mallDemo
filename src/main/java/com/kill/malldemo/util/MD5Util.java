package com.kill.malldemo.util;

import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @Description TODO
 * @Author lishen
 * @Date 26/7/21 9:17 pm
 **/
public class MD5Util {

    public static final String salt = "这是一个盐";

    // 加密
    public static String encode(String str) {
        if(StringUtils.isEmpty(str)) {
            throw new BusinessException(ErrorCode.GET_USER_NOT_FOUND);
        }

        return DigestUtils.md5DigestAsHex((str + salt).getBytes());
    }

    public static void main(String[] args) {
        String password = "123456";
        String result = encode(password);
        System.out.println(result);
    }

}

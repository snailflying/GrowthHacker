package cn.mw.growthhacker.utils;

import java.io.IOException;
import java.util.ArrayList;
import com.alibaba.fastjson.JSON;

public class RestUtil {
    public RestUtil() {
    }

    public static String getValidCharset(String charset) {
        return charset != null && charset.length() > 0?charset:"UTF-8";
    }

    public static <T> T parseAs(Class<T> cls, String body) throws IOException {
        return JSON.parseObject(body, cls);
    }

    public static <T> ArrayList<T> parseArray(Class<T> cls, String body) throws IOException {
        return (ArrayList)JSON.parseArray(body, cls);
    }
}
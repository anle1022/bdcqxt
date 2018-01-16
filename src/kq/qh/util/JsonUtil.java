package kq.qh.util;

import com.google.gson.Gson;
import com.google.gson.JsonNull;

public class JsonUtil{
    
    private static Gson gson=new Gson();

    /**
     * json对象转字符串
     * @param src
     * @return
     */
    public static String toJson(Object src) {
        if (src == null) {
            return gson.toJson(JsonNull.INSTANCE);
        }
        return gson.toJson(src);
    }
    
    /**
     * json字符串转json对象
     * @param json
     * @param clazz
     * @return
     */
    public static Object toObj(String json,Class<?> clazz){
    	return gson.fromJson(json, clazz);
    }
}
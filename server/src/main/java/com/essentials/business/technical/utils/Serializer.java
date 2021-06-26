package com.essentials.business.technical.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Serializer {
    //Json to specified object. (Json string -> Object)
    public static <T> T deserialize(String value, Class<T> returnType) {
        return (new Gson()).fromJson(value, returnType);
    }

    //Specified object to Json. (Object -> Json string)
    public static String serialize(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    // HttpExchange -> Request Body (InputStream) -> Json Request Body (String) -> Object (T)
    public static <T> T deserializeExchangeBody(HttpExchange exchange, Class<T> returnType) throws IOException {
        return deserialize(readString(exchange.getRequestBody()), returnType);
    }

    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}

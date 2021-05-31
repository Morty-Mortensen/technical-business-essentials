package com.essentials.business.technical.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
}

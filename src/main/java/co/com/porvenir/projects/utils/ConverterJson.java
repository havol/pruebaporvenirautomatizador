package co.com.porvenir.projects.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class ConverterJson {

    private static Gson gson;

    public static <T> T fromJson(String json, Class<T> classOfT){
        gson = getGson();
        return gson.fromJson(json, classOfT);
    }

    private static Gson getGson() {
        if (gson == null){
            gson = new GsonBuilder().create();
        }
        return gson;
    }

    public static <T> String getJson(T objeto) {
        final Gson gson = new Gson();
        return gson.toJson(objeto);
    }

    public static Map convertJsonMap(String objeto) {
        final Gson gson = new Gson();
        return gson.fromJson(objeto, Map.class);
    }

}
package com.oxiwyle.map.json;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/**
 * Класс для получения информации о стране.
 */
public class CountryJson {
    /** Словарь кодов и названий стран. */
    private HashMap<String, String> names = new HashMap<>();
    /** Контекст приложения. */
    private Context context;

    /**
     * Конструктор класса.
     * @param context контекст приложения
     */
    public CountryJson(Context context) {
        this.context = context;
        setNamesFromJson();
    }

    /**
     * Метод получения названия страны по её коду.
     * @param code код страны
     */
    public String getNameByCode(String code) {
        return names.containsKey(code) ? names.get(code) : "";
    }

    /**
     * Метод получения всех названий стран из JSON.
     */
    private void setNamesFromJson() {
        new Thread(new Runnable() {
            public void run() {
                String json = loadJsonFromAsset();
                String language = Locale.getDefault().getLanguage().equals("ru") ? "ru" : "en";
                if (json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        Iterator<String> iterator = jsonObject.keys();
                        while (iterator.hasNext()) {
                            String code = iterator.next();
                            String name = jsonObject.getJSONObject(code).getString(language);
                            names.put(code, name);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * Метод загрузки JSON из ресурсов приложения.
     * @return JSON-строка
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String loadJsonFromAsset() {
        String json;
        try {
            InputStream is = context.getAssets().open("countries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}

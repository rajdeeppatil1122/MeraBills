package com.merabills.android.utils;

import android.util.Log;
import com.merabills.android.model.Payment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    private static final Gson gson = new Gson();

    public static String toJson(List<Payment> payments) {
        Log.d("gson.toJson(payments)", gson.toJson(payments));
        return gson.toJson(payments);
    }

    public static List<Payment> fromJson(String json) {
        Type type = new TypeToken<List<Payment>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
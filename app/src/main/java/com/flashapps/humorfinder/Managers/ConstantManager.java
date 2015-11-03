package com.flashapps.humorfinder.Managers;

import com.flashapps.humorfinder.Models.Joke;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by dietervaesen on 3/11/15.
 */
public class ConstantManager {
    public static final String ENDPOINT_URL="http://api.icndb.com";
    public static final Type JOKE_TYPE = new TypeToken<Joke>() {
    }.getType();

    public static Gson buildGson() {//gson that exludes tables from realm is only for json to object otherwise add serializer

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .setDateFormat(DateFormat.FULL,DateFormat.FULL)
                .registerTypeAdapter(Date.class,ser)
                .registerTypeAdapter(Date.class,deser)//voor unix time conversion
                .create();
        return gson;

    }
    static JsonSerializer<Date> ser = new JsonSerializer<Date>() {
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext
                context) {
            return src == null ? null : new JsonPrimitive(src.getTime());
        }
    };

    static JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : new Date(json.getAsLong()*1000);
        }
    };
}

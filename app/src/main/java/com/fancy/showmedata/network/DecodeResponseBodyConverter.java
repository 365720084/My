package com.fancy.showmedata.network;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.fancy.showmedata.utils.log.LogUtils;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by bruce on 2017/3/22.
 */

public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    DecodeResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.adapter = adapter;
        this.gson = gson;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        String s = responseBody.string();
        Type type = new TypeToken<BaseResponse>() {
        }.getType();
        LogUtils.d("Retrofit2", "返回信息：" + s);
        JsonReader jsonReader = gson.newJsonReader(new StringReader(s));
        try {
            return adapter.read(jsonReader);
        } finally {
            responseBody.close();
        }
    }

}

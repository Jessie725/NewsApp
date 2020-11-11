package com.laioffer.tinnews.network;

import android.content.Context;

import com.ashokvarma.gander.GanderInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// providing a configured Retrofit instance
// that can then instantiate a NewsApi implementation
public class RetrofitClient {

    private static final String API_KEY = "65e87e5eca174d1f80516bd997de1402";
    private static final String BASE_URL = "https://newsapi.org/v2/";

    // http request, need api key
    // network layer，network setup
    public static Retrofit newInstance(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor()) // 发之前拦截，加key
                .build();
        return new Retrofit.Builder() // request build
                .baseUrl(BASE_URL) // 发送 request 的url
                .addConverterFactory(GsonConverterFactory.create()) //response 转化json
                .client(okHttpClient)
                .build();
    }

    // 可以改写header，+key
    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .header("X-Api-Key", API_KEY)
                    .build();
            return chain.proceed(request);
        }
    }
}

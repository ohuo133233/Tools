package com.wang.javatools.net.request;

import android.util.Log;

import androidx.annotation.NonNull;

import com.wang.javatools.base.BaseConstant;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpManager {
    private static final String TAG = "OkHttpManager";

    private OkHttpManager() {
        BaseNetManager.getInstance().initNet();
    }

    public static OkHttpManager getInstance() {
        return OkHttpManagerHolder.instance;
    }

    private static class OkHttpManagerHolder {
        private static final OkHttpManager instance = new OkHttpManager();
    }

    public void test() {
        OkHttpClient okHttpClient = BaseNetManager.getInstance().getOkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(BaseConstant.BASE_URL)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure : " + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Log.e(TAG, "code" + response.code());
            }
        });
    }

    public void get(String url) {
        OkHttpClient okHttpClient = BaseNetManager.getInstance().getOkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure : " + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.e(TAG, "code" + response.code());
            }
        });
    }

    public void get(String url, INetCallback iNetCallback) {
        OkHttpClient okHttpClient = BaseNetManager.getInstance().getOkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure : " + e.toString());
                iNetCallback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Log.e(TAG, "code" + response.code());
                iNetCallback.onResponse(call, response);
            }
        });
    }

    public void post(String url) {
        OkHttpClient okHttpClient = BaseNetManager.getInstance().getOkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure : " + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Log.e(TAG, "code" + response.code());
            }
        });
    }

    public void post(RequestBody requestBody, INetCallback iNetCallback) {
        OkHttpClient okHttpClient = BaseNetManager.getInstance().getOkHttpClient();
        Request request = new Request.Builder()
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure : " + e.toString());
                iNetCallback.onFailure(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Log.e(TAG, "code" + response.code());
                iNetCallback.onResponse(call, response);
            }
        });
    }

}

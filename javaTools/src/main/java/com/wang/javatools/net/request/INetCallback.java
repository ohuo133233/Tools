package com.wang.javatools.net.request;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface INetCallback {
    void onFailure(@NonNull Call call, @NonNull IOException e);

    void onResponse(@NonNull Call call, @NonNull Response response);
}

package com.wang.javatools.widget.toast;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.wang.javatools.R;


public class CustomToast {

    public void showShortToast(@NonNull Context context, @LayoutRes int layout, @NonNull String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        View toastRoot = LayoutInflater.from(context).inflate(layout, null);
        TextView textView = toastRoot.findViewById(R.id.text);
        textView.setText(message);
        WindowManager.LayoutParams parameter = new WindowManager.LayoutParams();
        parameter.type = WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG;
        toastRoot.setLayoutParams(parameter);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP, 0, 48);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastRoot);
        toast.show();
    }

    public void showLongToast(@NonNull Context context, @LayoutRes int layout, @NonNull String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        View toastRoot = LayoutInflater.from(context).inflate(layout, null);
        TextView textView = toastRoot.findViewById(R.id.text);
        textView.setText(message);
        WindowManager.LayoutParams parameter = new WindowManager.LayoutParams();
        parameter.type = WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG;
        toastRoot.setLayoutParams(parameter);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP, 0, 48);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastRoot);
        toast.show();
    }
}


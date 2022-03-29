package com.wang.javatools.manager.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class PermissionManager {
    public static final String TAG = PermissionManager.class.getSimpleName();
    public static final int REQUEST_CODE = 0x01;
    private Context mApplicationContext;
    private PermissionsFragment mPermissionsFragment;


    public PermissionManager(FragmentActivity activity) {
        mApplicationContext = activity.getApplicationContext();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mPermissionsFragment = (PermissionsFragment) fragmentManager.findFragmentByTag(TAG);

        if (mPermissionsFragment == null) {
            mPermissionsFragment = new PermissionsFragment();
            fragmentTransaction.add(mPermissionsFragment, TAG)
                    .commitNow();
        }
    }


    public void requestPermissions(ArrayList<String> permissions, IPermissionCallBack iPermissionCallBack) {
        Log.d(TAG, "requestPermissions");
        // 遍历权限，判断没有的去获取
        for (int i = 0; i < permissions.size(); i++) {
            if (!isPermissionGranted(mApplicationContext, permissions.get(i))) {
                Log.d(TAG, "没有权限，去请求");
                String[] permission = new String[]{permissions.get(i)};
                mPermissionsFragment.requestPermissions(permission, REQUEST_CODE);
                mPermissionsFragment.setIPermissionCallBack(iPermissionCallBack);
            }
        }
    }

    public void requestPermission(String permissions, IPermissionCallBack iPermissionCallBack) {
        Log.d(TAG, "requestPermission");
        // 遍历权限，判断没有的去获取
        if (!isPermissionGranted(mApplicationContext, permissions)) {
            Log.d(TAG, "没有权限，去请求");
            String[] permission = new String[]{permissions};
            mPermissionsFragment.requestPermissions(permission, REQUEST_CODE);
            mPermissionsFragment.setIPermissionCallBack(iPermissionCallBack);
        }

    }

    // 判断权限是否批准
    public boolean isPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context.getApplicationContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

}

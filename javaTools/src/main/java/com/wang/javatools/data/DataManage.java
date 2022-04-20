package com.wang.javatools.data;

import com.tencent.mmkv.MMKV;

public class DataManage {

    private MMKV mmkv;

    private DataManage() {
        mmkv = MMKV.defaultMMKV();
    }

    private static class DataManageHolder {
        private static final DataManage Instance = new DataManage();
    }

    public static DataManage getInstance() {
        return DataManageHolder.Instance;
    }


    public void put(String key, int value) {
        mmkv.encode(key, value);
    }

    public int get(String key) {
        return mmkv.decodeInt(key);
    }


}

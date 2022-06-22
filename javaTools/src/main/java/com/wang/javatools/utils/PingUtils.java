package com.wang.javatools.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PingUtils {

    private ExecutorService executorService;

    /**
     * 对保存实例的变量添加volatile的修饰
     */
    private volatile static PingUtils instance = null;

    private PingUtils() {

    }

    public static PingUtils getInstance() {
        //先检查实例是否存在，如果不存在才进入下面的同步块
        if (instance == null) {
            //同步块，线程安全的创建实例
            synchronized (PingUtils.class) {
                //再次检查实例是否存在，如果不存在才真的创建实例
                if (instance == null) {
                    instance = new PingUtils();
                }
            }
        }
        return instance;
    }

    public void executePing(String ip, OnNetWorkListener listener) {
        executePingCmd(spellPing(ip), listener);
    }

    private String spellPing(String ip) {
        String countCmd = " -c 4 ";   //一共ping几次
        String sizeCmd = " -s 64 ";  //数据包大小
        String timeCmd = " -i 1 ";   //1秒ping一次
        return "ping" + countCmd + timeCmd + sizeCmd + ip;
    }

    private void executePingCmd(String pingCmd, OnNetWorkListener listener) {
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Thread(new PingTask(pingCmd, 1, listener)));
    }

    // 创建ping任务
    private class PingTask implements Runnable {

        private String ping;
        private long delay;
        private OnNetWorkListener mOnNetWorkListener;

        public PingTask(String ping, long delay, OnNetWorkListener listener) {
            this.ping = ping;
            this.delay = delay;
            this.mOnNetWorkListener = listener;
        }

        @Override
        public void run() {
            Process process = null;
            BufferedReader successReader = null;
            BufferedReader errorReader = null;
            try {
                // 执行ping命令
                process = Runtime.getRuntime().exec(ping);
                // success
                successReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                // error
                errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                String lineStr;
                while ((lineStr = successReader.readLine()) != null) {
                    // receive
                    Log.d("NET_LOG", "successReader === " + lineStr + "\r\n");
                    mOnNetWorkListener.onSuccess("successReader === " + lineStr + "\r\n");
                }
                while ((lineStr = errorReader.readLine()) != null) {
                    // receive
                    Log.d("NET_LOG", "errorReader === " + lineStr + "\r\n");
                    mOnNetWorkListener.onFailed("errorReader === " + lineStr + "\r\n");
                }
                Thread.sleep(delay * 1000);
            } catch (Exception e) {
                Log.d("NET_LOG", "Exception === " + e.getMessage());
            } finally {
                try {
                    if (successReader != null) {
                        successReader.close();
                    }
                    if (errorReader != null) {
                        errorReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (process != null) {
                    process.destroy();
                }
            }
        }
    }

    public interface OnNetWorkListener {
        void onSuccess(String msg);

        void onFailed(String msg);
    }

}

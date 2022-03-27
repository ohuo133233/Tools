package com.wang.javatools.net;

public interface INetworkStateChanges {

    // 连接网络
    void onNetConnect();

    // 断开网络
    void onNetDisconnect();

}

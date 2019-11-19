
package com.reactlibrary;

import android.content.BroadcastReceiver;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;


public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private NetworkConnection netInfo = null;
    private static final String EVENT_CHANGE = "net-handler:Status Change";
    private ReactApplicationContext mContext = null;

    public NetworkBroadcastReceiver(NetworkConnection netInfo, ReactApplicationContext context) {
        this.netInfo = netInfo;
        mContext = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn =  (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo receivedInfo = conn.getActiveNetworkInfo();
        boolean connStatus = (receivedInfo != null && receivedInfo.isConnected());
        this.setConnectionStatus(connStatus);
    }

    private void setConnectionStatus(boolean status) {
        NetworkConnection network = new NetworkConnection();
        boolean currentStatus = network.getConnectionStatus();
        WritableMap receivedMessage = Arguments.createMap();
        receivedMessage.putBoolean("statusChange", status);
        if (mContext!=null && !status==currentStatus){
            mContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(EVENT_CHANGE, receivedMessage);
        }
        if (netInfo != null) {
            netInfo.setNetConnected(status);
        }
    }
}

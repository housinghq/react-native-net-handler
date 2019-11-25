
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
    private static final String EVENT_CHANGE = "connectionChange";
    private DeviceEventManagerModule.RCTDeviceEventEmitter jsModuleEventEmitter = null;
    private ReactApplicationContext mContext = null;

    public NetworkBroadcastReceiver(NetworkConnection netInfo,
                                    DeviceEventManagerModule.RCTDeviceEventEmitter jsModuleEventEmitter,
                                    ReactApplicationContext context) {
        this.netInfo = netInfo;
        this.jsModuleEventEmitter = jsModuleEventEmitter;
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
        NetworkConnection network = NetworkConnection.getInstance(mContext);
        boolean currentStatus = network.getConnectionStatus();
        WritableMap receivedMessage = Arguments.createMap();
        receivedMessage.putBoolean("status", status);
        if (jsModuleEventEmitter!=null && !status==currentStatus){
            jsModuleEventEmitter.emit(EVENT_CHANGE, receivedMessage);
        }
        if (netInfo != null) {
            netInfo.setNetConnected(status);
        }
    }
}

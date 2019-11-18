
package com.reactlibrary;

import android.content.BroadcastReceiver;
import com.facebook.react.bridge.ReactApplicationContext;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;


public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private NetworkConnection netInfo = null;
    private ReactApplicationContext mContext = null;
    private static final String EVENT = "net-handler:Status Change";
    
    public NetworkBroadcastReceiver(NetworkConnection netInfo) {
        this.netInfo = netInfo;
    }

    public NetworkBroadcastReceiver(ReactApplicationContext context){
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
        WritableNativeMap receivedMessage = new WritableNativeMap();
        if (currentStatus){
            receivedMessage.putString("Internet Status", "true");
        } else {
            receivedMessage.putString("Internet Status", "false");
        }
        if (mContext!=null && !status==currentStatus){
            mContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(EVENT, receivedMessage);
        }
        if (netInfo != null) {
            netInfo.setNetConnected(status);
        }
    }
}

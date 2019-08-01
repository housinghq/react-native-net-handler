
package com.reactlibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkBroadcastReceiver extends BroadcastReceiver {
    private NetworkConnection netInfo = null;

    public NetworkBroadcastReceiver(NetworkConnection netInfo) {
        this.netInfo = netInfo;
    }

    private void setConnectionStatus(boolean status) {
        if (netInfo != null) {
            netInfo.setNetConnected(status);
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn =  (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo receivedInfo = conn.getActiveNetworkInfo();
        boolean connStatus = (receivedInfo != null && receivedInfo.isConnected());
        this.setConnectionStatus(connStatus);
    }
}


package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnection {
    private static NetworkConnection netInfo = null;
    private static ReactApplicationContext mcontext = null;
    private boolean isNetConnected;

    private NetworkConnection() {}

    private NetworkConnection(ReactApplicationContext reactContext) {
        mcontext = reactContext;
        isNetConnected = updateConnectionFlag();
    }

    public static boolean updateConnectionFlag() {
        Context context = mcontext.getApplicationContext();
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null &&
                activeNetwork.isConnected());
    }

    public static NetworkConnection getInstance(ReactApplicationContext reactContext) {
        if (netInfo == null) {
            netInfo = new NetworkConnection(reactContext);
        }
        return netInfo;
    }

    public boolean getConnectionStatus() {
        return netInfo.isNetConnected;
    }

    public void setNetConnected(boolean connStatus) {
        isNetConnected = connStatus;
    }
}
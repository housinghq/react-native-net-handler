
package com.reactlibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.facebook.FacebookSdk.getApplicationContext;

public class NetworkConnection {
    private static NetworkConnection netInfo = null;
    private boolean isNetConnected;

    private NetworkConnection() {
        isNetConnected = updateConnectionFlag();
    }

    public static boolean updateConnectionFlag() {
        Context context = getApplicationContext();
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null &&
                activeNetwork.isConnected());
    }

    public static NetworkConnection getInstance() {
        if (netInfo == null) {
            netInfo = new NetworkConnection();
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
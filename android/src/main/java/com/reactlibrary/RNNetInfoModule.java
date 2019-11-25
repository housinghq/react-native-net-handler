
package com.reactlibrary;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNNetInfoModule extends ReactContextBaseJavaModule implements LifecycleEventListener {

  private NetworkConnection netInfo;
  private BroadcastReceiver mReceiver;
  private boolean isReceiverRegistered = false;

  public RNNetInfoModule(ReactApplicationContext reactContext) {
    super(reactContext);
    netInfo = NetworkConnection.getInstance(reactContext);
    DeviceEventManagerModule.RCTDeviceEventEmitter jsModuleEventEmitter =
            reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    mReceiver = new NetworkBroadcastReceiver(netInfo, jsModuleEventEmitter, reactContext);
    getReactApplicationContext().addLifecycleEventListener(this);
    registerReceiverIfNecessary(mReceiver);
  }

    @Override
    public String getName() {
        return "NetInfoModule";
    }

    @ReactMethod
    public boolean isNetConnected(Promise callback) {
      try {
          boolean val = netInfo.getConnectionStatus();
          callback.resolve(val);
          return val;
      } catch (Exception e) {
          callback.reject(e);
          return false;
      }
    }

    @Override
    public void onHostResume() {
        registerReceiverIfNecessary(mReceiver);
    }

    @Override
    public void onHostPause() {
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onHostDestroy() {
        unregisterReceiver(mReceiver);
    }

    private void registerReceiverIfNecessary(BroadcastReceiver receiver) {
        if (getCurrentActivity() == null && !isReceiverRegistered) return;
        try {
            getCurrentActivity().registerReceiver(
                    receiver,
                    new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            );
            isReceiverRegistered = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unregisterReceiver(BroadcastReceiver receiver) {
        if (isReceiverRegistered && getCurrentActivity() != null && receiver != null) {
            try {
                getCurrentActivity().unregisterReceiver(receiver);
                isReceiverRegistered = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
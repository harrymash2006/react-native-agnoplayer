package com.lib.agnoreactnative;

import androidx.annotation.NonNull;
import android.app.Activity;

import com.egeniq.agno.agnoplayer.player.AgnoPlayer;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.Objects;

public class AgnoPlayBridgeModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    public AgnoPlayBridgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        AgnoPlayer.Companion.initialize(getReactApplicationContext());
    }

    public void sendEvent(String name, WritableMap body){
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(name,body);
    }

    @NonNull
    @Override
    public String getName() {
        return "AgnoPlay";
    }
}

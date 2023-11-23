package com.lib.agnoreactnative;

import androidx.annotation.NonNull;
import android.app.Activity;
import android.view.View;

import com.egeniq.agno.agnoplayer.player.AgnoPlayer;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;

import java.util.Objects;

public class AgnoPlayBridgeModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;
    private int viewId = View.NO_ID;
    public AgnoPlayBridgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        AgnoPlayer.Companion.initialize(getReactApplicationContext());
    }

    public void sendEvent(String name, WritableMap body){
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(viewId, name,body);
    }

    @NonNull
    @Override
    public String getName() {
        return "AgnoPlay";
    }
}

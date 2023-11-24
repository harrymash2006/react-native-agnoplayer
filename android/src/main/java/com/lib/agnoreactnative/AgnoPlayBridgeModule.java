package com.lib.agnoreactnative;

import androidx.annotation.NonNull;
import android.view.View;
import com.egeniq.agno.agnoplayer.player.AgnoPlayer;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
public class AgnoPlayBridgeModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;
    private int viewId = View.NO_ID;
    public AgnoPlayBridgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        AgnoPlayer.Companion.initialize(getReactApplicationContext());
    }

    @NonNull
    @Override
    public String getName() {
        return "AgnoPlay";
    }
}

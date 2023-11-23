package com.lib.agnoreactnative;

import androidx.annotation.NonNull;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Collections;
import java.util.List;

public class AgnoPlayPackage  implements ReactPackage {

    private AgnoPlayBridgeModule nativeModule;
    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactApplicationContext) {
        nativeModule = new AgnoPlayBridgeModule(reactApplicationContext);
        return Collections.singletonList(nativeModule);
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactApplicationContext) {
        return List.of(
                new ReactAgnoPlayViewManager(nativeModule)
        );
    }
}

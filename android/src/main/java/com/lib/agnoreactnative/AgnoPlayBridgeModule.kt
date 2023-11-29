package com.lib.agnoreactnative

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.annotation.NonNull
import com.egeniq.agno.agnoplayer.player.AgnoPlayer
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class AgnoPlayBridgeModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    init {
        AgnoPlayer.initialize(reactApplicationContext)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @ReactMethod
    fun lockToPortrait() {
        currentActivity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @ReactMethod
    fun lockToLandscape() {
        currentActivity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    @NonNull
    override fun getName(): String {
        return "AgnoPlay"
    }
}

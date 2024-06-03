package com.lib.agnoreactnative

import android.annotation.SuppressLint
import android.app.PictureInPictureParams
import android.content.pm.ActivityInfo
import android.os.Build
import android.util.Rational
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

    @ReactMethod
    fun enterPipMode() {
        val activity = currentActivity
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val pipBuilder = PictureInPictureParams.Builder()
            val aspectRatio = Rational(9, 16)
            pipBuilder.setAspectRatio(aspectRatio)
            activity.enterPictureInPictureMode(pipBuilder.build())
        }
    }
}

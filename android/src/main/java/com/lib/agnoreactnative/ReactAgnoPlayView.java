package com.lib.agnoreactnative;

import android.app.Activity;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.uimanager.ThemedReactContext;

public class ReactAgnoPlayView extends FrameLayout implements LifecycleEventListener {
    private final String TAG = ReactAgnoPlayView.class.getSimpleName();
    private String sessionKey;
    private String brand;
    private String videoId;
    private String url;

    private Boolean showAds;
    private AgnoPlayBridgeModule nativeModule;
    private AgnoPlayerView agnoPlayerView;
    private boolean isInBackground;
    private Activity currentActivity;

    private PlayItem playerItem;

    public ReactAgnoPlayView(@NonNull final ThemedReactContext context, AgnoPlayBridgeModule nativeModule) {
        super(context);
        this.nativeModule = nativeModule;
        this.currentActivity = context.getCurrentActivity();
        createViews();
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        checkAndInitializePlayer();
    }

    public void setPlayerItem(PlayItem playerItem) {
        this.playerItem = playerItem;
        checkAndInitializePlayer();
    }

    public void setBrand(String brand) {
        this.brand = brand;
        checkAndInitializePlayer();
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
        checkAndInitializePlayer();
    }

    public void setUrl(String url) {
        this.url = url;
        checkAndInitializePlayer();
    }

    public void setShowAds(Boolean showAds) {
        this.showAds = showAds;
        checkAndInitializePlayer();
    }

    @Override
    public void onHostResume() {
        isInBackground = false;
        currentActivity.runOnUiThread(() -> agnoPlayerView.onHostResume());
    }

    @Override
    public void onHostPause() {
        isInBackground = true;
        currentActivity.runOnUiThread(() -> agnoPlayerView.onHostPause());
    }

    @Override
    public void onHostDestroy() {
        stopPlayback();
    }

    public void cleanUpResources() {
        stopPlayback();
    }

    private void stopPlayback() {
        releasePlayer();
    }

    private void releasePlayer() {
        currentActivity.runOnUiThread(() -> agnoPlayerView.onHostDestroy());
    }
    private void createViews() {
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        agnoPlayerView = new AgnoPlayerView(getContext());
        agnoPlayerView.setLayoutParams(layoutParams);
        agnoPlayerView.setBackgroundColor(getResources().getColor(com.egeniq.agno.agnoplayer.R.color.red));
        addView(agnoPlayerView, 0, layoutParams);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow");
    }

    private void checkAndInitializePlayer() {
        if (agnoPlayerView != null) {
            if (sessionKey!=null && brand!=null && (videoId!=null || url!=null) && showAds!=null && playerItem!=null){
                playerItem.setSessionKey(sessionKey);
                playerItem.setBrand(brand);
                playerItem.setShowAds(showAds);
                playerItem.setVideoId(videoId);
                playerItem.setUrl(url);
                agnoPlayerView.initialize(playerItem, currentActivity, nativeModule);
            }
        }
    }
}

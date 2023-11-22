package com.lib.agnoreactnative;

import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.uimanager.ThemedReactContext;

public class ReactAgnoPlayView extends FrameLayout implements LifecycleEventListener {
    private final String TAG = ReactAgnoPlayView.class.getSimpleName();
    private String sessionKey;
    private String brand;
    private String title;
    private String videoId;
    private String url;
    private Boolean showAds;
    private Boolean isFullScreen;
    private Boolean autoPlay;
    private String playerSkinColor;
    private String playButtonBackgroundColor;

    private Boolean skipAds;
    private Boolean muteOnAutoplay;
    private AgnoPlayBridgeModule nativeModule;
    private AgnoPlayerView agnoPlayerView;
    private boolean isInBackground;

    public ReactAgnoPlayView(@NonNull final ThemedReactContext context, AgnoPlayBridgeModule nativeModule) {
        super(context);
        this.nativeModule = nativeModule;
        createViews();
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        checkAndInitializePlayer();
    }

    public void setAutoPlay(Boolean autoPlay) {
        this.autoPlay = autoPlay;
        checkAndInitializePlayer();
    }

    public void setMuteOnAutoplay(Boolean muteOnAutoplay) {
        this.muteOnAutoplay = muteOnAutoplay;
        checkAndInitializePlayer();
    }

    public void setPlayButtonBackgroundColor(String playButtonBackgroundColor) {
        this.playButtonBackgroundColor = playButtonBackgroundColor;
        checkAndInitializePlayer();
    }

    public void setPlayerSkinColor(String playerSkinColor) {
        this.playerSkinColor = playerSkinColor;
        checkAndInitializePlayer();
    }

    public void setSkipAds(Boolean skipAds) {
        this.skipAds = skipAds;
        checkAndInitializePlayer();
    }

    public void setBrand(String brand) {
        this.brand = brand;
        checkAndInitializePlayer();
    }

    public void setTitle(String title) {
        this.title = title;
        checkAndInitializePlayer();
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
        checkAndInitializePlayer();
    }

    public void setShowAds(Boolean showAds) {
        this.showAds = showAds;
        checkAndInitializePlayer();
    }

    public void setFullScreen(Boolean fullScreen) {
        isFullScreen = fullScreen;
        checkAndInitializePlayer();
    }
    public void setUrl(String url) {
        this.url = url;
        checkAndInitializePlayer();
    }

    public void stop() {
    }

    @Override
    public void onHostResume() {
        isInBackground = false;
    }

    @Override
    public void onHostPause() {
        isInBackground = true;
    }

    @Override
    public void onHostDestroy() {
        stopPlayback();
    }

    public void cleanUpResources() {
        stopPlayback();
    }

    private void stopPlayback() {
        if (isFullScreen) {
            setFullScreen(false);
        }
        releasePlayer();
    }

    private void releasePlayer() {

    }
    private void createViews() {
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        agnoPlayerView = new AgnoPlayerView(getContext());
        agnoPlayerView.setLayoutParams(layoutParams);
        addView(agnoPlayerView, 0, layoutParams);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void checkAndInitializePlayer() {
        if (agnoPlayerView != null) {
            if (sessionKey!=null && brand!=null && (videoId!=null || url!=null) && showAds!=null/* && title!=null
                    && muteOnAutoplay!=null && skipAds!=null
                    && isFullScreen!=null && playerSkinColor!=null && playButtonBackgroundColor!=null
                    && autoPlay!=null*/){
                PlayItem playItem = new PlayItem(sessionKey, brand, title, videoId, url, showAds,
                        skipAds, muteOnAutoplay, isFullScreen, playerSkinColor,
                        playButtonBackgroundColor, autoPlay);
                agnoPlayerView.initialize(playItem);
            }
        }
    }
}

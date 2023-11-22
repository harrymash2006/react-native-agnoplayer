package com.lib.agnoreactnative;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

public class ReactAgnoPlayViewManager extends ViewGroupManager<ReactAgnoPlayView>{
    private AgnoPlayBridgeModule nativeModule;
    private static final String REACT_CLASS = "RCTAgnoPlay";
    private static final String PROP_SESSIONKEY = "sessionKey";
    private static final String PROP_BRAND = "brand";
    private static final String PROP_TITLE = "title";
    private static final String PROP_VIDEOID = "videoId";
    private static final String PROP_URL = "url";
    private static final String PROP_SHOWADS = "showAds";
    private static final String PROP_ISFULLSCREEN = "isFullScreen";
    private static final String PROP_AUTOPLAY = "autoPlay";
    private static final String PROP_PLAYERSKINCOLOR = "playerSkinColor";
    private static final String PROP_PLAYBUTTONBACKGROUNDCOLOR = "playButtonBackgroundColor";
    private static final String PROP_SKIPADS = "skipAds";
    private static final String PROP_MUTEONAUTOPLAY = "muteOnAutoplay";
    private static final String COMMAND_START_NAME = "setupAndStart";
    private static final int COMMAND_START_ID = 1;
    private static final String COMMAND_STOP_NAME = "stop";
    private static final int COMMAND_STOP_ID = 2;
    public ReactAgnoPlayViewManager(AgnoPlayBridgeModule nativeModule) {
        this.nativeModule = nativeModule;
    }

    @Override
    public void onDropViewInstance(ReactAgnoPlayView view) {
        view.cleanUpResources();
    }

    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }
    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    @ReactProp(name = PROP_SESSIONKEY)
    public void setSessionKey(ReactAgnoPlayView view, @Nullable String sessionKey) {
        view.setSessionKey(sessionKey);
    }

    @ReactProp(name = PROP_BRAND)
    public void setBrand(ReactAgnoPlayView view, @Nullable String brand) {
        view.setBrand(brand);
    }
    @ReactProp(name = PROP_TITLE)
    public void setTitle(ReactAgnoPlayView view, @Nullable String title) {
        view.setTitle(title);
    }
    @ReactProp(name = PROP_VIDEOID)
    public void setVideoId(ReactAgnoPlayView view, @Nullable String videoId) {
        view.setVideoId(videoId);
    }
    @ReactProp(name = PROP_URL)
    public void setUrl(ReactAgnoPlayView view, @Nullable String url) {
        view.setUrl(url);
    }
    @ReactProp(name = PROP_AUTOPLAY)
    public void setAutoPlay(ReactAgnoPlayView view, @Nullable Boolean autoPlay) {
        view.setAutoPlay(autoPlay);
    }
    @ReactProp(name = PROP_PLAYERSKINCOLOR)
    public void setPlayerSkinColor(ReactAgnoPlayView view, @Nullable String playerSkinColor) {
        view.setPlayerSkinColor(playerSkinColor);
    }
    @ReactProp(name = PROP_PLAYBUTTONBACKGROUNDCOLOR)
    public void setPlayButtonBackroundColor(ReactAgnoPlayView view, @Nullable String playButtonBackgroundColor) {
        view.setPlayButtonBackgroundColor(playButtonBackgroundColor);
    }

    @ReactProp(name = PROP_SKIPADS)
    public void setSkipAds(ReactAgnoPlayView view, @Nullable Boolean skipAds) {
        view.setSkipAds(skipAds);
    }
    @ReactProp(name = PROP_MUTEONAUTOPLAY)
    public void setMuteOnAutoPlay(ReactAgnoPlayView view, @Nullable Boolean muteOnAutoPlay) {
        view.setMuteOnAutoplay(muteOnAutoPlay);
    }
    @ReactProp(name = PROP_SHOWADS)
    public void setShowAds(ReactAgnoPlayView view, @Nullable Boolean showAds) {
        view.setShowAds(showAds);
    }
    @ReactProp(name = PROP_ISFULLSCREEN)
    public void setIsFullScreen(ReactAgnoPlayView view, @Nullable Boolean isFullScreen) {
        view.setFullScreen(isFullScreen);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(COMMAND_START_NAME, COMMAND_START_ID, COMMAND_STOP_NAME, COMMAND_STOP_ID);
    }

    /*@Override
    public void receiveCommand(ReactAgnoPlayView root, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case COMMAND_START_ID:
                root.setUpAndStart();
                break;
            case COMMAND_STOP_ID:
                root.stop();
                break;
        }
    }*/

    @NonNull
    @Override
    protected ReactAgnoPlayView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new ReactAgnoPlayView(themedReactContext, nativeModule);
    }
}

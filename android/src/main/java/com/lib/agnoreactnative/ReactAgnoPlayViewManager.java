package com.lib.agnoreactnative;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.lib.agnoreactnative.util.ReactBridgeUtils;

import java.util.Map;

public class ReactAgnoPlayViewManager extends ViewGroupManager<ReactAgnoPlayView>{
    private AgnoPlayBridgeModule nativeModule;
    private static final String REACT_CLASS = "RCTAgnoPlay";
    private static final String PROP_SESSIONKEY = "sessionKey";
    private static final String PROP_BRAND = "brand";
    private static final String PROP_VIDEOID = "videoId";
    private static final String PROP_URL = "url";
    private static final String PROP_TITLE = "itemTitle";
    private static final String PROP_AUTOPLAY = "autoPlay";
    private static final String PROP_MUTEONAUTOPLAY = "muteOnAutoplay";
    private static final String PROP_PLAYERSKINCOLOR = "playerSkinColor";
    private static final String PROP_PLAYBUTTONBACKGROUNDCOLOR = "playButtonBackgroundColor";
    private static final String PROP_POSTERURL = "posterURL";
    private static final String PROP_HIDEPROGRESSBARINADS = "hideProgressBarInAds";
    private static final String PROP_SKIPADS = "skipAds";
    private static final String PROP_MUXID = "muxId";
    private static final String PROP_SHOWTITLE = "showTitle";
    private static final String PROP_SHOWTESTAD = "showTestAd";
    private static final String PROP_ADTAG = "adTag";
    private static final String PROP_SHOWPLAYBUTTONONPAUSE = "showPlayButtonOnPause";
    private static final String PROP_SHOWSHAREBUTTON = "showShareButton";
    private static final String PROP_CHROMECASTENABLED = "chromecastEnabled";
    private static final String PROP_LOOP = "loop";
    private static final String PROP_GOOGLEANALYTICSENABLED = "googleAnalyticsEnabled";
    private static final String PROP_GOOGLEANALYTICSID = "googleAnalyticsId";

    private static final String PROP_SHOWADS = "showAds";
    private static final String PROP_ISFULLSCREEN = "isFullScreen";
    private static final String PROP_STARTOFFSET = "startOffset";

    private static final String PROP_PLAYERCONFIG = "playerConfig";

    private static final String COMMAND_PLAY_NAME = "play";
    private static final int COMMAND_PLAY_ID = 1;
    private static final String COMMAND_PAUSE_NAME = "pause";
    private static final int COMMAND_PAUSE_ID = 2;
    private static final String COMMAND_SEEK_NAME = "seekTo";
    private static final int COMMAND_SEEK_ID = 3;
    private static final String COMMAND_PORTRAIT_NAME = "lockToPortrait";
    private static final int COMMAND_PORTRAIT_ID = 4;
    private static final String COMMAND_CLOSEFULLSCREEN_NAME = "closeFullScreenPlayer";
    private static final int COMMAND_CLOSEFULLSCREEN_ID = 5;
    private static final String COMMAND_LANDSCAPE_NAME = "lockToLandscape";
    private static final int COMMAND_LANDSCAPE_ID = 6;

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
    @ReactProp(name = PROP_VIDEOID)
    public void setVideoId(ReactAgnoPlayView view, @Nullable String videoId) {
        view.setVideoId(videoId);
    }
    @ReactProp(name = PROP_URL)
    public void setUrl(ReactAgnoPlayView view, @Nullable String url) {
        view.setUrl(url);
    }

    @ReactProp(name = PROP_PLAYERCONFIG)
    public void setPlayerConfig(final ReactAgnoPlayView view, @Nullable ReadableMap playerConfig) {
        PlayItem playItem = new PlayItem();
        playItem.setTitle(ReactBridgeUtils.safeGetString(playerConfig, PROP_TITLE, null));
        playItem.setShowAds(ReactBridgeUtils.safeGetBool(playerConfig, PROP_SHOWADS, false));
        playItem.setStartOffset((long) ReactBridgeUtils.safeGetInt(playerConfig, PROP_STARTOFFSET, 0));
        playItem.setFullScreen(ReactBridgeUtils.safeGetBool(playerConfig, PROP_ISFULLSCREEN, false));
        playItem.setAutoPlay(ReactBridgeUtils.safeGetBool(playerConfig, PROP_AUTOPLAY, false));
        playItem.setMuteOnAutoPlay(ReactBridgeUtils.safeGetBool(playerConfig, PROP_MUTEONAUTOPLAY, true));
        playItem.setPlaySkinColor(ReactBridgeUtils.safeGetString(playerConfig, PROP_PLAYERSKINCOLOR, null));
        playItem.setPosterURL(ReactBridgeUtils.safeGetString(playerConfig, PROP_POSTERURL, null));
        playItem.setPlayButtonBackgroundColor(ReactBridgeUtils.safeGetString(playerConfig, PROP_PLAYBUTTONBACKGROUNDCOLOR, null));
        playItem.setHideProgressBarInAds(ReactBridgeUtils.safeGetBool(playerConfig, PROP_HIDEPROGRESSBARINADS, false));
        playItem.setSkipAds(ReactBridgeUtils.safeGetBool(playerConfig, PROP_SKIPADS, false));
        playItem.setMuxId(ReactBridgeUtils.safeGetString(playerConfig, PROP_MUXID, null));
        playItem.setShowTitle(ReactBridgeUtils.safeGetBool(playerConfig, PROP_SHOWTITLE, false));
        playItem.setShowPlayButtonOnPause(ReactBridgeUtils.safeGetBool(playerConfig, PROP_SHOWPLAYBUTTONONPAUSE, false));
        playItem.setShowShareButton(ReactBridgeUtils.safeGetBool(playerConfig, PROP_SHOWSHAREBUTTON, false));
        playItem.setChromecastEnabled(ReactBridgeUtils.safeGetBool(playerConfig, PROP_CHROMECASTENABLED, false));
        playItem.setLoop(ReactBridgeUtils.safeGetBool(playerConfig, PROP_LOOP, false));
        playItem.setGoogleAnalyticsEnabled(ReactBridgeUtils.safeGetBool(playerConfig, PROP_GOOGLEANALYTICSENABLED, false));
        playItem.setGoogleAnalyticsId(ReactBridgeUtils.safeGetString(playerConfig, PROP_GOOGLEANALYTICSID, null));
        playItem.setShowTestAd(ReactBridgeUtils.safeGetBool(playerConfig, PROP_SHOWTESTAD, false));
        playItem.setAdTag(ReactBridgeUtils.safeGetString(playerConfig, PROP_ADTAG, null));
        view.setPlayerItem(playItem);
    }
    @NonNull
    @Override
    protected ReactAgnoPlayView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new ReactAgnoPlayView(themedReactContext, nativeModule);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(COMMAND_PLAY_NAME, COMMAND_PLAY_ID,
                COMMAND_PAUSE_NAME, COMMAND_PAUSE_ID,
                COMMAND_SEEK_NAME, COMMAND_SEEK_ID,
                COMMAND_PORTRAIT_NAME, COMMAND_PORTRAIT_ID,
                COMMAND_LANDSCAPE_NAME, COMMAND_LANDSCAPE_ID,
                COMMAND_CLOSEFULLSCREEN_NAME, COMMAND_CLOSEFULLSCREEN_ID);
    }

    @Override
    public void receiveCommand(ReactAgnoPlayView root, String commandId, @Nullable ReadableArray args) {
        super.receiveCommand(root, commandId, args);
        switch (Integer.parseInt(commandId)) {
            case COMMAND_PLAY_ID:
                root.play();
                break;
            case COMMAND_PAUSE_ID:
                root.onHostPause();
                break;
            case COMMAND_SEEK_ID:
                assert args != null;
                root.seekTo(args.getInt(0));
                break;
            case COMMAND_PORTRAIT_ID:
                root.lockToPortrait();
                break;
            case COMMAND_LANDSCAPE_ID:
                root.lockToLandscape();
                break;
            case COMMAND_CLOSEFULLSCREEN_ID:
                root.closeFullScreenPlayer();
                break;
        }
    }

    @Nullable
    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put(
                "onFullScreen",
                MapBuilder.of(
                        "phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onFullScreen")
                )
        ).build();
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return super.getExportedCustomDirectEventTypeConstants();
    }
}

package com.lib.agnoreactnative;

public class PlayItem {
    String sessionKey;
    String brand;
    String title;
    String videoId;
    String url;
    Boolean showAds;
    Boolean skipAds;
    Boolean muteOnAutoPlay;
    Boolean isFullScreen;
    String playSkinColor;
    String playButtonBackgroundColor;
    Boolean autoPlay;

    public PlayItem(String sessionKey, String brand, String title, String videoId, String url, Boolean showAds, Boolean skipAds, Boolean muteOnAutoplay, Boolean isFullScreen, String playerSkinColor, String playButtonBackgroundColor, Boolean autoPlay) {
        this.sessionKey = sessionKey;
        this.brand = brand;
        this.title = title;
        this.videoId = videoId;
        this.showAds = showAds;
        this.skipAds = skipAds;
        this.url = url;
        this.muteOnAutoPlay = muteOnAutoplay;
        this.isFullScreen = isFullScreen;
        this.playSkinColor = playerSkinColor;
        this.playButtonBackgroundColor = playButtonBackgroundColor;
        this.autoPlay = autoPlay;
    }
}

//
//  PlayItem.swift
//  RCTAgnoPlay
//
//  Created by HARDIK MASHRU on 03/12/23.
//

struct PlayItem {
    var sessionKey: String?
    var brand: String?
    var title: String?
    var videoId: String?
    var url: String?
    var showTestAd: Bool?
    var adTag: String?
    var showAds: Bool?
    var skipAds: Bool?
    var muteOnAutoPlay: Bool?
    var fullScreen: Bool?
    var startOffset: Double?
    var playSkinColor: String?
    var customPlayButton: String?
    var playButtonBackgroundColor: String?
    var autoPlay: Bool?
    var posterURL: String?
    var hideControls: Bool?
    var assetType: String?
    var hideProgressBarInAds: Bool?
    var muxId: String?
    var showTitle: Bool?
    var showPlayButtonOnPause: Bool?
    var showShareButton: Bool?
    var chromecastEnabled: Bool?
    var loop: Bool?
    var googleAnalyticsEnabled: Bool?
    var disablePIPMode: Bool?
    var googleAnalyticsId: String?
        
    init(_ data: NSDictionary!) {
        guard data != nil else {
            return
        }
        
        sessionKey = data["sessionKey"] as? String
        brand = data["brand"] as? String
        title = data["title"] as? String
        videoId = data["videoId"] as? String
        assetType = data["assetType"] as? String
        url = data["url"] as? String
        showTestAd = data["showTestAd"] as? Bool
        adTag = data["adTag"] as? String
        customPlayButton = data["customPlayButton"] as? String
        showAds = data["showAds"] as? Bool
        hideControls = data["hideControls"] as? Bool
        skipAds = data["skipAds"] as? Bool
        muteOnAutoPlay = data["muteOnAutoPlay"] as? Bool
        fullScreen = data["isFullScreen"] as? Bool
        startOffset = data["startOffset"] as? Double
        playSkinColor = data["playSkinColor"] as? String
        playButtonBackgroundColor = data["playButtonBackgroundColor"] as? String
        autoPlay = data["autoPlay"] as? Bool
        posterURL = data["posterURL"] as? String
        hideProgressBarInAds = data["hideProgressBarInAds"] as? Bool
        muxId = data["muxId"] as? String
        showTitle = data["showTitle"] as? Bool
        showPlayButtonOnPause = data["showPlayButtonOnPause"] as? Bool
        showShareButton = data["showShareButton"] as? Bool
        chromecastEnabled = data["chromecastEnabled"] as? Bool
        loop = data["loop"] as? Bool
        disablePIPMode = data["disablePIPMode"] as? Bool
        googleAnalyticsEnabled = data["googleAnalyticsEnabled"] as? Bool
        googleAnalyticsId = data["googleAnalyticsId"] as? String
    }
}


//
//  PlayItem.swift
//  RCTAgnoPlay
//
//  Created by HARDIK MASHRU on 03/12/23.
//

class PlayItem {
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
    var startOffset: Int64?
    var playSkinColor: String?
    var playButtonBackgroundColor: String?
    var autoPlay: Bool?
    var posterURL: String?
    var hideProgressBarInAds: Bool?
    var muxId: String?
    var showTitle: Bool?
    var showPlayButtonOnPause: Bool?
    var showShareButton: Bool?
    var chromecastEnabled: Bool?
    var loop: Bool?
    var googleAnalyticsEnabled: Bool?
    var googleAnalyticsId: String?
    
    init() {}
    
    init(from dictionary: [String: Any]) {
        sessionKey = dictionary["sessionKey"] as? String
        brand = dictionary["brand"] as? String
        title = dictionary["title"] as? String
        videoId = dictionary["videoId"] as? String
        url = dictionary["url"] as? String
        showTestAd = dictionary["showTestAd"] as? Bool
        adTag = dictionary["adTag"] as? String
        showAds = dictionary["showAds"] as? Bool
        skipAds = dictionary["skipAds"] as? Bool
        muteOnAutoPlay = dictionary["muteOnAutoPlay"] as? Bool
        fullScreen = dictionary["fullScreen"] as? Bool
        startOffset = dictionary["startOffset"] as? Int64
        playSkinColor = dictionary["playSkinColor"] as? String
        playButtonBackgroundColor = dictionary["playButtonBackgroundColor"] as? String
        autoPlay = dictionary["autoPlay"] as? Bool
        posterURL = dictionary["posterURL"] as? String
        hideProgressBarInAds = dictionary["hideProgressBarInAds"] as? Bool
        muxId = dictionary["muxId"] as? String
        showTitle = dictionary["showTitle"] as? Bool
        showPlayButtonOnPause = dictionary["showPlayButtonOnPause"] as? Bool
        showShareButton = dictionary["showShareButton"] as? Bool
        chromecastEnabled = dictionary["chromecastEnabled"] as? Bool
        loop = dictionary["loop"] as? Bool
        googleAnalyticsEnabled = dictionary["googleAnalyticsEnabled"] as? Bool
        googleAnalyticsId = dictionary["googleAnalyticsId"] as? String
    }
}


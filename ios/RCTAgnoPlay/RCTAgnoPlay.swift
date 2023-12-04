//
//  RCTAgnoPlay.swift
//  RCTAgnoPlay
//
//  Created by HARDIK MASHRU on 03/12/23.
//

import Foundation
import React
import AVFoundation
import AgnoplayerSDK

class RCTAgnoPlay: UIView {
  
  private var _sessionKey: String = ""
  private var _brand: String = "agnoplay"
  private var _videoId: String? = nil
  private var _url:String? = nil
  private var _playerItem: PlayItem? = nil
  private var playerViewController: AgnoplayerViewController?

  // Events
  @objc var onFullScreen: RCTDirectEventBlock?

  @objc
  func setPlayerConfig(_ playerConfig:NSDictionary!) {
    _playerItem = PlayItem.init(playerConfig)
    checkAndInitializePlayer()
  }
  
  @objc
  func setSessionKey(_ sessionKey:String) {
    _sessionKey = sessionKey
    checkAndInitializePlayer()
  }
  
  @objc
  func setBrand(_ brand:String) {
    _brand = brand
    checkAndInitializePlayer()
  }
  
  @objc
  func setVideoId(_ videoId:String) {
    _videoId = videoId
    checkAndInitializePlayer()
  }
  
  @objc
  func setUrl(_ url:String) {
    _url = url
    checkAndInitializePlayer()
  }
  
  init(eventDispatcher:RCTEventDispatcher!) {
    super.init(frame: CGRect(x: 0, y: 0, width: 100, height: 100))
      
    NotificationCenter.default.addObserver(
        self,
        selector: #selector(applicationWillResignActive(notification:)),
        name: UIApplication.willResignActiveNotification,
        object: nil
    )

    NotificationCenter.default.addObserver(
        self,
        selector: #selector(applicationDidBecomeActive(notification:)),
        name: UIApplication.didBecomeActiveNotification,
        object: nil
    )

    NotificationCenter.default.addObserver(
        self,
        selector: #selector(applicationDidEnterBackground(notification:)),
        name: UIApplication.didEnterBackgroundNotification,
        object: nil
    )

    NotificationCenter.default.addObserver(
        self,
        selector: #selector(applicationWillEnterForeground(notification:)),
        name: UIApplication.willEnterForegroundNotification,
        object: nil
    )
    
    NotificationCenter.default.addObserver(
        self,
        selector: #selector(enterFullScreen(_:)),
        name: .agnoPlayerRequestedFullscreenEnter,
        object: nil
    )
    NotificationCenter.default.addObserver(
        self,
        selector: #selector(exitFullScreen(_:)),
        name: .agnoPlayerRequestedFullscreenExit,
        object: nil
    )
  }
    
  required init?(coder aDecoder: NSCoder) {
      super.init(coder: aDecoder)
  }
    
  func checkAndInitializePlayer() {
      if _sessionKey != nil, _brand != nil,
       (_videoId != nil || _url != nil),
       _playerItem != nil {
      initializePlayer()
    }
  }
  
  func initializePlayer() {
      Agnoplayer.setLicenseCheckBaseURL(Config.environment.licenseCheckBaseURL)
      
      Agnoplayer.setBaseURL(Config.environment.baseURL)
      
      if let playerConfig = _playerItem {
          
          var agnoPlayerItem = PlayerItem(title: playerConfig.title ?? "",
                                          identifier: _url ?? "",
                                          autoplay: playerConfig.autoPlay,
                                          playerSkinColor: playerConfig.playSkinColor, 
                                          skipAds: playerConfig.skipAds, 
                                          muxId: playerConfig.muxId,
                                          showPlayButtonOnPause: playerConfig.showPlayButtonOnPause, 
                                          chromecastEnabled: playerConfig.chromecastEnabled,
                                          airplayEnabled: playerConfig.chromecastEnabled,
                                          audioPlayerBackgroundColor: playerConfig.playSkinColor,
                                          muteOnAutoplay: playerConfig.muteOnAutoPlay ?? true,
                                          brandImage: _brand
                                        )
          agnoPlayerItem.mediaId = playerConfig.videoId
          if let url = playerConfig.posterURL.flatMap({ URL(string: $0) }) {
              agnoPlayerItem.posterURL = url
          }
          agnoPlayerItem.playButtonBackgroundColor = playerConfig.playButtonBackgroundColor ?? "#ffffff"
          agnoPlayerItem.showTitle = playerConfig.showTitle
          agnoPlayerItem.loop = playerConfig.loop ?? false
          agnoPlayerItem.googleAnalyticsEnabled = playerConfig.googleAnalyticsEnabled
          agnoPlayerItem.googleAnalyticsId = playerConfig.googleAnalyticsId
          
          if playerConfig.showAds ?? false {
              agnoPlayerItem.vastURL = playerConfig.adTag
          }
          
          if (playerConfig.fullScreen ?? false) {
              
          }
          playerViewController = AgnoplayerViewController.create(playerItem: agnoPlayerItem, delegate: nil, reporter: nil, expandedPlayerPresentationStyle: .pageSheet)
          playerViewController?.isPipEnabled = false
          if let timeMicroseconds = playerConfig.startOffset {
              let cmTime = CMTime(value: CMTimeValue(timeMicroseconds), timescale: 1000000)
              playerViewController?.player?.seek(to: cmTime)
          }
          guard let playerViewController = playerViewController else { return }
          playerViewController.view.frame = self.bounds
          addSubview(playerViewController.view)
      }
      
      
      
      /*Agnoplayer.getPlayerItem(brand: _brand,
                               id: _videoId,
                               preferredProtocol: nil,
                               cimData: nil,
                               licenseKey: LicenseConfig.agnoplay.rawValue) { [weak self] playerItem in
          guard let self = self else { return }
          
          var modifiedItem = playerItem
          if let playerConfig = _playerItem {
              modifiedItem.showShareButton = playerConfig.showShareButton ?? false
              modifiedItem.autoplay = playerConfig.autoPlay
              modifiedItem.muteOnAutoplay = playerConfig.muteOnAutoPlay ?? true
              modifiedItem.muxId = playerItem.muxId
          }
          
          if let url = exampleItem.url {
              modifiedItem.identifier = url
          }
          
          if case .test = exampleItem.configuration {
              modifiedItem.audioPlayerStartsFullscreen = true
          }
          
          if exampleItem.showAds {
              modifiedItem.vastURL = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpost&cmsid=496&vid=short_onecue&correlator="
          }
          
          self.createOrUpdatePlayerWith(modifiedItem)
          
          self.isLoading = false
      }
      
      onError: { [weak self] error in
          guard let self = self else { return }
          
          self.isLoading = false
          print(error)
          self.playerViewController?.player?.pause()
          self.playerViewController?.player?.stop()
          if error is LicenseError {
              let alert = UIAlertController(title: "Brand Error", message: "\(exampleItem.title) \n \(exampleItem.brand) \n\n \(error.localizedDescription)", preferredStyle: .alert)
              let ok = UIAlertAction(title: "OK", style: .default)
              alert.addAction(ok)
              DispatchQueue.main.async(execute: {
                  self.present(alert, animated: true, completion: {
                      self.tableView.deselectRow(at: IndexPath(row: exampleItemIndex, section: 0), animated: true)
                  })
              })
          }
      }*/
  }
  
  func play() {
    
  }
  
  func pause() {
    
  }
  
  func closeFullScreenPlayer() {
    
  }
  
  func lockToPortrait() {
    
  }
  
  func lockToLandscape() {
    
  }
  
  func seekTo(_ position: NSNumber) {
    
  }
    
  @objc private func enterFullScreen(_ notification:Notification) {
        // full screen layout change here
  }

  @objc private func exitFullScreen(_ notification:Notification) {
        // non full screen layout change here
  }
  
  @objc func applicationWillResignActive(notification:NSNotification!) {
    
  }
  
  @objc func applicationDidBecomeActive(notification: NSNotification!) {
    
  }
  
  @objc func applicationDidEnterBackground(notification:NSNotification!) {
    
  }
  
  @objc func applicationWillEnterForeground(notification:NSNotification!) {
    
  }
  
}

enum LicenseConfig: String, Codable {
    case agnoplay
    case agnoplayPodcast = "agnoplay-podcast"
    case agnoplayLive = "agnoplay-live"
    
    var license: String {
        switch self {
        case .agnoplay:
            switch Config.environment {
            case .production:
                return "f35e82e9-c3c5-44ad-bd92-99329dff2e6c"
            case .acceptance:
                return "4810c210-79dc-40f8-99eb-f09727dcecf7"
            case .egeniq:
                return "" // TODO: to be added
            }
        case .agnoplayPodcast:
            switch Config.environment {
            case .production:
                return "" // has_license returns false so no key needed
            case .acceptance:
                return "" // has_license returns false so no key needed
            case .egeniq:
                return "" // has_license returns false so no key needed
            }
        case .agnoplayLive:
            switch Config.environment {
            case .production:
                return "" // TODO: to be added
            case .acceptance:
                return "" // TODO: to be added
            case .egeniq:
                return "" // TODO: to be added
            }
        }
    }
}

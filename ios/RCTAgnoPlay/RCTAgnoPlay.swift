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
  private var _eventDispatcher:RCTEventDispatcher?
    
  // Events
  @objc var onFullScreen: RCTDirectEventBlock?
  @objc var onLoad: RCTDirectEventBlock?
  @objc var onPlayerStateChanged: RCTDirectEventBlock?
    
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
    
  override func removeFromSuperview() {
      playerViewController?.player?.pause()
      playerViewController?.removeFromParent()
      playerViewController = nil
      _eventDispatcher = nil
      NotificationCenter.default.removeObserver(self)
      super.removeFromSuperview()
  }
  
  init(eventDispatcher:RCTEventDispatcher!) {
    super.init(frame: CGRect(x: 0, y: 0, width: 100, height: 100))
    _eventDispatcher = eventDispatcher
      
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
          
          /*var agnoPlayerItem = PlayerItem(title: playerConfig.title ?? "",
                                          identifier: _url ?? "",
                                          mediaId: playerConfig.videoId, 
                                          autoplay: playerConfig.autoPlay,
                                          playerSkinColor: playerConfig.playSkinColor,
                                          playButtonBackgroundColor: playerConfig.playButtonBackgroundColor ?? "#ffffff", 
                                          skipAds: playerConfig.skipAds,
                                          muxId: playerConfig.muxId,
                                          showTitle: playerConfig.showTitle, 
                                          showPlayButtonOnPause: playerConfig.showPlayButtonOnPause,
                                          chromecastEnabled: playerConfig.chromecastEnabled,
                                          airplayEnabled: playerConfig.chromecastEnabled,
                                          audioPlayerBackgroundColor: playerConfig.playSkinColor,
                                          googleAnalyticsEnabled: playerConfig.googleAnalyticsEnabled ?? false,
                                          googleAnalyticsId: playerConfig.googleAnalyticsId, 
                                          muteOnAutoplay: playerConfig.muteOnAutoPlay ?? true,
                                          brandImage: _brand
                                        )
          if let url = playerConfig.posterURL.flatMap({ URL(string: $0) }) {
              agnoPlayerItem.posterURL = url
          }
          agnoPlayerItem.loop = playerConfig.loop ?? false
          
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
          addSubview(playerViewController.view)*/
          
          Agnoplayer.getPlayerItem(brand: _brand,
                                   id: _videoId,
                                   preferredProtocol: nil,
                                   cimData: nil,
                                   licenseKey: LicenseConfig.agnoplay.rawValue) { [weak self] playerItem in
              guard let self = self else { return }
              
              var modifiedItem = playerItem
              //modifiedItem.identifier = _sessionKey
              modifiedItem.loop = playerConfig.loop ?? false
              modifiedItem.showShareButton = playerConfig.showShareButton ?? false
              modifiedItem.autoplay = playerConfig.autoPlay
              modifiedItem.muteOnAutoplay = playerConfig.muteOnAutoPlay ?? true
              modifiedItem.playerSkinColor = playerConfig.playSkinColor ?? "#FFFFFF"
              modifiedItem.itemTitle = playerConfig.title
              modifiedItem.playButtonBackgroundColor = playerConfig.playButtonBackgroundColor ?? "#0000FF"
              modifiedItem.showTitle = playerConfig.showTitle
              modifiedItem.googleAnalyticsId = playerConfig.googleAnalyticsId
              modifiedItem.googleAnalyticsEnabled = playerConfig.googleAnalyticsEnabled
              
              if let url = playerConfig.posterURL.flatMap({ URL(string: $0) }) {
                  modifiedItem.posterURL = url
              }
              
              if let url = playerConfig.url {
                  modifiedItem.identifier = url
              }

              
              if playerConfig.showAds ?? false {
                  modifiedItem.vastURL = playerConfig.adTag
              }
              
              self.createOrUpdatePlayerWith(modifiedItem, playerConfig: playerConfig)
          }
          
          onError: { [weak self] error in
              guard let self = self else { return }
              
              print(error)
              self.playerViewController?.player?.pause()
              self.playerViewController?.player?.stop()
              if error is LicenseError {
                  DispatchQueue.main.async(execute: {
                      print("licence error")
                  })
              }
          }
      }
      
  }
    
    private func createOrUpdatePlayerWith(_ item: PlayerItem, playerConfig: PlayItem) {
      let reporter = AnalyticsReporter(providers: [EventStorageProvider(delegate: self)])
      playerViewController = AgnoplayerViewController.create(playerItem: item, delegate: nil, reporter: reporter, expandedPlayerPresentationStyle: .pageSheet)
      playerViewController?.player?.playerStateDelegate = self
      playerViewController?.isPipEnabled = false
      if let timeMicroseconds = playerConfig.startOffset {
          let cmTime = CMTime(value: CMTimeValue(timeMicroseconds), timescale: 1)
          playerViewController?.player?.seek(to: cmTime)
          if (!(playerConfig.autoPlay ?? false)) {
              playerViewController?.player?.pause()
          }
          if (playerConfig.fullScreen ?? false) {
              playerViewController?.modalPresentationStyle = .fullScreen
              //setDeviceOrientation(orientation: .landscapeLeft)
          }
      }
      guard let playerViewController = playerViewController else { return }
      playerViewController.view.frame = self.bounds
      addSubview(playerViewController.view)
  }
    
  func setDeviceOrientation(orientation: UIInterfaceOrientation) {
     UIDevice.current.setValue(orientation.rawValue, forKey: "orientation")
  }

  
  func play() {
      playerViewController?.player?.play()
  }
  
  func pause() {
      playerViewController?.player?.pause()
  }
  
  func closeFullScreenPlayer() {
      playerViewController?.modalPresentationStyle = .pageSheet
      onFullScreen?(getFullScreenData(isFullScreen: false, identifier: "0"))
  }
    
  func onLoadPlayer() {
      let data = ["duration": (playerViewController?.player?.currentDuration.seconds ?? 0) * 1000,
                  "sessionKey": _sessionKey] as [String : Any]
      if let onLoad = onLoad {
          onLoad(data)
      }
  }
    
    func onPlayerStateUpdated(state: String) {
        let data = ["state": state,
                    "sessionKey": _sessionKey] as [String : Any]
        if let onPlayerStateChanged = onPlayerStateChanged {
            onPlayerStateChanged(data)
        }
    }
    
  @objc func sendEvent(data: Dictionary<String,Any>) {
      NotificationCenter.default.post(
          name: Notification.Name("onFullScreen"),
          object: nil,
          userInfo: data
      )
  }
    
  func getFullScreenData(isFullScreen: Bool, identifier: String) -> Dictionary<String, Any> {
        let cmTime = CMTime(value: CMTimeValue(0), timescale: 1000000)
        return ["isFullScreenRequested": isFullScreen,
            "sessionKey": _sessionKey,
             "identifier": identifier,
            "isPlaying": playerViewController?.player?.isPlaying,
            "imageUrl": playerViewController?.playerItem?.posterURL?.absoluteString,
            "target": self.reactTag,
            "duration": NSNumber(value: Float(CMTimeGetSeconds(playerViewController?.player?.currentTime ?? cmTime)))]
  }
  
  func lockToPortrait() {
      playerViewController?.modalPresentationStyle = .pageSheet
  }
  
  func lockToLandscape() {
      playerViewController?.modalPresentationStyle = .fullScreen
  }
  
  func seekTo(_ position: NSNumber) {
      let cmTime = CMTime(value: CMTimeValue(truncating: position), timescale: 1)
      playerViewController?.player?.seek(to: cmTime)
  }
    
  func shouldMuteAudio(_ shouldMute: Bool) {
    playerViewController?.player?.isMuted = shouldMute
  }
    
  @objc private func enterFullScreen(_ notification:Notification) {
        // full screen layout change here
      var _mediaId = "0"
      if let userInfo = notification.userInfo {
              // Access data using keys
          if let mediaId = userInfo["mediaId"] as? String {
              // Handle the value
              print("mediaId:::: \(mediaId)")
              _mediaId = mediaId
          }
          // Access more data as needed
      }
      
      onFullScreen?(getFullScreenData(isFullScreen: true, identifier: _mediaId))
      //sendEvent(data: getFullScreenData(isFullScreen: true))
  }

  @objc private func exitFullScreen(_ notification:Notification) {
        // non full screen layout change here
      onFullScreen?(getFullScreenData(isFullScreen: false, identifier: "0"))
  }
  
  @objc func applicationWillResignActive(notification:NSNotification!) {
    
  }
  
  @objc func applicationDidBecomeActive(notification: NSNotification!) {
  }
  
  @objc func applicationDidEnterBackground(notification:NSNotification!) {
      playerViewController?.player?.pause()
  }
  
  @objc func applicationWillEnterForeground(notification:NSNotification!) {
    
  }
  
}

extension RCTAgnoPlay: PlayerStateDelegate {
    func onPlayerStateChanged(state: String) {
        if state == "STATE_READY" {
            onLoadPlayer()
        }
        onPlayerStateUpdated(state: state)
    }
    
    
}

extension RCTAgnoPlay: EventStorageProviderDelegate {
    func onEvent(event: AnalyticsEvent) {
        print("event logged::", event)
        if ((event == .ended || event == .percent100)/* && playerViewController?.playerItem?.identifier == _sessionKey*/) {
            onPlayerStateUpdated(state: "STATE_END")
        }
    }

    func eventsCleared() {
        
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

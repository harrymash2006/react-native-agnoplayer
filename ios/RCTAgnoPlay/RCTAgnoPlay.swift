//
//  RCTAgnoPlay.swift
//  RCTAgnoPlay
//
//  Created by HARDIK MASHRU on 03/12/23.
//

import Foundation
import React

class RCTAgnoPlay: UIView {
  
  private var _sessionKey: String? = nil
  private var _brand:String? = nil
  private var _videoId:String? = nil
  private var _url:String? = nil
  private var _playerItem:PlayItem? = nil
  
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
  
  @objc func applicationWillResignActive(notification:NSNotification!) {
    
  }
  
  @objc func applicationDidBecomeActive(notification: NSNotification!) {
    
  }
  
  @objc func applicationDidEnterBackground(notification:NSNotification!) {
    
  }
  
  @objc func applicationWillEnterForeground(notification:NSNotification!) {
    
  }
  
}

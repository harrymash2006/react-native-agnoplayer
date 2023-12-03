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
    _playerItem = PlayItem.init(from: playerConfig)
    checkAndInitializePlayer()
  }
  
  @objc
  func setSessionKey(_ sessionKey:NSString) {
    _sessionKey = sessionKey
    checkAndInitializePlayer()
  }
  
  @objc
  func setBrand(_ brand:NSString) {
    _brand = brand
    checkAndInitializePlayer()
  }
  
  @objc
  func setVideoId(_ videoId:NSString) {
    _videoId = videoId
    checkAndInitializePlayer()
  }
  
  @objc
  func setUrl(_ url:NSString) {
    _url = url
    checkAndInitializePlayer()
  }
  
  init(eventDispatcher:RCTEventDispatcher!) {
    
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
  
  func checkAndInitializePlayer() {
    if let sessionKey = sessionKey,
       let brand = brand,
       (videoId != nil || url != nil),
       let playerItem = playerItem {
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

//
//  RCTAgnoPlayManager.swift
//  RCTAgnoPlay
//
//  Created by HARDIK MASHRU on 03/12/23.
//

import React

@objc(RCTAgnoPlayManager)
class RCTAgnoPlayManager: RCTViewManager {
  
  override func view() -> UIView {
      return RCTAgnoPlay(eventDispatcher: bridge.eventDispatcher() as? RCTEventDispatcher)
  }
  
  func methodQueue() -> DispatchQueue {
      return bridge.uiManager.methodQueue
  }
      
  @objc(play:)
  func play(_ reactTag: NSNumber) -> Void {
      bridge.uiManager.prependUIBlock({_ , viewRegistry in
          let view = viewRegistry?[reactTag]
          if !(view is RCTAgnoPlay) {
              RCTLogError("Invalid view returned from registry, expecting RCTVideo, got: %@", String(describing: view))
          } else if let view = view as? RCTAgnoPlay {
              view.play()
          }
      })
  }
  
  @objc(pause:)
  func pause(_ reactTag: NSNumber) -> Void {
      bridge.uiManager.prependUIBlock({_ , viewRegistry in
          let view = viewRegistry?[reactTag]
          if !(view is RCTAgnoPlay) {
              RCTLogError("Invalid view returned from registry, expecting RCTVideo, got: %@", String(describing: view))
          } else if let view = view as? RCTAgnoPlay {
              view.pause()
          }
      })
  }
  
  @objc(closeFullScreenPlayer:)
  func closeFullScreenPlayer(_ reactTag: NSNumber) -> Void {
      bridge.uiManager.prependUIBlock({_ , viewRegistry in
          let view = viewRegistry?[reactTag]
          if !(view is RCTAgnoPlay) {
              RCTLogError("Invalid view returned from registry, expecting RCTVideo, got: %@", String(describing: view))
          } else if let view = view as? RCTAgnoPlay {
              view.closeFullScreenPlayer()
          }
      })
  }
  
  @objc(lockToPortrait:)
  func lockToPortrait(_ reactTag: NSNumber) -> Void {
      bridge.uiManager.prependUIBlock({_ , viewRegistry in
          let view = viewRegistry?[reactTag]
          if !(view is RCTAgnoPlay) {
              RCTLogError("Invalid view returned from registry, expecting RCTVideo, got: %@", String(describing: view))
          } else if let view = view as? RCTAgnoPlay {
              view.lockToPortrait()
          }
      })
  }
  
  @objc(lockToLandscape:)
  func lockToLandscape(_ reactTag: NSNumber) -> Void {
      bridge.uiManager.prependUIBlock({_ , viewRegistry in
          let view = viewRegistry?[reactTag]
          if !(view is RCTAgnoPlay) {
              RCTLogError("Invalid view returned from registry, expecting RCTVideo, got: %@", String(describing: view))
          } else if let view = view as? RCTAgnoPlay {
              view.lockToLandscape()
          }
      })
  }
  
  @objc(seekTo:reactTag:)
  func seekTo(position: NSNumber, reactTag: NSNumber) -> Void {
      bridge.uiManager.prependUIBlock({_ , viewRegistry in
          let view = viewRegistry?[reactTag]
          if !(view is RCTAgnoPlay) {
              RCTLogError("Invalid view returned from registry, expecting RCTVideo, got: %@", String(describing: view))
          } else if let view = view as? RCTAgnoPlay {
              view.seekTo(position)
          }
      })
  }
  
  override class func requiresMainQueueSetup() -> Bool {
      return true
  }
    
  
  
}

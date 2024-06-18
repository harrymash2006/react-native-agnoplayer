import React, { useEffect, useRef, useImperativeHandle, forwardRef } from 'react';
import PropTypes from 'prop-types';
import { Platform, UIManager, findNodeHandle, requireNativeComponent } from 'react-native';
import { ViewPropTypes } from 'deprecated-react-native-prop-types';
import AgnoPlay from './AgnoPlayerNativeModule';
const AgnoPlayerView = requireNativeComponent('RCTAgnoPlay');

const AgnoPlayerViewModule = forwardRef(({ sessionKey, brand, videoId, url, showAds, playerConfig, onFullScreen, onLoad, onPlayerStateChanged, style, onPipModeChanged }, ref) => {
  const viewRef = useRef(null);

  useEffect(() => {
    
    const onFullScreenListener = (fullScreenData) => {
      if (onFullScreen) {
        const data = fullScreenData.data
        if (data && data.sessionKey === sessionKey) {
          onFullScreen(data)
        }
      }
    };

    AgnoPlay.emitter.addListener('onFullScreen', onFullScreenListener);
    AgnoPlay.emitter.addListener('onLoad', onPlayerLoad);
    AgnoPlay.emitter.addListener('onPlayerStateChanged', onPlayerStateUpdated);
    AgnoPlay.emitter.addListener('onPipModeChanged', onPictureInPictureChanged);


    return () => {
        AgnoPlay.emitter.removeAllListeners('onFullScreen')
        AgnoPlay.emitter.removeAllListeners('onLoad')
        AgnoPlay.emitter.removeAllListeners('onPlayerStateChanged')
        AgnoPlay.emitter.removeAllListeners('onPipModeChanged')
    };
  });

  const onPictureInPictureChanged = (state) => {
    console.log('onPictureInPictureChanged::', state)
    if (onPipModeChanged) {
      onPipModeChanged(state)
    }
  }

  const onPlayerStateUpdated = (data) => {
    console.log('onPlayerStateChanged::', data)
    if (Platform.OS === 'ios' && onPlayerStateChanged && data && data.sessionKey == sessionKey) {
      onPlayerStateChanged(data)
    } else if (Platform.OS === 'android') {
      const stateData = data.data
      if (stateData && onPlayerStateChanged && stateData.sessionKey == sessionKey) {
        onPlayerStateChanged(stateData)
      }
    }
  }

  const onPlayerLoad = (data) => {
    console.log('onLoad::', data)
    if (Platform.OS === 'ios' && onLoad && data && data.sessionKey == sessionKey) {
      onLoad(data)
    } else if (Platform.OS === 'android') {
      const playerData = data.data
      if (playerData && onLoad && playerData.sessionKey == sessionKey) {
        onLoad(playerData)
      }
    }
  }

  const onFullScreenEventIOS = (data) => {
      console.log('onFullScreenEventIOS:', data)
      if (Platform.OS === 'ios' && onFullScreen && data && "key_"+data.identifier === sessionKey) {
        onFullScreen(data)
      } else if (Platform.OS === 'ios' && onFullScreen && !data.isFullScreenRequested && data.identifier == "0") {
        onFullScreen(data)
      }
  }

  useImperativeHandle(ref, () => ({
    play: () => {
      if (viewRef.current) {
        if (Platform.OS === 'android') {
          UIManager.dispatchViewManagerCommand(
            findNodeHandle(viewRef.current),
            UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.play.toString(),
            null
          );
        } else {
          AgnoPlay.nativeModule.play(findNodeHandle(viewRef.current))
        }
        
      }
    },
    enterPipMode: () => {
      AgnoPlay.nativeModule.enterPipMode();
    },
    changePipMode: (pipMode) => {
      if (viewRef.current) {
        if (Platform.OS === 'android') {
          UIManager.dispatchViewManagerCommand(
            findNodeHandle(viewRef.current),
            UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.changePipMode.toString(),
            [pipMode]
          );
        } else {
          AgnoPlay.nativeModule.changePipMode(pipMode, findNodeHandle(viewRef.current))
        }
        
      }
    },
    pause: () => {
      if (viewRef.current) {
        if (Platform.OS === 'android') {
          UIManager.dispatchViewManagerCommand(
            findNodeHandle(viewRef.current),
            UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.pause.toString(),
            null
          );
        } else {
          AgnoPlay.nativeModule.pause(findNodeHandle(viewRef.current))
        }
        
      }
    },
    lockToPortrait: () => {
      if (viewRef.current) {
        if (Platform.OS === 'android') {
          UIManager.dispatchViewManagerCommand(
            findNodeHandle(viewRef.current),
            UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.lockToPortrait.toString(),
            null
          );
        } else {
          AgnoPlay.nativeModule.lockToPortrait(findNodeHandle(viewRef.current))
        }
        
      }
    },
    lockToLandscape: () => {
      if (viewRef.current) {
        if (Platform.OS === 'android') {
          UIManager.dispatchViewManagerCommand(
            findNodeHandle(viewRef.current),
            UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.lockToLandscape.toString(),
            null
          );
        } else {
          AgnoPlay.nativeModule.lockToLandscape(findNodeHandle(viewRef.current))
        }
        
      }
    },
    closeFullScreenPlayer: () => {
      if (viewRef.current) {
        if (Platform.OS === 'android') {
          UIManager.dispatchViewManagerCommand(
            findNodeHandle(viewRef.current),
            UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.closeFullScreenPlayer.toString(),
            null
          );
        } else {
          AgnoPlay.nativeModule.closeFullScreenPlayer(findNodeHandle(viewRef.current))
        }
        
      }
    },
    seekTo: (position) => {
      if (viewRef.current) {
        if (Platform.OS === 'android') {
          UIManager.dispatchViewManagerCommand(
            findNodeHandle(viewRef.current),
            UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.seekTo.toString(),
            [position]
          );
        } else {
          AgnoPlay.nativeModule.seekTo(position, findNodeHandle(viewRef.current))
        }
        
      }
    },
    shouldMuteAudio: (value) => {
      if (viewRef.current) {
        if (Platform.OS === 'android') {
          UIManager.dispatchViewManagerCommand(
            findNodeHandle(viewRef.current),
            UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.shouldMuteAudio.toString(),
            [value]
          );
        } else {
          AgnoPlay.nativeModule.shouldMuteAudio(value, findNodeHandle(viewRef.current))
        }
        
      }
    }
  }));
  
  return (
    <AgnoPlayerView
      ref={viewRef}
      onFullScreen={(data) => onFullScreenEventIOS(data?.nativeEvent)}
      onLoad={(data) => onPlayerLoad(data?.nativeEvent)}
      onPlayerStateChanged={(data) => onPlayerStateUpdated(data?.nativeEvent)}
      {...{ sessionKey, brand, videoId, url, showAds, playerConfig, style }}
    />
  );
});

AgnoPlayerViewModule.propTypes = {
  ...ViewPropTypes,
  sessionKey: PropTypes.string,
  brand: PropTypes.string,
  videoId: PropTypes.string,
  url: PropTypes.string,
  playerConfig: PropTypes.shape({
    itemTitle: PropTypes.string,
    autoPlay: PropTypes.bool,
    showAds: PropTypes.bool,
    showTestAd: PropTypes.bool,
    muteOnAutoplay: PropTypes.bool,
    isFullScreen: PropTypes.bool,
    startOffset: PropTypes.number,
    playerSkinColor: PropTypes.string,
    posterURL: PropTypes.string,
    adTag: PropTypes.string,
    playButtonBackgroundColor: PropTypes.string,
    hideProgressBarInAds: PropTypes.bool,
    skipAds: PropTypes.bool,
    hideControls: PropTypes.bool,
    assetType: PropTypes.string,
    muxId: PropTypes.string,
    showTitle: PropTypes.bool,
    showPlayButtonOnPause: PropTypes.bool,
    showShareButton: PropTypes.bool,
    chromecastEnabled: PropTypes.bool,
    loop: PropTypes.bool,
    googleAnalyticsEnabled: PropTypes.bool,
    googleAnalyticsId: PropTypes.string,
    disablePIPMode: PropTypes.bool
  }),
  onFullScreen: PropTypes.func,
  style: PropTypes.object,
};

AgnoPlayerViewModule.defaultProps = {
  sessionKey: 'main',
  brand: 'agnoplay',
  videoId: 'Mbdskc9KsAii',
  style: {},
  playerConfig: {
    itemTitle: null,
    autoPlay: false,
    muteOnAutoplay: true,
    playerSkinColor: '#FFFFFF',
    posterURL: null,
    adTag: null,
    isFullScreen: false,
    hideControls: false,
    assetType: 'VOD',
    startOffset: 0,
    showTestAd: false,
    showAds: false,
    playButtonBackgroundColor: '#0000FF',
    hideProgressBarInAds: false,
    skipAds: false,
    muxId: null,
    showTitle: null,
    showPlayButtonOnPause: true,
    showShareButton: false,
    chromecastEnabled: true,
    loop: false,
    googleAnalyticsEnabled: false,
    googleAnalyticsId: null,
    disablePIPMode: false,
  },
};


export default AgnoPlayerViewModule
import React, { useEffect, useRef, useImperativeHandle, forwardRef } from 'react';
import PropTypes from 'prop-types';
import { Platform, UIManager, findNodeHandle, requireNativeComponent } from 'react-native';
import { ViewPropTypes } from 'deprecated-react-native-prop-types';
import AgnoPlay from './AgnoPlayerNativeModule';

const AgnoPlayerViewModule = forwardRef(({ sessionKey, brand, videoId, url, showAds, playerConfig, onFullScreen, style }, ref) => {
  const viewRef = useRef(null);

  useEffect(() => {
    const onFullScreenListener = (type) => {
      if (onFullScreen) {
        onFullScreen(type?.value === '1');
      }
    };

    AgnoPlay.emitter.addListener('onFullScreen', onFullScreenListener);

    return () => {
        AgnoPlay.emitter.removeAllListeners('onFullScreen')
    };
  });

  useImperativeHandle(ref, () => ({
    play: () => {
      if (viewRef.current) {
        UIManager.dispatchViewManagerCommand(
          findNodeHandle(viewRef.current),
          UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.play,
          null
        );
      }
    },
    pause: () => {
      console.log('inside pause')
      if (viewRef.current) {
        console.log('inside pause if')
        UIManager.dispatchViewManagerCommand(
          findNodeHandle(viewRef.current),
          UIManager.getViewManagerConfig('RCTAgnoPlay').Commands.pause,
          null
        );
      }
    },
  }));

  if (Platform.OS === 'android') {
    return (
      <AgnoPlayerView
        ref={viewRef}
        {...{ sessionKey, brand, videoId, url, showAds, playerConfig, style }}
      />
    );
  } else {
    return null;
  }
});

AgnoPlayerViewModule.propTypes = {
  ...ViewPropTypes,
  sessionKey: PropTypes.string,
  brand: PropTypes.string,
  videoId: PropTypes.string,
  url: PropTypes.string,
  playerConfig: PropTypes.shape({
    itemTitle: PropTypes.string,
    autoplay: PropTypes.bool,
    showAds: PropTypes.bool,
    showTestAd: PropTypes.bool,
    muteOnAutoplay: PropTypes.bool,
    playerSkinColor: PropTypes.string,
    posterURL: PropTypes.string,
    adTag: PropTypes.string,
    playButtonBackgroundColor: PropTypes.string,
    hideProgressBarInAds: PropTypes.bool,
    skipAds: PropTypes.bool,
    muxId: PropTypes.string,
    showTitle: PropTypes.bool,
    showPlayButtonOnPause: PropTypes.bool,
    showShareButton: PropTypes.bool,
    chromecastEnabled: PropTypes.bool,
    loop: PropTypes.bool,
    googleAnalyticsEnabled: PropTypes.bool,
    googleAnalyticsId: PropTypes.string,
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
    autoplay: false,
    muteOnAutoplay: true,
    playerSkinColor: '#FFFFFF',
    posterURL: null,
    adTag: null,
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
  },
};

const AgnoPlayerView = Platform.OS === 'android' ? requireNativeComponent('RCTAgnoPlay', AgnoPlayerViewModule, { nativeOnly: {} }) : null;

export default AgnoPlayerViewModule;
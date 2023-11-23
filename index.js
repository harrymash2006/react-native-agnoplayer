import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Platform, View, requireNativeComponent } from 'react-native';
import {ViewPropTypes} from 'deprecated-react-native-prop-types';

export default class AgnoPlayerViewModule extends Component {

    static propTypes = {
        ...ViewPropTypes,
        sessionKey: PropTypes.string,
        brand: PropTypes.string,
        videoId: PropTypes.string,
        url: PropTypes.string,
        showAds: PropTypes.bool,
        onFullScreen: PropTypes.func,
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
    }

    static defaultProps = {
        sessionKey: 'main',
        showAds: false,
        brand: 'agnoplay',
        videoId: 'Mbdskc9KsAii',
        //url: 'https://storage.googleapis.com/exoplayer-test-media-1/60fps/bbb-clear-1080/manifest.mpd',
        style: {},
        playerConfig: {
            // Provide default values for ConfigProperties here
            itemTitle: null,
            autoplay: false,
            muteOnAutoplay: true,
            playerSkinColor: '#FFFFFF',
            posterURL: null,
            adTag: null,
            showTestAd: false,
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
    }

    render() {
        if (Platform.OS === 'android') {
            return(
                <View style={{ flex:1 }}>
                    <AgnoPlayerView 
                    {...this.props}
                    style={{ flex: 1}}
                    />
                </View>
            )
        } else {
            return null
        }
        
    }

}
const AgnoPlayerView = Platform.OS === 'android' ? requireNativeComponent('RCTAgnoPlay', AgnoPlayerViewModule, {nativeOnly: {}}) : null;

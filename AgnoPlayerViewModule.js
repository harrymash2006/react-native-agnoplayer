import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Platform } from 'react-native';
import {ViewPropTypes} from 'deprecated-react-native-prop-types';
import AgnoPlay from './AgnoPlayerNativeModule'

export default class AgnoPlayerViewModule extends Component {

    static propTypes = {
        ...ViewPropTypes,
        sessionKey: PropTypes.string,
        brand: PropTypes.string,
        videoId: PropTypes.string,
        url: PropTypes.string,
        showAds: PropTypes.bool,
        playerConfig: PropTypes.object,
        onFullScreen: PropTypes.func,
    }

    static defaultProps = {
        sessionKey: 'main',
        showAds: false,
        brand: 'agnoplay',
        videoId: 'Mbdskc9KsAii',
        //url: 'https://storage.googleapis.com/exoplayer-test-media-1/60fps/bbb-clear-1080/manifest.mpd',
        style: {},
    }

    componentWillUnmount() {
        AgnoPlay.emitter.removeAllListeners()
    }

    componentDidMount() {
        AgnoPlay.emitter.addListener('onFullScreen', (type) => {
            console.log('inside event')
            if (this.props.onFullScreen) {
                console.log('inside event if')
                this.props.onFullScreen(type == "1")
            }
        })
    }

    render() {
        if (Platform.OS === 'android') {
            return(
                <AgnoPlayerView 
                {...this.props}
                />
            )
        } else {
            return null
        }
        
    }

}
const AgnoPlayerView = Platform.OS === 'android' ? requireNativeComponent('RCTAgnoPlay', AgnoPlayerViewModule, {nativeOnly: {}}) : null;

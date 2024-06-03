import { Component } from 'react';
import { ViewStyle } from 'react-native';
import { ConfigProperties, OnFullScreenData, onLoadData, PlayerStateData } from 'react-native-agnoplayer/types';

interface AgnoPlayerViewProps {
  sessionKey?: string;
  brand?: string;
  videoId?: string;
  url?: string;
  style?: ViewStyle;
  playerConfig?: ConfigProperties;
  onFullScreen?: (e: OnFullScreenData) => void;
  onLoad?: (e: onLoadData) => void;
  onPlayerStateChanged?: (e: PlayerStateData) => void;
  onPipModeChanged?: (e: boolean) => void;
}

export interface AgnoPlayNativeModule {
  lockToPortrait: () => Promise<void>;
  lockToLandscape: () => Promise<void>;
}

type PlayerState = 
    | 'STATE_IDLE'
    | 'STATE_READY'
    | 'STATE_BUFFERING'
    | 'STATE_PLAYING'
    | 'STATE_END'
    | 'STATE_UNKNOWN';

export const AgnoPlaybackState = {
    STATE_IDLE: 'STATE_IDLE' as PlayerState,
    STATE_READY: 'STATE_READY' as PlayerState,
    STATE_BUFFERING: 'STATE_BUFFERING' as PlayerState,
    STATE_PLAYING: 'STATE_PLAYING' as PlayerState,
    STATE_END: 'STATE_END' as PlayerState,
    STATE_UNKNOWN: 'STATE_UNKNOWN' as PlayerState,
};

export interface AgnoPlayerViewRef {
  play: () => void;
  pause: () => void;
  seekTo: (position: number) => void,
  closeFullScreenPlayer: () => void,
  lockToPortrait: () => void,
  lockToLandscape: () => void,
  shouldMuteAudio: (value: boolean) => void,
  changePipMode: (value: boolean) => void
}
export default class AgnoPlayerViewModule extends Component<AgnoPlayerViewProps> {}
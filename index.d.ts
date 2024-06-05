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

type SourceType = 
    | 'VOD'
    | 'TTS'
    | 'LIVE'
    | 'PODCAST';

type PlayerState = 
    | 'STATE_IDLE'
    | 'STATE_READY'
    | 'STATE_BUFFERING'
    | 'STATE_PLAYING'
    | 'STATE_END'
    | 'STATE_UNKNOWN';

export const AssetSourceType: { [key in SourceType]: key };
export const AgnoPlaybackState: { [key in PlayerState]: key };

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
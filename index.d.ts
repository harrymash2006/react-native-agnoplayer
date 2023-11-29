import { Component } from 'react';
import { ViewStyle } from 'react-native';
import { ConfigProperties, OnFullScreenData } from 'react-native-agnoplayer/types';

interface AgnoPlayerViewProps {
  sessionKey?: string;
  brand?: string;
  videoId?: string;
  url?: string;
  style?: ViewStyle;
  playerConfig?: ConfigProperties;
  onFullScreen?: (e: OnFullScreenData) => void;
}

export interface AgnoPlayNativeModule {
  lockToPortrait: () => Promise<void>;
  lockToLandscape: () => Promise<void>;
}

export interface AgnoPlayerViewRef {
  play: () => void;
  pause: () => void;
  seekTo: (position: number) => void,
  closeFullScreenPlayer: () => void,
  lockToPortrait: () => void,
  lockToLandscape: () => void
}

export default class AgnoPlayerViewModule extends Component<AgnoPlayerViewProps> {}
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

export interface AgnoPlayerViewRef {
  play: () => void;
  pause: () => void;
}

export default class AgnoPlayerViewModule extends Component<AgnoPlayerViewProps> {}
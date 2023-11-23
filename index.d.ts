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
  onFullScreen?: OnFullScreenData;
}

export default class AgnoPlayerViewModule extends Component<AgnoPlayerViewProps> {}
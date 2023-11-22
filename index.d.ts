import { Component } from 'react';
import { ViewStyle } from 'react-native';
import { ConfigProperties } from 'react-native-agnoplayer/types';

interface AgnoPlayerViewProps {
  sessionKey?: string;
  brand?: string;
  videoId?: string;
  url?: string;
  showAds?: boolean;
  style?: ViewStyle;
  playerConfig?: ConfigProperties;
}

export default class AgnoPlayerViewModule extends Component<AgnoPlayerViewProps> {}
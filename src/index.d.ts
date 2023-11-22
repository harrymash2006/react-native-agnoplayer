import { Component } from 'react';
import { ViewStyle } from 'react-native';

interface AgnoPlayerViewProps {
  sessionKey?: string;
  brand?: string;
  videoId?: string;
  url?: string;
  showAds?: boolean;
  style?: ViewStyle;
}

export default class AgnoPlayerViewModule extends Component<AgnoPlayerViewProps> {}
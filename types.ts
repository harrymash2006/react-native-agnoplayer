export type ConfigProperties = {
    itemTitle?: string;
    autoPlay?: boolean;
    isFullScreen?: boolean;
    startOffset?: number;
    muteOnAutoplay?: boolean;
    playerSkinColor?: string;
    posterURL?: string;
    showAds?: boolean;
    adTag?:string;
    showTestAd?: boolean;
    playButtonBackgroundColor?: string;
    hideProgressBarInAds?: boolean;
    skipAds?: boolean;
    hideControls?:boolean;
    assetType?:string;
    muxId?: string;
    showTitle?: boolean;
    showPlayButtonOnPause?: boolean;
    showShareButton?: boolean;
    chromecastEnabled?: boolean;
    loop?: boolean;
    googleAnalyticsEnabled?: boolean;
    googleAnalyticsId?: string;
    disablePIPMode?: boolean;
  };

  export type OnFullScreenData = Readonly<{
    isFullScreenRequested: boolean;
    sessionKey: string;
    duration: number;
    isPlaying: boolean;
    imageUrl: string;
  }>;

  export type onLoadData = Readonly<{
    duration: string;
    sessionKey: string;
  }>;

  export type PlayerStateData = Readonly<{
    state: string;
    sessionKey: string;
  }>;

  export type ConfigProps = Readonly<
  ConfigProperties | NodeRequire
>;
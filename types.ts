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
    muxId?: string;
    showTitle?: boolean;
    showPlayButtonOnPause?: boolean;
    showShareButton?: boolean;
    chromecastEnabled?: boolean;
    loop?: boolean;
    googleAnalyticsEnabled?: boolean;
    googleAnalyticsId?: string;
  };

  export type OnFullScreenData = Readonly<{
    isFullScreenRequested: boolean;
    sessionKey: string;
    duration: number;
    isPlaying: boolean;
    imageUrl: string;
  }>;

  export type ConfigProps = Readonly<
  ConfigProperties | NodeRequire
>;
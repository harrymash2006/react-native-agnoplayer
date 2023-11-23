export type ConfigProperties = {
    itemTitle?: string;
    autoplay?: boolean;
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
  }>;

  export type ConfigProps = Readonly<
  ConfigProperties | NodeRequire
>;
export type ConfigProperties = {
    itemTitle?: string;
    autoplay?: boolean;
    muteOnAutoplay?: boolean;
    playerSkinColor?: string;
    posterURL?: string;
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

  export type ConfigProps = Readonly<
  ConfigProperties | NodeRequire
>;
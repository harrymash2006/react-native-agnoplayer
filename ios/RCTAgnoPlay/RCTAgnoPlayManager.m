//
//  RCTAgnoPlayViewManager.m
//  RCTAgnoPlay
//
//  Created by HARDIK MASHRU on 03/12/23.
//

#import <React/RCTBridge.h>
#import "React/RCTViewManager.h"

@interface RCT_EXTERN_MODULE(RCTAgnoPlayManager, RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(playerConfig, NSDictionary);
RCT_EXPORT_VIEW_PROPERTY(sessionKey, NSString);
RCT_EXPORT_VIEW_PROPERTY(brand, NSString);
RCT_EXPORT_VIEW_PROPERTY(videoId, NSString);
RCT_EXPORT_VIEW_PROPERTY(url, NSString);

/*RCT_EXPORT_VIEW_PROPERTY(itemTitle, NSString);
RCT_EXPORT_VIEW_PROPERTY(autoPlay, BOOL);
RCT_EXPORT_VIEW_PROPERTY(muteOnAutoplay, BOOL);
RCT_EXPORT_VIEW_PROPERTY(playerSkinColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(playButtonBackgroundColor, NSString);
RCT_EXPORT_VIEW_PROPERTY(posterURL, NSString);
RCT_EXPORT_VIEW_PROPERTY(muxId, NSString);
RCT_EXPORT_VIEW_PROPERTY(adTag, NSString);
RCT_EXPORT_VIEW_PROPERTY(hideProgressBarInAds, BOOL);
RCT_EXPORT_VIEW_PROPERTY(skipAds, BOOL);
RCT_EXPORT_VIEW_PROPERTY(showTitle, BOOL);
RCT_EXPORT_VIEW_PROPERTY(showTestAd, BOOL);
RCT_EXPORT_VIEW_PROPERTY(showPlayButtonOnPause, BOOL);
RCT_EXPORT_VIEW_PROPERTY(showShareButton, BOOL);
RCT_EXPORT_VIEW_PROPERTY(chromecastEnabled, BOOL);
RCT_EXPORT_VIEW_PROPERTY(loop, BOOL);
RCT_EXPORT_VIEW_PROPERTY(googleAnalyticsEnabled, BOOL);
RCT_EXPORT_VIEW_PROPERTY(googleAnalyticsId, NSString);*/

RCT_EXPORT_VIEW_PROPERTY(onFullScreen, RCTDirectEventBlock);

RCT_EXTERN_METHOD(play:(nonnull NSNumber *)reactTag)
RCT_EXTERN_METHOD(pause:(nonnull NSNumber *)reactTag)
RCT_EXTERN_METHOD(closeFullScreenPlayer:(nonnull NSNumber *)reactTag)
RCT_EXTERN_METHOD(lockToPortrait:(nonnull NSNumber *)reactTag)
RCT_EXTERN_METHOD(lockToLandscape:(nonnull NSNumber *)reactTag)
RCT_EXTERN_METHOD(seekTo:(nonnull NSNumber *)seek
                 reactTag:(nonnull NSNumber *)reactTag)

@end

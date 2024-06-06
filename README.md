### AgnoPlayerView
================

The AgnoPlayerView is a React Native component for playing media items. It supports various features such as autoplay, full screen mode, and ad handling.

### Installation
----------------

To install the AgnoPlayerView, follow these steps:

1.  Install the AgnoPlayerView package using npm or yarn:
    ```bash
    npm install react-native-agnoplayer
    yarn add react-native-agnoplayer
    ```

2.  Link the package to your project:
    ```bash
    react-native link react-native-agnoplayer
    ```

### Usage
------------

Here is an example of how to use the AgnoPlayerView:

```jsx
import React, { useState, useEffect } from 'react';
import { View, Text } from 'react-native';
import AgnoPlayerView from 'react-native-agnoplayer';

const App = () => {
  const [videoRef, setVideoRef] = useState(null);
  const [activeVideoRef, setActiveVideoRef] = useState(null);

  useEffect(() => {
    // Initialize the video player
    const videoPlayer = new AgnoPlayerView({
      brand: 'dvhn',
      sessionKey: `key_${video.sourceId}`,
      videoId: video.sourceId,
      playerConfig: {
        showAds: true,
        posterURL: video.imageUrl,
        showTestAd: true,
        loop: true,
        adTag:
          'https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpost&cmsid=496&vid=short_onecue&correlator=',
      },
      onFullScreen: (data) => {
        console.log('Full screen requested:', data);
      },
      onLoad: (data) => {
        console.log('Player duration:', data.duration);
        videoRef?.current?.shouldMuteAudio(true);
      },
      onPipModeChanged: (value) => {
        console.log('PIP mode changed:', value);
        activeVideoRef?.current?.changePipMode(value);
      },
      onPlayerStateChanged: (newState) => {
        console.log('Player state:', newState.state);
        if (newState.state === 'STATE_READY') {
          setActiveVideoRef(videoRef);
        } else {
          setActiveVideoRef();
        }
      },
    });

    // Set the video player reference
    setVideoRef(videoPlayer);

    return (
      <View>
        <AgnoPlayerView
          ref={videoRef}
          style={{
            width: windowWidth - 32,
            height: (windowWidth - 32) * 0.5625,
          }}
          brand="dvhn"
          sessionKey={`key_${video.sourceId}`}
          videoId={video.sourceId}
          playerConfig={{
            showAds: true,
            posterURL: video.imageUrl,
            showTestAd: true,
            loop: true,
            adTag:
              'https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpost&cmsid=496&vid=short_onecue&correlator=',
          }}
          onFullScreen={(data) => onFullScreenRequested(data)}
          onLoad={(data) => {
            console.log('player duration::' + data.duration);
            videoRef?.current?.shouldMuteAudio(true);
          }}
          onPipModeChanged={(value) => {
            console.log('onPipModeChanged::' + activeVideoRef?.current);
            activeVideoRef?.current?.changePipMode(value);
          }}
          onPlayerStateChanged={(newState) => {
            console.log('playerState::', newState.state);
            console.log('playerState const::', AgnoPlaybackState.STATE_READY);
            // STATE_IDLE // STATE_READY // STATE_END // STATE_PLAYING // STATE_READY
            if (newState.state === 'STATE_READY') {
              setActiveVideoRef(videoRef);
            } else {
              setActiveVideoRef();
            }
          }}
        />
      </View>
    );
};

export default App;
```

### Configuration Properties
---------------------------

Here are the configuration properties for the AgnoPlayerView:

| Property | Type | Default | Options | Required |
|----------|------|---------|---------|----------|
| `mediaId` | String | - | - | Yes |
| `brandName` | String | - | - | Yes |
| `brandType` | String | - | - | No |
| `platform` | String | - | - | Yes |
| `group` | String | - | - | No |
| `autoplay` | Boolean | - | `true`, `false` | No |
| `muteOnAutoplay` | Boolean | - | `true`, `false` | No |
| `autoPlayOnlyInView` | Boolean | - | `true`, `false` | No |
| `hideControls` | Boolean | - | `true`, `false` | No |
| `itemTitle` | String | - | - | No |
| `seriesTitle` | String | - | - | No |
| `description` | String | - | - | No |
| `vastUrl` | String | - | - | No |
| `posterURL` | String | - | - | No |
| `shareURL` | Uri | - | - | No |
| `customPlayButton` | String | - | Base64 encoded image | No |
| `playerSkinColor` | String | - | Hex color code | No |
| `playButtonBackgroundColor` | String | - | Hex color code | No |
| `adTimeout` | required: Int, default: 0 | - | - | No |
| `adThreshold` | Long | - | - | No |
| `midrollThreshold` | Long | - | - | No |
| `hideProgressBarInAds` | Boolean | - | `true`, `false` | No |
| `skipAds` | Boolean | - | `true`, `false` | No |
| `noAds` | Boolean | - | `true`, `false` | No |
| `muxId` | String | - | - | No |
| `muxAnalyticsSampleRate` | Int | - | - | No |
| `isLive` | Boolean | - | `true`, `false` | No |
| `liveOfflineMessage` | String | - | - | No |
| `titlePosition` | String | - | - | No |
| `showTitle` | Boolean | - | `true`, `false` | No |
| `showShareButton` | required: Boolean, default: false | - | - | No |
| `showPlayButtonOnPause` | required: Boolean, default: false | - | - | No |
| `chromecastEnabled` | Boolean | - | `true`, `false` | No |
| `videoSettings` | Boolean | - | - | No |
| `account` | String | - | - | No |
| `cimData` | CimData | - | - | No |
| `cimId` | String | - | - | No |
| `mediaData` | MediaData | - | - | No |
| `url` | String | - | - | No |
| `audioOnly` | Boolean | - | `true`, `false` | No |
| `loop` | required: Boolean, default: false | - | - | No |
| `podcastPlayerBackgroundColor` | required: String, default #000000 | - | - | No |
| `podcastResumeStrategy` | Enum: PodcastResumeStrategy | - | - | No |
| `podcastFeature` | Enum: PodcastFeature | - | - | No |
| `assetType` | AssetType | - | - | No |
| `keyboardControls` | required: Boolean, default: true | - | - | No |
| `startOffset` | Long | - | - | No |
| `preferredStreamingProtocol` | required: StreamingProtocol | - | - | No |
| `podcastMiniPlayerTitleSource` | enum: PodcastTextSource | - | - | No |
| `podcastMiniPlayerSubTitleSource` | enum: PodcastTextSource | - | - | No |
| `podcastExpandedPlayerTitleSource` | enum: PodcastTextSource | - | - | No |
| `podcastExpandedPlayerSubTitleSource` | enum: PodcastTextSource | - | - | No |
| `podcastExpandedShowDescriptionBehindButton` | Boolean | - | `true`, `false` | No |
| `playAdBeforeStartPosition` | Boolean | - | `true`, `false` | No |
| `chapters` | Boolean | - | `true`, `false` | No |
| `chaptersUrl` | String | - | - | No |
| `chaptersCuesColor` | String | - | Hex color code | No |
| `brandLogoPosition` | String | - | - | No |
| `brandImage` | String | - | Base64 encoded logo | No |
| `publisher` | String | - | - | No |
| `agnoplayData` | Boolean | - | - | No |
| `agnoplayDataApiKey` | String | - | - | No |
| `agnoplayDataApiEndpoint` | String | - | - | No |
| `agnoplayDataCaptureEventType` | List<String> | - | - | No |

### Error Handling
-----------------

| Method | Description |
|--------|-------------|
| `addErrorListener` | Add an error listener to the player |
| `removeErrorListener` | Remove an error listener from the player |
| `showError` | Show an error message |

### Tracking Events
-------------------

| Event | Description |
|--------|-------------|
| `onEvent` | Triggered when an event occurs |
| `onError` | Triggered when an error occurs |

### Playback State
-----------------

| State | Description |
|--------|-------------|
| `STATE_IDLE` | The player does not have any media to play |
| `STATE_READY` | The player is able to immediately play from its current position but is currently paused |
| `STATE_BUFFERING` | The player is not able to immediately play from its current position |
| `STATE_PLAYING` | The player is actively playing a player item |
| `STATE_END` | The player has ended |

### Licenses
-------------

| License | Description |
|--------|-------------|
| `brand.hasLicense` | Check if the brand has a license |

### Integrations
----------------

| Integration | Description |
|--------|-------------|
| `Mux tracking` | Track events using Mux |
| `Cim tracking` | Track events using Cim |
| `Gemius` | Track events using Gemius |


### Migration Guide
-------------------

1.  Update your `package.json` file to include the new version.
2.  Run `npm install` or `yarn install` to update your dependencies.
3.  Update your code to use the new version.


### Contributing
-------------------

1.  Fork the repository.
2.  Create a new branch for your changes.
3.  Make your changes and commit them.
4.  Push your changes to your branch.
5.  Create a pull request to merge your changes into the main branch.

### License
-------------

*   The AgnoPlayerView is licensed under the MIT License.
*   You can use the AgnoPlayerView for free, but you must include the copyright notice in your application.
*   You can modify the AgnoPlayerView, but you must include the copyright notice in your application.

### Contact
-------------

*   Email: harrymash2006@gmail.com
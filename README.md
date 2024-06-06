### AgnoPlayer
================

The AgnoPlayer is a React Native component for playing media items. It supports various features such as autoplay, full screen mode, and ad handling.

### Installation
----------------

To install the AgnoPlayer, follow these steps:

1.  Install the AgnoPlayer package using npm or yarn:
    ```bash
    npm install https://github.com/harrymash2006/react-native-agnoplayer.git
    ```

    ```bash
    yarn add https://github.com/harrymash2006/react-native-agnoplayer.git
    ```

### Usage
------------

Here is an example of how to use the AgnoPlayer:

```jsx
import React, { useState, useEffect } from 'react';
import { View, Text } from 'react-native';
import AgnoPlayer from 'react-native-agnoplayer';

const App = () => {
  const [videoRef, setVideoRef] = useState(null);

    return (
      <View>
        <AgnoPlayer
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
          onFullScreen={data => onFullScreenRequested(data)}
          onLoad={data => {
            console.log('player duration::' + data.duration);
            videoRef?.current?.shouldMuteAudio(true);
          }}
          onPipModeChanged={value => {
            // update the state as per the requirements.
          }}
          onPlayerStateChanged={newState => {
            console.log('playerState::', newState.state);
            console.log('playerState const::', AgnoPlaybackState.STATE_READY);
            // STATE_IDLE // STATE_READY // STATE_END // STATE_PLAYING // STATE_READY
            if (newState.state === 'STATE_READY') {
              //Take the necessary steps to proceed further when the player is ready.
            } else {
              //Take the necessary steps to proceed further when the player is stopped.
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

### Main Properties

| Property               | Type                              | Required                       |
|------------------------|-----------------------------------|--------------------------------|
| `sessionKey`           | String                            | Yes                            |
| `brand`                | String                            | Yes                            |
| `videoId`              | String                            | Yes, if url is not present.    |
| `url`                  | String                            | Yes, if videoId is not present.|
| `style`                | ViewStyle                         | No                             |
| `playerConfig`         | ConfigProperties                  | No                             |
| `onFullScreen`         | (e: OnFullScreenData) => void     | No                             |
| `onLoad`               | (e: onLoadData) => void           | No                             |
| `onPlayerStateChanged` | (e: PlayerStateData) => void      | No                             |
| `onPipModeChanged`     | (e: boolean) => void              | No                             |

##### ConfigProperties

| Property                      | Type    | Options         | Required | Default             |
|-------------------------------|---------|-----------------|----------|---------------------|
| `itemTitle`                   | String  | -               | No       | null                |
| `autoPlay`                    | Boolean | -               | No       | false               |
| `isFullScreen`                | Boolean | -               | No       | false               |
| `startOffset`                 | Number  | -               | No       | 0                   |
| `muteOnAutoplay`              | Boolean | -               | No       | true                |
| `playerSkinColor`             | String  | Hex color code  | No       | '#FFFFFF'           |
| `posterURL`                   | String  | -               | No       | null                |
| `showAds`                     | Boolean | -               | No       | false               |
| `adTag`                       | String  | -               | No       | null                |
| `showTestAd`                  | Boolean | -               | No       | false               |
| `playButtonBackgroundColor`   | String  | Hex color code  | No       | '#0000FF'           |
| `hideProgressBarInAds`        | Boolean | -               | No       | false               |
| `skipAds`                     | Boolean | -               | No       | false               |
| `hideControls`                | Boolean | -               | No       | false               |
| `assetType`                   | String  | -               | No       | 'VOD'               |
| `muxId`                       | String  | -               | No       | null                |
| `showTitle`                   | Boolean | -               | No       | null                |
| `showPlayButtonOnPause`       | Boolean | -               | No       | true                |
| `showShareButton`             | Boolean | -               | No       | false               |
| `chromecastEnabled`           | Boolean | -               | No       | true                |
| `loop`                        | Boolean | -               | No       | false               |
| `googleAnalyticsEnabled`      | Boolean | -               | No       | false               |
| `googleAnalyticsId`           | String  | -               | No       | null                |

#### OnFullScreenData

| Property                | Type    |
|-------------------------|---------|
| `isFullScreenRequested` | Boolean |
| `sessionKey`            | String  |
| `duration`              | Number  |
| `isPlaying`             | Boolean |
| `imageUrl`              | String  |

#### onLoadData

| Property    | Type   |
|-------------|--------|
| `duration`  | String |
| `sessionKey`| String |

#### PlayerStateData

| Property    | Type                                                                                       |
|-------------|--------------------------------------------------------------------------------------------|
| `state`     | String [`<PlaybackState>`](https://github.com/harrymash2006/react-native-agnoplayer/tree/Create-ReadMe?tab=readme-ov-file#playbackstate) |
| `sessionKey`| String                                                                                     |

### PlaybackState

| State           | Description                                      |
|-----------------|--------------------------------------------------|
| `STATE_IDLE`    | The player does not have any media to play       |
| `STATE_READY`   | The player is able to immediately play from its current position but is currently paused |
| `STATE_BUFFERING`| The player is not able to immediately play from its current position |
| `STATE_PLAYING` | The player is actively playing a player item     |
| `STATE_END`     | The player has ended                             |

### Contributing
-------------------

1.  Fork the repository.
2.  Create a new branch for your changes.
3.  Make your changes and commit them.
4.  Push your changes to your branch.
5.  Create a pull request to merge your changes into the main branch.

### License
-------------

*   The AgnoPlayer is licensed under the MIT License.
*   You can use the AgnoPlayer for free, but you must include the copyright notice in your application.
*   You can modify the AgnoPlayer, but you must include the copyright notice in your application.

### Contact
-------------

*   Name: Hardik Mashru
*   Email: harrymash2006@gmail.com
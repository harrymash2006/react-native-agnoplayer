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

| Property                      | Type    | Options         | Required |
|-------------------------------|---------|-----------------|----------|
| `itemTitle`                   | String  | -               | No       |
| `autoPlay`                    | Boolean | -               | No       |
| `isFullScreen`                | Boolean | -               | No       |
| `startOffset`                 | Number  | -               | No       |
| `muteOnAutoplay`              | Boolean | -               | No       |
| `playerSkinColor`             | String  | Hex color code  | No       |
| `posterURL`                   | String  | -               | No       |
| `showAds`                     | Boolean | -               | No       |
| `adTag`                       | String  | -               | No       |
| `showTestAd`                  | Boolean | -               | No       |
| `playButtonBackgroundColor`   | String  | Hex color code  | No       |
| `hideProgressBarInAds`        | Boolean | -               | No       |
| `skipAds`                     | Boolean | -               | No       |
| `hideControls`                | Boolean | -               | No       |
| `assetType`                   | String  | -               | No       |
| `muxId`                       | String  | -               | No       |
| `showTitle`                   | Boolean | -               | No       |
| `showPlayButtonOnPause`       | Boolean | -               | No       |
| `showShareButton`             | Boolean | -               | No       |
| `chromecastEnabled`           | Boolean | -               | No       |
| `loop`                        | Boolean | -               | No       |
| `googleAnalyticsEnabled`      | Boolean | -               | No       |
| `googleAnalyticsId`           | String  | -               | No       |

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

| Property    | Type   |
|-------------|--------|
| `state`     | String |
| `sessionKey`| String |

### Error Handling

| Method                | Description                           |
|-----------------------|---------------------------------------|
| `addErrorListener`    | Add an error listener to the player   |
| `removeErrorListener` | Remove an error listener from the player|
| `showError`           | Show an error message                 |

### Tracking Events

| Event     | Description                  |
|-----------|------------------------------|
| `onEvent` | Triggered when an event occurs |
| `onError` | Triggered when an error occurs |

### Playback State

| State           | Description                                      |
|-----------------|--------------------------------------------------|
| `STATE_IDLE`    | The player does not have any media to play       |
| `STATE_READY`   | The player is able to immediately play from its current position but is currently paused |
| `STATE_BUFFERING`| The player is not able to immediately play from its current position |
| `STATE_PLAYING` | The player is actively playing a player item     |
| `STATE_END`     | The player has ended                             |

### Integrations

| Integration      | Description            |
|------------------|------------------------|
| `Mux tracking`   | Track events using Mux |
| `Cim tracking`   | Track events using Cim |
| `Gemius`         | Track events using Gemius |

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
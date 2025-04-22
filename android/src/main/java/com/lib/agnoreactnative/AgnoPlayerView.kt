package com.lib.agnoreactnative

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.util.AttributeSet
import android.util.Log
import android.view.Choreographer
import android.view.OrientationEventListener
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.egeniq.agno.agnoplayer.analytics.AnalyticsEventListener
import com.egeniq.agno.agnoplayer.analytics.Event
import com.egeniq.agno.agnoplayer.data.model.AssetType
import com.egeniq.agno.agnoplayer.data.model.PlayerItem
import com.egeniq.agno.agnoplayer.player.AgnoErrorListener
import com.egeniq.agno.agnoplayer.player.AgnoMediaPlayer
import com.egeniq.agno.agnoplayer.player.AgnoMediaPlayerFactory
import com.egeniq.agno.agnoplayer.player.AgnoPlayerStateListener
import com.egeniq.agno.agnoplayer.player.AssetSourceType
import com.egeniq.agno.agnoplayer.player.LogLevel
import com.egeniq.agno.agnoplayer.player.PlayerItemHolder
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.lib.agnoreactnative.util.Constants
import com.lib.agnoreactnative.util.Environment
import kotlinx.coroutines.launch


class AgnoPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), AgnoErrorListener, AgnoPlayerStateListener,
    AnalyticsEventListener {

    private var nativeModule: AgnoPlayBridgeModule? = null
    private val coroutineScope = CustomViewCoroutineScope()
    private var context: Context = context
    private var agnoPlayerView: com.egeniq.agno.agnoplayer.player.ui.AgnoPlayerView
    private var constraintLayout: ConstraintLayout
    private var mediaPlayer: AgnoMediaPlayer? = null
    private val dummyLifecycleOwner = DummyLifecycleOwner()
    private val dummyViewModelStoreOwner = DummyViewModelStoreOwner()
    private var orientationEventListener: OrientationEventListener? = null
    private var currentActivity: Activity? = null
    private var videoIdentifier: String? = null
    private var sessionKey = ""
    init {
        inflate(context, R.layout.activity_stream, this)
        agnoPlayerView = findViewById(R.id.agno_player_view)
        constraintLayout = findViewById(R.id.root_layout)

        agnoPlayerView.elevation = 8.0f // Set elevation
        /*orientationEventListener = object : OrientationEventListener(context) {

            override fun onOrientationChanged(orientation: Int) {
                val isPortrait = orientation > 300 || orientation < 60 || orientation in 120..240

                if ((currentActivity?.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT && isPortrait) || (currentActivity?.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && !isPortrait)) {
                    rootView?.postDelayed({
                        currentActivity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                    }, 300)
                }
            }
        }.also {
            it.enable()
        }*/

        setupLayoutHack()
    }

    private fun getEnvironment(): Environment {
        return Environment.PRODUCTION
    }

    fun initialize(
        playerItem: PlayItem,
        activity: Activity,
        nativeModule: AgnoPlayBridgeModule
    ) {
        this.nativeModule = nativeModule
        this.videoIdentifier = playerItem.sessionKey
        if (playerItem.sessionKey != null) {
            sessionKey = playerItem.sessionKey!!
        }
        this.currentActivity = activity
        Log.e("mediaplayer", "mediaplayerinitialized")
        mediaPlayer = AgnoMediaPlayerFactory.getMediaPlayer(
            context = activity,
            lifecycleOwner = dummyLifecycleOwner,
            viewModelStoreOwner = dummyViewModelStoreOwner,
            sessionKey = playerItem.sessionKey,
            agnoPlayerView = agnoPlayerView,
            miniPlayerView = null,
            logLevel = if (BuildConfig.DEBUG) LogLevel.DEBUG else null
        )
        mediaPlayer?.addErrorListener(this)
        mediaPlayer?.addPlayerStateListener(this)
        mediaPlayer?.addAnalyticsListener(this)
        mediaPlayer?.setFullScreenRequestedCallback { inFullScreen ->
            /*activity.requestedOrientation = if (inFullScreen) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }*/
            Log.e("TAG", "setFullScreenRequestedCallback")
            exitFullScreen()
            sendEvent("onFullScreen", "isFullScreenRequested", getFullScreenEventData(inFullScreen))
        }
        val advertTag =
            if (playerItem.adTag != null) {
                playerItem.adTag
            } else {
                if (playerItem.showTestAd == true) {
                    Constants.AdTag.PRE_ROLL
                } else {
                    null
                }
            }

        // playerItem.disablePIPMode  get PIP mode disable value here
        coroutineScope.launch {
            try {

                val playerItemFromList =
                    if (playerItem.videoId != null && playerItem.videoId?.isNotEmpty() == true) {
                        mediaPlayer?.getPlayerItem(
                            brandId = playerItem.brand.orEmpty(),
                            videoId = playerItem.videoId.orEmpty(),
                            preferredProtocol = null,
                            playAd = playerItem.showAds == true,
                            advertTag = advertTag,
                            type = getAssetType(playerItem.assetType)
                        )
                    } else {
                        mediaPlayer?.getPlayerItemFromUrl(
                            brandId = playerItem.brand.orEmpty(),
                            videoUrl = playerItem.url.orEmpty(),
                            preferredProtocol = null,
                            playAd = playerItem.showAds == true,
                            type = getAssetType(playerItem.assetType),
                        )
                    }.also { item ->
                        item?.showShareButton = playerItem.showShareButton == true
                    }?.copy(autoplay = playerItem.autoPlay,
                        startOffset = playerItem.startOffset,
                        identifier = sessionKey,
                        hideControls = playerItem.hideControls,
                        itemTitle = playerItem.title, muteOnAutoplay = playerItem.muteOnAutoPlay,
                        playerSkinColor = playerItem.playSkinColor, posterURL = playerItem.posterURL,
                        customPlayButton = when {
                            playerItem.customPlayButton == null -> null // Don't set customPlayButton if it's null
                            playerItem.customPlayButton!!.isEmpty() -> Constants.NOIMAGE // Default value for empty string
                            else -> playerItem.customPlayButton // Set to the provided string if it's not null or empty
                        },                        playButtonBackgroundColor = playerItem.playButtonBackgroundColor, hideProgressBarInAds = playerItem.hideProgressBarInAds == true,
                        skipAds = playerItem.skipAds == true, muxId = playerItem.muxId, showTitle = playerItem.showTitle,
                        showPlayButtonOnPause = playerItem.showPlayButtonOnPause == true, chromecastEnabled = playerItem.chromecastEnabled,
                        loop = playerItem.loop == true)

                if (playerItem.fullScreen == true) {
                    lockToLandscape()
                    enterFullScreen()
                }
                playContent(playerItemFromList)
            } catch (ex: Exception) {
                showError(ex)
            }
        }
    }

    private fun getAssetType(type: String?): AssetSourceType {
        return when (type) {
            "VOD" -> AssetSourceType.VOD
            "TTS" -> AssetSourceType.TTS
            "LIVE" -> AssetSourceType.LIVE
            "PODCAST" -> AssetSourceType.PODCAST
            else -> AssetSourceType.VOD
        }
    }

    private fun sendEvent(event: String, type: String, payload: WritableMap) {
            val eventPayload = Arguments.createMap().apply {
                putMap("data", payload)
            }
            sendNativeToJSEvent(event, eventPayload)
    }

    private fun sendNativeToJSEvent(event: String, data: WritableMap?) {
        currentActivity?.runOnUiThread {
            val reactContext = context as ReactContext
            reactContext.getJSModule(
                DeviceEventManagerModule.RCTDeviceEventEmitter::class.java).emit(event, data)
        }
    }

    private fun playContent(playerItem: PlayerItem?) {
        if (playerItem == null) {
            mediaPlayer?.showError()
        } else {
            mediaPlayer?.load(playerItem)
            if (playerItem.autoplay == true) {
                //mediaPlayer?.play()
            }
        }
    }

    fun setupLayoutHack() {
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                manuallyLayoutChildren()
                getViewTreeObserver().dispatchOnGlobalLayout()
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }

    fun manuallyLayoutChildren() {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(
                MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(measuredHeight, MeasureSpec.EXACTLY)
            )
            child.layout(0, 0, child.measuredWidth, child.measuredHeight)
        }
    }

    private fun showError(error: Exception?) {
        if (error != null) {
            Log.e("showError:", error.localizedMessage)
            mediaPlayer?.showError()

        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        coroutineScope.cancel()
    }

    override fun onError(error: Throwable) {
        error.message?.let { Log.e("AgnoPlayerError", "AgnoPlayerr::$it") }
    }

    override fun onPlayerStateChange(state: Int) {
        Log.i("AgnoPlayerInfo", "AgnoPlayerr::$state")
        if (state == 1) {
            // ready to play
            onLoad()
        }
        onPlayerStateUpdate(state)
    }

    fun onResume() {
        // mediaPlayer?.bindToService(true) // commented this as per new Agno SDK
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //enterFullScreen()
        } else {
            //exitFullScreen()
        }
    }

    private fun getPlayerData(): WritableMap {
        val payload = Arguments.createMap()
        payload.putString("duration", mediaPlayer?.getDuration().toString())
        payload.putString("sessionKey", videoIdentifier)
        return payload
    }

    private fun getFullScreenEventData(isFullScreen: Boolean): WritableMap {
        val payload = Arguments.createMap()
        payload.putBoolean("isFullScreenRequested", isFullScreen)
        payload.putString("sessionKey", videoIdentifier)
        payload.putBoolean("isPlaying", mediaPlayer?.getPlaybackState() == 3)
        payload.putString("imageUrl", mediaPlayer?.playerItem?.posterURL)
        mediaPlayer?.getCurrentPosition()?.let { payload.putInt("duration", it.toInt()) }
        return payload
    }

    private fun enterFullScreen() {
        mediaPlayer?.onFullScreenChanged(true)
    }

    private fun exitFullScreen() {
        mediaPlayer?.onFullScreenChanged(false)
    }

    fun onPause() {
        Log.e("onPause", "onPause")
        mediaPlayer?.pause()
    }

    fun play() {
        mediaPlayer?.play()
    }

    fun onDestroy() {
        mediaPlayer?.release()
        orientationEventListener?.disable()
    }

    fun seekTo(position: Int) {
        mediaPlayer?.seekTo(position.toLong())
    }

    fun shouldMuteAudio(shouldMute: Boolean) {
        mediaPlayer?.setMuted(shouldMute)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    fun lockToPortrait() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.setDimensionRatio(R.id.agno_player_view, "H,9:16")
        constraintSet.applyTo(constraintLayout)
        rootView?.postDelayed({
            currentActivity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }, 100)
    }

    fun lockToLandscape() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.setDimensionRatio(R.id.agno_player_view, "H,16:9")
        constraintSet.applyTo(constraintLayout)
        rootView?.postDelayed({
            currentActivity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }, 100)
    }

    fun closeFullScreenPlayer() {
        exitFullScreen()
        lockToPortrait()
        sendEvent("onFullScreen", "isFullScreenRequested", getFullScreenEventData(false))
    }

    private fun onLoad() {
        sendEvent("onLoad", "onLoadPlayer", getPlayerData())
    }

    private fun onPlayerStateUpdate(state: Int) {
        val payload = Arguments.createMap()
        payload.putString("state", returnState(state))
        payload.putString("sessionKey", videoIdentifier)
        sendEvent("onPlayerStateChanged", "onPlayerStateChanged", payload)
    }

    private fun returnState(state: Int): String {
        when (state) {
            0 -> return "STATE_IDLE"
            1 -> return "STATE_READY"
            2 -> return "STATE_BUFFERING"
            3 -> return "STATE_PLAYING"
            4 -> return "STATE_END"
            else -> return "STATE_UNKNOWN"
        }
    }

    override fun onEvent(event: Event, playerItemHolder: PlayerItemHolder) {
        println("onEvent::"+ event.name)
        println("playerItemHolder::"+ playerItemHolder.playerItem?.identifier)
        if (playerItemHolder.playerItem?.identifier == sessionKey && (event.name == "ended"/* || event.name == "100-percent"*/)) {
            onPlayerStateUpdate(4)
        }
    }

    fun onPipChanged(value: Boolean) {
        mediaPlayer?.onPictureInPictureModeChanged(value)
    }
}
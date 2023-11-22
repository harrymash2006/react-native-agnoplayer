package com.lib.agnoreactnative

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.util.AttributeSet
import android.util.Log
import android.view.Choreographer
import android.view.OrientationEventListener
import android.widget.LinearLayout
import com.egeniq.agno.agnoplayer.content.LicenseError
import com.egeniq.agno.agnoplayer.data.model.PlayerItem
import com.egeniq.agno.agnoplayer.player.AgnoErrorListener
import com.egeniq.agno.agnoplayer.player.AgnoMediaPlayer
import com.egeniq.agno.agnoplayer.player.AgnoMediaPlayerFactory
import com.egeniq.agno.agnoplayer.player.AgnoPlayerStateListener
import com.egeniq.agno.agnoplayer.player.LogLevel
import kotlinx.coroutines.launch


class AgnoPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), AgnoErrorListener, AgnoPlayerStateListener {

    private val coroutineScope = CustomViewCoroutineScope()
    private var context: Context = context
    private var agnoPlayerView: com.egeniq.agno.agnoplayer.player.ui.AgnoPlayerView
    private var mediaPlayer: AgnoMediaPlayer? = null
    private val dummyLifecycleOwner = DummyLifecycleOwner()
    private val dummyViewModelStoreOwner = DummyViewModelStoreOwner()
    private var orientationEventListener: OrientationEventListener? = null
    private var currentActivity: Activity? = null

    init {
        inflate(context, R.layout.activity_stream, this)
        agnoPlayerView = findViewById(R.id.agno_player_view)
        agnoPlayerView.elevation = 8.0f // Set elevation
        orientationEventListener = object : OrientationEventListener(context) {

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
        }

        setupLayoutHack()
    }

    private fun getEnvironment(): Environment {
        return Environment.PRODUCTION
    }

    fun initialize(playerItem: PlayItem, activity: Activity) {
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
        mediaPlayer?.setAudioPlayerStartsFullscreen(true)
        mediaPlayer?.setFullScreenRequestedCallback { inFullScreen ->
            activity?.requestedOrientation = if (inFullScreen) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
        val advertTag =
            if (playerItem.url != null) {
                Constants.AdTag.PRE_ROLL
            } else {
                Constants.AdTag.PRE_MID_POST_ROLL
            }

        coroutineScope.launch {
            try {
                val playerItemFromList =
                    if (playerItem.videoId != null && playerItem.videoId.isNotEmpty()) {
                        mediaPlayer?.getPlayerItem(
                            brandId = playerItem.brand,
                            licenseKey = null,
                            videoId = playerItem.videoId.orEmpty(),
                            preferredProtocol = null,
                            playAd = playerItem.showAds,
                            advertTag = advertTag,
                            baseUrl = getEnvironment().baseUrl,
                            licenseBaseUrl = getEnvironment().licenseBaseUrl
                        )
                    } else {
                        mediaPlayer?.getPlayerItemFromUrl(
                            brandId = playerItem.brand,
                            licenseKey = null,
                            videoUrl = playerItem.url.orEmpty(),
                            preferredProtocol = null,
                            playAd = playerItem.showAds,
                            advertTag = advertTag,
                            baseUrl = getEnvironment().baseUrl,
                            licenseBaseUrl = getEnvironment().licenseBaseUrl
                        )
                    }.also { item ->
                        item?.showShareButton = false
                    }
                playContent(playerItemFromList)
            } catch (ex: Exception) {
                showError(ex)
            }
        }
    }

    private fun playContent(playerItem: PlayerItem?) {
        if (playerItem == null) {
            mediaPlayer?.showError()
        } else {
            mediaPlayer?.load(playerItem)
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
            if (error is LicenseError) {
                mediaPlayer?.showError(error.errorDescription(context))
            } else {
                mediaPlayer?.showError()
            }
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
    }
}
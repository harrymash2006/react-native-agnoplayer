package com.lib.agnoreactnative;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;

import com.egeniq.agno.agnoplayer.content.LicenseError;
import com.egeniq.agno.agnoplayer.data.model.PlayerItem;
import com.egeniq.agno.agnoplayer.player.AgnoErrorListener;
import com.egeniq.agno.agnoplayer.player.AgnoMediaPlayer;
import com.egeniq.agno.agnoplayer.player.AgnoMediaPlayerFactory;
import com.egeniq.agno.agnoplayer.player.AgnoPlayerStateListener;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class AgnoPlayerViewJava extends FrameLayout implements AgnoErrorListener, AgnoPlayerStateListener {

    private Context context;
    private static final String TAG = "AgnoPlayerView";
    private com.egeniq.agno.agnoplayer.player.ui.AgnoPlayerView agnoPlayerView;
    private AgnoMediaPlayer mediaPlayer;

    public AgnoPlayerViewJava(Context context) {
        this(context, null);
    }

    public AgnoPlayerViewJava(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AgnoPlayerViewJava(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        agnoPlayerView = new com.egeniq.agno.agnoplayer.player.ui.AgnoPlayerView(context);
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        agnoPlayerView.setLayoutParams(layoutParams);

        agnoPlayerView.setBackgroundColor(getResources().getColor(com.egeniq.agno.agnoplayer.R.color.red));
        // Set custom attributes using set methods
        // Set the LinearLayout as the content view
        addView(agnoPlayerView, 0, layoutParams);
    }

    public Environment getEnvironment() {
        return Environment.PRODUCTION;
    }

    public void initialize(PlayItem playerItem, Activity currentActivity) {
        try {
            mediaPlayer = AgnoMediaPlayerFactory.Companion.getMediaPlayer(
                    context,
                    (ViewModelStoreOwner) currentActivity,
                    playerItem.sessionKey,
                    (LifecycleOwner) currentActivity,
                    agnoPlayerView,
                    null,
                    null,
                    null
            );
            mediaPlayer.addErrorListener(this);
            mediaPlayer.addPlayerStateListener(this);

            Continuation<PlayerItem> continuation = new Continuation<>() {
                @NonNull
                @Override
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }

                @Override
                public void resumeWith(@NonNull Object result) {
                    try {
                        currentActivity.runOnUiThread(() -> {
                            PlayerItem playerItem = (PlayerItem) result;
                            System.out.println("Player item: " + playerItem);
                            playerItem.setShowShareButton(false);
                            playContent(playerItem);
                        });
                    } catch (Exception e) {
                        showError(e);
                    }
                }
            };
            if (playerItem.videoId != null && !playerItem.videoId.isBlank()) {
                mediaPlayer.getPlayerItem(
                        playerItem.brand,
                        null,
                        playerItem.videoId,
                        null,
                        playerItem.showAds,
                        Constants.AdTag.PRE_MID_POST_ROLL,
                        getEnvironment().getBaseUrl(),
                        getEnvironment().getLicenseBaseUrl(),
                        continuation
                );
            } else {
                mediaPlayer.getPlayerItemFromUrl(
                        playerItem.brand,
                        null,
                        playerItem.url,
                        null,
                        playerItem.showAds,
                        Constants.AdTag.PRE_MID_POST_ROLL,
                        getEnvironment().getBaseUrl(),
                        getEnvironment().getLicenseBaseUrl(),
                        continuation
                );
            }
        }catch (Exception e){
            Log.e("AgnoMediaPlayer", e.getMessage());
        }
    }

    private void playContent(PlayerItem playerItem) {
        if (playerItem == null) {
            if (mediaPlayer != null) {
                mediaPlayer.showError("error when playing content");
            }
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.load(playerItem);
                //mediaPlayer.play();
            }
        }
    }

    private void showError(Exception error) {
        if (error != null) {
            if (error instanceof LicenseError) {
                if (mediaPlayer != null) {
                    mediaPlayer.showError(((LicenseError) error).errorDescription(context));
                }
            } else {
                if (mediaPlayer != null) {
                    mediaPlayer.showError("error:"+error.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        Log.e(TAG, "error:"+throwable.getMessage());
    }

    @Override
    public void onPlayerStateChange(int i) {
        Log.i(TAG, "playerstate:"+i);
    }
}


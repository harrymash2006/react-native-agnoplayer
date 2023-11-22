package com.lib.agnoreactnative;
import static com.egeniq.agno.agnoplayer.player.AgnoMediaPlayerFactory.*;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.egeniq.agno.agnoplayer.content.LicenseError;
import com.egeniq.agno.agnoplayer.data.model.PlayerItem;
import com.egeniq.agno.agnoplayer.player.AgnoMediaPlayer;
import com.egeniq.agno.agnoplayer.player.AgnoMediaPlayerFactory;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

public class AgnoPlayerView extends FrameLayout implements LifecycleOwner, ViewModelStoreOwner {

    private Context context;
    private com.egeniq.agno.agnoplayer.player.ui.AgnoPlayerView agnoPlayerView;
    private AgnoMediaPlayer mediaPlayer;

    public AgnoPlayerView(Context context) {
        this(context, null);
    }

    public AgnoPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AgnoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        agnoPlayerView = new com.egeniq.agno.agnoplayer.player.ui.AgnoPlayerView(context);
        agnoPlayerView.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        //agnoPlayerView.setBackgroundColor(getResources().getColor(R.color.black));
        agnoPlayerView.setElevation(8.0f); // Set elevation

        // Set custom attributes using set methods
        // Set the LinearLayout as the content view
        addView(agnoPlayerView);
    }

    public Environment getEnvironment() {
        return Environment.PRODUCTION;
    }

    public void initialize(PlayItem playerItem) {
        mediaPlayer = AgnoMediaPlayerFactory.Companion.getMediaPlayer(
                context,
                this,
                playerItem.sessionKey,
                this,
                agnoPlayerView,
                null,
                null,
                null
        );

        Continuation<PlayerItem> continuation = new Continuation<>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return getContext();
            }

            @Override
            public void resumeWith(@NonNull Object result) {
                try {
                    PlayerItem playerItem = (PlayerItem) result;
                    System.out.println("Player item: " + playerItem);
                    playerItem.setShowShareButton(false);
                    playContent(playerItem);
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
                    null,
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
                    null,
                    getEnvironment().getBaseUrl(),
                    getEnvironment().getLicenseBaseUrl(),
                    continuation
            );
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
    public Lifecycle getLifecycle() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ViewModelStore getViewModelStore() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}


package com.apps.videochannel.base;

import android.content.Intent;
import android.widget.Toast;

import com.apps.videochannel.R;
import com.apps.videochannel.classes.Constant;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public abstract class YouTubeFailureRecoveryActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    public abstract YouTubePlayer.Provider getYouTubePlayerProvider();

    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        try {
            if (youTubeInitializationResult.isUserRecoverableError()) {
                youTubeInitializationResult.getErrorDialog(this, 1).show();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            YtData instance = YtData.getInstance();
            if (instance.getData().equals("")) {
                getYouTubePlayerProvider().initialize(Constant.API_KEY, this);
            } else {
                getYouTubePlayerProvider().initialize(instance.getData(), this);
            }
        }
    }
}

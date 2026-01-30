package com.example.codecheckproject;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class Music extends Service {

    private MediaPlayer player;
    private int currentMusic = -1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int musicResId = intent.getIntExtra("music", -1);

        // Only change music if it's different
        if (musicResId != -1 && musicResId != currentMusic) {
            playMusic(musicResId);
        }

        return START_STICKY;
    }

    private void playMusic(int resId) {
        if (player != null) {
            player.stop();
            player.release();
        }

        currentMusic = resId;
        player = MediaPlayer.create(this, resId);
        player.setLooping(true);
        player.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

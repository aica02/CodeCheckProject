package com.example.codecheckproject;

import android.content.Context;
import android.media.SoundPool;
import android.media.AudioAttributes;
import android.os.Build;

public class SoundEffects {

    private static SoundPool soundPool;
    private static int clickSound, correctSound, wrongSound;

    public static void init(Context context) {
        if (soundPool != null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attrs = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(attrs)
                    .build();
        } else {
            soundPool = new SoundPool(5, android.media.AudioManager.STREAM_MUSIC, 0);
        }

        clickSound = soundPool.load(context, R.raw.click, 1);
        correctSound = soundPool.load(context, R.raw.correct, 1);
        wrongSound = soundPool.load(context, R.raw.wrong, 1);
    }

    public static void playClick() {
        if (soundPool != null) {
            soundPool.play(clickSound, 1, 1, 1, 0, 1);
        }
    }

    public static void playCorrect() {
        if (soundPool != null) {
            soundPool.play(correctSound, 1, 1, 2, 0, 1);
        }
    }

    public static void playWrong() {
        if (soundPool != null) {
            soundPool.play(wrongSound, 1, 1, 2, 0, 1);
        }
    }



    public static void release() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }


}

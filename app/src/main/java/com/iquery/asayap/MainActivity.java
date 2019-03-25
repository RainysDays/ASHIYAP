package com.iquery.asayap;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.media.SoundPool;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    MediaPlayer mp1,mp2;
    Button one;
    SoundPool sp;
    boolean loaded=false;
    private int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.atta);
        view.setOnTouchListener(this);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        sp = new SoundPool(50,AudioManager.STREAM_MUSIC,0);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded=true;
            }
        });
        soundID=sp.load(this,R.raw.ashiap,1);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            AudioManager audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
            float actualVolume = (float)audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float maxVolume = (float)audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float volume = (actualVolume/maxVolume);
            if(loaded){
                sp.play(soundID,volume,volume,1,0,1f);
            }
        }
        return false;
    }
}

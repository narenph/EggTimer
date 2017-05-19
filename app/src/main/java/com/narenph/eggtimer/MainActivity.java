package com.narenph.eggtimer;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    SeekBar seekBar;
    TextView timerTextView;
    Button controllerButton;
    CountDownTimer countDownTimer;
    Boolean counterIsActive = false;

    public void updateTime(int secLeft){


        int minutes = secLeft / 60;
        int seconds = secLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9){

            secondString ="0"+secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);


    }

    public void resetTimer()
    {
        timerTextView.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("START!");
        seekBar.setEnabled(true);
        counterIsActive = false;


    }

    public void bPressed(View view) {

        if (counterIsActive == false) {


            counterIsActive = true;
            seekBar.setEnabled(false);
            controllerButton.setText("RESET!");
            Log.i("Info", "Button Pressed");
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {


                @Override
                public void onTick(long millisUntilFinished) {

                    updateTime((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    timerTextView.setText("0:00");
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mPlayer.start();


                    resetTimer();

                }
            }.start();
        }
        else{

            resetTimer();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        timerTextView= (TextView) findViewById(R.id.timerTextView);
        controllerButton =(Button) findViewById(R.id.controllerButton);

        seekBar.setMax(300);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTime(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}


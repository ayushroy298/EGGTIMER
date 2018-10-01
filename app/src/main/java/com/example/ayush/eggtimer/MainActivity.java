package com.example.ayush.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{


    String time(int n)
    {
        String t;
        int min=n/60,sec=n%60;
        if(min>9)
            t= Integer.toString(min) + " : ";
        else
            t= "0" + Integer.toString(min) + " : ";
        if(sec>9)
            t+= Integer.toString(sec);
        else
            t+= "0" + Integer.toString(sec);
        return t;

    }

    boolean isActive=false;
    SeekBar seekBar;
    TextView textView;
    Button button;
    CountDownTimer countDownTimer;
    public void onClick(View view)
    {

        final MediaPlayer mediaPlayer=MediaPlayer.create(this, R.raw.horn);

        if(!isActive)
        {
            button.setText("Stop!!");
            seekBar.setEnabled(false);
            isActive=true;
            countDownTimer = new CountDownTimer((seekBar.getProgress()*1000) + 100,1000)
            {
                @Override
                public void onTick(long l)
                {
                    textView.setText(time((int)l/1000));
                    seekBar.setProgress((int)l/1000);
                }

                @Override
                public void onFinish()
                {
                    mediaPlayer.start();
                    button.setText("Go!!");
                    isActive=false;
                    seekBar.setEnabled(true);
                    seekBar.setProgress(60);
                    textView.setText(time(60));
                }
            }.start();

        }
        else
        {
            button.setText("Go!!");
            isActive=false;
            seekBar.setEnabled(true);
            seekBar.setProgress(60);
            textView.setText(time(60));
            countDownTimer.cancel();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        seekBar.setMax(60);
        seekBar.setProgress(60);
        textView.setText(time(60));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                textView.setText(time(i));
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

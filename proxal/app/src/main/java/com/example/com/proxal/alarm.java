package com.example.com.proxal;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class alarm extends AppCompatActivity {
    private TextView t0,t1,t2,t3;
    public static      int i=0;
    private Button b0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.alarm1);
        mediaPlayer.start();
        t0=(TextView)findViewById(R.id.t0);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
b0=(Button)findViewById(R.id.b0);
b0.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                        switch (i){
                            case 0:
                            {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        t0.setVisibility(View.INVISIBLE);
                                        t1.setVisibility(View.INVISIBLE);
                                        t2.setVisibility(View.INVISIBLE);
                                        t3.setVisibility(View.INVISIBLE);

                                    }
                                });
                                break;
                            }
                            case 1:
                            { runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {

                                t0.setVisibility(View.VISIBLE);
                              //  t1.setVisibility(View.INVISIBLE);
                                //t2.setVisibility(View.INVISIBLE);
                                t3.setVisibility(View.VISIBLE);    }
                            });
                                break;
                            }
                            case 2:
                            { runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {
                                //t0.setVisibility(View.VISIBLE);
                                t1.setVisibility(View.VISIBLE);
                                t2.setVisibility(View.VISIBLE);
                                //t3.setVisibility(View.VISIBLE);
                                                }
                            }); break;
                            }
                            case 3:
                            { runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {
                                t0.setVisibility(View.INVISIBLE);
                                //t1.setVisibility(View.VISIBLE);
                                //t2.setVisibility(View.VISIBLE);
                                t3.setVisibility(View.INVISIBLE);
                                i=0;
                                                }
                            }); break;

                            }

                        }
              i++;
            }
        }, 500,500);

    }
}

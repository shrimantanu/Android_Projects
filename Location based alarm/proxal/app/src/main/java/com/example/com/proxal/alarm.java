package com.example.com.proxal;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;

import java.util.Timer;
import java.util.TimerTask;

public class alarm extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private TextView t0,t1,t2,t3;
    public static      int i=0;
    private Button b0;
    private GoogleApiClient mClient;
    private Geofencing mGeofencing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.alarm1);
        mediaPlayer.start();
        t0=(TextView)findViewById(R.id.t0);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        mClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, this)
                .build();

        mGeofencing = new Geofencing(this, mClient);
b0=(Button)findViewById(R.id.b0);
b0.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mGeofencing.unRegisterAllGeofences();
        Intent intent = new Intent(getApplicationContext(), log.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        finish();
        mediaPlayer.stop();
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

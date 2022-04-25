package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AlarmActivity extends AppCompatActivity {


    private TextView txView_notAlarm;
    private LinearLayout lout_add_option;
    private Button btn_add;
    private Button btn_option;
    private ListView liView_alarm;
    private Button btn_stopWatch;
    private Button btn_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //init variation
        btn_stopWatch = (Button) findViewById(R.id.btn_stopWatch);
        btn_timer = (Button) findViewById(R.id.btn_timer);

        btn_stopWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmActivity.this, StopWatchActivity.class);
                startActivity(intent);
            }
        });

        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });





    }
}
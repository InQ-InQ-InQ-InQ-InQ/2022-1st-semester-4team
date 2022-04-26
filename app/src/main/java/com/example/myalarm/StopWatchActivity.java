package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StopWatchActivity extends AppCompatActivity {

    private Button btn_alarm;
    private Button btn_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        btn_alarm = (Button) findViewById(R.id.btn_alarm);
        btn_timer = (Button) findViewById(R.id.btn_timer);

        btn_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StopWatchActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });

        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StopWatchActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });
    }
}
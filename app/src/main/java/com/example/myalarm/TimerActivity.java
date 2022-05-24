package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimerActivity extends AppCompatActivity {

    private Button btn_stopWatch;
    private Button btn_alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_acticity);

        btn_alarm = (Button) findViewById(R.id.btn_alarm);
        btn_stopWatch = (Button) findViewById(R.id.btn_stopWatch);

        btn_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimerActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });

        btn_stopWatch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimerActivity.this, StopWatchActivity.class);
                startActivity(intent);
            }
        });
    }
}
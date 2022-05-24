package com.example.myalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class StopWatchActivity extends AppCompatActivity {

    private TextView tv_stopwatch_viewer, tv_record_viewer;
    private Button btn_alarm, btn_timer;
    private Button btn_start_pause_resume, btn_record_init;


    //status
    public static final int INIT = 0;
    public static final int RUN = 1;
    public static final int PAUSE = 2;
    public static int status = INIT;
    private int cnt = 1;
    private long baseTime,pauseTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        tv_stopwatch_viewer = (TextView) findViewById(R.id.tv_stopwatch_viewer);
        btn_alarm = (Button) findViewById(R.id.btn_alarm);
        btn_timer = (Button) findViewById(R.id.btn_timer);
        btn_start_pause_resume = (Button) findViewById(R.id.btn_start_pause_resume);
        btn_record_init = (Button) findViewById(R.id.btn_record_init);
        tv_record_viewer = (TextView) findViewById(R.id.tv_record_viewer);
        tv_record_viewer.setVisibility(View.GONE);


        //clickLister
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

        btn_start_pause_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start_pause_resume.setText("PAUSE");
                btn_start_pause_resume.setBackgroundResource(R.drawable.btn_red);
                //Toast.makeText(StopWatchActivity.this, "스톱워치를 시작합니다", Toast.LENGTH_SHORT).show();
                start_pause_resume();
            }
        });

        btn_record_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                record_init();
            }
        });

    }

    private void start_pause_resume() {
        switch (status){
            case INIT:
                baseTime = SystemClock.elapsedRealtime();
                handler.sendEmptyMessage(0);
                btn_start_pause_resume.setText("PAUSE");
                status = RUN;
                break;
            case RUN:
                handler.removeMessages(0);
                pauseTime = SystemClock.elapsedRealtime();
                btn_start_pause_resume.setText("RESUME");
                btn_record_init.setText("RESET");
                status = PAUSE;
                break;
            case PAUSE:
                long reStart = SystemClock.elapsedRealtime();
                baseTime += (reStart - pauseTime);
                handler.sendEmptyMessage(0);
                btn_start_pause_resume.setText("PAUSE");
                btn_record_init.setText("RECORD");
                status = RUN;
        }

    }

    private void record_init() {
        switch (status) {
            case RUN:
                tv_record_viewer.setVisibility(View.VISIBLE);
                String timeList = tv_record_viewer.getText().toString();
                timeList+= String.format("%2d. %s\n",cnt,getTime());
                tv_record_viewer.setText(timeList);
                cnt++;
                break;
            case PAUSE:
                Toast.makeText(StopWatchActivity.this, "RESET", Toast.LENGTH_SHORT).show();
                tv_stopwatch_viewer.setText("00:00:00");
                //record.setText("");
                baseTime = 0;
                pauseTime = 0;
                cnt = 1;
                status = INIT;
                btn_record_init.setText("RECORD");
                btn_start_pause_resume.setText("START");
                btn_start_pause_resume.setBackgroundResource(R.drawable.btn_blue);
                tv_record_viewer.setVisibility(View.GONE);
                tv_record_viewer.setText("");
                break;
        }
    }

    @NonNull
    private String getTime(){
        long nowTime = SystemClock.elapsedRealtime();
        long overTime = nowTime - baseTime;
        long m = overTime/1000/60;
        long s = (overTime/1000)%60;
        long ms = overTime % 100000 / 10 % 100;
        String recTime = String.format("%02d:%02d:%02d",m,s,ms);
        return recTime;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            tv_stopwatch_viewer.setText(getTime());
            handler.sendEmptyMessage(0);
        }
    };
}
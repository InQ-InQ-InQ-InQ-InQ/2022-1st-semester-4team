package com.example.myalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
<<<<<<< HEAD
=======
import android.graphics.Color;
import android.graphics.drawable.Drawable;
>>>>>>> c2ddd9776bf133f6e74fbc680a35cd7c06104f0e
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

<<<<<<< HEAD
    private TextView tv_stopwatch_viewer;
    private Button btn_alarm, btn_timer;
    private Button btn_stopwatch_start, btn_stopwatch_record;
=======
    private TextView tv_stopwatch_viewer, tv_record_viewer;
    private Button btn_alarm, btn_timer;
    private Button btn_start_pause_resume, btn_record_init;

>>>>>>> c2ddd9776bf133f6e74fbc680a35cd7c06104f0e

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
<<<<<<< HEAD
        btn_stopwatch_start = (Button) findViewById(R.id.btn_stopWatch_start);
        btn_stopwatch_record = (Button) findViewById(R.id.btn_stopWatch_record);

=======
        btn_start_pause_resume = (Button) findViewById(R.id.btn_start_pause_resume);
        btn_record_init = (Button) findViewById(R.id.btn_record_init);
        tv_record_viewer = (TextView) findViewById(R.id.tv_record_viewer);
        tv_record_viewer.setVisibility(View.GONE);


        //clickLister
>>>>>>> c2ddd9776bf133f6e74fbc680a35cd7c06104f0e
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

<<<<<<< HEAD
        btn_stopwatch_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
                Toast.makeText(StopWatchActivity.this, "스톱워치를 시작합니다", Toast.LENGTH_SHORT).show();
                staButton();
=======
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
>>>>>>> c2ddd9776bf133f6e74fbc680a35cd7c06104f0e
            }
        });

    }

<<<<<<< HEAD
    private void staButton(){
        switch (status){
            case INIT:
                //어플리케이션이 실행되고 나서 실제로 경과된 시간...
                baseTime = SystemClock.elapsedRealtime();
                System.out.println("1234");
                //핸들러 실행
                handler.sendEmptyMessage(0);
                btn_stopwatch_start.setText("멈춤");
                //btn_stopwatch_record.setEnabled(true);

                //상태 변환
                status = RUN;
                break;
            case RUN:
                //핸들러 정지
                handler.removeMessages(0);

                //정지 시간 체크
                pauseTime = SystemClock.elapsedRealtime();

                btn_stopwatch_start.setText("시작");
                btn_stopwatch_record.setText("리셋");

                //상태변환
=======
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
>>>>>>> c2ddd9776bf133f6e74fbc680a35cd7c06104f0e
                status = PAUSE;
                break;
            case PAUSE:
                long reStart = SystemClock.elapsedRealtime();
                baseTime += (reStart - pauseTime);
<<<<<<< HEAD

                handler.sendEmptyMessage(0);

                btn_stopwatch_start.setText("멈춤");
                btn_stopwatch_record.setText("기록");

=======
                handler.sendEmptyMessage(0);
                btn_start_pause_resume.setText("PAUSE");
                btn_record_init.setText("RECORD");
>>>>>>> c2ddd9776bf133f6e74fbc680a35cd7c06104f0e
                status = RUN;
        }

    }

<<<<<<< HEAD
=======
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

>>>>>>> c2ddd9776bf133f6e74fbc680a35cd7c06104f0e
    @NonNull
    private String getTime(){
        long nowTime = SystemClock.elapsedRealtime();
        long overTime = nowTime - baseTime;
        long m = overTime/1000/60;
        long s = (overTime/1000)%60;
<<<<<<< HEAD
        long ms = overTime % 1000;
        String recTime = String.format("%02d:%02d:%03d",m,s,ms);
        return recTime;
    }
    Handler handler = new Handler(){

=======
        long ms = overTime % 100000 / 10 % 100;
        String recTime = String.format("%02d:%02d:%02d",m,s,ms);
        return recTime;
    }
    Handler handler = new Handler(){
>>>>>>> c2ddd9776bf133f6e74fbc680a35cd7c06104f0e
        @Override
        public void handleMessage(@NonNull Message msg) {
            tv_stopwatch_viewer.setText(getTime());
            handler.sendEmptyMessage(0);
        }
    };
}
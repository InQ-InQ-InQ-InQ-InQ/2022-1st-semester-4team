package com.example.myalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

    private TextView tv_stopwatch_viewer;
    private Button btn_alarm, btn_timer;
    private Button btn_stopwatch_start, btn_stopwatch_record;

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
        btn_stopwatch_start = (Button) findViewById(R.id.btn_stopWatch_start);
        btn_stopwatch_record = (Button) findViewById(R.id.btn_stopWatch_record);

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

        btn_stopwatch_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
                Toast.makeText(StopWatchActivity.this, "스톱워치를 시작합니다", Toast.LENGTH_SHORT).show();
                staButton();
            }
        });

    }

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
                status = PAUSE;
                break;
            case PAUSE:
                long reStart = SystemClock.elapsedRealtime();
                baseTime += (reStart - pauseTime);

                handler.sendEmptyMessage(0);

                btn_stopwatch_start.setText("멈춤");
                btn_stopwatch_record.setText("기록");

                status = RUN;
        }

    }

    @NonNull
    private String getTime(){
        long nowTime = SystemClock.elapsedRealtime();
        long overTime = nowTime - baseTime;
        long m = overTime/1000/60;
        long s = (overTime/1000)%60;
        long ms = overTime % 1000;
        String recTime = String.format("%02d:%02d:%03d",m,s,ms);
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
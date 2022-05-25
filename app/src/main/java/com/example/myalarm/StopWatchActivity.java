package com.example.myalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class StopWatchActivity extends AppCompatActivity {

    private TextView tv_stopwatch_viewer;
    private TextView tv_interval, tv_interval_record, tv_course_record;
    private ScrollView sv_record_viewer_bottom;
    private Button btn_alarm, btn_timer;
    private Button btn_start_pause_resume, btn_record_init;
    private LinearLayout lout_record_viewer_bottom, lout_record_viewer_top;

    //status
    public static final int INIT = 0;
    public static final int RUN = 1;
    public static final int PAUSE = 2;
    public static int status = INIT;
    private int cnt = 1;
    private long baseTime,pauseTime;
    private long interval;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        interval = 1;
        sv_record_viewer_bottom = (ScrollView) findViewById(R.id.sv_record_viewer_bottom);
        tv_interval = (TextView) findViewById(R.id.tv_interval);
        tv_interval_record = (TextView) findViewById(R.id.tv_interval_record);
        tv_course_record = (TextView) findViewById(R.id.tv_course_record);
        tv_stopwatch_viewer = (TextView) findViewById(R.id.tv_stopwatch_viewer);
        btn_alarm = (Button) findViewById(R.id.btn_alarm);
        btn_timer = (Button) findViewById(R.id.btn_timer);
        btn_start_pause_resume = (Button) findViewById(R.id.btn_start_pause_resume);
        btn_record_init = (Button) findViewById(R.id.btn_record_init);
        lout_record_viewer_top = (LinearLayout) findViewById(R.id.lout_record_viewer_top);
        lout_record_viewer_bottom = (LinearLayout) findViewById(R.id.lout_record_viewer_bottom);
        lout_record_viewer_top.setVisibility(View.GONE);
        sv_record_viewer_bottom.setVisibility(View.GONE);


        /*
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
        */

        btn_start_pause_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_start_pause_resume.setText("중지");
                btn_start_pause_resume.setBackgroundResource(R.drawable.btn_red);
                btn_record_init.setTextColor(Color.parseColor("#000000"));
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
                btn_start_pause_resume.setText("중지");
                status = RUN;
                break;
            case RUN:
                handler.removeMessages(0);
                pauseTime = SystemClock.elapsedRealtime();
                btn_start_pause_resume.setText("계속");
                btn_start_pause_resume.setBackgroundResource(R.drawable.btn_blue);
                btn_record_init.setText("초기화");
                status = PAUSE;
                break;
            case PAUSE:
                long reStart = SystemClock.elapsedRealtime();
                baseTime += (reStart - pauseTime);
                handler.sendEmptyMessage(0);
                btn_start_pause_resume.setText("중지");
                btn_record_init.setText("구간 기록");
                status = RUN;
        }

    }

    private void record_init() {
        switch (status) {
            case RUN:
                //set record viewer visible
                sv_record_viewer_bottom.setVisibility(View.VISIBLE);
                lout_record_viewer_top.setVisibility(View.VISIBLE);

                //set interval
                String intervalList = tv_interval.getText().toString();
                intervalList = interval+"\n\n"+intervalList;
                tv_interval.setText(intervalList);
                interval++;

                //set course record
                String courseRecordList = tv_course_record.getText().toString();
                courseRecordList = String.format("%s\n\n",getTime()) + courseRecordList;
                tv_course_record.setText(courseRecordList);
                cnt++;

                //set interval record
                StringTokenizer st;
                String intervalRecordList = tv_interval_record.getText().toString();
                String LatestInterval = "";
                if(courseRecordList.length()>12) {
                    LatestInterval = courseRecordList.substring(10,18);
                    System.out.println(LatestInterval);
                }
                else {
                    LatestInterval = courseRecordList.substring(0, 8);
                }
                //Toast.makeText(StopWatchActivity.this, LatestInterval, Toast.LENGTH_SHORT).show();
                String currentRecord = courseRecordList.substring(0, 8);
                if(LatestInterval.equals(currentRecord)) {
                    LatestInterval = "00:00:00";
                }

                st = new StringTokenizer(currentRecord, ":");
                long CMM = Integer.parseInt(st.nextToken());
                long CSS = Integer.parseInt(st.nextToken());
                long CMS = Integer.parseInt(st.nextToken());
                st = new StringTokenizer(LatestInterval, ":");
                long LMM = Integer.parseInt(st.nextToken());
                long LSS = Integer.parseInt(st.nextToken());
                long LMS = Integer.parseInt(st.nextToken());
                long DMM = CMM - LMM;
                long DSS = CSS - LSS;
                long DMS = CMS - LMS;
                if(DMS < 0) {
                    DMS += 100;
                    DSS--;
                }
                if(DSS < 0) {
                    DSS += 60;
                    DMM--;
                }
                String intervalRecord = String.format("%02d:%02d:%02d",DMM,DSS,DMS);
                tv_interval_record.setText(intervalRecord+"\n\n"+intervalRecordList);
                break;

            case PAUSE:
                //Toast.makeText(StopWatchActivity.this, "RESET", Toast.LENGTH_SHORT).show();
                tv_stopwatch_viewer.setText("00:00:00");
                baseTime = 0;
                pauseTime = 0;
                cnt = 1;
                interval = 1;
                status = INIT;
                btn_record_init.setText("구간 기록");
                btn_start_pause_resume.setText("시작");
                btn_start_pause_resume.setBackgroundResource(R.drawable.btn_blue);
                btn_record_init.setTextColor(Color.parseColor("#6C6C6C"));
                sv_record_viewer_bottom.setVisibility(View.GONE);
                lout_record_viewer_top.setVisibility(View.GONE);
                tv_course_record.setText("");
                tv_interval.setText("");
                tv_interval_record.setText("");
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
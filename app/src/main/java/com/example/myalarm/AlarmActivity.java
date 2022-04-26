package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myalarm.repository.AlarmMemoryRepository;
import com.example.myalarm.repository.AlarmRepository;

import java.util.List;

public class AlarmActivity extends AppCompatActivity {

    private AlarmRepository repository;

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

        repository = new AlarmMemoryRepository();

        //init variation
        btn_add = (Button) findViewById(R.id.btn_addAlarm);
        btn_stopWatch = (Button) findViewById(R.id.btn_stopWatch);
        btn_timer = (Button) findViewById(R.id.btn_timer);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmActivity.this, AddAlarmActivity.class);
                startActivity(intent);
            }
        });


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

        //ListView
        liView_alarm = (ListView) findViewById(R.id.lv_alarm);
        List<Alarm> list = repository.getAlarmList();
        ArrayAdapter<Alarm> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        liView_alarm.setAdapter(adapter);



    }
}
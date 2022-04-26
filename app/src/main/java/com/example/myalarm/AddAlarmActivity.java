package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myalarm.repository.AlarmMemoryRepository;
import com.example.myalarm.repository.AlarmRepository;

public class AddAlarmActivity extends AppCompatActivity {

    private Button btn_mon;
    private Button btn_tue;
    private Button btn_wed;
    private Button btn_thu;
    private Button btn_fri;
    private Button btn_sat;
    private Button btn_sun;
    private Button btn_create;

    private boolean dateMod, dayMod;
    private int hour, minute;
    private boolean [] dayArray;
    private String name;
    private boolean sound, vibe, recall;
    private AlarmRepository repository = new AlarmMemoryRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);





        dayArray = new boolean [7];
        int myColor = ContextCompat.getColor(getApplicationContext(), R.color.purple_500);

        //day button
        btn_mon = (Button) findViewById(R.id.btn_mon);
        btn_tue = (Button) findViewById(R.id.btn_tue);
        btn_wed = (Button) findViewById(R.id.btn_wed);
        btn_thu = (Button) findViewById(R.id.btn_thu);
        btn_fri = (Button) findViewById(R.id.btn_fri);
        btn_sat = (Button) findViewById(R.id.btn_sat);
        btn_sun = (Button) findViewById(R.id.btn_sun);

        btn_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dayArray[0]) {
                    dayArray[0] = true;
                    btn_mon.setTextColor(myColor);
                }
                else {
                    dayArray[0] = false;
                    btn_mon.setTextColor(Color.BLACK);
                }
            }
        });

        //create button
        btn_create = (Button) findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateMod = true;
                dayMod = true;
                hour = 0;
                minute = 0;
                dayArray = new boolean[7];
                name = "test";
                sound = true;
                vibe = true;
                recall = true;
                Alarm alarm = new Alarm(dateMod, dayMod, hour, minute, dayArray, name, sound, vibe, recall);
                repository.createAlarm(alarm);
                Intent intent = new Intent(AddAlarmActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });
    }
}
package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView txView_notAlarm;
    private LinearLayout lout_add_option;
    private Button btn_add;
    private Button btn_option;
    private ListView liView_alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init variation
        txView_notAlarm = (TextView) findViewById(R.id.txView_notAlarm);
        lout_add_option = (LinearLayout) findViewById(R.id.lout_add_Option);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_option = (Button) findViewById(R.id.btn_option);
        liView_alarm = (ListView) findViewById(R.id.liView_alarm);



    }
}
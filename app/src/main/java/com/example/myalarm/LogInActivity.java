package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_password;
    private Button btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);


        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_id.getText().toString().equals("myAlarm")) {
                    Toast.makeText(getApplicationContext(), "hello world", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(LogInActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });




    }
}
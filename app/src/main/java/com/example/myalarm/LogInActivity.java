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
    private Button btn_signUp;
    private Button btn_rememberId;
    private Button btn_forgetId;
    private Button btn_forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        btn_signUp = (Button) findViewById(R.id.btn_signUp);
        btn_rememberId = (Button) findViewById(R.id.btn_rememberId);
        btn_forgetId = (Button) findViewById(R.id.btn_forgetId);
        btn_forgetPassword = (Button) findViewById(R.id.btn_forgetPassword);

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_id.getText().toString().equals("myAlarm")) {
                    Toast.makeText(getApplicationContext(), "hello world", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(LogInActivity.this, StopWatchActivity.class);
                startActivity(intent);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });






    }
}
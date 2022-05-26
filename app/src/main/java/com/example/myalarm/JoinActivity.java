package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    private Button btn_join;
    private EditText et_id;
    private EditText et_password1;
    private EditText et_password2;
    private EditText et_name;
    private EditText et_email;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        
        btn_join = (Button) findViewById(R.id.btn_join);
        et_id = (EditText) findViewById(R.id.et_join_id);
        et_password1 = (EditText) findViewById(R.id.et_join_password1);
        et_password2 = (EditText) findViewById(R.id.et_join_password2);
        et_name = (EditText) findViewById(R.id.et_join_name);
        et_email = (EditText) findViewById(R.id.et_join_email);


        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_password2.getText().toString().equals(et_password1.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(JoinActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
package edu.android.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // 멤버변수
    ImageButton btn;
    EditText edit_ID,edit_PW;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (ImageButton) findViewById(R.id.imagebtn_login_next);
        intent = new Intent(this,MatchActivity.class);
        edit_ID = (EditText) findViewById(R.id.edit_ID);
        edit_PW = (EditText) findViewById(R.id.edit_PW);

        btn.setOnClickListener(this);

    } // end onCreate()

    // 로그인 버튼
    @Override
    public void onClick(View view) {
        startActivity(intent);
        finish();
    } // end onClick()
} // end class LoginActivity

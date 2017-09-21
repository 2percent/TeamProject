package edu.android.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // 멤버변수
    private ImageButton btn_next;
    private EditText edit_ID,edit_PW;

    public static final String KEY_MEMBER = "ID,PW";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_next = (ImageButton) findViewById(R.id.btn_next);
        edit_ID = (EditText) findViewById(R.id.edit_ID);
        edit_PW = (EditText) findViewById(R.id.edit_PW);

        btn_next.setOnClickListener(this);

    } // end onCreate()

    // 로그인 버튼 눌렀을 때 matchActivity로 id 와 pw 값을 가지고 감.
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this,MatchActivity.class);
        String[] member = {
                edit_ID.getText().toString(),
                edit_PW.getText().toString()
        };

        intent.putExtra(KEY_MEMBER,member);
        startActivity(intent);
        finish();
    } // end onClick()
} // end class LoginActivity

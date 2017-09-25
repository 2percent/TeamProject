package edu.android.teamproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "edu.android";
    private static final String MEMBER = "member";

    // 멤버변수
    ImageButton btn;
    EditText edit_ID,edit_PW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (ImageButton) findViewById(R.id.imagebtn_login_next);

        edit_ID = (EditText) findViewById(R.id.edit_ID);
        edit_PW = (EditText) findViewById(R.id.edit_PW);

        btn.setOnClickListener(this);

    } // end onCreate()

    // 로그인 버튼
    @Override
    public void onClick(View view) {
        String id = edit_ID.getText().toString();
        DiaryLab dao = DiaryLab.getInstance(this);
        dao.isSelectAll(id);

    } // end onClick()

    public void idResult(boolean bool){
        String id = edit_ID.getText().toString();
        String pw = edit_PW.getText().toString();

        SharedPreferences pref = getSharedPreferences("id", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("id", id);
        edit.commit();

        if(bool){
            Toast.makeText(this, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
        }else {
            String[] member = {id, pw};
            Intent intent = new Intent(this, MatchActivity.class);
            intent.putExtra(MEMBER, member);
            startActivity(intent);
            finish();
        }
    }

} // end class LoginActivity




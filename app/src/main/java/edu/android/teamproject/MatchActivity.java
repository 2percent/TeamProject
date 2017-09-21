package edu.android.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import static edu.android.teamproject.LoginActivity.KEY_MEMBER;

public class MatchActivity extends AppCompatActivity implements View.OnClickListener {

    //멤버변수
    private ImageButton btn_calender,
                btn_link;
    private EditText edit_my_phone,
             edit_your_phone;
    private TextView text_start_day;

    // Controller 객체 DiaryLab 생성
    DiaryLab dao = DiaryLab.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        btn_calender = (ImageButton) findViewById(R.id.btn_calender);
        btn_link = (ImageButton) findViewById(R.id.btn_link);
        edit_my_phone = (EditText) findViewById(R.id.edit_my_phone);
        edit_your_phone = (EditText) findViewById(R.id.edit_your_phone);
        text_start_day = (TextView) findViewById(R.id.text_start_day);

        btn_calender.setOnClickListener(this);
        btn_link.setOnClickListener(this);

    } // end onCreate()

    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        String[] member = intent.getStringArrayExtra(KEY_MEMBER);

        ImageButton btn = (ImageButton)view;
        // 다음 버튼을 클릭 했을 때
        if(btn == btn_link){

            String id = member[0]; // id 값
            String pw = member[1]; // pw 값
            String my = edit_my_phone.getText().toString(); // 내폰번
            String your = edit_your_phone.getText().toString(); // 상대폰번
            String startday = text_start_day.getText().toString(); // 사귄 날짜
            ModelMember m = new ModelMember(id,pw,my,your,startday); // 가져온 값 모델에 담아줌.

//            dao.insertMember(m);

        }// 캘린더 버튼을 클릭 했을 때
        else if(btn == btn_calender){

        }// error
        else{
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }
} // end class MatchActivity

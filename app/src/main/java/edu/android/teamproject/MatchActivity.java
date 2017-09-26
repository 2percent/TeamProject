package edu.android.teamproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MatchActivity extends AppCompatActivity implements View.OnClickListener,DateDialogFragment.DateSelectListener {

    private static final String MEMBER = "member";
    private static final String TAG = "edu.android";
    //멤버변수
    private ImageButton imagebtn_match_calender,
            imagebtn_match_link;
    private EditText edit_match_my_phone,
            edit_match_your_phone;
    private TextView text_match_start_day;

    // Controller 객체 DiaryLab 생성
    DiaryLab dao = DiaryLab.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        imagebtn_match_calender = (ImageButton) findViewById(R.id.imagebtn_match_calender);
        imagebtn_match_link = (ImageButton) findViewById(R.id.imagebtn_match_link);
        edit_match_my_phone = (EditText) findViewById(R.id.edit_match_my_phone);
        edit_match_your_phone = (EditText) findViewById(R.id.edit_match_your_phone);
        text_match_start_day = (TextView) findViewById(R.id.text_match_start_day);

        imagebtn_match_calender.setOnClickListener(this);
        imagebtn_match_link.setOnClickListener(this);

    } // end onCreate()

    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        String[] member = intent.getStringArrayExtra(MEMBER);

        ImageButton btn = (ImageButton) view;
        // 다음 버튼을 클릭 했을 때
        if (btn == imagebtn_match_link) {

            String id = member[0]; // id 값
            String pw = member[1]; // pw 값
            String my = edit_match_my_phone.getText().toString().trim(); // 내폰번
            String your = edit_match_your_phone.getText().toString().trim(); // 상대폰번
            String startday = text_match_start_day.getText().toString().trim(); // 사귄 날짜

            SharedPreferences pref = getSharedPreferences("id", MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            edit.putString("id", id);
            edit.putString("pw", pw);
            edit.putString("my", my);
            edit.putString("your", your);
            edit.putString("startday",startday);

            edit.commit();


            if(my.equals("")){
                edit_match_my_phone.setHintTextColor(getResources().getColor(R.color.colorAccent));
                edit_match_my_phone.setHint("내 전화번호를 입력하세요");
            }else if(your.equals("")){
                edit_match_your_phone.setHintTextColor(getResources().getColor(R.color.colorAccent));
                edit_match_your_phone.setHint("상대방 전화번호를 입력하세요");
            }else if(startday.equals("")){
                text_match_start_day.setHintTextColor(getResources().getColor(R.color.colorAccent));
                text_match_start_day.setHint("연애시작 날짜를 적어주세요.");
            }else{
                ModelMember m = new ModelMember(id,pw,my,your,startday); // 가져온 값 모델에 담아줌.

                dao.insertMember(m);

                Intent intent2 = new Intent(this, MainActivity.class);
                intent2.putExtra("id",id);
                startActivity(intent2);
                finish();
            }

        }// 캘린더 버튼을 클릭 했을 때
        else if(btn == imagebtn_match_calender){
            DateDialogFragment dlg = new DateDialogFragment();
            dlg.show(getSupportFragmentManager(), "date_picker_dlg");
        }// error
        else{
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void dateSelected(int year, int month, int day) {
        text_match_start_day.setText(year+"/"+(month+1)+"/"+day);
    }
} // end class MatchActivity

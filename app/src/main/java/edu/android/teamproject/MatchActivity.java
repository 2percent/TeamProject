package edu.android.teamproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    //멤버변수
    ImageButton btn_calender,btn_link;
    EditText edit_my_phone,edit_your_phone;
    TextView text_start_day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        btn_calender = (ImageButton) findViewById(R.id.btn_calender);
        btn_link = (ImageButton) findViewById(R.id.btn_link);
        edit_my_phone = (EditText) findViewById(R.id.edit_my_phone);
        edit_your_phone = (EditText) findViewById(R.id.edit_your_phone);
        text_start_day = (TextView) findViewById(R.id.text_start_day);



    } // end onCreate()

} // end class MatchActivity

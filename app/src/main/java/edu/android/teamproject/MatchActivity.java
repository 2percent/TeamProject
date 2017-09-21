package edu.android.teamproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    //멤버변수
    ImageButton btn;
    EditText et;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        btn = (ImageButton) findViewById(R.id.btn_calender);
        btn = (ImageButton) findViewById(R.id.btn_link);
        et = (EditText) findViewById(R.id.edit_my_phone);
        et = (EditText) findViewById(R.id.edit_your_phone);
        tv = (TextView) findViewById(R.id.text_start_day);


    } // end onCreate()

} // end class MatchActivity

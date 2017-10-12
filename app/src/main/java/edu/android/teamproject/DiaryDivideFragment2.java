package edu.android.teamproject;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryDivideFragment2 extends Fragment {

    private static final String TAG = "edu.android";
    private TextView text_diary_divide_receiveday        // 일기 보여줄 날짜 (receivedayDate)
            , text_diary_divide_weather           // 날씨 (weather)
            , text_diary_divide_kimozzi           // 기분(kimozzi)
            , text_diary_divide_writeday; // 일기를 쓴 날짜(sendDate)

    private ImageView image_diary_divide_picture; // 이미지 (fileName)
    private TextView image_diary_divide_diary_contents; // 일기내용(Content(color+font+size))
    private Spinner diary_divide_spinner;
    public Spinner getDiary_divide_spinner() {
        return diary_divide_spinner;
    }

    private ArrayAdapter<String> adapter;

    public DiaryDivideFragment2() {}

    private DiaryFragment diaryFragment;

    public void setDiaryFragment(DiaryFragment diaryFragment) {
        this.diaryFragment = diaryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_diary_dvide_fragment2, container, false);

        text_diary_divide_receiveday = view.findViewById(R.id.text_diary_divide_receiveday2);  // 일기 도착 날
        text_diary_divide_weather = view.findViewById(R.id.text_diary_divide_weather2);         // 날씨
        text_diary_divide_kimozzi = view.findViewById(R.id.text_diary_divide_kimozzi2);         // 기분
        text_diary_divide_writeday = view.findViewById(R.id.text_diary_divide_writeday2); // 일기 쓴 날짜
        image_diary_divide_picture = view.findViewById(R.id.image_diary_divide_picture2);               // 사진
        image_diary_divide_diary_contents = view.findViewById(R.id.image_diary_divide_diary_contents2); // 일기 내용
        diary_divide_spinner = view.findViewById(R.id.diary_divide_spinner2);

        return view;
    }


}

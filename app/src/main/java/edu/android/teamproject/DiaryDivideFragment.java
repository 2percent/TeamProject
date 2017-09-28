package edu.android.teamproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryDivideFragment extends Fragment {

    private static final String TAG = "edu.android";
    private TextView text_diary_divide_receiveday        // 일기 보여줄 날짜 (receivedayDate)
            , text_diary_divide_weather           // 날씨 (weather)
            , text_diary_divide_kimozzi           // 기분(kimozzi)
            , text_diary_divide_writeday_title; // 일기를 쓴 날짜(sendDate)

    private ImageView image_diary_divide_picture; // 이미지 (fileName)
    private TextView image_diary_divide_diary_contents; // 일기내용(Content(color+font+size))

    public DiaryDivideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_diary_divide, container, false);

        text_diary_divide_receiveday = view.findViewById(R.id.text_diary_divide_receiveday);
        text_diary_divide_weather = view.findViewById(R.id.text_diary_divide_weather);
        text_diary_divide_kimozzi = view.findViewById(R.id.text_diary_divide_kimozzi);
        text_diary_divide_writeday_title = view.findViewById(R.id.text_diary_divide_writeday_title);
        image_diary_divide_picture = view.findViewById(R.id.image_diary_divide_picture);
        image_diary_divide_diary_contents = view.findViewById(R.id.image_diary_divide_diary_contents);

        showMyDiary();

        return view;
    }


    private void showMyDiary() {

        DiaryLab dao = DiaryLab.getInstance(this);

        String my = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getString("my","0");
        String your = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getString("your","0");
        String key = String.valueOf(getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getInt("key",0));

        dao.selectMyDiary(my,your,key);
    }

    private ArrayList<ModelDiary> list = new ArrayList<>();
    public void getlistDiary(boolean b, ArrayList<ModelDiary> list) {
            if(b == false){
                Toast.makeText(getContext(), "첫번째 일기를 작성해봐요", Toast.LENGTH_SHORT).show();
            }else{
                    this.list.add(list.get(0));

            }
        }

}

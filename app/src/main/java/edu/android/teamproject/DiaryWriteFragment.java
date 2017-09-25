package edu.android.teamproject;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryWriteFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "edu.android";

    EditText edit_diary_write_weather,
            edit_diary_write_kimozzi,
            edit_diary_write_content;
    ImageButton imagebtn_diary_write_sendTo;

    public DiaryWriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary_write, container, false);

        edit_diary_write_weather = view.findViewById(R.id.edit_diary_write_weather);
        edit_diary_write_kimozzi = view.findViewById(R.id.edit_diary_write_kimozzi);
        edit_diary_write_content = view.findViewById(R.id.edit_diary_write_content);
        //edittext 자동 줄바꿈
        edit_diary_write_content.setHorizontallyScrolling(false);

        imagebtn_diary_write_sendTo = view.findViewById(R.id.imagebtn_diary_write_sendto);
        imagebtn_diary_write_sendTo.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == imagebtn_diary_write_sendTo){
            Log.i(TAG, "edit_diary_write_weather : " + edit_diary_write_weather.getText());
            Log.i(TAG, "edit_diary_write_kimozzi : " + edit_diary_write_kimozzi.getText());
            Log.i(TAG, "edit_diary_write_content : " + edit_diary_write_content.getText());
        }
    }
}

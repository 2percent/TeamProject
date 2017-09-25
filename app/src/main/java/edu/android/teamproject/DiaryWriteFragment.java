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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class DWFragment extends Fragment implements  View.OnClickListener, Serializable{
    private static final int SELECT_IMAGE = 100;
    private ImageButton imagebtn_emoticon,imagebtn_diary_write_add_picture  ;
    private TextView textView;
    private  Bitmap image_bitmap;
public class DiaryWriteFragment extends Fragment {

    TextView textView;
    EditText edit_diary_write;
    private Uri imageUri;

    public DiaryWriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary_write, container, false);

        edit_diary_write = (EditText)view.findViewById(R.id.edit_diary_write);
        //edittext 자동 줄바꿈
        edit_diary_write.setHorizontallyScrolling(false);

        return view;
    }

}

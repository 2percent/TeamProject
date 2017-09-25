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

    private Uri imageUri;

    public DWFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary_write, container, false);

        imagebtn_emoticon = (ImageButton)view.findViewById(R.id.imagebtn_emoticon);
        imagebtn_emoticon.setOnClickListener(this);

        imagebtn_diary_write_add_picture = view.findViewById(R.id.imagebtn_diary_write_add_picture);

        imagebtn_diary_write_add_picture.setOnClickListener(this);

        return view;
    }

    

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {


                    imageUri = data.getData();


                    image_bitmap = MediaStore.Images.Media.getBitmap(getContext ().getContentResolver()
                            , imageUri);

                    imagebtn_diary_write_add_picture.setImageBitmap(image_bitmap);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        
        ImageButton btn = (ImageButton)view;
        if(btn == imagebtn_diary_write_add_picture){

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE);

        }else if(btn == imagebtn_emoticon){
            Intent intent = new Intent(getContext(), GoodPaintBoardActivity.class);
            intent.putExtra("image_uri", imageUri);

            startActivity(intent);
        }
        
        
    }


}

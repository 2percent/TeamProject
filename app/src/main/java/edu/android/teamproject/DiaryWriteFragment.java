package edu.android.teamproject;


import android.app.Activity;
import android.content.ContentResolver;
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
import android.widget.ImageButton;
import android.widget.ImageView;

import java.net.URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryWriteFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "edu.android";
    private static final int SELECT_IMAGE = 100;

    EditText edit_diary_write_weather,
            edit_diary_write_kimozzi,
            edit_diary_write_content;
    ImageButton imagebtn_diary_write_sendTo;
    ImageView image_diary_write_add_picture;
    ImageButton imagebtn_edit;
    Uri image_uri ;
    private Bitmap image_bitmap;

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
        image_diary_write_add_picture = view.findViewById(R.id.image_diary_write_add_picture);
        image_diary_write_add_picture.setOnClickListener(this);
        image_uri = null;
        imagebtn_edit = view.findViewById(R.id.imagebtn_edit);
        imagebtn_edit.setOnClickListener(this);
        imagebtn_diary_write_sendTo = view.findViewById(R.id.imagebtn_diary_write_sendto);
        imagebtn_diary_write_sendTo.setOnClickListener(this);



        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());
                    image_uri = data.getData();
                    //이미지 데이터를 비트맵으로 받아온다.
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),
                            image_uri);

                    //배치해놓은 ImageView에 set
                    image_diary_write_add_picture.setImageBitmap(image_bitmap);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onClick(View view) {
        if(view == imagebtn_diary_write_sendTo){
            Log.i(TAG, "edit_diary_write_weather : " + edit_diary_write_weather.getText());
            Log.i(TAG, "edit_diary_write_kimozzi : " + edit_diary_write_kimozzi.getText());
            Log.i(TAG, "edit_diary_write_content : " + edit_diary_write_content.getText());
        }else if(view == imagebtn_edit){
            if(image_uri != null){
                Intent intent = new Intent(getContext(), GoodPaintBoardActivity.class);
                intent.putExtra("image_uri",image_uri);
                startActivity(intent);
            }else{
                Intent intent = new Intent(getContext(), GoodPaintBoardActivity2.class);
                startActivity(intent);
            }

        }else if(view == image_diary_write_add_picture){

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE);

        }
    }


}

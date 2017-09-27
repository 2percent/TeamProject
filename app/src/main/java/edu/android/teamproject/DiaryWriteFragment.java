package edu.android.teamproject;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.net.URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryWriteFragment extends Fragment implements View.OnClickListener, DateDialogFragment.DateSelectListener{

    private static final String TAG = "edu.android";
    private static final int SELECT_IMAGE = 100;

    EditText edit_diary_write_weather,
            edit_diary_write_kimozzi,
            edit_diary_write_content;
    TextView text_diary_write_receiveday;
    ImageButton imagebtn_diary_write_sendTo;
    ImageView image_diary_write_add_picture;
    ImageButton imagebtn_edit;
    ImageButton imagebtn_diary_write_calender;
    Uri image_uri ;
    private Bitmap image_bitmap;
    private Bitmap image_return;
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
        imagebtn_diary_write_calender = view.findViewById(R.id.imagebtn_diary_write_calender);
        imagebtn_diary_write_calender.setOnClickListener(this);

        text_diary_write_receiveday = view.findViewById(R.id.text_diary_write_receiveday);

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
        }else if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){

                try {
                    if (data != null) {
                        String fileName = data.getStringExtra("image");
                        Log.i(TAG, "onActivityResult:fileName=" + fileName);
                        File imgFile = new  File(fileName);

                        if(imgFile.exists()){

                            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                            image_diary_write_add_picture.setImageBitmap(myBitmap);



                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if (requestCode == 2){
            if(resultCode == Activity.RESULT_OK){

                try {
                    if (data != null) {
                        String fileName = data.getStringExtra("image");
                        Log.i(TAG, "onActivityResult:fileName=" + fileName);
                        File imgFile = new  File(fileName);

                        if(imgFile.exists()){

                            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                            image_diary_write_add_picture.setImageBitmap(myBitmap);



                        }
                    }


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
                startActivityForResult(intent,1);



            }else{
                Intent intent = new Intent(getContext(), GoodPaintBoardActivity2.class);
                startActivityForResult(intent,2);
            }

        }else if(view == image_diary_write_add_picture){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE);
        }else if(view == imagebtn_diary_write_calender){
            DateDialogFragment dlg = new DateDialogFragment(this);
            dlg.show(getFragmentManager(),"DiaryWrite_Fragment_Date_dlg");
        }
    }

    @Override
    public void dateSelected(int year, int month, int dayOfMonth) {
        text_diary_write_receiveday.setText(year + " / " + (1+month) + " / " + dayOfMonth);
    }
}

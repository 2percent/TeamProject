package edu.android.teamproject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryWriteFragment extends Fragment implements View.OnClickListener, DateDialogFragment.DateSelectListener
        , AdapterView.OnItemSelectedListener {

    private static final String TAG = "edu.android";
    private static final int SELECT_IMAGE = 100;

    EditText edit_diary_write_weather,
            edit_diary_write_kimozzi,
            edit_diary_write_content;
    TextView text_diary_write_receiveday;
    ImageButton imagebtn_diary_write_sendTo;
    ImageView image_diary_write_add_picture;
    ImageButton add_picture, edit_picture;
    ImageButton imagebtn_diary_write_calender;
    Uri image_uri;
    private Bitmap image_bitmap;

    Spinner spinner_diary_write_size, spinner_diary_write_font, spinner_diary_write_color;

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
        add_picture = view.findViewById(R.id.imagebtn_diary_write_add_picture);
        add_picture.setOnClickListener(this);
        image_uri = null;
        edit_picture = view.findViewById(R.id.imagebtn_diary_write_edit_picture);
        edit_picture.setOnClickListener(this);
        imagebtn_diary_write_sendTo = view.findViewById(R.id.imagebtn_diary_write_sendto);
        imagebtn_diary_write_sendTo.setOnClickListener(this);
        imagebtn_diary_write_calender = view.findViewById(R.id.imagebtn_diary_write_calender);
        imagebtn_diary_write_calender.setOnClickListener(this);

        text_diary_write_receiveday = view.findViewById(R.id.text_diary_write_receiveday);

        // 폰트 찾고 리스너 등록
        spinner_diary_write_size = view.findViewById(R.id.spinner_diary_write_size);
        spinner_diary_write_font = view.findViewById(R.id.spinner_diary_write_font);
        spinner_diary_write_color = view.findViewById(R.id.spinner_diary_write_color);
        // 스피너 아이템 등록
        spinner_diary_write_size.setOnItemSelectedListener(this);
        spinner_diary_write_font.setOnItemSelectedListener(this);
        spinner_diary_write_color.setOnItemSelectedListener(this);

//        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/BMJUA.ttf");
//        edit_diary_write_content.setTypeface(typeface);


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
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
        } else if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                try {
                    if (data != null) {
                        String fileName = data.getStringExtra("image");
                        Log.i(TAG, "onActivityResult:fileName=" + fileName);
                        File imgFile = new File(fileName);

                        if (imgFile.exists()) {

                            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                            image_diary_write_add_picture.setImageBitmap(myBitmap);


                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                try {
                    if (data != null) {
                        String fileName = data.getStringExtra("image");
                        Log.i(TAG, "onActivityResult:fileName=" + fileName);
                        File imgFile = new File(fileName);

                        if (imgFile.exists()) {

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
        if (view == imagebtn_diary_write_sendTo) {
            Log.i(TAG, "edit_diary_write_weather : " + edit_diary_write_weather.getText());
            Log.i(TAG, "edit_diary_write_kimozzi : " + edit_diary_write_kimozzi.getText());
            Log.i(TAG, "edit_diary_write_content : " + edit_diary_write_content.getText());
        } else if (view == edit_picture) {
            if (image_uri != null) {
                Intent intent = new Intent(getContext(), GoodPaintBoardActivity.class);
                intent.putExtra("image_uri", image_uri);
                startActivityForResult(intent, 1);

            } else {
                Intent intent = new Intent(getContext(), GoodPaintBoardActivity2.class);
                startActivityForResult(intent, 2);
            }

        } else if (view == add_picture) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE);
        } else if (view == imagebtn_diary_write_calender) {
            DateDialogFragment dlg = new DateDialogFragment(this);
            dlg.show(getFragmentManager(), "DiaryWrite_Fragment_Date_dlg");
        }
    }

    @Override
    public void dateSelected(int year, int month, int dayOfMonth) {
        text_diary_write_receiveday.setText(year + " / " + (1 + month) + " / " + dayOfMonth);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // 현호형 이거 view 객체로 하는게아니라 adapterView로 하니까 됩니다. 이걸로 하시면 될듯요!!!
        if (adapterView == spinner_diary_write_size) {
            if (i == 1) {
                edit_diary_write_content.setTextSize(16);
            } else if (i == 2) {
                edit_diary_write_content.setTextSize(18);
            } else if (i == 3) {
                edit_diary_write_content.setTextSize(20);
            } else if (i == 4) {
                edit_diary_write_content.setTextSize(22);
            } else if (i == 5) {
                edit_diary_write_content.setTextSize(24);
            } else if (i == 6) {
                edit_diary_write_content.setTextSize(26);
            } else if (i == 7) {
                edit_diary_write_content.setTextSize(28);
            } else if (i == 8) {
                edit_diary_write_content.setTextSize(30);
            } else if (i == 9) {
                edit_diary_write_content.setTextSize(32);
            } else if (i == 10) {
                edit_diary_write_content.setTextSize(34);
            } else if (i == 11) {
                edit_diary_write_content.setTextSize(36);
            } else if (i == 12) {
                edit_diary_write_content.setTextSize(38);
            } else if (i == 13) {
                edit_diary_write_content.setTextSize(40);
            }
        }

        if (adapterView == spinner_diary_write_color) {
            Log.i(TAG, "컬러");
            if (i == 1) {
                edit_diary_write_content.setTextColor(Color.BLACK);
            } else if (i == 2) {
                edit_diary_write_content.setTextColor(Color.RED);
            } else if (i == 3) {
                edit_diary_write_content.setTextColor(Color.GREEN);
            } else if (i == 4) {
                edit_diary_write_content.setTextColor(Color.BLUE);
            } else if (i == 5) {
                edit_diary_write_content.setTextColor(Color.YELLOW);
            } else if (i == 6) {
                edit_diary_write_content.setTextColor(Color.WHITE);
            }
        }

        if (adapterView == spinner_diary_write_font) {
            Log.i(TAG, "폰트");
            if (i == 1) {
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/BMJUA.ttf");
                edit_diary_write_content.setTypeface(typeface);
            } else if (i == 2) {
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/BMHANNA.ttf");
                edit_diary_write_content.setTypeface(typeface);
            } else if (i == 3) {
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/BMDOHYEON.ttf");
                edit_diary_write_content.setTypeface(typeface);
            } else if (i == 4) {
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/BMYEONSUNG.ttf");
                edit_diary_write_content.setTypeface(typeface);
            }



        }

    }// end onselected

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

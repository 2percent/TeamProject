package edu.android.teamproject;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryDivideFragment extends Fragment {

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

    public DiaryDivideFragment() {
        // Required empty public constructor
    }

    private DiaryFragment diaryFragment;

    public void setDiaryFragment(DiaryFragment diaryFragment) {
        this.diaryFragment = diaryFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_diary_divide, container, false);

        text_diary_divide_receiveday = view.findViewById(R.id.text_diary_divide_receiveday);  // 일기 도착 날
        text_diary_divide_weather = view.findViewById(R.id.text_diary_divide_weather);         // 날씨
        text_diary_divide_kimozzi = view.findViewById(R.id.text_diary_divide_kimozzi);         // 기분
        text_diary_divide_writeday = view.findViewById(R.id.text_diary_divide_writeday); // 일기 쓴 날짜
        image_diary_divide_picture = view.findViewById(R.id.image_diary_divide_picture);               // 사진
        image_diary_divide_diary_contents = view.findViewById(R.id.image_diary_divide_diary_contents); // 일기 내용
        diary_divide_spinner = view.findViewById(R.id.diary_divide_spinner);

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

    // 여기서 일 하면 됌.
    private void selectDiary() {
        // 스피너 선택 되었을 때
        diary_divide_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // 현재 선택된 스피너의 값을 가져옴(날짜)
                String date = (String) diary_divide_spinner.getSelectedItem();
                // 디비로부터 가지고온 모든 다이어리 데이터를 스피너에서 선택된 값과 같은지 비교하기 위해 for문 사용
                for(int index=0; index<list.size(); index++){
                    if(list.get(index).getSendDate().equals(date)){

                        ModelDiary m = list.get(index);
                        showDiary(m);


                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 스피너 아이템 디비로부터 받아온 날짜를 넣어줌.
        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, spinnerItem);
        diary_divide_spinner.setAdapter(adapter);

    }

    private ArrayList<String> spinnerItem = new ArrayList<>();

    public ArrayList<String> getSpinnerItem() {
        return spinnerItem;
    }

    private ArrayList<ModelDiary> list = new ArrayList<>();
    public void getlistDiary(boolean b, ArrayList<ModelDiary> list) {
        // 일기내용을 전부 가져옴.
        if(b) {
            // 전부 가져와서 멤버변수 list에 저장
            this.list.add(list.get(0));
            spinnerItem.add(list.get(0).getSendDate());

            int key = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getInt("key", 0);
            if(this.list.size() == key){

                selectDiary();
            }
        }else{
            Toast.makeText(getContext(), "첫번째 일기를 작성해 보세요.", Toast.LENGTH_SHORT).show();
        }

    }

    // 최종적으로 위젯에 다이어리를 보여주는 메소드
    private ModelDiary model = null;
    public void setModel(ModelDiary m) {
        this.model = m;
    }
    private void showDiary(ModelDiary m) {

        DiaryLab lab = DiaryLab.getInstance(this);

        if(model != null){

            File file = new File(m.getFileName());
            if(file.exists()){
                Bitmap mybitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                image_diary_divide_picture.setImageBitmap(mybitmap);

            }

            text_diary_divide_receiveday.setText(model.getReceivedayDate());  // 일기 도착 날
            text_diary_divide_weather.setText(model.getWeather()); // 날씨
            text_diary_divide_kimozzi.setText(model.getKimozzi()); // 기분
            text_diary_divide_writeday.setText(model.getSendDate());   // 일기 쓴 날짜
            image_diary_divide_diary_contents.setText(model.getContent()); // 일기 내용
            image_diary_divide_diary_contents.setTextColor(model.getColor()); // 컬러
            image_diary_divide_diary_contents.setTextSize(TypedValue.COMPLEX_UNIT_PX, model.getSize());   // 사이즈
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), model.getFont());
            image_diary_divide_diary_contents.setTypeface(typeface);    // font
            image_diary_divide_diary_contents.setBackground(getResources().getDrawable((int) model.getBackground())); // Background


        }else{

            lab.getImage(m,image_diary_divide_picture,this);

            text_diary_divide_receiveday.setText(m.getReceivedayDate());  // 일기 도착 날
            text_diary_divide_weather.setText(m.getWeather()); // 날씨
            text_diary_divide_kimozzi.setText(m.getKimozzi()); // 기분
            text_diary_divide_writeday.setText(m.getSendDate());   // 일기 쓴 날짜
            image_diary_divide_diary_contents.setText(m.getContent()); // 일기 내용
            image_diary_divide_diary_contents.setTextColor(m.getColor()); // 컬러
            image_diary_divide_diary_contents.setTextSize(TypedValue.COMPLEX_UNIT_PX,m.getSize());   // 사이즈
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), m.getFont());
            image_diary_divide_diary_contents.setTypeface(typeface);    // font
            image_diary_divide_diary_contents.setBackground(getResources().getDrawable((int) m.getBackground())); // Background

        }
    }

}


//    이미지 바로 안나옴. 탭뷰가 미리 만들어 줌으로써 리스너가 먼저 호출되고나서
//        이미지를 띄워줄려고하니 첫 게시물이 이미지가 안들어감
//
//        프래그먼트 생각하자
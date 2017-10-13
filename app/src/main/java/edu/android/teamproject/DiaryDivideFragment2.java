package edu.android.teamproject;


import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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

        this.showMyDiary2();

        return view;
    }


    private void showMyDiary2() {

        DiaryLab dao = DiaryLab.getInstance(this);

        String my = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getString("my","0");
        String your = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getString("your","0");
        String key = String.valueOf(getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getInt("key",0));

        dao.selectMyDiary2(my,your,key);
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


    // 최종적으로 위젯에 다이어리를 보여주는 메소드
    private ModelDiary model = null;
    public void setModel(ModelDiary m) {
        this.model = m;
    }
    private void showDiary(ModelDiary m) {

        DiaryLab lab = DiaryLab.getInstance();

        if(m != null) {
            lab.getImage2(m, image_diary_divide_picture,this);

            text_diary_divide_receiveday.setText(m.getReceivedayDate());  // 일기 도착 날
            text_diary_divide_weather.setText(m.getWeather()); // 날씨
            text_diary_divide_kimozzi.setText(m.getKimozzi()); // 기분
            text_diary_divide_writeday.setText(m.getSendDate());   // 일기 쓴 날짜

            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            int year = Integer.parseInt(format.format(new Date()));
            SimpleDateFormat format2 = new SimpleDateFormat("MM");
            int month = Integer.parseInt(format2.format(new Date()));
            SimpleDateFormat format3 = new SimpleDateFormat("dd");
            int day = Integer.parseInt(format3.format(new Date()));


            Calendar calendar = new GregorianCalendar(year, month, day);
            long sysdate = calendar.getTimeInMillis();

            // 보여줄 날짜
            String date = m.getReceivedayDate();
            SimpleDateFormat f1 = new SimpleDateFormat("yyyy");
            date = date.replace("/", "");
            date = date.replace(" ", "");
            int year2 = Integer.parseInt(date.substring(0, 4));
            int month2 = Integer.parseInt(date.substring(4, 6));
            int day2 = Integer.parseInt(date.substring(6, 8));
            Calendar calendar2 = new GregorianCalendar(year2, month2, day2);
            long receivedate = calendar2.getTimeInMillis();
            Log.i(TAG, "현재   날짜 : " + year + " / " + month + " / " + day);
            Log.i(TAG, "보여줄 날짜 : " + year2 + " / " + month2 + " / " + day2);
            Log.i(TAG, "현재   날짜 : " + sysdate );
            Log.i(TAG, "보여줄 날짜 : " + receivedate );

            if (sysdate >= receivedate) {
                image_diary_divide_diary_contents.setText(m.getContent()); // 일기 내용
                image_diary_divide_diary_contents.setTextColor(m.getColor()); // 컬러
                image_diary_divide_diary_contents.setTextSize(TypedValue.COMPLEX_UNIT_PX, m.getSize());   // 사이즈
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), m.getFont());
                image_diary_divide_diary_contents.setTypeface(typeface);    // font
                image_diary_divide_diary_contents.setBackground(getResources().getDrawable((int) m.getBackground())); // Background
            }
            if(sysdate < receivedate) {
                image_diary_divide_diary_contents.setText("");
                image_diary_divide_diary_contents.setBackground(getResources().getDrawable(R.drawable.padlock));
            }
        }


    }

}

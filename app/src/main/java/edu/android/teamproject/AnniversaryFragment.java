
package edu.android.teamproject;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



@TargetApi(Build.VERSION_CODES.N)
public class AnniversaryFragment extends Fragment implements View.OnClickListener,DateDialogFragment.DateSelectListener {


    private static final String MEMBER = "member";

    private static final String TAG = "edu.android";


    ImageButton imagebtn_anniversary_calender;
    TextView text_add_anniversary, text_1year, text_100day, text_start_day;

    private AppCompatActivity compatActivity;
    private ListView listView;

    ArrayList<String> list;
    StringBuffer buffer;
    StringBuffer buffer1;
    StringBuffer buffer2;
    Date today = new Date();
    Calendar cal = Calendar.getInstance();


    public AnniversaryFragment() {
    }


    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_anniversary);

        list = new ArrayList<>();
        buffer = new StringBuffer();
        buffer1 = new StringBuffer();
        buffer2 = new StringBuffer();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anniversary, null);

        text_100day = (TextView) view.findViewById(R.id.text_100day);
        text_1year = (TextView) view.findViewById(R.id.text_1year);
        text_start_day = (TextView) view.findViewById(R.id.text_start_day);


        imagebtn_anniversary_calender = (ImageButton) view.findViewById(R.id.imagebtn_anniversary_calender);
        imagebtn_anniversary_calender.setOnClickListener(this);
        text_add_anniversary = (TextView) view.findViewById(R.id.text_add_anniversary);


        // 시작한 날 서버에서 가져오는 것.
        String startday = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getString("startday", "");
        text_start_day.setText(startday);
        Log.i(TAG, "시작일: " + startday);

        // 기념일 보여주기 (100일 단위, 1주년 단위)
        try {

            Calendar todayCal = new GregorianCalendar(); // 현재 날
            Log.i(TAG, "*****" + todayCal.get(Calendar.YEAR) + "/" + todayCal.get(Calendar.MONTH) + "/" + todayCal.get(Calendar.DAY_OF_MONTH));

            // 오늘 날짜
            long today = todayCal.getTimeInMillis();
            Log.i(TAG, "오늘 : "+ today);

            // 시작일
            String[] temp = startday.split("/");
            int year = Integer.parseInt(temp[0]);
            int month = Integer.parseInt(temp[1]) - 1;
            int day = Integer.parseInt(temp[2]);
            Log.i(TAG, "*****" + year + "/" + month + "/" + day);

            Calendar calendar = new GregorianCalendar(year, month, day);
            long s = calendar.getTimeInMillis();

            Log.i(TAG, "시작일 : " + s);

            // getTimeInMiillis 는 millisecond 단위로 일정 시간을 반환하는 method
            long count = (today - s)/ (24 * 60 * 60 * 1000);  // 총 만난날로 계산됨.

            Log.i(TAG, "총 만난 날 ..... : "+ count);

            //1. 100일 단위 날짜 D-day 보여주기
            for(int d =1 ; d<100; d++) {
                if (count==100*d ) {
                    text_100day.setText(" ♥ 오늘은 만난지" + count + "일 입니다 ♥");

                } else {

                        buffer1.append("♥" + (100 * d) + "일이 " + "D-day " + ((100 * d) - count) + " 입니다 ♥" + "\n");
                    text_100day.setText(buffer1);
                    Log.i(TAG, today + "  " + "D-day" + ((100 * d) - count) + " 입니다.");
                }
            }

            //2. 1주년 단위 날짜 D-day 보여주기
            int d = 1;
                if (count == 365 * d) {
                    for(; d<100; d++) {
                        text_1year.setText(today + " ♥ 오늘은 " + d + " 주년 입니다 ♥");
                    }
                } else if (count < 365 * d) {
                    for ( d = 1; d<10; d++) {
                        buffer.append("♥ " +d + "주년은 " + (year+d) + "/" + (month+1) + "/" + day + " 입니다 ♥"+"\n");
                    } // end for()
                    text_1year.setText(buffer);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;

    } // end onCreateView()


    public void setContentView(int contentView) {
        //this.contentView = contentView;
    }


    // 달력
    @Override
    public void onClick(View view) {

        ImageButton btn = (ImageButton) view;
        // 캘린더 버튼 클릭 시,
        if (btn == imagebtn_anniversary_calender) {
            DateDialogFragment d = new DateDialogFragment(this);
            d.show(getFragmentManager(), "datePicker");
        } else {
            Toast.makeText(compatActivity, " 날짜를 다시 선택해주세요 ", Toast.LENGTH_SHORT).show();
        } // end if()


    } // end onClick()

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void dateSelected(int year, int month, int  day) {
        Log.i(TAG, "----------------------------------------------------------------------------------------------" );

            String year2 = String.valueOf(year);
            String month2 = String.valueOf(month+1);
            String day2 = String.valueOf(day);
        list.add(year2 + "년 " + (month2) + "월 " + day2 + "일 \n") ;
        Log.i(TAG, "-------------" +year2 + "년 " + (month2) + "월 " + day2 + "일 \n" );


            if (list != null) {
                for (String s1 : list) {
                    buffer2.append(s1);
                } // end for()
                text_add_anniversary.setText(buffer2);
                buffer2.delete(0,buffer2.length());


                Log.i(TAG, "//" + list);


            }// end if()


    } // end dateSelected


    } // end class






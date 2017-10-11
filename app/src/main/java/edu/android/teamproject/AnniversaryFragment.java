
package edu.android.teamproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AnniversaryFragment extends Fragment implements View.OnClickListener,DateDialogFragment.DateSelectListener {


    private static final String MEMBER = "member";

    private static final String TAG = "edu.android";

    ImageButton imagebtn_anniversary_calender;
    TextView text_add_anniversary, text_match_start_day;

    private AppCompatActivity compatActivity;
    private ListView listView;

    List<String> list;
    StringBuffer buffer;


    public AnniversaryFragment() {
        // Required empty public constructor
    }


    //값!!!!!!
    //static final String[] LIST_MENU = {"\"100일\", \"2017/09/25\", \"D-77\""};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_anniversary);




        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anniversary, null);

        //우선 빈 데이터 리스트 생성
        ArrayList<String> items = new ArrayList<String>();

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, items);

//        String startday = getContext().getSharedPreferences("id" , getContext().MODE_PRIVATE).getString("startday", "0");
        // TODO: startday 날짜로 바꿔서 거기에 사귄날짜 현재날짜를 구해서 (현재날짜 - 사귄날짜) 사귄날짜 100, 200 , 365(1년) , 730(2년) , 1095(3년)
//        int day = (현재날짜 - 사귄날짜);
//        if(day == 100){
//
//        }

        // listView 생성 및 adapter 지정.
        ListView listView = (ListView) view.findViewById(R.id.list_item_view);
        listView.setAdapter(adapter);

        imagebtn_anniversary_calender = (ImageButton) view.findViewById(R.id.imagebtn_anniversary_calender);
        imagebtn_anniversary_calender.setOnClickListener(this);
        text_add_anniversary = (TextView) view.findViewById(R.id.text_add_anniversary);


        //
        list = new ArrayList<>();



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


    @Override
    public void dateSelected(int year, int month, int day) {

        // 서버에 올릴 때 list 로 올리기 ! ( 값 list에 있당 )

        list.add(year + "년 " + (month + 1) + "월 " + day + "일\n");

        buffer = new StringBuffer();

        if(list != null) {
          for(String s : list){
             buffer.append(s);
          } // end for()
            text_add_anniversary.setText(buffer);


//            String Anniversary = text_add_anniversary.getText().toString();
//            String id = getContext().getSharedPreferences("id" , getContext().MODE_PRIVATE).getString("id", "0");
//
//            // Model
//            ModelDday dday = new ModelDday(Anniversary, id);
//
//            // Controller
//            DiaryLab dao = DiaryLab.getInstance();
//            dao.insertAnniversary(dday);


        } // end if()

        Log.i(TAG, "//"+list);

    } // end dateSelected


    } // end class






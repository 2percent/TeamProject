
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

import java.util.ArrayList;


public class AnniversaryFragment extends Fragment implements View.OnClickListener,DateDialogFragment.DateSelectListener {



    private static final String MEMBER = "member";

    private static final String TAG = "edu.android";

    ImageButton imagebtn_anniversary_calender;
    TextView text_add_anniversary;

    private AppCompatActivity compatActivity;
    private ListView listView;

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

        Log.i(TAG, "뷰_ㅠ");


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anniversary,null);

        //우선 빈 데이터 리스트 생성
        ArrayList<String> items = new ArrayList<String>();

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, items);


        // listView 생성 및 adapter 지정.
        ListView listView = (ListView) view.findViewById(R.id.list_item_view);
        listView.setAdapter(adapter);

        Log.i(TAG, "뷰뷰뷰뷰");

        imagebtn_anniversary_calender = (ImageButton)view.findViewById(R.id.imagebtn_anniversary_calender);
        imagebtn_anniversary_calender.setOnClickListener(this);
        text_add_anniversary = (TextView) view.findViewById(R.id.text_add_anniversary);



        return view;




    } // end onCreateView()


    public void setContentView(int contentView) {
        //this.contentView = contentView;
    }




   // 달력
    @Override
    public void onClick(View view) {


        Log.i(TAG, "들어왔?");
//        Intent intent = getIntent();
//        String[] member = intent.getStringArrayExtra(MEMBER);

        ImageButton btn = (ImageButton) view;
        // 캘린더 버튼 클릭 시,
        if (btn == imagebtn_anniversary_calender) {
            Log.i(TAG, "빠큐모고");
            DateDialogFragment d = new DateDialogFragment(this);
            d.show(getFragmentManager(), "datePicker");

        } //end if()
    } // end onClick()


   @Override
    public void dateSelected(int year, int month, int day) {
        text_add_anniversary.setText(year+"/"+(month+1)+"/"+day);
    }

}

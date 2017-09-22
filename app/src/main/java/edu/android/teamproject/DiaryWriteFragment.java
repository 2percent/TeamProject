package edu.android.teamproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryWriteFragment extends Fragment {

    TextView textView;
    EditText edit_diary_write;

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

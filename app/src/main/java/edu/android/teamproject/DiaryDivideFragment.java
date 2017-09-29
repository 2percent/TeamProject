package edu.android.teamproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryDivideFragment extends Fragment {

    TextView writeday;

    public DiaryDivideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary_divide, container, false);

        writeday = view.findViewById(R.id.text_diary_divide_writeday);
        writeday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDialogFragment dlg = new DateDialogFragment();
                dlg.show(getFragmentManager(), "text_diary_divide_writeday");
            }
        });


        return view;
    }

}

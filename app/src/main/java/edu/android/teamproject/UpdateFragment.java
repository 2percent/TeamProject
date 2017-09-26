package edu.android.teamproject;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    TextView subtitle;



    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        subtitle = (TextView) view.findViewById(R.id.subtitle);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BMJUA.ttf");
        subtitle.setTypeface(typeFace);

        return view;
    }

}

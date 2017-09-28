package edu.android.teamproject;


import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment implements View.OnClickListener{

    TextView subtitle;

    EditText editPw, editMyPhone, editYourPhone;
    private Button btn_update;
    private String startday;
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

        editPw = view.findViewById(R.id.editPw);
        editMyPhone = view.findViewById(R.id.editMyphone);
        editYourPhone = view.findViewById(R.id.editYourPhone);
        btn_update = view.findViewById(R.id.btn_update);

        SharedPreferences pref = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE);
        String pw = pref.getString("pw", "");
        String my = pref.getString("my", "");
        String you = pref.getString("your", "");
        startday = pref.getString("startday", "");

        editPw.setText(pw);
        editMyPhone.setText(my);
        editYourPhone.setText(you);


        btn_update.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == btn_update){

            SharedPreferences sp = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE);
            ModelMember m = new ModelMember();
            m.setId(sp.getString("id",""));
            m.setPassword(editPw.getText().toString());
            m.setMyPhoneNum(editMyPhone.getText().toString());
            m.setYourPhoneNum(editYourPhone.getText().toString());
            m.setStartDay(startday);

            DiaryLab dao = DiaryLab.getInstance();
            dao.updateMember(m);

            Toast.makeText(getContext(), "회원정보수정 완료 되었습니다.", Toast.LENGTH_SHORT).show();

        }
    }
}

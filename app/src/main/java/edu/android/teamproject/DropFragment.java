package edu.android.teamproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DropFragment extends Fragment {

    EditText editReally;

    public DropFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drop, container, false);

        editReally = view.findViewById(R.id.EditReally);
        editReally.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        && i == KeyEvent.KEYCODE_ENTER) {
                    dropAccount();
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    private void dropAccount() {

        Toast.makeText(getContext(), editReally.getText().toString(), Toast.LENGTH_SHORT).show();
        // TODO: 회원 탈퇴 액션 추가
    }

}

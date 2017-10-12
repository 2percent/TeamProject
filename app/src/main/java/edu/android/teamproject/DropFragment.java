package edu.android.teamproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class DropFragment extends Fragment {

    private static final String TAG = "edu.android2";
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

        String your = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getString("my", "0"); // 내 휴대폰
        String my = getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getString("your", "0"); // 상대 휴대폰
        String key = String.valueOf(getContext().getSharedPreferences("id", getContext().MODE_PRIVATE).getInt("key", 0));
        String yourmy = my+"_"+your;

        if(editReally.getText().toString().equals("나는 모든 추억을 불사르겠습니다")){

            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference mConditionRef = mRootRef.child("Diary");
            DatabaseReference data2 = mConditionRef.child(yourmy);

            data2.removeValue();

            Log.i(TAG,yourmy);

            data2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        appleSnapshot.getRef().removeValue();
                    };
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            StorageReference storage = FirebaseStorage.getInstance().getReference();
            StorageReference storage2 = storage.child("images");
            StorageReference storage3 = storage2.child(yourmy);

            storage3.delete();


        } else {

        }

    }

}

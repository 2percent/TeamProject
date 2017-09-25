package edu.android.teamproject;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by STU on 2017-09-19.
 */

// Controller 기능들은 여기에
public class DiaryLab {

    private static DiaryLab instance;
    // 파이어 베이스 데이터베이스 사용 하기 위한 FirebaseDatabase , DatabaseReference 객체 생성.
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static Context context;

    private DiaryLab() {}
    private DiaryLab(Context context){
        this.context = context;
    }

    public static DiaryLab getInstance() {
        if (instance == null) {
            instance = new DiaryLab();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();
        }
        return instance;
    }
    public static DiaryLab getInstance(Context context) {
        if (instance == null) {
            instance = new DiaryLab(context);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();
        }
        return instance;
    }
    /////////////////////////////// 싱글턴 디자인 여기까지 /////////////////////////

    private static final String TAG = "edu.android";

    private static final String MEMBER_COLUMN = "Member";

    // 회원 가입 메소드
    public int insertMember(ModelMember m) {
        try{
            databaseReference.child(m.getId()).setValue(m);
        }catch(Exception e){
            return 0;
        }
        return 1;
    }// end insertMember()

    // 회원 가입이 되어있는지 안되어있는지 확인하기위해 뽑아오는 메소드
    private boolean bool = false;
    public void isSelectAll(String id) {

            databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<ModelMember> list = new ArrayList();
                    ModelMember m = dataSnapshot.getValue(ModelMember.class);
                    list.add(m);

                    Log.i(TAG, list.size() + "" );
                    if (list.size() > 0 && list.get(0) != null) {
                        ((LoginActivity) context).idResult(true);
                    } else {
                        ((LoginActivity) context).idResult(false);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



    }

}

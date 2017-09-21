package edu.android.teamproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by STU on 2017-09-19.
 */

// Controller 기능들은 여기에
public class DiaryLab {

    static List<ModelDiary> lab;

    private static DiaryLab instance;
    public static DiaryLab getInstance(){
        if(instance == null){
            instance = new DiaryLab();
            lab = new ArrayList<>();
        }
        return instance;
    }
    /////////////////////////////// 싱글턴 디자인 여기까지 /////////////////////////

    // 파이어 베이스 데이터베이스 사용 하기 위한.
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private static final String MEMBER_COLUMN = "Member";

    // 회원 가입 메소드
    public int insertMember(ModelMember m){
        try{
            databaseReference.child(MEMBER_COLUMN).setValue(m);
        }catch(Exception e){
            return 0;
        }
        return 1;
    }// end insertMember()

}

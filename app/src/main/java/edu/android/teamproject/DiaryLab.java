package edu.android.teamproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Controller 기능들은 여기에
public class DiaryLab {

    private static DiaryLab instance;
    // 파이어 베이스 데이터베이스 사용 하기 위한 FirebaseDatabase , DatabaseReference 객체 생성.
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    // 파이어 베이스 Storage 사용하기 위한 객체 생성
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    StorageReference rootReference = firebaseStorage.getReferenceFromUrl("gs://teamproject-4600b.appspot.com/");



    private static Context context;

    private DiaryLab() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private DiaryLab(Context context){
        this();
        this.context = context;
        fragSet = new HashSet<>();
    }
    private DiaryLab(Fragment f){
        this();
        fragSet = new HashSet<>();
    }

    private Set<Fragment> fragSet;


    public static DiaryLab getInstance() {
        if (instance == null) {
            instance = new DiaryLab();
        }
        return instance;
    }
    public static DiaryLab getInstance(Context context) {
        if (instance == null) {
            instance = new DiaryLab(context);
        } else {
            instance.context = context;
        }
        return instance;
    }
    public static DiaryLab getInstance(Fragment f) {
        if (instance == null) {
            instance = new DiaryLab(f);
        }
        instance.fragSet.add(f);

        return instance;
    }
    /////////////////////////////// 싱글턴 디자인 여기까지 /////////////////////////

    private static final String TAG = "edu.android";

    private static final String MEMBER_COLUMN = "Member";

    // 회원 가입 메소드
    public int insertMember(ModelMember m) {
        try{
            DatabaseReference data = databaseReference.child("Member");
            Task<Void> finalData = data.child(m.getId()).setValue(m);
            DatabaseReference data2 = databaseReference.child("Diary");
            data2.child(m.getYourPhoneNum()+"_"+m.getMyPhoneNum()).setValue("회원가입성공");

        }catch(Exception e){
            return 0;
        }
        return 1;
    }// end insertMember()

    // 회원 가입이 되어있는지 안되어있는지 확인하기위해 뽑아오는 메소드
    public void isSelectAll(String id) {
        DatabaseReference data = databaseReference.child("Member");
        data.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<ModelMember> list = new ArrayList();
                ModelMember m = dataSnapshot.getValue(ModelMember.class);
                list.add(m);

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

    // 설정에서 회원 수정
    public void updateMember(ModelMember m) {
        try {
            DatabaseReference data = databaseReference.child("Member");
            Task<Void> finalData = data.child(m.getId()).setValue(m);
        }catch (Exception e){
            Toast.makeText(context, "알수 없는 오류 발생", Toast.LENGTH_SHORT).show();
        }
    }

    // 일기장 데이터 저장
    public void insertDiary(ModelDiary m) {
        Log.i(TAG, "일기장 넣기 dao 메서드");
        String fileName = m.getFileName();

        // 이미지 저장
        StorageReference reference = rootReference.child("images");
        StorageReference reference1 = reference.child(m.getYourPhone()+"_"+m.getMyphone());
        int b = Integer.parseInt(m.getKey())+1;
        String keyimage = String.valueOf(b);
        StorageReference reference2 = reference1.child(keyimage);

        Uri file = Uri.fromFile(new File(fileName));
        StorageReference riversRef = reference2.child(file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i(TAG, "이미지 업로드 성공");
            }
        });

        // 일기 정보 업로드
        try {
            DatabaseReference data = databaseReference.child("Diary");
            DatabaseReference data2 = data.child(m.getYourPhone()+"_"+m.getMyphone());
            int a = Integer.parseInt(m.getKey())+1;
            String key = String.valueOf(a);
            data2.child(key).setValue(m);
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }


    }

    // 일기 불러오기
    public void selectMyDiary(String my, String your, String key) {

        // 데이터만 뽑아오고
        DatabaseReference data = databaseReference.child("Diary");
        DatabaseReference data2 = data.child(your+"_"+my);

            data2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ArrayList<ModelDiary> list = new ArrayList();
                    list.add(dataSnapshot.getValue(ModelDiary.class));
                    for (Fragment f : fragSet) {
                        if (list.size() == 0 || list == null) {
                            if (f instanceof DiaryDivideFragment) {
                                ((DiaryDivideFragment) f).getlistDiary(false, list);
                            }
                        } else {
                            Log.i(TAG, f + "");
                            if (f instanceof DiaryDivideFragment) {
                                ((DiaryDivideFragment) f).getlistDiary(true, list);
                            }
                        }
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

    }

//    private ArrayList<ModelDiary> diaryList;
//    public ArrayList<ModelDiary> getDiaryList() {
//        return diaryList;
//    }
    // 일기 불러오기
    public void selectMyDiary2(String my, String your, String key) {

        // 데이터만 뽑아오고
        DatabaseReference data = databaseReference.child("Diary");
        DatabaseReference data2 = data.child(my+"_"+your);

        data2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ArrayList<ModelDiary> list = new ArrayList();
                list.add(dataSnapshot.getValue(ModelDiary.class));
                for (Fragment f : fragSet) {
                    if (list.size() == 0 || list == null) {
                        if (f instanceof DiaryDivideFragment2) {
                            ((DiaryDivideFragment2) f).getlistDiary(false, list);
                        }
                    } else {
                        if (f instanceof DiaryDivideFragment2) {
                            ((DiaryDivideFragment2) f).getlistDiary(true, list);
                        }
                    }
                }
//                diaryList = new ArrayList<ModelDiary>();
//                diaryList.add(dataSnapshot.getValue(ModelDiary.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    // 일기장 이미지 파일 가져오는 메소드
    public void getImage(ModelDiary m, ImageView imageview, Fragment con) {

        StorageReference storage = FirebaseStorage.getInstance().getReference();
        String filename = "images/"+m.getYourPhone()+"_"+m.getMyphone()+"/"+(Integer.parseInt(m.getKey())+1)+"/"+m.getId()+"_"+m.getSendDate()+".jpg";
            StorageReference storageReference = storage.child(filename);
                Glide.with(con/* context */)
                        .using(new FirebaseImageLoader())
                        .load(storageReference)
                        .into(imageview);


    }

    // 일기장 이미지 파일 가져오는 메소드
    public void getImage2(ModelDiary m, ImageView imageview, Fragment con) {

        StorageReference storage = FirebaseStorage.getInstance().getReference();
        String filename = "images/"+m.getMyphone()+"_"+m.getYourPhone()+"/"+(Integer.parseInt(m.getKey())+1)+"/"+m.getId()+"_"+m.getSendDate()+".jpg";
        StorageReference storageReference = storage.child(filename);

                Glide.with(con/* context */)
                        .using(new FirebaseImageLoader())
                        .load(storageReference)
                        .into(imageview);


    }

    // 기념일 추가 데이터 저장
    public void insertAnniversary(ModelDday dday) {
//        DatabaseReference data = databaseReference.child("Anniversary");
//        Task<Void> finalData = data.child(dday.getId()).setValue(dday);
    }

}

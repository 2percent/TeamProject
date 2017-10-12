package edu.android.teamproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiaryFragment extends Fragment {

    private static final String TAG = "edu.android";
    private SelectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private Fragment tempFrag;

    public Fragment getTempFrag() {
        return tempFrag;
    }


    public DiaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_diary_container);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_diary);

        mSectionsPagerAdapter = new SelectionsPagerAdapter(getChildFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    public class SelectionsPagerAdapter extends FragmentPagerAdapter {

        public SelectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new DiaryDivideFragment();
                Log.i(TAG,"내 프래그먼트 생성");
            } else if (position == 1) {
                fragment = new DiaryDivideFragment();
                tempFrag = fragment;
                ((DiaryDivideFragment) fragment).setDiaryFragment(DiaryFragment.this);
            }
            else if (position == 1){
                fragment = new DiaryDivideFragment2();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "나";
                case 1:
                    return "너";
            }
            return null;
        }
    }
}



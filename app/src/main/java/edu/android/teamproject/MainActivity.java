package edu.android.teamproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "edu.android";
    private SelectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView mainText;

//    private String DW = getString(R.string.DW);
//    private String Diary = getString(R.string.Diary);
//    private String us = getString(R.string.us);

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView) findViewById(R.id.topText);

        mSectionsPagerAdapter = new SelectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.isSelected() == true && tab.getText().equals("일기쓰기")){
                    mainText.setText("일기쓰기");
                } else if (tab.isSelected() == true && tab.getText().equals("일기장")){
                    mainText.setText("일기장");
                } else if (tab.isSelected() == true && tab.getText().equals("우리")){
                    mainText.setText("우리");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG,""+item);
        return super.onOptionsItemSelected(item);
    }


    public static class SelectionsPagerAdapter extends FragmentPagerAdapter {

        private static final String TAG = "edu.android";

        public SelectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if(position == 0) {
                fragment = new DWFragment();
            } else if (position == 1){
                fragment = new DFragment();
            } else if (position == 2){
                fragment = new AnniFragment();
            }
            return fragment;
        }


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "일기쓰기";
                case 1:
                    return "일기장";
                case 2:
                    return "우리";
            }
            return null;
        }
    }
}

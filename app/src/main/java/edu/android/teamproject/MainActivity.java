package edu.android.teamproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private int tem;
    private static final String TAG = "edu.android";
    private static final String[] TAB_TITLES = {"일기쓰기", "일기장", "우리"};

    private TabLayout tabLayout;
    private TabLayout.Tab tab;
    private SelectionsPagerAdapter mSelectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView text_main_count_day;
    private TextView mainText;
    static public Fragment tempFrag;

    private int loginCount = 0;
    int year,month, day;

    public static Fragment getTempFrag() {
        return tempFrag;
    }

    public TabLayout.Tab getTab() {
        tab = tabLayout.getTabAt(1);
        return tab;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 아이디 존재 유무에 따라 로그인창 보여줄지 말지
        SharedPreferences pref = getSharedPreferences("id", MODE_PRIVATE);
        String id = pref.getString("id", "0");



        text_main_count_day = (TextView) findViewById(R.id.text_main_count_day);


        // 시작한 날 서버에서 가져오는 것.
        String startday =getSharedPreferences("id", MODE_PRIVATE).getString("startday", "");

        Log.i(TAG, "시작일: " + startday);

        // 기념일 보여주기 (100일 단위, 1주년 단위)
        try {

            Calendar todayCal = new GregorianCalendar(); // 현재 날
            Log.i(TAG, "*****" + todayCal.get(Calendar.YEAR) + "/" + todayCal.get(Calendar.MONTH) + "/" + todayCal.get(Calendar.DAY_OF_MONTH));

            // 오늘 날짜
            long today = todayCal.getTimeInMillis();
            Log.i(TAG, "오늘 : " + today);

            // 시작일
            String[] temp = startday.split("/");
            int year = Integer.parseInt(temp[0]);
            int month = Integer.parseInt(temp[1]) - 1;
            int day = Integer.parseInt(temp[2]);
            Log.i(TAG, "*****" + year + "/" + month + "/" + day);

            Calendar calendar = new GregorianCalendar(year, month, day);
            long s = calendar.getTimeInMillis();

            Log.i(TAG, "시작일 : " + s);

            // getTimeInMiillis 는 millisecond 단위로 일정 시간을 반환하는 method
            long count = (today - s) / (24 * 60 * 60 * 1000);  // 총 만난날로 계산됨.

            Log.i(TAG, "총 만난 날 ..... : " + count);


            Toast.makeText(this, "만난지 " + count + "일 ♥", Toast.LENGTH_SHORT).show();
            text_main_count_day.setText("만난지 " + count + "일 ♥");

        }catch (Exception e){
            Log.e(TAG, e.getMessage());

        }


        if(id.equals("0")){
            Intent intent2 = new Intent(this, LoginActivity.class);
            startActivity(intent2);
            finish();
        }

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);


        AppBarLayout app = (AppBarLayout)findViewById(R.id.appbar);

        app.setBackgroundColor(Color.rgb(232,60,60));

        mainText = (TextView) findViewById(R.id.text_main_tab_selected);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/BMJUA.ttf");
        mainText.setTypeface(typeFace);

        mSelectionsPagerAdapter = new SelectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.viewpager_main_container);
        mViewPager.setAdapter(mSelectionsPagerAdapter);
        mViewPager.setBackgroundColor(Color.rgb(255, 255, 255));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        MainActivity TabView Image -> onTabSelected()
        tabLayout = (TabLayout) findViewById(R.id.tabs_main);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.write));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.diary));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.calendar));
        tabLayout.addOnTabSelectedListener(this);



        ImageButton setting = (ImageButton) findViewById(R.id.btn_main_settings);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_top);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "" + item);
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //MainActivity TabView Image
        int tabPosition = tab.getPosition();
        mainText.setText(TAB_TITLES[tabPosition]);
        mViewPager.setCurrentItem(tabPosition);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }


    public static class SelectionsPagerAdapter extends FragmentPagerAdapter {

        private static final String TAG = "edu.android";

        public SelectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new DiaryWriteFragment();
            } else if (position == 1) {
                fragment = new DiaryFragment();
                tempFrag = fragment;
            } else if (position == 2) {
                fragment = new AnniversaryFragment();
            }
            return fragment;
        }


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
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

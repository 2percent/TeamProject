package edu.android.teamproject;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class SettingActivity extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4;

    Button button1,button2,button3,button4;

    public ExpandableRelativeLayout getExpandableLayout2() {
        return expandableLayout2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        button1 = (Button) findViewById(R.id.expandableButton1);
        button3 = (Button) findViewById(R.id.expandableButton3);
        button4 = (Button) findViewById(R.id.expandableButton4);

        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout4 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout4);

        //      toolbar = (Toolbar)findViewById(R.id.toolBar);
        //      toolbar.setTitle("Expandable Layout Tutorial");
        //     setSupportActionBar(toolbar);
        button2 = (Button) findViewById(R.id.expandableButton2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//            }
//        });

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/BMJUA.ttf");
        button2.setTypeface(typeFace);

    }

    @Override
    public void onBackPressed() {
        try {
            if (expandableLayout2.isExpanded() == true) {
                expandableLayout2.collapse();
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            super.onBackPressed();
        }
    }

    public void expandableButton1(View view) {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        othersCollapse(view);
        expandableLayout1.toggle(); // toggle expand and collapse
    }

    public void expandableButton2(View view) {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        othersCollapse(view);
        expandableLayout2.toggle(); // toggle expand and collapse
    }

    public void expandableButton3(View view) {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        othersCollapse(view);
        expandableLayout3.toggle(); // toggle expand and collapse
    }

    public void expandableButton4(View view) {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        othersCollapse(view);
        expandableLayout4.toggle(); // toggle expand and collapse
    }

    private void othersCollapse(View view) {

        if (view == button1){
            if(expandableLayout2.isExpanded()){
                expandableLayout2.collapse();
            }
            if(expandableLayout3.isExpanded()){
                expandableLayout3.collapse();
            }
            if(expandableLayout4.isExpanded()){
                expandableLayout4.collapse();
            }
        } else if (view == button2){
            if(expandableLayout1.isExpanded()){
                expandableLayout1.collapse();
            }
            if(expandableLayout3.isExpanded()){
                expandableLayout3.collapse();
            }
            if(expandableLayout4.isExpanded()){
                expandableLayout4.collapse();
            }
        } else if (view == button3){
            if(expandableLayout1.isExpanded()){
                expandableLayout1.collapse();
            }
            if(expandableLayout2.isExpanded()){
                expandableLayout2.collapse();
            }
            if(expandableLayout4.isExpanded()){
                expandableLayout4.collapse();
            }
        } else if (view == button4){
            if(expandableLayout1.isExpanded()){
                expandableLayout1.collapse();
            }
            if(expandableLayout2.isExpanded()){
                expandableLayout2.collapse();
            }
            if(expandableLayout3.isExpanded()){
                expandableLayout3.collapse();
            }
        }
    } // end OthersCollopse
}

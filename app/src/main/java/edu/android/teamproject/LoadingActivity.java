package edu.android.teamproject;

import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingActivity extends AppCompatActivity {

    private ImageView imageLoading;
    private TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);

        textTitle = (TextView) findViewById(R.id.text_loading_title);
        imageLoading = (ImageView) findViewById(R.id.image_loading_logo);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BMJUA.ttf");
        textTitle.setTypeface(typeface);

        startLoading();

    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 500);
    }
}

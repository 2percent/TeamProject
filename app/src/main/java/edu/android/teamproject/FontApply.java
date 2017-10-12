package edu.android.teamproject;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by STU on 2017-10-10.
 */

public class FontApply extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/BMJUA.ttf"))
                .addBold(Typekit.createFromAsset(this, "fonts/BMJUA.ttf"));

    }
}


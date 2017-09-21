package edu.android.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity {

    ImageButton btn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (ImageButton) findViewById(R.id.confirm);
        intent = new Intent(this,MatchActivity.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });
    }
}

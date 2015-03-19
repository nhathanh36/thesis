package com.english_for_kid.thanhnha.englishforkid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.english_for_kid.thanhnha.englishforkid.R;

/**
 * Created by Thanh Nha on 2/9/2015.
 */
public class Home extends Activity {
    private View trochoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        trochoi = (View) findViewById(R.id.trochoi);

        trochoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ChooseGame.class);
                startActivity(intent);
            }
        });
    }
}

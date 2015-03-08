package com.english_for_kid.thanhnha.englishforkid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.english_for_kid.thanhnha.englishforkid.R;
import com.english_for_kid.thanhnha.englishforkid.model.MyDatabase;

/**
 * Created by Thanh Nha on 2/9/2015.
 */
public class Home extends Activity {
    private MyDatabase db = new MyDatabase(this);
    private View layout_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        layout_start = (View) findViewById(R.id.layout_start);

        layout_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Learning.class);
                startActivity(intent);
            }
        });
    }
}

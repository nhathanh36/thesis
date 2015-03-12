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
    private View look_n_choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        look_n_choose = (View) findViewById(R.id.lookNChoose);

        look_n_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, LookChoose.class);
                startActivity(intent);
            }
        });
    }
}

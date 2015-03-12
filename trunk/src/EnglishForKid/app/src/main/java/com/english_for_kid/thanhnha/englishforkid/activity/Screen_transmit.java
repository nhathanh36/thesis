package com.english_for_kid.thanhnha.englishforkid.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.english_for_kid.thanhnha.englishforkid.Constant;
import com.english_for_kid.thanhnha.englishforkid.R;
import com.english_for_kid.thanhnha.englishforkid.model.*;
import com.english_for_kid.thanhnha.englishforkid.model.MyDatabase;


public class Screen_transmit extends ActionBarActivity {
    private MyDatabase db = new MyDatabase(this);
    //////////////////////////////
    private int id[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    private String name[] = {"Lion", "Pig", "Tiger", "Chicken", "Dog", "Cat",
            "Rose", "Apricot", "Orchid", "Lily", "Sunflower", "Cherry blossom",
            "Apple", "Orange", "Banana", "Grape", "Mango", "Pineapple",
            "Lemon", "Cherry"};
    private int src[] = {R.drawable.lion,R.drawable.pig, R.drawable.tiger, R.drawable.chicken,R.drawable.dog, R.drawable.cat,
            R.drawable.rose,R.drawable.apricot,R.drawable.orchid, R.drawable.lily, R.drawable.sunflower, R.drawable.cherry_blossom,
            R.drawable.apple,R.drawable.orange,R.drawable.banana,R.drawable.grape,R.drawable.mango,R.drawable.pineapple,R.drawable.lemon,R.drawable.cherry};
    private int group[] = {1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,3,3};
    @Override
    ////////////////////////////////////
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transmit_activity);

        //Dùng cài đặt sau 5 giây màn hình tự chuyển
        Thread bamgio = new Thread(){
            public void run() {
                try {
                    db.open();
                    db.deleteAllVocabulary();
                    for (int i = 0; i < Constant.NUMVOCABULARY; i++) {
                        Vocabulary vb = new Vocabulary(id[i], name[i], src[i], group[i]);
                        db.createVocabulary(vb);
                    }
                    db.close();
                    sleep(2000);
                } catch (Exception e) { }
                finally
                { Intent intent = new Intent(Screen_transmit.this, Home.class);
                    startActivity(intent);
                }
            }
        };
        bamgio.start();
    }

    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.english_for_kid.thanhnha.englishforkid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.english_for_kid.thanhnha.englishforkid.R;
import com.english_for_kid.thanhnha.englishforkid.model.Group_VB;
import com.english_for_kid.thanhnha.englishforkid.model.MyDatabase;
import com.english_for_kid.thanhnha.englishforkid.model.Vocabulary;


public class Screen_transmit extends Activity {
    private MyDatabase db = new MyDatabase(this);
    //////////////////////////////
    private String name[] = {
            "Lion", "Pig", "Tiger", "Chicken", "Dog",
            "Cat", "Ant", "Turtle", "Fish", "Zebra",
            "Panda", "Hippo", "Bird", "Butterfly", "Frog",
            "Bee", "Penguin", "Rabbit", "Elephant", "Cow",
            "Crab", "Duck",
            /////////////////////////////////////////////////////
            "Rose", "Apricot", "Orchid", "Lily", "Sunflower",
            "Cherry blossom", "Lotus", "Tulip",
            //////////////////////////////////////
            "Apple", "Orange", "Banana", "Grape", "Mango",
            "Pineapple", "Lemon", "Cherry", "Peppers", "Grapefruit",
            "Watermelon","Mangosteen", "Pumpkin", "Carrot", "Avocado",
            "Tomato", "Coconut","Strawberry",
            /////////////////////////
            "A", "B", "C", "D", "E", "F","G","H","I","J",
            "K","L","M","N","O","P","Q","R","S","T",
            "U","V","W","X","Y","Z",
            //////////////////////////
            "Ambulance", "Bike", "Bus", "Car", "Cruise Bike",
            "Helicopter", "Plane", "Ship", "Train", "Truck",
            /////////////////////////////////////////////////
            "Bells", "Candles", "Candy Cane", "Christmas Card", "Christmas Tree",
            "Presents", "Reindeer", "Snowman", "Santa claus", "Stocking",
            ///////////////////////////////////////////////////////
            "Badminton","Basketball","Cycling","Dance","Fencing",
            "Horse race","Hurdler","Ice-skating","Kayak","Rugby",
            "Scuba-diving","Soccer","Gymnastics"};

    private int src[] = {
            R.drawable.lion,R.drawable.pig, R.drawable.tiger,
            R.drawable.chicken,R.drawable.dog, R.drawable.cat,
            R.drawable.ant,R.drawable.turtle1,R.drawable.fish,
            R.drawable.zebra,R.drawable.panda,R.drawable.hippopotamus,
            R.drawable.bird,R.drawable.butterfly,R.drawable.frog,
            R.drawable.bee,R.drawable.penguin,R.drawable.rabbit1,
            R.drawable.elephant,R.drawable.cow,R.drawable.crab,
            R.drawable.duck,
            /////////////////////////////////////////////////////
            R.drawable.rose,R.drawable.apricot,R.drawable.orchid,
            R.drawable.lily, R.drawable.sunflower, R.drawable.cherry_blossom1,
            R.drawable.lotus, R.drawable.tulip,
            ////////////////////////////////////////////////////////
            R.drawable.apple,R.drawable.orange,R.drawable.banana,
            R.drawable.grape,R.drawable.mango,R.drawable.pineapple,
            R.drawable.lemon,R.drawable.cherry,R.drawable.peppers,
            R.drawable.grapefruit,R.drawable.watermelon,R.drawable.mangosteen,
            R.drawable.pumpkin,R.drawable.carrot,R.drawable.avocado,
            R.drawable.tomato,R.drawable.coconut,R.drawable.strawberry,
            /////////////////////////////////////////
            R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,
            R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,
            R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,
            R.drawable.m,R.drawable.n,R.drawable.o,R.drawable.p,
            R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t,
            R.drawable.u,R.drawable.v,R.drawable.w,R.drawable.x,
            R.drawable.y,R.drawable.z,
            /////////////////////////////////////////////////////////////
            R.drawable.ambulance,R.drawable.bike,R.drawable.bus,
            R.drawable.car,R.drawable.cruisebike,R.drawable.helicopter,
            R.drawable.plane,R.drawable.ship,R.drawable.train,R.drawable.truck,
            ///////////////////////////////////////////////////////
            R.drawable.bells2, R.drawable.candles,R.drawable.candycane,
            R.drawable.christmascard,R.drawable.christmastree,
            R.drawable.presents,R.drawable.reindeer,R.drawable.snowman1,
            R.drawable.santaclaus,R.drawable.stocking,
            ///////////////////////////////////////////////////////////
            R.drawable.badminton,R.drawable.basketball,R.drawable.cycling,
            R.drawable.dance,R.drawable.fencing1,R.drawable.horse_race,
            R.drawable.hurdler,R.drawable.ice_skating,R.drawable.kayak,
            R.drawable.rugby,R.drawable.scuba_diving,R.drawable.soccer,
            R.drawable.gymnastics};

    private int group[] = {
            1,1,1,1,1,
            1,1,1,1,1,
            1,1,1,1,1,
            1,1,1,1,1,
            1,1,
            //////////
            2,2,2,2,2,
            2,2,2,
            /////////
            3,3,3,3,3,
            3,3,3,3,3,
            3,3,3,3,3,
            3,3,3,
            ///////////
            4,4,4,4,4,
            4,4,4,4,4,
            4,4,4,4,4,
            4,4,4,4,4,
            4,4,4,4,4,
            4,
            ////////
            5,5,5,5,5,
            5,5,5,5,5,
            /////////
            6,6,6,6,6,
            6,6,6,6,6,
            /////////
            7,7,7,7,7,
            7,7,7,7,7,
            7,7,7};

    String[] name_group = {"Động vật", "Loài hoa", "Trái cây",
            "Bảng chữ cái", "Phương tiện", "Giáng sinh",
            "Thể thao"};

    int[] image_group = {R.drawable.pig,R.drawable.lily,R.drawable.apple,
            R.drawable.a,R.drawable.ambulance,R.drawable.christmastree,
            R.drawable.ice_skating};
    ////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transmit_activity);

        //Dùng cài đặt sau 5 giây màn hình tự chuyển
        Thread bamgio = new Thread(){
            public void run() {
                try {
                    db.open();
                    db.deleteAllVocabulary();
                    for (int i = 0; i < name.length; i++) {
                        Vocabulary vb = new Vocabulary(i + 1, name[i], src[i], group[i]);
                        db.createVocabulary(vb);
                    }
                    db.close();
                    db.open();
                    db.deleteAllGroup();
                    for (int i = 0; i < name_group.length; i++) {
                        Group_VB group = new Group_VB(i + 1, image_group[i], name_group[i] );
                        db.createGroup(group);
                    }
                    db.close();
                } catch (Exception e) { }
                finally {
                    Intent intent = new Intent(Screen_transmit.this, Home.class);
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

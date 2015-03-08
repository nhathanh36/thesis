package com.english_for_kid.thanhnha.englishforkid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.english_for_kid.thanhnha.englishforkid.R;
import com.english_for_kid.thanhnha.englishforkid.Utility;
import com.english_for_kid.thanhnha.englishforkid.model.MyDatabase;
import com.english_for_kid.thanhnha.englishforkid.model.Vocabulary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;

import static com.english_for_kid.thanhnha.englishforkid.R.drawable.home_look;

/**
 * Created by Thanh Nha on 2/10/2015.
 */
public class Learning extends Activity {
    private MyDatabase db = new MyDatabase(this);
    /*private Vocabulary vocabularyRandom;
    private List<Vocabulary> vocabularies;
    private List<Vocabulary> vocabulariesSameGroup = new ArrayList<Vocabulary>();
    private Random random = new Random();
    private List<TextView> textViews = new ArrayList<TextView>();
    private TextView tv_Random_Answer;
    private static int point = 0; // To get point of the question, static will keep the value of point
    private static int count = 0; // To count the time loading activity
    private PopupWindow popupWindow;
    private Button btnClosePopup;

    public Learning() {
    }
    /*private EditText editText;
    private Button btnClosePopup;
    private Button btnCreatePopup;
    private Button btnSaveVB;
    private PopupWindow popupWindow;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_activity);

        Vocabulary vb1 = new Vocabulary(1, "Lion", "D:/THESIS/Hinh anh/ABC.png", 1);

        db.open();
        db.createVocabulary(vb1);
        db.close();

        // Get image
        Bitmap myBitmap = BitmapFactory.decodeFile(vb1.getImage());
        // Image view used to set the bitmap
        ImageView myImage = (ImageView) findViewById(R.id.imageA);
        // Setting the image to the image view
        myImage.setImageBitmap(myBitmap);

        /*Vocabulary vb2 = new Vocabulary(1, "Pig", 1);
        Vocabulary vb3 = new Vocabulary(2, "Tiger", 1);
        Vocabulary vb4 = new Vocabulary(3, "Chicken", 1);

        Vocabulary vb5 = new Vocabulary(4, "Rose", 2);
        Vocabulary vb6 = new Vocabulary(5, "Apricot", 2);
        Vocabulary vb7 = new Vocabulary(6, "Orchid", 2);
        Vocabulary vb8 = new Vocabulary(7, "Lily", 2);
        Vocabulary vb9 = new Vocabulary(8, "Sunflower", 2);
        Vocabulary vb10 = new Vocabulary(9, "Cherry blossom", 2);

        Vocabulary vb11 = new Vocabulary(10, "Apple", 3);
        Vocabulary vb12 = new Vocabulary(11, "Orange", 3);
        Vocabulary vb13 = new Vocabulary(12, "Banana", 3);
        Vocabulary vb14 = new Vocabulary(13, "Grape", 3);
        Vocabulary vb15 = new Vocabulary(14, "Mango", 3);
        Vocabulary vb16 = new Vocabulary(15, "Pineapple", 3);
        Vocabulary vb17 = new Vocabulary(16, "Lemon", 3);
        Vocabulary vb18 = new Vocabulary(17, "Cherry", 3);


        db.open();
        db.createVocabulary(vb1);
        db.createVocabulary(vb2);
        db.createVocabulary(vb3);
        db.createVocabulary(vb4);
        db.createVocabulary(vb5);
        db.createVocabulary(vb6);
        db.createVocabulary(vb7);
        db.createVocabulary(vb8);
        db.createVocabulary(vb9);
        db.createVocabulary(vb10);
        db.createVocabulary(vb11);
        db.createVocabulary(vb12);
        db.createVocabulary(vb13);
        db.createVocabulary(vb14);
        db.createVocabulary(vb15);
        db.createVocabulary(vb16);
        db.createVocabulary(vb17);
        db.createVocabulary(vb18);
        db.close();

        db.open();
        vocabularyRandom = db.getRandomVocabulary();
        vocabularies = db.getAllVocabulary();
        db.close();

        /**
         * Get vocabularies in the same group
         */
        /*for (int i = 0; i < vocabularies.size(); i++) {
            if (vocabularies.get(i).getGroup() == vocabularyRandom.getGroup()) {
                vocabulariesSameGroup.add(vocabularies.get(i));
            }
        }

        final int sizeOfVocabularySameGroup = vocabulariesSameGroup.size();
        final TextView tv_Question = (TextView) findViewById(R.id.textViewQuestion);
        tv_Question.setText(vocabularyRandom.getName());

        /**
         * Get random TextView
         * To set correct answer's position
         */
        /*textViews.add((TextView) findViewById(R.id.textViewA));
        textViews.add((TextView) findViewById(R.id.textViewB));
        textViews.add((TextView) findViewById(R.id.textViewC));
        textViews.add((TextView) findViewById(R.id.textViewD));

        tv_Random_Answer = textViews.get(random.nextInt(textViews.size()));
        tv_Random_Answer.setText(vocabularyRandom.getName());
        textViews.remove(tv_Random_Answer);

        /**
         * Get position of 3 answer except correct answer's position above
         * Load 3 vocabulary into 3 random position
         */
        /*ListIterator listIterator = vocabulariesSameGroup.listIterator();
        while (listIterator.hasNext()){
            Object obVoca = listIterator.next();
            Vocabulary voca = (Vocabulary) obVoca;
            if ((voca.getName()).equals(vocabularyRandom.getName())) {
                listIterator.remove();
            }
        }
        for (int i = 0; i < 3; i++) {
            TextView textView = textViews.get(random.nextInt(textViews.size()));
            Vocabulary vocabulary_answer = vocabulariesSameGroup.get(
                    random.nextInt(vocabulariesSameGroup.size()));
            textView.setText(vocabulary_answer.getName());
            vocabulariesSameGroup.remove(vocabulary_answer);
            textViews.remove(textView);
        }

        /**
         * Check user's answer if it's correct
         * Increase point if it's correct
         */
        /*final List<TextView> tvCheckOnClick = new ArrayList<TextView>();
        tvCheckOnClick.add((TextView) findViewById(R.id.textViewA));
        tvCheckOnClick.add((TextView) findViewById(R.id.textViewB));
        tvCheckOnClick.add((TextView) findViewById(R.id.textViewC));
        tvCheckOnClick.add((TextView) findViewById(R.id.textViewD));
        for (int i = 0; i <= 3; i++) {
            final int finalI = i;
            (tvCheckOnClick.get(i)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String getValueTextView = tvCheckOnClick.get(finalI).getText().toString();
                    if (getValueTextView.equals(vocabularyRandom.getName())) {
                        point++;
                        TextView tvPoint = (TextView) findViewById(R.id.imageA);
                        tvPoint.setText(String.valueOf(point));
                        if (count++ == 2) {
                                (tvCheckOnClick.get(finalI)).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            // We need to get the instance of the LayoutInflater
                                            LayoutInflater inflater = (LayoutInflater) Learning.this
                                                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                            View layout = inflater.inflate(R.layout.popup_layout,
                                                    (ViewGroup) findViewById(R.id.popup_element));

                                            popupWindow = new PopupWindow(layout, 300, 370, true);
                                            popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

                                            TextView tvResult = (TextView) layout.findViewById(R.id.textViewResult);
                                            tvResult.setText("Your result: " + String.valueOf(point) + "/20");

                                            btnClosePopup = (Button) layout.findViewById(R.id.btn_close);
                                            btnClosePopup.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    popupWindow.dismiss();
                                                }
                                            });

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            count = 0;
                            point = 0;
                        }
                        // Go to the next question
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    } else {
                        if (count++ == 2) {
                            ListIterator listIt = tvCheckOnClick.listIterator();
                            while (listIt.hasNext()) {
                                Object ob = listIt.next();
                                TextView tv = (TextView) ob;
                                tv.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            // We need to get the instance of the LayoutInflater
                                            LayoutInflater inflater = (LayoutInflater) Learning.this
                                                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                            View layout = inflater.inflate(R.layout.popup_layout,
                                                    (ViewGroup) findViewById(R.id.popup_element));

                                            popupWindow = new PopupWindow(layout, 300, 370, true);
                                            popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

                                            TextView tvResult = (TextView) layout.findViewById(R.id.textViewResult);
                                            tvResult.setText("Your result: " + String.valueOf(point) + "/20");

                                            btnClosePopup = (Button) layout.findViewById(R.id.btn_close);
                                            btnClosePopup.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    popupWindow.dismiss();
                                                }
                                            });

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                            count = 0;
                            point = 0;
                        }
                        // Go to the next question
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                }
            });
        }*/
    }
}

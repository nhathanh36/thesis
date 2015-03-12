package com.english_for_kid.thanhnha.englishforkid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.english_for_kid.thanhnha.englishforkid.Constant;
import com.english_for_kid.thanhnha.englishforkid.R;
import com.english_for_kid.thanhnha.englishforkid.model.MyDatabase;
import com.english_for_kid.thanhnha.englishforkid.model.Question_Look;
import com.english_for_kid.thanhnha.englishforkid.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Thanh Nha on 2/10/2015.
 */
public class LookChoose extends Activity {
    private MyDatabase db = new MyDatabase(this);
    private List<Vocabulary> vocabulariesRandom;
    private List<Vocabulary> vocabularies = new ArrayList<Vocabulary>();
    private List<Question_Look> listquestion = new ArrayList<Question_Look>();
    private Question_Look question = new Question_Look();
    private ImageView imageViewQuestion = null;
    private List<TextView> textViews = new ArrayList<TextView>();
    private static TextView tv = null;
    private static int i = 1;

    private int id[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    private String name[] = {"Lion", "Pig", "Tiger", "Chicken", "Dog", "Cat",
                             "Rose", "Apricot", "Orchid", "Lily", "Sunflower", "Cherry blossom",
                             "Apple", "Orange", "Banana", "Grape", "Mango", "Pineapple",
                             "Lemon", "Cherry"};
    private int src[] = {R.drawable.lion,R.drawable.pig, R.drawable.tiger, R.drawable.chicken,R.drawable.dog, R.drawable.cat,
                         R.drawable.rose,R.drawable.apricot,R.drawable.orchid, R.drawable.lily, R.drawable.sunflower, R.drawable.cherry_blossom,
                         R.drawable.apple,R.drawable.orange,R.drawable.banana,R.drawable.grape,R.drawable.mango,R.drawable.pineapple,R.drawable.lemon,R.drawable.cherry};
    private int group[] = {1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,3,3};
    public LookChoose() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_choose_activity);

        db.open();
        db.deleteAllVocabulary();
        for (int i = 0; i < Constant.NUMVOCABULARY; i++) {
            Vocabulary vb = new Vocabulary(id[i], name[i], src[i], group[i]);
            db.createVocabulary(vb);
        }
        vocabulariesRandom = db.getRandomVocabularies();
        vocabularies = db.getAllVocabulary();

        listquestion = db.listQuestion(Constant.NUMQUESTION);

        //question = db.getQuestion();
        db.close();

        LoadQuestion(listquestion.get(0));
        //LoadQuestion(question);

            textViews.get(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (i < listquestion.size()) {
                        LoadQuestion(listquestion.get(i));
                        i++;
                    }
                }
            });

        textViews.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < listquestion.size()) {
                    LoadQuestion(listquestion.get(i));
                    i++;
                }
            }
        });

        textViews.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < listquestion.size()) {
                    LoadQuestion(listquestion.get(i));
                    i++;
                }
            }
        });

        if (i == Constant.NUMQUESTION)
        {
            Toast toast = Toast.makeText(LookChoose.this, "10", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    protected void onDestroy() {
        //imageQuestion = null;
        super.onDestroy();


    }

    public void LoadQuestion(Question_Look question)
    {
        textViews.clear();

        textViews.add((TextView) findViewById(R.id.textViewA));
        textViews.add((TextView) findViewById(R.id.textViewB));
        textViews.add((TextView) findViewById(R.id.textViewC));
        textViews.add((TextView) findViewById(R.id.textViewD));

        Random random = new Random();
        int pos = random.nextInt(textViews.size());
        tv = textViews.get(random.nextInt(textViews.size()));
        tv.setText(question.getCorrectAnswer());
        textViews.remove(tv);


        ImageView imageQuestion = (ImageView) findViewById(R.id.imageQuestion);
        imageQuestion = null;
        imageQuestion.setImageResource(question.getImage());

        String[] answers_wrong ={ question.getAnswerB(), question.getAnswerC(), question.getAnswerD() };
        int arrayPos = 0;
        for (int i = 0; i < textViews.size(); i++) {
            //if (i != pos) {
                (textViews.get(i)).setText(answers_wrong[arrayPos]);
                arrayPos++;
            //}
        }
    }


        /*Vocabulary vocabulary = vocabulariesRandom.get(0);
        Question_Look question_look = new Question_Look();
        question_look.setImage(vocabulary.getImage());
        question_look.setCorrectAnswer(vocabulary.getName());
        question_look.setGroup(vocabulary.getGroup());
        question_look.setAnswerB("");
        question_look.setAnswerC("");
        question_look.setAnswerD("");

        ListIterator<Vocabulary> vbRemove = vocabularies.listIterator();
        while (vbRemove.hasNext()) {
            Vocabulary vocabulary1 = vbRemove.next();
            if (vocabulary.getName() == vocabulary1.getName()) {
                vocabularies.remove(vocabulary1);
            }
        }
        ListIterator<Vocabulary> vocabularyListIterator = vocabularies.listIterator();
        while (vocabularyListIterator.hasNext()){
            Vocabulary vocabulary_temp = vocabularyListIterator.next();
            if (vocabulary_temp.getGroup() == question_look.getGroup()) {
                if (question_look.getAnswerB().equals(""))
                    question_look.setAnswerB(vocabulary_temp.getName());
                else if (question_look.getAnswerC().equals(""))
                    question_look.setAnswerC(vocabulary_temp.getName());
                else question_look.setAnswerD(vocabulary_temp.getName());

                if (!question_look.getAnswerB().equals("") && !question_look.getAnswerC().equals("")
                       && !question_look.getAnswerD().equals("")) break;
            }
        }
        questions.add(question_look);
        if (imageViewQuestion != null)
            ((BitmapDrawable)imageViewQuestion.getBackground()).getBitmap().recycle();
        else {
            imageViewQuestion = (ImageView) findViewById(R.id.imageQuestion);
            imageViewQuestion.setImageResource(question_look.getImage());       // OutOfMemoryError
        }
        TextView tv_Random_Answer = textViews.get(random.nextInt(textViews.size()));
        tv_Random_Answer.setText(question_look.getCorrectAnswer());
        textViews.remove(tv_Random_Answer);

        String[] answers_wrong ={question_look.getAnswerB(),
                                 question_look.getAnswerC(),question_look.getAnswerD()};
        for (int i = 0; i < 3; i++) {
            (textViews.get(i)).setText(answers_wrong[i]);
        }

        /*for (int i = 0; i < 3; i++) {
            ListIterator<Vocabulary> listIterator = vocabulariesRandom.listIterator();
            while (listIterator.hasNext()) {
                Vocabulary vocabulary1 = listIterator.next();
                textViews.get(i).setText(vocabulary1.getName());
            }
        }

        /*tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageQuestion.setImageResource(vocabulariesRandom.get(i).getImage());
                tv1.setText(vocabulariesRandom.get(i).getName());
                i++;
            }
        });*/

        //ImageView imageQuestion = (ImageView) findViewById(R.id.imageQuestion);
        //ListIterator<Vocabulary> listIterator = vocabulariesRandom.listIterator();
        /*if (listIterator.hasNext()) {
            Vocabulary vocabulary = (Vocabulary) listIterator.next();
            imageQuestion.setImageResource(vocabulary.getImage());

            TextView tv = (TextView) findViewById(R.id.textViewA);
            tv.setText(vocabulary.getName());

        }
            /*questions.add(question_look);
            if (question_look.getImage().)
            question_look.setCorrectAnswer(vocabulary.getName());
            question_look.setGroup(vocabulary.getGroup());
            questions.add(question_look);
        }
        ListIterator<Question_Look> questionListIterator = questions.listIterator();
        ImageView imageQuestion = (ImageView) findViewById(R.id.imageViewQuestion);
        if (questionListIterator.hasNext()) {
            Question_Look question_look = questionListIterator.next();
            imageQuestion.setImageResource(question_look.getImage());

            textViews.add((TextView) findViewById(R.id.textViewA));
            textViews.add((TextView) findViewById(R.id.textViewB));
            textViews.add((TextView) findViewById(R.id.textViewC));
            textViews.add((TextView) findViewById(R.id.textViewD));


        }

        /*ListIterator<Question_Look> questionListIterator = questions.listIterator();
        while (listIterator.hasNext()){
            Vocabulary vocabulary = (Vocabulary) listIterator.next();
            while (questionListIterator.hasNext()) {
                Question_Look question_look = (Question_Look) questionListIterator.next();
                if (vocabulary.getName() != question_look.getAnswerA()
                                         && vocabulary.getGroup() == question_look.getGroup()) {
                    if (question_look.getAnswerB().equals(""))
                        question_look.setAnswerB(vocabulary.getName());
                    else if (question_look.getAnswerC().equals(""))
                        question_look.setAnswerC(vocabulary.getName());
                    else question_look.setAnswerD(vocabulary.getName());
                }
                questions.add(question_look);
            }
        }

        ImageView imageQuestion = (ImageView) findViewById(R.id.imageViewQuestion);
        if (questionListIterator.hasNext()) {
            Question_Look question_look = questionListIterator.next();
            imageQuestion.setImageResource(question_look.getImage());
            /**
             * Get random TextView
             * To set correct answer's position
             */
            /*textViews.add((TextView) findViewById(R.id.textViewA));
            textViews.add((TextView) findViewById(R.id.textViewB));
            textViews.add((TextView) findViewById(R.id.textViewC));
            textViews.add((TextView) findViewById(R.id.textViewD));

            Random random = new Random();
            TextView tv_Random_Answer = textViews.get(random.nextInt(textViews.size()));
            tv_Random_Answer.setText(question_look.getAnswerA());
            textViews.remove(tv_Random_Answer);

            /*List<String> answers_wrong = new ArrayList<>();
            answers_wrong.add(question_look.getAnswerB());
            answers_wrong.add(question_look.getAnswerC());
            answers_wrong.add(question_look.getAnswerD());
            for (int i = 0; i < 3; i++) {
                (textViews.get(i)).setText(answers_wrong.get(i));
            }
        }
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
    //}
}

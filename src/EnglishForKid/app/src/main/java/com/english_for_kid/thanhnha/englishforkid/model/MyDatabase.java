package com.english_for_kid.thanhnha.englishforkid.model;

/**
 * Created by Thanh Nha on 2/27/2015.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyDatabase {
    private static final String DATABASE_NAME = "DB5";
    private static final int DATABASE_VERSION = 1;

    /**
     * Table Vocabulary:
     * Attribute: ID, Name, Image, Group
     */
    private static final String VOCABULARY_TABLE = "VOCABULARY";
    private static final String ID_VB = "ID_VB";
    private static final String NAME_VB = "NAME_VB";
    private static final String IMAGE_VB = "IMAGE_VB";
    private static final String GROUP_VB = "GROUP_VB";

    private static final String CREATE_TABLE_VOCALBULARY = "CREATE TABLE " + VOCABULARY_TABLE + " ("
                                                            + ID_VB + " INTEGER PRIMARY KEY, "
                                                            + NAME_VB + " VARCHAR NOT NULL UNIQUE, "
                                                            + IMAGE_VB + " INTEGER NOT NULL, "
                                                            + GROUP_VB + " INTEGER NOT NULL);";


    /*public String createTable() {
        return CREATE_TABLE_VOCALBULARY;
    }
    /**
     * Table Group:
     * Attribute: ID, Name
     */
   /* private static final String GROUP_TABLE = "GROUP";
    private static final String ID_GROUP = "ID";
    private static final String NAME_GROUP = "NAME";

    private static final String CREATE_TABLE_GROUP = "CREATE TABLE " + GROUP_TABLE + " ("
                                                      + ID_GROUP + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                      + NAME_GROUP + " VARCHAR NOT NULL);";*/

    private static Context context;
    static SQLiteDatabase db;
    private OpenHelper openHelper;

    public MyDatabase(Context c){
        MyDatabase.context = c;
    }

    public MyDatabase open() throws SQLException{
        openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        openHelper.close();
    }

    /*public void createVocabulary(Vocabulary vocabulary) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_VB, vocabulary.getId());
        contentValues.put(NAME_VB, vocabulary.getName());
        contentValues.put(IMAGE_VB, Utility.getBytes(vocabulary.getImage()));
        contentValues.put(GROUP_VB, vocabulary.getGroup());

        //db.insert(VOCABULARY_TABLE, null, contentValues);
    }*/

    public String createVocabulary(Vocabulary vocabulary) {
        String a = "INSERT OR IGNORE INTO VOCABULARY(ID_VB, NAME_VB, IMAGE_VB, GROUP_VB) VALUES (" + vocabulary.getId()
                                + ", '" + vocabulary.getName() + "', " + vocabulary.getImage() + ", "
                                + vocabulary.getGroup() + ")";

        db.execSQL(a);
        return a;
    }

    public void deleteVocabulary(Vocabulary vocabulary) {
        String id = String.valueOf(vocabulary.getId());
        db.delete(VOCABULARY_TABLE, ID_VB + "=?", new String[] {id});
    }

    public void deleteAllVocabulary() {
        String a = "DELETE FROM " + VOCABULARY_TABLE;
        db.execSQL(a);
    }

    public List<Vocabulary> getRandomVocabularies() {
        List<Vocabulary> vocabularies = new ArrayList<Vocabulary>();
        Cursor cursor = db.rawQuery("SELECT * FROM VOCABULARY ORDER BY RANDOM() LIMIT 1", null);
        if (cursor.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setId(cursor.getInt(cursor.getColumnIndex(ID_VB)));
                vocabulary.setName(cursor.getString(cursor.getColumnIndex(NAME_VB)));
                vocabulary.setImage(cursor.getInt(cursor.getColumnIndex(IMAGE_VB)));
                vocabulary.setGroup(cursor.getInt(cursor.getColumnIndex(GROUP_VB)));
                vocabularies.add(vocabulary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vocabularies;
    }

    public List<Vocabulary> getAllRandomVocabularies() {
        List<Vocabulary> vocabularies = new ArrayList<Vocabulary>();
        Cursor cursor = db.rawQuery("SELECT * FROM VOCABULARY ORDER BY RANDOM()", null);
        if (cursor.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setId(cursor.getInt(cursor.getColumnIndex(ID_VB)));
                vocabulary.setName(cursor.getString(cursor.getColumnIndex(NAME_VB)));
                vocabulary.setImage(cursor.getInt(cursor.getColumnIndex(IMAGE_VB)));
                vocabulary.setGroup(cursor.getInt(cursor.getColumnIndex(GROUP_VB)));
                vocabularies.add(vocabulary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vocabularies;
    }

    /**
     *
     * @return
     */
    public Question_Look getQuestion() {
        Question_Look question = new Question_Look();
        List<Vocabulary> vocabularyRandom = new ArrayList<Vocabulary>();
        List<Vocabulary> vocabulariesNoDuplicate = new ArrayList<Vocabulary>();
        while(vocabulariesNoDuplicate.size() < 4) {
            vocabularyRandom = getAllRandomVocabularies();
            HashSet<Vocabulary> hashSet = new HashSet<Vocabulary>(vocabularyRandom);
            List<Vocabulary> list = new ArrayList<Vocabulary>(hashSet);
            for (Object item : list) {
                Vocabulary vocabulary = (Vocabulary) item;
                if (vocabulariesNoDuplicate.size() == 0) {
                    vocabulariesNoDuplicate.add(vocabulary);
                }
                else {
                    if (vocabulariesNoDuplicate.get(0).getGroup() == vocabulary.getGroup()) {
                        vocabulariesNoDuplicate.add(vocabulary);
                    }
                }
            }
        }
        question.setCorrectAnswer(vocabulariesNoDuplicate.get(0).getName());
        question.setImage(vocabulariesNoDuplicate.get(0).getImage());
        question.setAnswerB(vocabulariesNoDuplicate.get(1).getName());
        question.setAnswerC(vocabulariesNoDuplicate.get(2).getName());
        question.setAnswerD(vocabulariesNoDuplicate.get(3).getName());
        return question;
    }

    public List<Question_Look> listQuestion(int maxQuestion)
    {
        boolean flag = false;
        Question_Look question = new Question_Look();
        List<Question_Look> listQNoDuplicate = new ArrayList<Question_Look>();
        question = getQuestion();
        listQNoDuplicate.add(question);

        while (listQNoDuplicate.size() < maxQuestion)
        {
            Question_Look temp = getQuestion();
            for(int i = 0; i < listQNoDuplicate.size(); i++)
            {
                if(temp.getCorrectAnswer() == listQNoDuplicate.get(i).getCorrectAnswer()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                listQNoDuplicate.add(temp);
            }
        }
        return  listQNoDuplicate;
    }


    public Vocabulary getVocabularyByName(String name) {
        String[] columns = new String[] {ID_VB, NAME_VB, IMAGE_VB, GROUP_VB};

        Cursor cur = db.query(VOCABULARY_TABLE, columns, NAME_VB + "=?",
        new String[]{name}, null, null, null);

        if (cur.moveToFirst()) {
            int id = cur.getInt(cur.getColumnIndex(ID_VB));
            int image = cur.getInt(cur.getColumnIndex(IMAGE_VB));
            int group = cur.getInt(cur.getColumnIndex(GROUP_VB));
            cur.close();
            return new Vocabulary(id, name, image, group);
        }
        cur.close();
        return null;
    }

    public Vocabulary getVocabularyByImage(int image) {
        String str_image = String.valueOf(image);
        String[] columns = new String[] {ID_VB, NAME_VB, IMAGE_VB, GROUP_VB};

        Cursor cur = db.query(VOCABULARY_TABLE, columns, NAME_VB + "=?",
                new String[]{str_image}, null, null, null);

        if (cur.moveToFirst()) {
            int id = cur.getInt(cur.getColumnIndex(ID_VB));
            String name = cur.getString(cur.getColumnIndex(NAME_VB));
            int group = cur.getInt(cur.getColumnIndex(GROUP_VB));
            cur.close();
            return new Vocabulary(id, name, image, group);
        }
        cur.close();
        return null;
    }

    public List<Vocabulary> getAllVocabulary() {
        List<Vocabulary> vocabularies = new ArrayList<Vocabulary>();

        String query = "SELECT * FROM " + VOCABULARY_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setId(cursor.getInt(cursor.getColumnIndex(ID_VB)));
                vocabulary.setName(cursor.getString(cursor.getColumnIndex(NAME_VB)));
                vocabulary.setImage(cursor.getInt(cursor.getColumnIndex(IMAGE_VB)));
                vocabulary.setGroup(cursor.getInt(cursor.getColumnIndex(GROUP_VB)));
                vocabularies.add(vocabulary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return vocabularies;
    }

    public String getAllVocabulary1() {
        String query = "SELECT * FROM " + VOCABULARY_TABLE + " LIMIT 5";
        String temp = "";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                temp += cursor.getInt(cursor.getColumnIndex(ID_VB)) + "-"
                     + cursor.getString(cursor.getColumnIndex(NAME_VB)) + "-"
                     + cursor.getInt(cursor.getColumnIndex(IMAGE_VB)) + "-"
                     + cursor.getInt(cursor.getColumnIndex(GROUP_VB)) + "END";
            } while (cursor.moveToNext());
        }
        cursor.close();
        return temp;
    }

/**
 * class OpenHelper
 */
private static class OpenHelper extends SQLiteOpenHelper {
    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        arg0.execSQL(CREATE_TABLE_VOCALBULARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS " + VOCABULARY_TABLE);
    }
}
}

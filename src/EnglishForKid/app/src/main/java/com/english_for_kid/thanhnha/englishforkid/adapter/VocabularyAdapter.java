package com.english_for_kid.thanhnha.englishforkid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.english_for_kid.thanhnha.englishforkid.model.Vocabulary;

import java.util.ArrayList;

/**
 * Created by Thanh Nha on 3/4/2015.
 */
public class VocabularyAdapter extends BaseAdapter {

    private Context context;
    ArrayList<Vocabulary> vocabularies = new ArrayList<Vocabulary>();

    // Constructor
    public VocabularyAdapter(Context context, ArrayList<Vocabulary> vocabularies) {
        this.context = context;
        this.vocabularies = vocabularies;
    }
    @Override
    public int getCount() {
        return vocabularies.size();
    }

    @Override
    public Object getItem(int position) {
        return vocabularies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

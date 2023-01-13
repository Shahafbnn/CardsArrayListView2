package com.example.cardsarraylistview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    ArrayList<Card> deck;
    Card current;
    int currentIndex;
    EditText ETN = findViewById(R.id.ETN_DeckInput);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ETN.addTextChangedListener(this);

        deck = new ArrayList<Card>();
        createDeck();
        Collections.shuffle(deck);
        Card current = deck.get(0);
        int currentIndex = 0;


    }
    void updateCurrent(int index){
        if(index < 0 || index > deck.size()-1) return;
        current = deck.get(index);
    }

    public void restart(View view){
        for(int i = 0; i < deck.size(); i++){
            deck.get(i).restart();
        }
    }

    public void createDeck(){
        Card last = new Card((ImageView)findViewById(R.id.IV_Card), 0, 2);
        for(int i = 0; i < 52; i++){
            deck.add(last);
            last.next(last);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(editable.toString() == "null") return;

    }
}
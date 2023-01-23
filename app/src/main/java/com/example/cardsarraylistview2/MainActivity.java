package com.example.cardsarraylistview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    ArrayList<Card> deck;
    ArrayList<Card> used;
    Card current;
    int currentIndex;
    EditText ETN;
    ListView LV;
    ImageView IV_LeftArrow;
    ImageView IV_RightArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ETN = findViewById(R.id.ETN_DeckInput);
        LV = findViewById(R.id.LV);

        IV_LeftArrow = findViewById(R.id.IV_LeftArrow);
        IV_LeftArrow.setOnClickListener(this);
        IV_LeftArrow.setClickable(true);
        IV_LeftArrow.bringToFront();

        IV_RightArrow = findViewById(R.id.IV_RightArrow);
        IV_RightArrow.setOnClickListener(this);
        IV_RightArrow.setClickable(true);
        IV_RightArrow.bringToFront();



        ETN.addTextChangedListener(this);
        used = new ArrayList<Card>();
        deck = new ArrayList<Card>();
        createDeck();
        for(int i = 0; i < deck.size(); i++){
            Log.v("deck" + i, "suit: " + deck.get(i).getSuit() + ", value: " + deck.get(i).getValue());
        }
        Collections.shuffle(deck);
        Card current = deck.get(0);
        int currentIndex = 0;
        LV.setAdapter( new ArrayAdapter<Card>(this, android.R.layout.simple_list_item_1, deck) );

    }
    void updateCurrent(int index){
        if(index < 0 || index > deck.size()-1) return;
        used.add(current);
        current = deck.get(index);
        deck.get(currentIndex).justFlip(getResources(), getPackageName());
    }

    public void restart(View view){
        for(int i = 0; i < deck.size(); i++){
            deck.get(i).restart();
        }
    }

    public void createDeck(){
        Card last = new Card(findViewById(R.id.IV_Card), 0, 2);
        for(int i = 0; i < 52; i++){
            deck.add(last);
            last.next(last);
            last = new Card(last);
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
        if(editable.toString().equals("null")) return;
        if(editable.toString().equals("")) return;

        currentIndex = Integer.parseInt(editable.toString());
        updateCurrent(currentIndex);


    }

    @Override
    public void onClick(View view) {
        Log.d("onClick","OnClick is called");

        if(view==IV_LeftArrow){
            updateCurrent(currentIndex - 1);
            currentIndex++;
            Log.d("onClick","IV_LeftArrow is called");

        }
        if(view==IV_RightArrow){
            //Log.d("onClick","Card: suit:" + deck.get(currentIndex + 1).getSuit() + "value: "+ deck.get(currentIndex ++).getValue());
            updateCurrent(currentIndex + 1);
            if(!(currentIndex < 0 || currentIndex > deck.size()-1)) currentIndex++;
            Log.d("onClick","IV_RightArrow is called");

        }

    }
}
package com.example.assignmenttwo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class GamePlay {
    private static final int MAX_MATCHES = 6;
    private int totalGuesses,totalCorrect = 0;
    private boolean isFirstGuess;
    private Card cardFirst;
    private Card cardSecond;
    private ArrayList<String> cardTypes;
    private ArrayList<Card> cards;
    private TextView textviewGuesses;
    private Context context;

    public GamePlay(Context context){
        this.context = context;
        this.totalGuesses = 0;
        this.totalCorrect = 0;
        this.isFirstGuess = true;
        this.cardTypes = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public void setupGame(){

    }

    public void setupImageviewsAndOnclicks(){

    }

    public void displayCardFaceUp(Card card){

    }

    public void displayCardFaceDown(Card card){

    }

    public void displayCards(){

    }

    public void flipCard(Card card){

    }

    public void onclickCard(View view){

    }

    public void checkCardMatch(){

    }

    public void GameOver(){

    }

    public Card getCardByCardNum(int cardNum){
        return card;
    }

    public void updateGuessesTextview(){

    }
}

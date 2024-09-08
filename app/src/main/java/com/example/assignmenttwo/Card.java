package com.example.assignmenttwo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Card {
    private int cardNum;
    private String cardType, cardBack, cardFront;
    private ImageView imageviewCard;
    private boolean isFaceUp;

    public Card(int cNum, String cType, String cBack, String cFront){
        this.cardNum = cNum;
        this.cardType = cType;
        this.cardBack = cBack;
        this.cardFront = cFront;
        this.isFaceUp = false;  // By default, the card starts face down
    }

    public int getCardNum(){
        return cardNum;
    }

    public String getCardType(){
        return cardType;
    }

    public String getCardBack() {
        return cardBack;
    }

    public String getCardFront(){
        return cardFront;
    }

    public boolean isFaceUp(){
        return isFaceUp;
    }

    public void setFaceUp(boolean faceUp){
        this.isFaceUp = faceUp;
    }

    public ImageView getImageviewCard(){
        return imageviewCard;
    }

    public void setImageviewCard(ImageView ivCard){
        this.imageviewCard = ivCard;
    }
}

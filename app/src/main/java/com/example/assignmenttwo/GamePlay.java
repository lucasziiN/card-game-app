package com.example.assignmenttwo;

import java.util.Collections;
import com.example.assignmenttwo.Card;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class GamePlay extends AppCompatActivity {
    private static final int MAX_MATCHES = 6;
    private int totalGuesses,totalCorrect = 0;
    private boolean isFirstGuess;
    private Card cardFirst;
    private Card cardSecond;
    private ArrayList<String> cardTypes;
    private ArrayList<Card> cards;
    private TextView textviewGuesses;
    private Context context;
    private boolean isBusy = false;

    public GamePlay(Context context){
        this.context = context;
        this.totalGuesses = 0;
        this.totalCorrect = 0;
        this.isFirstGuess = true;
        this.cardTypes = new ArrayList<>();
        this.cards = new ArrayList<>();

        // Initialize the TextView for guesses
        textviewGuesses = ((GameActivity) context).findViewById(R.id.tv_num_guesses);
    }

    public void setupGame(){
        // Add 6 unique cards and their pairs (6 pairs total = 12 cards)
        cards.add(new Card(0, "artist", "img_card_back", "img_card_front_artist"));
        cards.add(new Card(1, "artist", "img_card_back", "img_card_front_artist"));
        cards.add(new Card(2, "astronaut", "img_card_back", "img_card_front_astronaut"));
        cards.add(new Card(3, "astronaut", "img_card_back", "img_card_front_astronaut"));
        cards.add(new Card(4, "coder", "img_card_back", "img_card_front_coder"));
        cards.add(new Card(5, "coder", "img_card_back", "img_card_front_coder"));
        cards.add(new Card(6, "doctor", "img_card_back", "img_card_front_doctor"));
        cards.add(new Card(7, "doctor", "img_card_back", "img_card_front_doctor"));
        cards.add(new Card(8, "police", "img_card_back", "img_card_front_police"));
        cards.add(new Card(9, "police", "img_card_back", "img_card_front_police"));
        cards.add(new Card(10, "scientist", "img_card_back", "img_card_front_scientist"));
        cards.add(new Card(11, "scientist", "img_card_back", "img_card_front_scientist"));

        // Shuffle the cards
        //Collections.shuffle(cards);

        setupImageviewsAndOnclicks();
        // Show a Toast for each card added
        //for (Card card : cards) {
        //    Toast.makeText(context, "Added card: " + card.getCardNum() + " - " + card.getCardType(), Toast.LENGTH_SHORT).show();
        //}
    }

    public void setupImageviewsAndOnclicks() {
        for (int i = 0; i < cards.size(); i++) {
            // Construct the ImageView ID dynamically
            String currIdImageViewCard = "iv_card_" + i;

            // Get the resource ID of the ImageView using getIdentifier
            int resID = ((GameActivity) context).getResources().getIdentifier(currIdImageViewCard, "id", ((GameActivity) context).getPackageName());

            // Find the ImageView by the dynamically generated ID
            ImageView currImageViewCard = ((GameActivity) context).findViewById(resID);

            // If the ImageView exists, set up your logic here
            if (currImageViewCard != null) {
                // Tag the ImageView with the card index
                currImageViewCard.setTag(i);

                // Associate the ImageView with the corresponding Card object
                cards.get(i).setImageviewCard(currImageViewCard);

                // Set OnClickListener to trigger the onclickCard method
                currImageViewCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Call the onclickCard method when a card is clicked
                        onclickCard(v);
                    }
                });
            }
        }
    }


    public void displayCardFaceUp(Card card){
        // Get the front image resource ID
        int cardFrontResId = context.getResources().getIdentifier(card.getCardFront(), "drawable", context.getPackageName());

        // Get the ImageView directly from the card
        ImageView imageView = card.getImageviewCard();

        // Set the front image on the ImageView
        if (imageView != null) {
            imageView.setImageResource(cardFrontResId);
        } else {
            Log.d("GamePlay", "ImageView is null for card: " + card.getCardNum());
        }
    }

    public void displayCardFaceDown(Card card){
        // Get the back image resource ID
        int cardBackResId = context.getResources().getIdentifier(card.getCardBack(), "drawable", context.getPackageName());

        // Get the ImageView directly from the card
        ImageView imageView = card.getImageviewCard();

        // Set the back image on the ImageView
        if (imageView != null) {
            imageView.setImageResource(cardBackResId);
        } else {
            Log.d("GamePlay", "ImageView is null for card: " + card.getCardNum());
        }
    }

    public void displayCards(){

    }

    public void flipCard(Card card){
        if (card.isFaceUp()) {
            // If the card is already face up, do nothing or flip it back down
            displayCardFaceDown(card);
            card.setFaceUp(false);
        } else {
            // If the card is face down, flip it up
            displayCardFaceUp(card);
            card.setFaceUp(true);
        }

    }

    public void onclickCard(View view){

        if (isBusy) {
            return;  // Ignore clicks if the game is currently processing another pair
        }

        // Get the card index from the tag
        int cardIndex = (int) view.getTag();
        Card clickedCard = cards.get(cardIndex);

        Log.d("GamePlay", "Card clicked: " + clickedCard.getCardType() + " | isFaceUp: " + clickedCard.isFaceUp());


        // Don't allow flipping the same card twice
        if (clickedCard == cardFirst || clickedCard == cardSecond) {
            return;
        }

        // Flip the clicked card
        flipCard(clickedCard);

        Log.d("GamePlay", "Card clicked: " + clickedCard.getCardType() + " | isFaceUp: " + clickedCard.isFaceUp());

        // Set the first or second card
        if (isFirstGuess) {
            cardFirst = clickedCard;
            isFirstGuess = false;
        } else {
            cardSecond = clickedCard;
            isBusy = true;  // Set the busy flag since a pair is now selected
            // We have two cards selected, check if they match
            checkCardMatch();


            isFirstGuess = true;  // Reset the flag for the next turn
        }

        // Log the card or show a Toast
        //Toast.makeText(context, "Card clicked: " + clickedCard.getCardType(), Toast.LENGTH_SHORT).show();
        Log.d("GamePlay", "Card clicked: " + clickedCard.getCardType());
    }

    public void checkCardMatch(){
        // Ensure we have two cards to compare
        if (cardFirst != null && cardSecond != null) {

            // Increment the number of guesses
            totalGuesses++;
            updateGuessesTextview();  // Update the TextView to reflect the new guess count

            // If the two cards match (based on cardType)
            if (cardFirst.getCardType().equals(cardSecond.getCardType())) {
                // The cards match - keep them face up and disable further clicks
                cardFirst.getImageviewCard().setEnabled(false);  // Disable further clicks
                cardSecond.getImageviewCard().setEnabled(false); // Disable further clicks

                // Increment the total correct matches
                totalCorrect++;

                // Check if the game is over (all pairs are matched)
                if (totalCorrect == MAX_MATCHES) {
                    GameOver();  // Call GameOver method if all pairs are matched
                }

                // Reset for the next pair
                cardFirst = null;
                cardSecond = null;

                isBusy = false;  // Reset the busy flag
            } else {
                // If the cards don't match, flip them back after a delay
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayCardFaceDown(cardFirst);
                        displayCardFaceDown(cardSecond);

                        cardFirst.setFaceUp(false);
                        cardSecond.setFaceUp(false);

                        cardFirst = null;
                        cardSecond = null;

                        isBusy = false;  // Reset the busy flag
                    }
                }, 1000);  // Delay for 1 second (1000 ms)
            }

        }
    }

    public void GameOver() {
        if (context == null) {
            Log.d("GameOver", "Context is null");
        } else {
            Log.d("GameOver", "Context is valid: " + context.toString());
            Log.d("GameOver", "Number of guesses: " + totalGuesses);
            Intent intent = new Intent(context, PlayerActivity.class);
            intent.putExtra("playerScore", totalGuesses);
            context.startActivity(intent);
        }
        //Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(context, PlayerActivity.class);
        //startActivity(intent);
    }

    public Card getCardByCardNum(int cardNum){
        Card card = new Card(0, "artist", "img_card_back", "img_card_front_artist");
        return card;
    }

    public void updateGuessesTextview(){
        if (textviewGuesses != null) {
            textviewGuesses.setText("Guesses: " + totalGuesses);
        }
    }
}

package com.example.assignmenttwo;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The GamePlay class handles the logic for the memory card game, including managing
 * the cards, tracking the user's guesses, and handling game events like matches and game over.
 */
public class GamePlay extends AppCompatActivity {
    private static final int MAX_MATCHES = 6;  // The maximum number of matches required to win
    private int totalGuesses, totalCorrect = 0;  // Tracks total guesses and correct matches
    private boolean isFirstGuess;  // Flag to determine if it's the first or second card being guessed
    private Card cardFirst;  // The first card selected in a guess
    private Card cardSecond;  // The second card selected in a guess
    private ArrayList<String> cardTypes;  // List of card types
    private ArrayList<Card> cards;  // List of Card objects
    private TextView textviewGuesses;  // TextView to display the number of guesses
    private Context context;  // The context in which this gameplay occurs
    private boolean isBusy = false;  // Flag to prevent actions while cards are being processed

    /**
     * Constructor to initialize the game with the context.
     *
     * @param context The context of the current activity
     */
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

    /**
     * Sets up the game by adding cards to the deck, shuffling them,
     * and initializing the ImageView and click event listeners.
     */
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

        // Shuffle cards
        Collections.shuffle(cards);

        setupImageviewsAndOnclicks();

    }

    /**
     * Sets up the ImageViews and OnClickListeners for each card.
     */
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

    /**
     * Displays the front image of the card.
     *
     * @param card The card to be displayed face up
     */
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

    /**
     * Displays the back image of the card.
     *
     * @param card The card to be displayed face down
     */
    public void displayCardFaceDown(Card card) {
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

    /**
     * Flips the card (either face up or face down depending on its current state).
     *
     * @param card The card to be flipped
     */
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

    /**
     * Handles the event when a card is clicked by the user.
     *
     * @param view The view representing the clicked card
     */
    public void onclickCard(View view){

        if (isBusy) {
            return;  // Ignore clicks if the game is currently processing another pair
        }

        // Get the card index from the tag
        int cardIndex = (int) view.getTag();
        Card clickedCard = cards.get(cardIndex);

        // Don't allow flipping the same card twice
        if (clickedCard == cardFirst || clickedCard == cardSecond) {
            return;
        }

        // Flip the clicked card
        flipCard(clickedCard);

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
    }

    /**
     * Checks if the two selected cards match. If they match, they remain face up.
     * If not, they are flipped face down after a short delay.
     */
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

    /**
     * Called when the game is over (all pairs matched).
     * Starts a new activity to display the player's score.
     */
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
    }

    /**
     * Updates the TextView displaying the number of guesses.
     */
    public void updateGuessesTextview(){
        if (textviewGuesses != null) {
            textviewGuesses.setText("Guesses: " + totalGuesses);
        }
    }
}

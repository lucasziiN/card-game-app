package com.example.assignmenttwo;


import android.widget.ImageView;

/**
 * The Card class represents a single card in a card-matching game.
 * Each card has attributes such as its number, type, and image references
 * for the front and back sides. It also tracks whether the card is face-up or face-down.
 */
public class Card {
    private int cardNum; // The card's number, could be used to identify or match cards
    private String cardType, cardBack, cardFront; // Card type and paths for card's front and back images
    private ImageView imageviewCard; // This is the ImageView that displays the card in the UI
    private boolean isFaceUp; // This flag determines if the card is currently face-up or face-down

    /**
     * Constructor to initialize a Card object with its number, type,
     * and paths to the images representing the card's front and back sides.
     *
     * @param cNum   The card's unique number
     * @param cType  The type of the card (e.g., 'astronaut', 'artist')
     * @param cBack  The image path for the back of the card
     * @param cFront The image path for the front of the card
     */
    public Card(int cNum, String cType, String cBack, String cFront){
        this.cardNum = cNum;
        this.cardType = cType;
        this.cardBack = cBack;
        this.cardFront = cFront;
        this.isFaceUp = false;  // Cards starts face down by default
    }

    /**
     * Gets the card's unique number.
     *
     * @return the card's number
     */
    public int getCardNum(){
        return cardNum;
    }

    /**
     * Gets the card's type.
     *
     * @return the card's type as a string
     */
    public String getCardType(){
        return cardType;
    }

    /**
     * Gets the image path representing the back of the card.
     *
     * @return the image path for the back of the card
     */
    public String getCardBack() {
        return cardBack;
    }

    /**
     * Gets the image path representing the front of the card.
     *
     * @return the image path for the front of the card
     */
    public String getCardFront(){
        return cardFront;
    }

    /**
     * Checks if the card is currently face-up.
     *
     * @return true if the card is face-up, false otherwise
     */
    public boolean isFaceUp(){
        return isFaceUp;
    }

    /**
     * Sets whether the card should be face-up or face-down.
     *
     * @param faceUp true to set the card face-up, false to set it face-down
     */
    public void setFaceUp(boolean faceUp){
        this.isFaceUp = faceUp;
    }

    /**
     * Gets the ImageView associated with this card, which is used to display it in the UI.
     *
     * @return the ImageView representing this card
     */
    public ImageView getImageviewCard(){
        return imageviewCard;
    }

    /**
     * Sets the ImageView that will be used to display this card in the UI.
     *
     * @param ivCard the ImageView for this card
     */
    public void setImageviewCard(ImageView ivCard){
        this.imageviewCard = ivCard;
    }
}

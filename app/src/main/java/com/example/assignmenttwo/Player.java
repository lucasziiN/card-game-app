package com.example.assignmenttwo;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

/**
 * The Player class represents a player in the game. It stores the player's name, avatar, and score.
 * Avatars are represented by drawable resources and identified by their index in an array.
 */
public class Player {
    private String playerName;  // The player's name
    private int avatarID;  // The index of the player's avatar in the AVATAR_IDS array
    private Drawable playerAvatar;  // The drawable representing the player's avatar
    private int playerScore;  // The player's score
    private Context context;  // The context to access resources like drawables

    // Array of drawable resource IDs for avatars
    private static final int[] AVATAR_IDS = {
            R.drawable.img_card_front_artist,
            R.drawable.img_card_front_astronaut,
            R.drawable.img_card_front_coder,
            R.drawable.img_card_front_doctor,
            R.drawable.img_card_front_police,
            R.drawable.img_card_front_scientist,
            R.drawable.img_card_front_welder
    };

    /**
     * Constructor to create a Player object.
     *
     * @param context The application context, used for retrieving resources.
     * @param name The name of the player.
     * @param avatarID The index of the player's avatar in the AVATAR_IDS array.
     * @param score The player's initial score.
     */
    public Player(Context context, String name, int avatarID, int score){
        this.context = context;
        this.playerName = name;
        this.avatarID = avatarID;
        this.playerScore = score;
    }

    /**
     * Gets the player's name.
     *
     * @return The player's name.
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Retrieves the player's avatar drawable based on the avatarID.
     *
     * @return The drawable representing the player's avatar.
     */
    public Drawable getPlayerAvatar(){
        // Use the avatarID to get the corresponding drawable from the AVATAR_IDS array
        return ContextCompat.getDrawable(this.context, AVATAR_IDS[avatarID]);
    }

    /**
     * Gets the player's score.
     *
     * @return The player's score.
     */
    public int getPlayerScore(){
        return playerScore;
    }


}

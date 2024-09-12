package com.example.assignmenttwo;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class Player {
    private String playerName;
    private int avatarID;  // Store the avatar ID
    private Drawable playerAvatar;
    private int playerScore;
    private Context context;

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

    public Player(Context context, String name, int avatarID, int score){
        this.context = context;
        this.playerName = name;
        this.avatarID = avatarID;  // Store the avatar ID
        this.playerScore = score;
    }

    public String getPlayerName(){
        return playerName;
    }

    public Drawable getPlayerAvatar(){
        // Use the avatarID to get the corresponding drawable from the AVATAR_IDS array
        return ContextCompat.getDrawable(this.context, AVATAR_IDS[avatarID]);
    }

    public int getPlayerScore(){
        return playerScore;
    }


}

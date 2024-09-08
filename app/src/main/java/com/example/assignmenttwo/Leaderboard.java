package com.example.assignmenttwo;

import android.content.Context;
import java.util.ArrayList;

public class Leaderboard {
    private int[] IMG_ARRAY;
    private ArrayList<Player> leaderboard;
    private static Leaderboard leaderboardInstance;

    public Leaderboard(){
        this.IMG_ARRAY = new int[5];  // Example size, change as needed
        this.leaderboard = new ArrayList<>();
    }

    public Leaderboard getLeaderboardInstance() {
        if (leaderboardInstance == null) {
            leaderboardInstance = new Leaderboard();
        }
        return leaderboardInstance;
    }

    public int[] getImageArray(){
        return IMG_ARRAY;
    }

    public void updateLeaderboard(Player currentPlayer){

    }

    public void displayLeaderboard(Context context){

    }
}

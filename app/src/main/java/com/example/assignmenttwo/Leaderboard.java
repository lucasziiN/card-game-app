package com.example.assignmenttwo;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Leaderboard {
    private int[] IMG_ARRAY;
    private ArrayList<Player> leaderboard;
    private static Leaderboard leaderboardInstance;


    public Leaderboard(){
        this.IMG_ARRAY = new int[5];  // Example size, change as needed
        this.leaderboard = new ArrayList<>();
    }

    public static Leaderboard getLeaderboardInstance() {
        if (leaderboardInstance == null) {
            leaderboardInstance = new Leaderboard();
        }
        return leaderboardInstance;
    }

    public int[] getImageArray(){
        return IMG_ARRAY;
    }

    public void updateLeaderboard(Player currentPlayer) {
        leaderboard.add(currentPlayer);
        boolean changeMade = true;
        // The outer loop should continue as long as a swap has been made.
        while (changeMade) {
            changeMade = false;
            // Only iterate up to leaderboard.size() - 1 to prevent IndexOutOfBoundsException
            for (int i = 0; i < leaderboard.size() - 1; i++) {
                // Compare the scores of consecutive players
                if (leaderboard.get(i).getPlayerScore() > leaderboard.get(i + 1).getPlayerScore()) {
                    // Swap players in the leaderboard
                    Player temp = leaderboard.get(i);
                    leaderboard.set(i, leaderboard.get(i + 1));
                    leaderboard.set(i + 1, temp);
                    changeMade = true;  // A swap was made, so we'll need another pass
                }
            }
        }
    }


    public void displayLeaderboard(Context context){
        // Ensure that the context is the correct activity
        LeaderboardActivity activity = (LeaderboardActivity) context;

        for (int i = 0; i < leaderboard.size(); i++) {
            Player player = leaderboard.get(i);

            // Dynamically select the correct ImageView and TextViews for each player
            ImageView imageView = activity.findViewById(context.getResources().getIdentifier(
                    "iv_leaderboard_avatar" + (i + 1), "id", context.getPackageName()));
            imageView.setImageDrawable(player.getPlayerAvatar());

            TextView textViewName = activity.findViewById(context.getResources().getIdentifier(
                    "tv_leaderboard_name" + (i + 1), "id", context.getPackageName()));
            textViewName.setText(player.getPlayerName());

            TextView textViewScore = activity.findViewById(context.getResources().getIdentifier(
                    "tv_leaderboard_score" + (i + 1), "id", context.getPackageName()));
            textViewScore.setText(String.valueOf(player.getPlayerScore()));  // Convert score to string
        }
    }
}

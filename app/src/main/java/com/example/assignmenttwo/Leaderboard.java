package com.example.assignmenttwo;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * The Leaderboard class is responsible for managing a list of players and their scores.
 * It follows the singleton pattern to ensure only one instance exists at any time.
 * The class includes methods to update the leaderboard, display it, and maintain an array of player avatars.
 */
public class Leaderboard {
    private ArrayList<Player> leaderboard;  // A list to maintain the leaderboard of players
    private static Leaderboard leaderboardInstance;  // A singleton instance of the Leaderboard class

    /**
     * Constructor for the Leaderboard class.
     * Initializes the IMG_ARRAY and the leaderboard ArrayList.
     */
    public Leaderboard(){
        this.leaderboard = new ArrayList<>();
    }

    /**
     * Gets the instance of the Leaderboard.
     * If no instance exists, it will create one.
     *
     * @return The singleton instance of the Leaderboard.
     */
    public static Leaderboard getLeaderboardInstance() {
        if (leaderboardInstance == null) {
            leaderboardInstance = new Leaderboard();
        }
        return leaderboardInstance;
    }

    /**
     * Updates the leaderboard with a new player and sorts it in ascending order of player scores.
     * This method adds the current player to the leaderboard and sorts using a basic bubble sort algorithm.
     *
     * @param currentPlayer The current player to be added to the leaderboard.
     */
    public void updateLeaderboard(Player currentPlayer) {
        // Add current player to the leaderboard before sorting it
        leaderboard.add(currentPlayer);
        boolean changeMade = true;
        // Bubble-sort algorithm to sort player scores in the leaderboard
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

    /**
     * Displays the leaderboard by dynamically setting the avatars, player names, and scores in the corresponding UI elements.
     * It uses the player's avatar, name, and score to update the respective ImageView and TextView elements.
     *
     * @param context The context of the activity calling this method, expected to be LeaderboardActivity.
     */
    public void displayLeaderboard(Context context){
        // Ensure that the context is the correct activity
        LeaderboardActivity activity = (LeaderboardActivity) context;

        // Loop through each player in the leaderboard and update the UI
        for (int i = 0; i < leaderboard.size(); i++) {
            Player player = leaderboard.get(i);

            // Select the correct ImageView and TextViews for each player
            ImageView imageView = activity.findViewById(context.getResources().getIdentifier(
                    "iv_leaderboard_avatar" + (i + 1), "id", context.getPackageName()));
            // Set the player's avatar in the corresponding ImageView
            imageView.setImageDrawable(player.getPlayerAvatar());

            // Find the correct TextView for the player's name
            TextView textViewName = activity.findViewById(context.getResources().getIdentifier(
                    "tv_leaderboard_name" + (i + 1), "id", context.getPackageName()));
            // Set the player's name in the corresponding TextView
            textViewName.setText(player.getPlayerName());

            // Find the correct TextView for the player's score
            TextView textViewScore = activity.findViewById(context.getResources().getIdentifier(
                    "tv_leaderboard_score" + (i + 1), "id", context.getPackageName()));
            // Set the player's score in the corresponding TextView
            textViewScore.setText(String.valueOf(player.getPlayerScore()));  // Convert score to string
        }
    }
}

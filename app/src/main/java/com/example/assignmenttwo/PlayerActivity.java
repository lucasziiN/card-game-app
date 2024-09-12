package com.example.assignmenttwo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    private int avatarID, playerScore;
    private String playerName;
    private Intent intent;
    private Leaderboard leaderboardInstance;

    private ArrayList<String> avatarList = new ArrayList<>();

    public Player currPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the totalGuesses (playerScore) from the Intent
        Intent intent = getIntent();
        playerScore = intent.getIntExtra("playerScore", 0);  // 0 is the default value if nothing is passed

        // Now you can use the playerScore, for example, set it in a TextView
        TextView textViewPlayerScore = findViewById(R.id.tv_playerscore);
        textViewPlayerScore.setText("Score: " + playerScore);

        avatarList.add("artist");
        avatarList.add("astronaut");
        avatarList.add("coder");
        avatarList.add("doctor");
        avatarList.add("police");
        avatarList.add("scientist");

        // Initialize the leaderboard instance
        leaderboardInstance = Leaderboard.getLeaderboardInstance();
    }

    public void onclickSubmit(View view){
        Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show();

        // Retrieve the player's name from an EditText
        EditText editTextPlayerName = findViewById(R.id.et_playername);
        playerName = editTextPlayerName.getText().toString();  // Get the player's name

        // Retrieve the selected avatar from the RadioGroup
        RadioGroup radioGroupAvatar = findViewById(R.id.rg_avatar);
        int selectedAvatarRadioId = radioGroupAvatar.getCheckedRadioButtonId();  // Get the ID of the selected radio button

        // Ensure that a selection was made
        if (selectedAvatarRadioId == -1) {
            Toast.makeText(this, "Please select an avatar.", Toast.LENGTH_SHORT).show();
            return;  // Exit if no selection was made
        }

        // Retrieve the selected RadioButton
        RadioButton selectedRadioButton = findViewById(selectedAvatarRadioId);

        // Get the tag from the selected RadioButton (this will return the avatar index as a String)
        String avatarTag = selectedRadioButton.getTag().toString();

        // Convert the avatarTag to an integer (this will be used as the avatar index)
        avatarID = Integer.parseInt(avatarTag);

        // Example: Print the selected avatar's tag
        Log.d("PlayerActivity", "Selected Avatar Index: " + avatarID);




        // Create a Player object using the mapped avatar index
        currPlayer = new Player(this, playerName, avatarID, playerScore);

        // Update the leaderboard
        leaderboardInstance.updateLeaderboard(currPlayer);


        // Pass player data to LeaderboardActivity
        Intent intent = new Intent(PlayerActivity.this, LeaderboardActivity.class);
        intent.putExtra("playerName", playerName);
        intent.putExtra("playerScore", playerScore);
        intent.putExtra("avatarID", avatarID);  // Pass the avatar index, not the RadioButton ID

        // Start LeaderboardActivity
        startActivity(intent);
    }


}
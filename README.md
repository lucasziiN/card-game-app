# Card Clash: Android Multiplayer Card Game

An interactive card game Android app developed as part of the paper COMPX202 at the University of Waikato. The game features multiple screens, player profiles, scoring logic, and a persistent leaderboard.

## 🎮 Features

- Turn-based card gameplay
- Leaderboard tracking with persistent player scores
- Multiple activity screens using Android Intents
- Clean object-oriented design with Java classes like `Card`, `Player`, and `GamePlay`

## 📱 Technologies Used

- Java (Android SDK)
- Android Studio
- XML layouts and UI components

## 🧱 Architecture

- `MainActivity.java`: Main menu / entry point
- `GameActivity.java`: Manages the game loop and rendering
- `LeaderboardActivity.java`: Displays top player scores
- `PlayerActivity.java`: Handles user input and profile creation
- `GamePlay.java`: Core game logic and win conditions
- `Card.java`: Data model for each card
- `Player.java`: Tracks name, score, and performance

## 🛠 How to Build

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/card-clash.git
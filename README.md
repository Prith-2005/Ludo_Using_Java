# 🎲 Java Ludo Game (Swing GUI)

A fully functional **Ludo board game** built using **Java & Swing GUI**, implementing real Ludo rules such as dice rolling, token movement, capture (kill), safe zones, turn rotation, and winner detection.

This project was developed step-by-step with a focus on **Object-Oriented Programming (OOP)** and **GUI event handling**.

---

## 📌 Features

* 🎲 Dice rolling with animated effect
* 🔄 Turn-based gameplay (Red → Green → Yellow → Blue)
* 🚪 Token entry only when dice = 6
* 🧠 Smart token selection using mouse clicks
* ☠️ Token capture (kill logic)
* 🛡 Safe zones where tokens cannot be killed
* 🏆 Automatic winner detection with popup
* 🎨 Clean and colorful Ludo board UI
* 🧩 Modular design using OOP principles

---

## 🛠 Technologies Used

* **Java (JDK 8+)**
* **Java Swing**
* **AWT**
* **Object-Oriented Programming (OOP)**

---

## 📂 Project Structure

```
LudoGame/
│
├── Main.java              # Entry point
├── BoardPanel.java        # Game board UI & rendering
├── GameController.java    # Core game logic
├── Player.java            # Player model
├── Token.java             # Token model
└── README.md              # Project documentation
```

---

## 🎮 How to Play

1. Click **Roll 🎲** to roll the dice
2. If the dice shows **6**, you can bring a token out of home
3. Click on a token to move it
4. Landing on an opponent’s token (outside safe zones) sends it back home ☠️
5. Rolling **6** gives an extra turn
6. First player to bring **all 4 tokens to the end** wins 🏆

---

## 🧠 Game Rules Implemented

* Tokens start inside their home area
* A token enters the board only when dice = 6
* Safe zones protect tokens from capture
* Only exact or valid moves are allowed
* Turn rotates unless dice = 6
* Winner is declared automatically

---

## ▶️ How to Run the Project

1. Install **Java JDK (8 or above)**
2. Open the project in **IntelliJ / Eclipse / VS Code**
3. Compile and run `Main.java`
4. Enjoy the game 🎉

---

## 🚀 Future Enhancements

* 🎵 Sound effects (dice roll, token kill)
* 🏁 Colored home paths
* 🎲 Smooth token movement animation
* 🤖 Single-player mode (AI)
* 📱 Mobile version using JavaFX

---

## 👨‍💻 Author

**Prithwish Chatterjee**
B.Tech – Computer Science & Business Systems (CSBS)

---

## ⭐ Acknowledgements

* Inspired by the classic **Ludo board game**
* Built for learning **Java Swing & OOP concepts**




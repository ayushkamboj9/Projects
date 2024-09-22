
# Dino Game

A simple 2D side-scrolling game where the player controls a dinosaur and tries to avoid obstacles. This game is built using Java Swing and AWT, demonstrating basic game mechanics like jumping, obstacle movement, and collision detection.

![Alt text](https://upload.wikimedia.org/wikipedia/commons/2/2d/Dinosaur_gameover.png)

---




## Features

- **Dinosaur Movement**: The player controls a dinosaur that can jump to avoid obstacles.
- **Obstacle Generation**: Random obstacles appear at the top and bottom and move toward the dinosaur.
- **Collision Detection**: The game detects collisions between the dinosaur and obstacles, which ends the game.
- **Scoring System**: The score increases the longer the player survives.


## Installation

1. Clone the repository

```bash
  [git clone] (https://github.com/ayushkamboj9/Dinosaur-Game.git)

```
2. Compile the Java files:
 ```
 javac DinoGamee.java

 ```
 3. Run the game:
 ```
 java DinoGamee

 ```
## Installation

1. Clone the repository

```bash
  [git clone] (https://github.com/ayushkamboj9/Dinosaur-Game.git)

```
2. Compile the Java files:
 ```
 javac DinoGamee.java

 ```
 3. Run the game:
 ```
 java DinoGamee

 ```
---

# How to Play

- Press the ```SPACEBAR``` to make the dinosaur jump.
- Avoid the obstacles moving towards you.
- Survive as long as possible to increase your score.
- The game ends when the dinosaur collides with an obstacle.

---

# Code Overview

## DinoGamee.java

- Game Window: The game uses ```JFrame``` to create the main game window and ```JPanel``` to handle the rendering of game elements.
- Movement: The dinosaur's movement is controlled by the spacebar ```(KeyListener)```.
- Obstacle Movement: Obstacles move from the right side of the screen toward the dinosaur.
- Collision Detection: Collision is handled using Java's ```Rectangle``` class, which checks for intersections between the dinosaur and obstacles.

---

## Key Classes and Methods

- DinoGamee: The main game class that handles rendering, game logic, and user input.
- Obstacle: Represents the obstacles in the game and their movement.
- actionPerformed(): Updates the game state, including obstacle movement and score increments.
- paintComponent(): Handles the rendering of the game, including drawing the dinosaur, obstacles, and score.

---

## Technologies Used

- Java: Core language for game logic.
- Swing: Used to create the game window and handle graphical rendering.
- AWT: Utilized for game elements like Rectangle for collision detection.

---

## OOP Concepts

- Encapsulation: The game uses classes like DinoGamee and Obstacle to encapsulate the logic and attributes of different game components.
- Polymorphism: The game makes use of interfaces such as KeyListener and ActionListener for handling user input and game updates.
- Inheritance: JPanel is extended to create the custom game panel where the game is rendered.

---

## Challenges Faced
- Handling smooth jumping and collision detection was a challenge due to the timing and positioning of obstacles relative to the dinosaur.
- Balancing the difficulty by adjusting the obstacle spawn rate and speed to make the game fun yet challenging.

---

## Future Improvements
- Add more obstacle variations and power-ups.
- Implement different difficulty levels.
- Introduce sound effects and background music.

## License

This project is licensed under the MIT License. See the ```LICENSE``` file for more information.


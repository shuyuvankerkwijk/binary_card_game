# Shuyu's personal project -- made during the CPSC210 course at UBC!
**Jan 30, 2024**

## Application purpose:
The purpose of this application is to give new computer science students more intuition and practice with binary numbers and logic gates. This application is inspired by games like SET, where users have to find 'sets' of cards. Similarly, each card will have both a binary number (either 0 or 1), as well as a logic gate (AND, OR, NAND, or NOR). Players have to find 'pairs' of two cards where applying the operations on both cards to the numbers on the cards results in True for both operations.

**Example of 'pairs' (game mechanics):**
1. CARD 1: (1, AND) with CARD 2: (1, OR)
2. CARD 1: (0, NAND) with CARD 2: (0, NOR)
3. CARD 1: (1, OR) with CARD 2: (0, NAND)
Note that in all above examples, the operations on both cards, applied to the numbers on both cards, returns True

## Who will use it?
- New computer science students trying to practice gates
- Anyone looking for some brain-stimulating fun!

## Why I created this project
- I wanted to challenge my coding abilities to create both the user interface and the backend system
- I love the game SET and wanted to create something similar that was relevant to computer science students

## Instructions for users
- You can add cards to the board by clicking the "Add Cards" button.
- You can make pairs by choosing two cards and clicking "Choose Cards". The system will let you know if the pair is valid, and will add your cards to the right panel if the pair is valid. 
- You can run the GUI by running 'GameUI'
- You can save the state of your game by clicking "Save Game"
- You can reload the state of your game by clicking "Load Game"
- Events are logged

## Possible future improvements:
- Refactor GameUI and Game: I plan to refactor the GameUI and Game classes into one. Right now, GameUI calls methods from Game like DrawCards, SelectPair, etc., but Game itself relies on CardGame for many of these functions, which makes them tightly coupled. Originally, I used Game as the UI controller, but when I introduced GameUI, I didn’t change Game to control the GUI as well. Instead, I created GameUI, but now I see the need to streamline this by moving some functions into other classes.
- Single Responsibility Principle (SRP): To stick to SRP, I’ll split some of the current functions of Game/GameUI into other classes. For example, I’ll let GameBoardUI handle board-related functions and PairsDisplayPanel manage pairs-related tasks. I’ll also move additional functionalities like tracking selected cards and managing the options panel into their own classes. This will help keep GameUI focused on being the starting point for the game and managing the highest level of user interactions.
- Singleton Pattern Consideration: I realized that since I only want one instance of Game, and GameUI is passing this instance to PairsDisplayPanel and GameBoardUI, I could have used the Singleton design pattern. While it’s not necessary right now because Game isn’t passed around excessively, this could be a useful change if the GUI becomes more complex with more features needing to access Game. This would ensure everything points to the same object and prevent the creation of multiple Game instances.

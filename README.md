# Shuyu's personal project
**Jan 30, 2024**

## Application purpose:
The purpose of this application is to give new computer science 
students more intuition and practice with binary numbers and 
logic gates. This application is inspired by games like SET, 
where users have to find 'sets' of cards. Similarly, each 
card will have both a binary number (either 0 or 1), as well
as a logic gate (AND, OR, NAND, or NOR). Players have to find
'pairs' of two cards where applying the operations on both cards to the 
numbers on the cards results in True for both operations.

**Example of 'pairs' (game mechanics):**
1. CARD 1: (1, AND) with CARD 2: (1, OR)
2. CARD 1: (0, NAND) with CARD 2: (0, NOR)
3. CARD 1: (1, OR) with CARD 2: (0, NAND)

## Who will use it?
- New computer science students trying to practice gates
- Anyone looking for some brain-stimulating fun!

## Why is this project of interest to you?
- I think it will challenge my coding abilities to create 
both the user interface and the backend system
- I love the game SET and wanted to create something similar 
that was relevant to the course

## User stories
1. As a user, I want to be able to select two cards and have
the game determine whether they are a 'pair' or not 
2. As a user, I want to be able to add 'pairs' that I find 
to a list of pairs that I have found so far ***(add multiple
X's to my Y)***
3. As a user, I want to be able to add new cards to the game
if I can't find a pair or am running out of cards
4. As a user, I want to be able to see statistics on how many
pairs I have found and how many are valid
5. As a user, I want to be able to view a list of all pairs
I have selected and whether they were valid or not ***(list all
the X's in my Y)***
6. As a user, when I select the quit option in the game I
want to have the option to save my current game and statistics.
7. As a user, when I start the game, I want to have the option
to load an existing game from a file.

## Instructions for Grader
- You can generate the first required action related to the 
user story "adding multiple Xs to a Y" by clicking "Add Cards" 
button.
- You can generate the second required action related to the
user story "adding multiple Xs to a Y" by choosing two cards
and clicking "Choose Cards". The system will let you know if
the pair is valid, and will add your cards to the right panel
if the pair is valid. 
- You can locate my visual component by running 'GameUI'
- You can save the state of my application by clicking 
"Save Game"
- You can reload the state of my application by clicking
"Load Game"

## Phase 4: Task 2
Representative sample of events:

Tue Apr 02 23:09:54 PDT 2024
New game created.
Tue Apr 02 23:09:54 PDT 2024
4 new cards added to the game board.
Tue Apr 02 23:09:57 PDT 2024
2 new cards added to the game board.
Tue Apr 02 23:10:01 PDT 2024
Cards 2 and 1 were selected on the board
Tue Apr 02 23:10:03 PDT 2024
2 new cards added to the game board.
Tue Apr 02 23:10:08 PDT 2024
Cards 5 and 3 were selected on the board
Tue Apr 02 23:10:15 PDT 2024
Cards 2 and 0 were selected on the board
Tue Apr 02 23:10:17 PDT 2024
2 new cards added to the game board.

## Phase 4: Task 3
I would refactor GameUI and Game into one class. Currently,
GameUI calls Game for methods such as 'DrawCards', 'SelectPair',
etc... but Game itself calls on CardGame for a lot of these
functions. The reason it is set up this way is because for
Phase 2 I was using Game as my UI class to control all actions,
but for Phase 3, instead of changing Game to also control the 
GUI, I ended up making a new GameUI class. However, since I
want to maintain SRP, I would split up some of the functions
of Game/GameUI into other classes -- for example, I would allow
GameBoardUI to handle the board-related functions and 
PairsDisplayPanel to handle the pairs-related functions. I 
would also remove some additional functionality currently
in the Game/GameUI into other classes, such as keeping track
of the selected Cards, the options panel, etc... These 
refactoring changes would ensure the purpose of GameUI 
is kept as being the starting point for the game and for 
organizing the highest level of user-interactions.

I also realized that since I actually only wanted one
instance of Game, and that my GameUI was passing instances
of Game through to PairsDisplayPanel and GameBoardUI, that
if I did not make the refactoring change mentioned above,
I also could have chosen to use a Singleton design pattern
for Game, where instances of the game could be retrieved
instead of passing the entire object. 

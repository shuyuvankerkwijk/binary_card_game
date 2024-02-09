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
to a list of pairs that I have found so far 
3. As a user, I want to be able to view all the pairs I have 
found so far 
4. As a user, I want to be able to see statistics on how many
pairs I have found

*(Potential extra user story if I have time)*
5. As a user, I want to be able to increase the difficulty of
the game by making it so I have to find 'triples' of cards
instead of 'pairs'
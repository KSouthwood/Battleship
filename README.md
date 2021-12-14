# [Battleship](https://hyperskill.org/projects/125)

## About
Writing games is probably one of the most exciting tasks in programming. 
Develop your own version of the popular "Battleship Game" to play with 
your friends!

## Learning Outcomes
You will understand the process of developing such a complex program as a 
game and learn about processing user input and handling errors.

### Stage 1: [Take position!](https://hyperskill.org/projects/125/stages/663/implement)
###### Review the rules and place your ships on the game field.

Battleship (also called _Battleships_ or _Sea Battle_) is a two-player 
strategy game whose history traces back to the First World War. It started 
off as a pencil and paper game, until Milton Bradley coined the rules and 
published the game. Fun fact: it was one of the first games to be produced 
as a computer game in 1979! In this project, we will recreate this 
timeless classic.

We'll have a 10x10 game field and five ships to arrange on the field. The 
ships can be placed horizontally or vertically but not diagonally across 
the grid spaces; the ships should not cross or touch each other.  The goal 
is to sink all the ships of the opponent before your opponent does this 
to you.

Positioning the ships is exactly where we are going to start! The goal of 
this first stage is to place all the ships on the game field according to 
the rules.

### Stage 2: [The first shot](https://hyperskill.org/projects/125/stages/664/implement)
###### Try shelling your own ships to test the new guns.

The goal of this game is to sink all the ships of your opponent. Our fleet 
is not ready for a big battle yet, so let's practice shooting on our field.
Place all your units on the battlefield and take a shot!

In this step, you need to develop a system of shooting with accompanying 
messages about hits and misses.

### Stage 3: [Fog of war](https://hyperskill.org/projects/125/stages/665/implement)
###### Arrange your ships, hide them behind the fog of war, and fire at a random position.

It seems a little odd to shoot your own ships. Let's imagine they are not 
ours! You can ask a friend to place the ships on the game field (or do it 
yourself, but it will be less exciting), and then the program will hide 
the ships under the fog of war. You just have to take a blind shot.

### Stage 4: [The end of the war](https://hyperskill.org/projects/125/stages/666/implement)
###### Denote the conditions for the end of the game.

It looks like everything is ready for full-scale battlefield maneuvers! 
This time, don't cease fire until all the ships are sunk. At the end of 
the game, your program should print a congratulatory message to the 
winner: `You sank the last ship. You won. Congratulations!`

### Stage 5: [Friend or foe](https://hyperskill.org/projects/125/stages/667/implement)
###### Make your game more interesting by adding another player with their own field.

Here is a good way to show off your new skills: offer a friend to play a 
computer game that you wrote yourself! Of course, it is much more fun to 
play Battleship with someone else: the possibility of winning or losing 
adds a thrill to the game!

Both players add the ships to their fields one by one (no peeking!), and 
then start shelling each other until one of them succeeds. To make the 
game fair and prevent the players from peeping at each other's fields, 
after each move add the message "`Press Enter and pass the move to another 
player`", which will clear the screen.
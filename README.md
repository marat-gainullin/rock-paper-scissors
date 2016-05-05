# Rock paper scissors game
Thihs is rock paper scissors game console application

## Design
The game is designed as console application and executes player's commands.
Each command is accosiated with an action code.
Action code is aware of player's history and makes decisions based on that history.
Also, the game has a lighwieght console processor isolated from game's logic.
Since a player wants to play a new game each time, history is not persisted.
Game has a logic, that hints a user about 1 hour elapsed from the begining of game session.
This feature is implemented because the player has only 1 hour a day to play with the game.

## Install
The game requires Java 8 to be installed.
Since Java 8 is installed, everything is ready to run the game.

## Build
To build the game you need Maven.
Cd into a folder of the project and type `mvn package` on the command line.

## Run
Type on the command line `java -jar rps-console-1.0` and follow game's prompt to play.

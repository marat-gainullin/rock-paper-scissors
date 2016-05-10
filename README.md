# Rock paper scissors game
Thihs is rock paper scissors game console application

## Design
The game is designed as console application and executes player's commands.
First, the game invites a player to choose game mode (player himself vs. computer or player's computer vs. foreign computer).
Then, the game allows to select one of available tools or quit the game and return to mode selection.
In the computer vs. computer mode, game waits for player's confirmation of a new round or quit command.

The game has a lighwieght console processor isolated from game's logic.
The game has a strategy, that is followed by computers while making moves.
Since a player wants to play a new game each time, computers' moves are base on randomness,
but within certain strategy based on history of moves.

Game has a logic, that hints a user about 1 hour elapsed from the begining of game session.
This feature is implemented because the player has only 1 hour a day to play with the game.

### Strategy
Computers' moves are made based on strategy of successful tools selection.
Past rounds serve as a gain / penalty source. Every round adds to its tool a penalty or a gain weight.
Before the selection of a tool for the next round, random number is generated and projected on a tools range with non-uniform segmentation based on tools' weight.

### Extension
If you want to add tools(weapons) and transform the game into anothe game, you should add new tools to `Tool` enumeration and re-implement `Round.winner()` method.
Also, you should refactor `Round` tests.

## Install
The game requires Java 8 to be installed.
Since Java 8 is installed, everything is ready to run the game.

## Build
To build the game you need Maven.
Cd into a folder of the project and type `mvn package` on the command line.

## Run
Type on the command line `java -jar rps-console-1.0` and follow game's prompt to play.

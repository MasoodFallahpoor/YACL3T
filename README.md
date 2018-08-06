YACL3T
=======
A command-line human-vs-computer Tic Tac Toe which uses [MiniMax algorithm](https://en.wikipedia.org/wiki/Minimax) to select the best move for computer. (Assuming that I implemented the AI part correctly!) If computer starts the game then the best result you can get is a tie but if you start the game then there is a chance for you to win.

Some notes
--------------
- No [Alpha-beta pruning](https://en.wikipedia.org/wiki/Alpha-beta_pruning) is used (it's an overkill for such a game with such a small game tree)
- A lot of sanity checks like checking for *IndexOutOfBoundsException* are ignored.
- The code is not that much well-organized from a software-engineer's perspective.
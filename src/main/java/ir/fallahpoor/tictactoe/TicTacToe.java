package ir.fallahpoor.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private Board board;
    private Random random;
    private Scanner input;

    public TicTacToe() {
        input = new Scanner(System.in);
        random = new Random();
    }

    public void start() {
        initialize();
        makeFirstMove();
        playGame();
        checkWhoWon();
    }

    private void initialize() {
        board = new Board();
    }

    private void makeFirstMove() {

        System.out.print("Who starts? (1-computer, 2-user): ");
        int choice = input.nextInt();

        if (choice == 1) {
            int row = random.nextInt(Board.BOARD_SIZE);
            int column = random.nextInt(Board.BOARD_SIZE);
            Cell cell = new Cell(row, column, Player.COMPUTER);
            board.makeMove(cell);
            board.display();
        }

    }

    private void playGame() {

        while (!board.isGameOver()) {

            System.out.println("Your turn:");

            System.out.print("Enter row: ");
            int row = input.nextInt();

            System.out.print("Enter column: ");
            int column = input.nextInt();

            Cell cell = new Cell(row, column, Player.USER);

            board.makeMove(cell);
            board.display();

            if (board.isGameOver()) {
                break;
            }

            board.makeMove(board.getBestMove());
            board.display();

        }

    }

    private void checkWhoWon() {
        if (board.isWinning(Player.COMPUTER)) {
            System.out.println("Computer won!");
        } else if (board.isWinning(Player.USER)) {
            System.out.println("You won!");
        } else {
            System.out.println("It's a tie.");
        }
    }

}

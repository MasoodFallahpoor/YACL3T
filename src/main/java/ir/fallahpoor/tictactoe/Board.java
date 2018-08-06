package ir.fallahpoor.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    public static final int BOARD_SIZE = 3;
    private List<Cell> miniMaxValues;
    private Cell[][] board;

    public Board() {

        miniMaxValues = new ArrayList<>();

        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Cell(i, j, Player.NONE);
            }
        }

    }

    public boolean isGameOver() {
        return (isWinning(Player.COMPUTER) || isWinning(Player.USER) || getEmptyCells().isEmpty());
    }

    private List<Cell> getEmptyCells() {

        List<Cell> emptyCells = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Cell cell = board[i][j];
                if (cell.getPlayer() == Player.NONE) {
                    emptyCells.add(cell);
                }
            }
        }

        return emptyCells;

    }

    public void makeMove(Cell cell) {
        if (isRowValid(cell.getRow()) && isColumnValid(cell.getColumn()) &&
                isCellEmpty(board[cell.getRow()][cell.getColumn()])) {
            board[cell.getRow()][cell.getColumn()] = cell;
        }
    }

    private boolean isRowValid(int row) {
        return (0 <= row && row < BOARD_SIZE);
    }

    private boolean isColumnValid(int column) {
        return (0 <= column && column < BOARD_SIZE);
    }

    private boolean isCellEmpty(Cell cell) {
        return (cell.getPlayer() == Player.NONE);
    }

    public void display() {

        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {

            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(" " + board[i][j].getPlayer() + " ");
            }

            System.out.println();

        }

        System.out.println();

    }

    public boolean isWinning(Player player) {

        boolean isWinning = false;

        if (board[0][0].getPlayer() == player &&
                board[1][1].getPlayer() == player &&
                board[2][2].getPlayer() == player) {
            isWinning = true;
        }

        if (board[0][2].getPlayer() == player &&
                board[1][1].getPlayer() == player &&
                board[2][0].getPlayer() == player) {
            isWinning = true;
        }

        for (int i = 0; i < BOARD_SIZE; i++) {

            if (board[i][0].getPlayer() == player &&
                    board[i][1].getPlayer() == player &&
                    board[i][2].getPlayer() == player) {
                isWinning = true;
            }

            if (board[0][i].getPlayer() == player &&
                    board[1][i].getPlayer() == player &&
                    board[2][i].getPlayer() == player) {
                isWinning = true;
            }

        }

        return isWinning;

    }

    public Cell getBestMove() {

        computeMiniMax();

        int max = Integer.MIN_VALUE;
        int best = Integer.MIN_VALUE;

        for (int i = 0; i < miniMaxValues.size(); i++) {
            if (max < miniMaxValues.get(i).getMiniMaxValue()) {
                max = miniMaxValues.get(i).getMiniMaxValue();
                best = i;
            }
        }

        return miniMaxValues.get(best);

    }

    private void computeMiniMax() {
        miniMaxValues.clear();
        miniMax(0, Player.COMPUTER);
    }

    private int miniMax(int depth, Player player) {

        // Begin check for terminal states (those states where either someone is winning or the board is full)
        if (isWinning(Player.COMPUTER)) {
            return 1;
        }

        if (isWinning(Player.USER)) {
            return -1;
        }

        if (getEmptyCells().isEmpty()) {
            return 0;
        }
        // End check for terminal states

        List<Integer> scores = new ArrayList<>();

        for (Cell cell : getEmptyCells()) {

            if (player == Player.COMPUTER) {

                cell.setPlayer(Player.COMPUTER);
                makeMove(cell);

                int currentScore = miniMax(depth + 1, Player.USER);
                scores.add(currentScore);

                if (depth == 0) {
                    cell.setMiniMaxValue(currentScore);
                    miniMaxValues.add(cell);
                }

            } else if (player == Player.USER) {

                cell.setPlayer(Player.USER);
                makeMove(cell);

                int currentScore = miniMax(depth + 1, Player.COMPUTER);
                scores.add(currentScore);

            }

            // Return board to its former state
            board[cell.getRow()][cell.getColumn()] = new Cell(cell.getRow(), cell.getColumn(), Player.NONE);

        }

        return (player == Player.COMPUTER ? Collections.max(scores) : Collections.min(scores));

    }

}

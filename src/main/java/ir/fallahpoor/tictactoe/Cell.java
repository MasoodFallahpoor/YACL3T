package ir.fallahpoor.tictactoe;

public class Cell {

    private int row;
    private int column;
    private Player player;
    private int miniMaxValue;

    public Cell(int row, int column, Player player) {
        this.row = row;
        this.column = column;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMiniMaxValue() {
        return miniMaxValue;
    }

    public void setMiniMaxValue(int miniMaxValue) {
        this.miniMaxValue = miniMaxValue;
    }

}

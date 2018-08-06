package ir.fallahpoor.tictactoe;

public enum Player {

    COMPUTER("X"),
    USER("O"),
    NONE("-");

    Player(String playerMark) {
        this.playerMark = playerMark;
    }

    private final String playerMark;

    @Override
    public String toString() {
        return playerMark;
    }

}

package co.codingnomads.tictactoetutorial;

public class Player {

    private String symbol;
    private String winningText;
    private int color;

    public Player(String symbol, String winningText, int color) {
        this.symbol = symbol;
        this.winningText = winningText;
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getWinningText() {
        return winningText;
    }

    public int getColor() {
        return color;
    }
}

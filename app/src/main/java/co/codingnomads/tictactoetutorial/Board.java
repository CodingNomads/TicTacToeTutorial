package co.codingnomads.tictactoetutorial;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Board {
    private List<String> marks = new ArrayList<>(asList("", "", "", "", "", "", "", "", ""));
    private List<PossibleWin> possibleWins = new ArrayList<>();

    public Board() {
        possibleWins.add(new PossibleWin(0, 1, 2));
        possibleWins.add(new PossibleWin(3, 4, 5));
        possibleWins.add(new PossibleWin(6, 7, 8));

        possibleWins.add(new PossibleWin(0, 3, 6));
        possibleWins.add(new PossibleWin(1, 4, 7));
        possibleWins.add(new PossibleWin(2, 5, 8));

        possibleWins.add(new PossibleWin(0, 4, 8));
        possibleWins.add(new PossibleWin(2, 4, 6));
    }

    public void mark(Integer position, String mark) {
        marks.set(position, mark);
    }

    public boolean isThereWinner(Player player) {
        for (PossibleWin possibleWin : possibleWins) {
            if (checkIfAreSame(possibleWin, player.getSymbol())) {
                return true;
            }
        }
        return false;
    }

    public void erase() {
        marks = new ArrayList<>(asList("", "", "", "", "", "", "", "", ""));
    }

    private boolean checkIfAreSame(PossibleWin possibleWin, String playerSymbol) {
        return marks.get(possibleWin.getFirst()).equals(playerSymbol) &&
                marks.get(possibleWin.getSecond()).equals(playerSymbol) &&
                marks.get(possibleWin.getThird()).equals(playerSymbol);
    }
}

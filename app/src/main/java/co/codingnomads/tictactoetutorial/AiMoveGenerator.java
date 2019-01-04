package co.codingnomads.tictactoetutorial;

import java.util.ArrayList;
import java.util.List;

import static co.codingnomads.tictactoetutorial.GameActivity.AI_SYMBOL;
import static co.codingnomads.tictactoetutorial.GameActivity.PLAYER_SYMBOL;

public class AiMoveGenerator {

    private Board board;

    public AiMoveGenerator(Board board) {
        this.board = board;
    }

    public Move getBestMove(String player) {
        Move bestMove = new Move();

        List<Move> availableMoves = getAvailableMoves();

        if (board.hasWon(AI_SYMBOL)) {
            bestMove.setScore(-10);
            return bestMove;
        } else if (board.hasWon(PLAYER_SYMBOL)) {
            bestMove.setScore(10);
            return bestMove;
        } else if (availableMoves.size() == 0) {
            bestMove.setScore(0);
            return bestMove;
        }

        List<Move> moves = new ArrayList<>();

        for (Move availableMove : availableMoves) {
            board.mark(availableMove.getPosition(), player);
            if (player.equals(PLAYER_SYMBOL)) {
                availableMove.setScore(getBestMove(AI_SYMBOL).getScore());
            } else {
                availableMove.setScore(getBestMove(PLAYER_SYMBOL).getScore());
            }
            board.mark(availableMove.getPosition(), "");
            moves.add(availableMove);
        }

        if (player.equals(PLAYER_SYMBOL)) {
            int bestScore = -1000;
            for (Move move : moves) {
                if (move.getScore() > bestScore) {
                    bestScore = move.getScore();
                    bestMove = move;
                }
            }
        } else {
            int bestScore = 1000;
            for (Move move : moves) {
                if (move.getScore() < bestScore) {
                    bestScore = move.getScore();
                    bestMove = move;
                }
            }
        }

        return bestMove;
    }

    private List<Move> getAvailableMoves() {
        List<String> marks = board.getMarks();
        List<Move> potentialMoveList = new ArrayList<>();

        for (int i = 0; i < marks.size(); i++) {
            if (marks.get(i).isEmpty()) {
                Move move = new Move();
                move.setPosition(i);
                potentialMoveList.add(move);
            }
        }
        return potentialMoveList;
    }
}

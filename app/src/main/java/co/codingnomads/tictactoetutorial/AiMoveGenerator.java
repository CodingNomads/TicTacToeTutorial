package co.codingnomads.tictactoetutorial;

import java.util.Random;

public class AiMoveGenerator {

    private Board board;
    private Random random  = new Random();

    public AiMoveGenerator(Board board) {
        this.board = board;
    }

    public Integer getMove() {
        if(board.isFull()){
            return null;
        }

        while(true){
            int i = random.nextInt(9);
            if(board.isEmpty(i)){
                return i;
            }
        }
    }
}

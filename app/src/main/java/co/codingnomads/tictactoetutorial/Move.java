package co.codingnomads.tictactoetutorial;

public class Move {
    private int score;
    private int position;


    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}

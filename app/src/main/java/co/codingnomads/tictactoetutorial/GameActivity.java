package co.codingnomads.tictactoetutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    public static final String PLAYER_SYMBOL = "X";
    public static final String AI_SYMBOL = "O";

    private List<Button> buttons;
    private Button startButton;
    private TextView gameResultTv;
    private Player player;
    private Player ai;
    private Board board;
    private AiMoveGenerator aiMoveGenerator;
    private boolean gameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        player = new Player(PLAYER_SYMBOL, getString(R.string.win), Color.BLUE);
        ai = new Player(AI_SYMBOL, getString(R.string.lose), Color.RED);
        board = new Board();
        aiMoveGenerator = new AiMoveGenerator(board);
        initializeUi();
    }

    private void initializeUi() {
        initializeBoardButtons();
        initializeStartButton();
        gameResultTv = findViewById(R.id.tv_gameResult);
    }

    private void initializeStartButton() {
        startButton = findViewById(R.id.b_start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });
    }

    private void restart() {
        board.erase();
        gameResultTv.setText("");
        cleanButtons();
        enableButtons();
        gameOver = false;
    }

    private void initializeBoardButtons() {
        buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.b_top_left));
        buttons.add((Button) findViewById(R.id.b_top_center));
        buttons.add((Button) findViewById(R.id.b_top_right));
        buttons.add((Button) findViewById(R.id.b_middle_left));
        buttons.add((Button) findViewById(R.id.b_middle_center));
        buttons.add((Button) findViewById(R.id.b_middle_right));
        buttons.add((Button) findViewById(R.id.b_bottom_left));
        buttons.add((Button) findViewById(R.id.b_bottom_center));
        buttons.add((Button) findViewById(R.id.b_bottom_right));
        addButtonActions();
    }

    private void addButtonActions() {
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button clickedButton = (Button) view;
                    movePlayer(buttons.indexOf(clickedButton));
                    if (!gameOver) {
                        moveAi();
                    }
                }
            });
        }
    }

    private void movePlayer(int playerMove) {
        markButton(buttons.get(playerMove), player);
        markBoard(playerMove, player);
        checkWin(player);
        checkTie();
    }

    private void moveAi() {
        Move aiMove = aiMoveGenerator.getBestMove(ai.getSymbol());
        if (aiMove != null) {
            markButton(buttons.get(aiMove.getPosition()), ai);
            markBoard(aiMove.getPosition(), ai);
            checkWin(ai);
            checkTie();
        }

    }

    private void checkTie() {
        if (!gameOver && board.isFull()) {
            gameOver = true;
            gameResultTv.setText(getString(R.string.tie));
        }
    }

    private void checkWin(Player player) {
        if (board.hasWon(player.getSymbol())) {
            gameOver = true;
            gameResultTv.setText(player.getWinningText());
            disableButtons();
        }
    }

    private void markBoard(int position, Player player) {
        board.mark(position, player.getSymbol());
    }

    private void markButton(Button clickedButton, Player player) {
        clickedButton.setTextColor(player.getColor());
        clickedButton.setText(player.getSymbol());
        clickedButton.setClickable(false);
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setClickable(false);
        }
    }

    private void enableButtons() {
        for (Button button : buttons) {
            button.setClickable(true);
        }
    }

    private void cleanButtons() {
        for (Button button : buttons) {
            button.setText("");
        }
    }
}

package co.codingnomads.tictactoetutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    public static final String PLAYER_SYMBOL = "X";

    private List<Button> buttons;
    private Button startButton;
    private TextView gameResultTv;
    private Player player = new Player(PLAYER_SYMBOL);
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initializeUi();
        board = new Board();
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
                board.erase();
                gameResultTv.setText("");
                cleanButtons();
                enableButtons();
            }
        });
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
                    clickedButton.setText(PLAYER_SYMBOL);
                    clickedButton.setClickable(false);
                    board.mark(buttons.indexOf(clickedButton), PLAYER_SYMBOL);
                    if(board.isThereWinner(player)){
                        gameResultTv.setText("You win!");
                        disableButtons();
                    }
                }
            });
        }
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

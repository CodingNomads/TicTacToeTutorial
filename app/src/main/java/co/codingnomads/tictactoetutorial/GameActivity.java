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
    private TextView gameResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initializeUi();
    }

    private void initializeUi() {
        initializeButtons();
        gameResultTv = findViewById(R.id.tv_gameResult);
    }

    private void initializeButtons() {
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
                    ((Button) view).setText(PLAYER_SYMBOL);
                }
            });
        }
    }
}

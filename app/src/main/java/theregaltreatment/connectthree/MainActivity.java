package theregaltreatment.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [] [] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    CharSequence player;

    boolean gameIsActive = true;


    public void dropIn (View view) {

        ImageView counter = (ImageView) view;
        int tapped = Integer.parseInt(counter.getTag().toString());

        if (gameState[tapped] == 2 && gameIsActive) {

            gameState[tapped] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.red);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.green);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(500);

            for(int [] winningPosition: winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                       && gameState[winningPosition[0]] != 2) {
                    if (gameState[winningPosition[0]] == 0) {
                       player = "Player 1 (Red) wins!";
                    }
                    else {
                        player = "Player 2 (Green) wins!";
                    }

                    TextView winText = (TextView) findViewById(R.id.winText);
                    winText.setText(player);
                    LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
                    playAgain.setVisibility(View.VISIBLE);
                    gameIsActive = false;
                }
                else {
                    boolean gameIsOver = true;
                    for (int gameCounter: gameState) {
                        if (gameCounter == 2) gameIsOver = false;
                    }
                        if (gameIsOver) {
                            player = "Draw";
                            TextView winText = (TextView) findViewById(R.id.winText);
                            winText.setText(player);
                            LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
                            playAgain.setVisibility(View.VISIBLE);
                            gameIsActive = false;
                        }
                    }

                }
            }

        }

    public void playAgain (View view) {
        LinearLayout playAgain = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgain.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for (int i = 0; i<gameState.length; i++) {
            gameState [i] = 2;
        }

        GridLayout board = (GridLayout) findViewById(R.id.gameBoard);

        for(int i = 0; i< board.getChildCount(); i++) {
            ImageView img = (ImageView) board.getChildAt(i);
            img.setImageDrawable(null);
        }
        gameIsActive = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

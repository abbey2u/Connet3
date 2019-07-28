package com.example.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0=yellow, 1=read

    int activePlayer = 0;
    boolean gameIsActive = true;

    //2 means unplayed

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8}, {2,4,6}};

    public  void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPositions : winningPosition) {

                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&

                        gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]] != 2) {

                    String winner = " Red";

                    if (gameState[winningPositions[0]] == 0) {
                        winner = "Yellow";
                    }
                    System.out.println(gameState[winningPositions[0]]);

                    //someone has won

                    gameIsActive = false;

                    TextView textView = findViewById(R.id.winnerMessage);
                    textView.setText(winner + "  has won");

                    LinearLayout layout = findViewById(R.id.messagDisplay);
                    layout.setVisibility(View.VISIBLE);

                }else {

                    boolean gameIsOver = true;
                    for (int counterState : gameState){
                        if (counterState ==2) gameIsOver =false;
                        }
                        if (gameIsOver){

                            TextView textView = findViewById(R.id.winnerMessage);
                            textView.setText(" It is a draw ");

                            LinearLayout layout = findViewById(R.id.messagDisplay);
                            layout.setVisibility(View.VISIBLE);


                    }
                }
            }

        }
    }

    public void playAgain(View view) {

            gameIsActive = true;

            LinearLayout linearLayout = findViewById(R.id.messagDisplay);

            linearLayout.setVisibility(View.INVISIBLE);

            activePlayer = 0;

            for (int i = 0; i < gameState.length; i++) {

                gameState[i] = 2;
            }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

            for (int i =0; i<gridLayout.getChildCount(); i++){

                ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
            }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

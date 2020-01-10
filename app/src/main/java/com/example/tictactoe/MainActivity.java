package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {



    // 0: yellow    1: red  2: null
    int []gameState={2,2,2,2,2,2,2,2,2};
    int [][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    int activePlayer=0;
    boolean gameActive=true;
    public void dropIn(View view){
        ImageView count = (ImageView) view;
        int tappedPosition=Integer.parseInt(count.getTag().toString());
        if(gameState[tappedPosition]==2 && gameActive) {
            gameState[tappedPosition] = activePlayer;
            count.setTranslationY(-1500);

            if (activePlayer == 0) {

                count.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                count.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            count.animate().translationYBy(1500).rotation(3600).setDuration(400);

            for (int[] x : winningPositions) {
                if (gameState[x[0]] == gameState[x[1]] && gameState[x[1]] == gameState[x[2]] && gameState[x[0]] != 2) {
                    String winner = "";

                    gameActive=false;
                    if (activePlayer == 1) {
                        winner = "Yellow";
                     //   Toast.makeText(this, winner+" Won" ,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        winner = "Red";
                      //  Toast.makeText(this, winner+" Won" ,Toast.LENGTH_SHORT).show();
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView =(TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " Has Won");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);



                }

            }

        }
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView =(TextView) findViewById(R.id.winnerTextView);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);


        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView)gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        activePlayer=0;
        gameActive=true;



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

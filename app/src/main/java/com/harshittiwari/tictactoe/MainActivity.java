package com.harshittiwari.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //Player representation
    // 0-X
    // 1-O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meanings;
    // 0-X
    // 1-O
    // 2-null
    int[][] winPositions = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    Button playAgain;
    ImageView image0, image1, image2, image3, image4, image5, image6, image7, image8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = findViewById(R.id.relative_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        playAgain = findViewById(R.id.play_again);
        image0 = findViewById(R.id.image0);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);

    }

        public void playerclicked(View view) {
            ImageView img = (ImageView) view;
            int tappedImage = Integer.parseInt(img.getTag().toString());
                if (gameState[tappedImage] == 2) {
                    gameState[tappedImage] = activePlayer;
                    img.setTranslationY(-1000f);
                    if (activePlayer == 0) {
                        img.setImageResource(R.drawable.x);
                        activePlayer = 1;
                        TextView status = findViewById(R.id.status);
                        status.setText("O's Turn - Tap to Play");
                    } else {
                        img.setImageResource(R.drawable.o);
                        activePlayer = 0;
                        TextView status = findViewById(R.id.status);
                        status.setText("X's turn - Tap to Play");
                    }
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                // check if any player has won
                for (int[] winPosition : winPositions) {
                    if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                            gameState[winPosition[1]] == gameState[winPosition[2]] &&
                            gameState[winPosition[0]] != 2) {
                        //somebody has won.
                        String winnerStr;
                        gameActive = false;
                        if (gameState[winPosition[0]] == 0) {
                            winnerStr = " X has won...";
                        } else {
                            winnerStr = " O has won...";
                        }
                        // Update the status bar for winner announcement

                        TextView status = findViewById(R.id.status);
                        status.setText(winnerStr);

                    }else{

                        boolean isGameADraw = true;
                        for(int state : gameState){
                            if(state == 2){
                                isGameADraw = false;
                            }
                        }

                        if(isGameADraw){
                            TextView status = findViewById(R.id.status);
                            status.setText("This Game is Draw");
                        }
                    }
                }
            }

    public void playAgain(View view) {
            gameActive = true;
            activePlayer = 0;
            for (int i=0; i<gameState.length;i++){
                gameState[i] = 2;
            }
               image0.setImageResource(0);
               image1.setImageResource(0);
               image2.setImageResource(0);
               image3.setImageResource(0);
               image4.setImageResource(0);
               image5.setImageResource(0);
               image6.setImageResource(0);
               image7.setImageResource(0);
               image8.setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn -Tap to Play");
    }

}
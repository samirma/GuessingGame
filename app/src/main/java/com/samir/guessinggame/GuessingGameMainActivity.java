package com.samir.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.samir.guessinggame.guessGame.GuessGameDelegate;
import com.samir.guessinggame.guessGame.GuessingGame;
import com.samir.guessinggame.guessGame.GuessingGameFactory;

public class GuessingGameMainActivity extends AppCompatActivity implements GuessGameDelegate {

    private GuessingGame guessingGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_game_main);

        guessingGame = GuessingGameFactory.getGuessingGame(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        guessingGame.start();
    }


    @Override
    public void askForAttribute(String attribute) {

    }

    @Override
    public void takeAGuess(String animal) {

    }
}

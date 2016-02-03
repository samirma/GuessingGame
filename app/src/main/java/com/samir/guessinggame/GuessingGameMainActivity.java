package com.samir.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.samir.guessinggame.guessGame.GuessGameDelegate;
import com.samir.guessinggame.guessGame.GuessingGame;
import com.samir.guessinggame.guessGame.GuessingGameFactory;

public class GuessingGameMainActivity extends AppCompatActivity implements GuessGameDelegate {

    private GuessingGame guessingGame;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_game_main);

        guessingGame = GuessingGameFactory.getGuessingGame(this);

        textView = (TextView) findViewById(R.id.id_text);

        editText = (EditText) findViewById(R.id.id_user_input);
        editText.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        guessingGame.start();
    }


    @Override
    public void askForAttribute(String attribute) {
        textView.setText(attribute + "?");
    }

    @Override
    public void takeAGuess(String animal) {
        textView.setText(animal + "?");
    }

    @Override
    public void askNewAttribute() {
        textView.setText("Aninal?");
        editText.setVisibility(View.VISIBLE);
    }

    public void answerYes(View view) {
        guessingGame.yes();
    }

    public void answerNo(View view) {
        guessingGame.no();
    }

    public void include(View view) {
        final String input = editText.getText().toString();
    }

}

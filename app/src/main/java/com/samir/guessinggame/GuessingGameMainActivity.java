package com.samir.guessinggame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.samir.guessinggame.guessGame.GuessGameDelegate;
import com.samir.guessinggame.guessGame.GuessingGame;
import com.samir.guessinggame.guessGame.GuessingGameFactory;

public class GuessingGameMainActivity extends AppCompatActivity implements GuessGameDelegate {

    private GuessingGame guessingGame;
    private TextView textView;

    private String newAttribute;
    private String newAnimal;

    private boolean isAskingForAttribute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_game_main);

        guessingGame = GuessingGameFactory.getGuessingGame(this);

        textView = (TextView) findViewById(R.id.id_text);

        isAskingForAttribute = true;

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
        isAskingForAttribute = true;
        showInputDialog();
    }

    @Override
    public void askNewAnimal() {
        isAskingForAttribute = false;
        showInputDialog();
    }

    @Override
    public void inputNewAttributeAnimal() {
        guessingGame.learnAttributeForAnimal(newAttribute, newAnimal);
    }

    public void answerYes(View view) {
        guessingGame.yes();
    }

    public void answerNo(View view) {
        guessingGame.no();
    }

    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final Editable text = editText.getText();

                        final String value = text.toString();

                        if (isAskingForAttribute) {
                            newAttribute = value;
                            guessingGame.newAttributeDone();
                        } else {
                            newAnimal = value;
                            guessingGame.newAnimalDone();
                        }

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

}

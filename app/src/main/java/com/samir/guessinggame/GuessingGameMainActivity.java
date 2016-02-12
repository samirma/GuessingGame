package com.samir.guessinggame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.samir.guessinggame.guessGame.GuessGameDelegate;
import com.samir.guessinggame.guessGame.GuessingGame;
import com.samir.guessinggame.guessGame.GuessingGameFactory;

public class GuessingGameMainActivity extends AppCompatActivity implements GuessGameDelegate {

    private GuessingGame guessingGame;
    private TextView textView;

    private String newAttribute;
    private String newAnimal;

    private String oldAttribute;
    private String oldAnimal;

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
        oldAttribute = attribute;
        final String question = String.format("Does the animal that you thought about %s?", attribute);
        textView.setText(question);
    }

    @Override
    public void takeAGuess(String animal) {
        oldAnimal = animal;
        final String question = String.format("Is the animal that you thought about a %s?", animal);
        textView.setText(question);
    }

    @Override
    public void askNewAttribute() {
        isAskingForAttribute = true;
        final String question = String.format("A %s _____ but a %s does not (Fill it an animal trait, like '%s'", newAnimal, oldAnimal, oldAttribute);
        showInputDialog(question);
    }

    @Override
    public void askNewAnimal() {
        isAskingForAttribute = false;
        showInputDialog("What was the animal that you thought about?");
    }

    @Override
    public void inputNewAttributeAnimal() {
        guessingGame.learnAttributeForAnimal(newAttribute, newAnimal);
    }

    @Override
    public void youWin() {
        CharSequence text = "I win again!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    public void answerYes(View view) {
        guessingGame.yes();
    }

    public void answerNo(View view) {
        guessingGame.no();
    }

    protected void showInputDialog(final String titleText) {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);

        TextView title = (TextView) promptView.findViewById(R.id.id_question);

        title.setText(titleText);

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

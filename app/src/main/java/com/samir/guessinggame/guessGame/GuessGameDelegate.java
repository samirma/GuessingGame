package com.samir.guessinggame.guessGame;

/**
 * Interface in order to game interact with the GUI
 */
public interface GuessGameDelegate {

    void askForAttribute(final String attribute);

    void takeAGuess(final String animal);

    void askNewAttribute();

    void askNewAnimal();

    void inputNewAttributeAnimal();

    void youWin();

}

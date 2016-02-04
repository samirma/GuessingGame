package com.samir.guessinggame.guessGame;


public interface GuessGameDelegate {

    void askForAttribute(final String attribute);

    void takeAGuess(final String animal);

    void askNewAttribute();

    void askNewAnimal();

    void inputNewAttributeAnimal();

    void youWin();
}

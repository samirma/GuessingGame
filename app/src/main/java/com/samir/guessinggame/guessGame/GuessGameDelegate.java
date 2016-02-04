package com.samir.guessinggame.guessGame;


import com.samir.guessinggame.guessGame.engine.Status;

public interface GuessGameDelegate {

    void askForAttribute(final String attribute);

    void takeAGuess(final String animal);

    void askNewAttribute();

    void askNewAnimal();

    void inputNewAttributeAnimal();

    void youWin();

    void takeAAlternativeGuess(String alternativeAnimal);

}

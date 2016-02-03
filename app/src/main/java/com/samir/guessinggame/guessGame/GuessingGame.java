package com.samir.guessinggame.guessGame;


public interface GuessingGame {
    void start();

    void no();

    void yes();

    boolean isFinished();

    void learnAttributeForAnimal(String newAttribute, String newAnimal);

    void newAttributeDone();

    void newAnimalDone();
}

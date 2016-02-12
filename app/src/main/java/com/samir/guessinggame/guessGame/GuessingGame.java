package com.samir.guessinggame.guessGame;

/**
 * Interface used by gui to interact with guess game
 */
public interface GuessingGame {
    void start();

    void no();

    void yes();

    void learnAttributeForAnimal(String newAttribute, String newAnimal);

    void newAttributeDone();

    void newAnimalDone();
}

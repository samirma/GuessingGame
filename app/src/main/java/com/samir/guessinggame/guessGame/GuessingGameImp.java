package com.samir.guessinggame.guessGame;


import com.samir.guessinggame.guessGame.engine.GuessingGameEngine;
import com.samir.guessinggame.guessGame.engine.GuessingGameEngineFactory;
import com.samir.guessinggame.guessGame.engine.Status;
import com.samir.guessinggame.guessGame.model.Animal;
import com.samir.guessinggame.guessGame.model.Attribute;
import com.samir.guessinggame.guessGame.model.ResponseType;

/**
 * GuessingGameImp act as a controller between to interface and the core engine
 * This class know which GUI method shuld be called for each status
 */
public class GuessingGameImp implements GuessingGame {

    final GuessingGameEngine guessingGameEngine;

    private GuessGameDelegate delegate;

    public GuessingGameImp(GuessGameDelegate delegate) {
        this.delegate = delegate;
        guessingGameEngine = GuessingGameEngineFactory.getGuessingGame();
    }


    @Override
    public void start() {
        guessingGameEngine.start();
        askForAttibute();
    }

    private void askForAttibute() {
        final String attributeName = guessingGameEngine.getNodeName();
        delegate.askForAttribute(attributeName);
    }

    @Override
    public void no() {
        final Status status = guessingGameEngine.getStatus();

        if (Status.WAITING_ANSWER_FOR_ATTRIBUTE.equals(status)) {
            noForAttribute();
        } else if (Status.WAITING_ANSWER_FOR_ANIMAL.equals(status)) {
            noForAnimal();
        }
    }

    private void noForAnimal() {
        delegate.askNewAnimal();
    }

    private void noForAttribute() {

        final ResponseType responseType = guessingGameEngine.noForAttribute();

        if (ResponseType.CHECK_NEXT_ATTRIBUTE.equals(responseType)) {

            askForAttibute();

        } else {
            delegate.takeAGuess(guessingGameEngine.getNodeName());
        }
    }

    @Override
    public void yes() {
        final Status status = guessingGameEngine.getStatus();
        if (Status.WAITING_ANSWER_FOR_ATTRIBUTE.equals(status)) {
            guessingGameEngine.yesForAttribute();
            final String animalName = guessingGameEngine.getNodeName();
            delegate.takeAGuess(animalName);

        } else if (Status.WAITING_ANSWER_FOR_ANIMAL.equals(status)) {
            start();
            guessingGameEngine.youWin();
            delegate.youWin();
        }
    }


    @Override
    public void learnAttributeForAnimal(String newAttribute, String newAnimal) {

        final Attribute newAttributeAnimal = new Attribute(newAttribute);

        final Animal animal = new Animal(newAnimal);

        //Save new information
        guessingGameEngine.learnAttributeForAnimal(newAttributeAnimal, animal);

        //Restart the game
        guessingGameEngine.start();

        askForAttibute();

    }

    @Override
    public void newAttributeDone() {
        delegate.inputNewAttributeAnimal();
    }

    @Override
    public void newAnimalDone() {
        delegate.askNewAttribute();
    }


}

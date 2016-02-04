package com.samir.guessinggame.guessGame;


import com.samir.guessinggame.guessGame.engine.GuessingGameEngine;
import com.samir.guessinggame.guessGame.engine.GuessingGameEngineFactory;
import com.samir.guessinggame.guessGame.engine.Status;
import com.samir.guessinggame.guessGame.model.Animal;
import com.samir.guessinggame.guessGame.model.Attribute;
import com.samir.guessinggame.guessGame.model.ResponseType;

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
        final String attributeName = guessingGameEngine.getAttributeName();
        delegate.askForAttribute(attributeName);
    }

    @Override
    public void no() {
        final Status status = guessingGameEngine.getStatus();

        if (Status.WAITING_ANSWER_FOR_ATTRIBUTE.equals(status)) {
            noForAttribute();
        } else if (Status.WAITING_ANSWER_FOR_ANIMAL.equals(status)) {
            noForAnimal();
        }else if (Status.CHECKING_NEXT_ATTIBUTE.equals(status)) {
            noForAnimal();
        }
    }

    private void noForAnimal() {
        guessingGameEngine.yesForAttribute();
        delegate.askNewAnimal();
    }

    private void noForAttribute() {

        final ResponseType responseType = guessingGameEngine.youLost();

        if (ResponseType.GO_TO_LEARNING_MODE.equals(responseType)) {

            delegate.askNewAttribute();

        } if (ResponseType.GO_TO_ALTERNATIVE_NEXT_GUESS.equals(responseType)) {

            final String alternativeAnimal = guessingGameEngine.getAlternativeAnimal();
            delegate.takeAAlternativeGuess(alternativeAnimal);

        } else {
            askForAttibute();
        }
    }

    @Override
    public void yes() {
        final Status status = guessingGameEngine.getStatus();
        if (Status.WAITING_ANSWER_FOR_ATTRIBUTE.equals(status)) {
            guessingGameEngine.yesForAttribute();
            final String animalName = guessingGameEngine.getAnimalName();
            delegate.takeAGuess(animalName);

        } else if (Status.WAITING_ANSWER_FOR_ANIMAL.equals(status)) {
            start();
            guessingGameEngine.youWin();
            delegate.youWin();
        }
    }

    @Override
    public boolean isFinished() {
        final Status status = guessingGameEngine.getStatus();
        return Status.FINISHED_WIN.equals(status);
    }

    @Override
    public void learnAttributeForAnimal(String newAttribute, String newAnimal) {

        final Attribute newAttributeAnimal = new Attribute(newAttribute);

        final Animal animal = new Animal(newAnimal);

        guessingGameEngine.learnAttributeForAnimal(newAttributeAnimal, animal);

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

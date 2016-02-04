package com.samir.guessinggame.guessGame.memory;


import com.samir.guessinggame.guessGame.engine.GuessingGameEngine;
import com.samir.guessinggame.guessGame.engine.Status;
import com.samir.guessinggame.guessGame.model.Animal;
import com.samir.guessinggame.guessGame.model.Attribute;
import com.samir.guessinggame.guessGame.model.ResponseType;

public class GuessingGameEngineMemory implements GuessingGameEngine {

    private Attribute current;

    private Status status;

    @Override
    public String getAttributeName() {
        return current.getName();
    }

    @Override
    public void reset() {
        current = GuessGameRepository.getLiveInLive();
    }

    @Override
    public void start() {
        reset();
        status = Status.WAITING_ANSWER_FOR_ATTRIBUTE;
    }

    @Override
    public void yesForAttribute() {
        status = Status.WAITING_ANSWER_FOR_ANIMAL;
    }

    @Override
    public String getAnimalName() {
        return current.getAnimal().getName();
    }

    @Override
    public ResponseType youLost() {

        ResponseType result;

        final Attribute nextAttribute = current.getNextAttribute();
        if (nextAttribute == null) {

            if (Status.WAITING_ANSWER_FOR_ATTRIBUTE.equals(status)) {
                result = ResponseType.GO_TO_ALTERNATIVE_NEXT_GUESS;
                status = Status.WAITING_ANSWER_FOR_ANIMAL;
            } else {
                result = ResponseType.GO_TO_LEARNING_MODE;
                status = Status.LEARNING;
            }

        } else {

            if (!Status.CHECKING_NEXT_ATTIBUTE.equals(status)) {
                result = ResponseType.CHECK_NEXT_ATTRIBUTE;
                status = Status.CHECKING_NEXT_ATTIBUTE;
            } else {
                result = ResponseType.GO_TO_NEXT_GUESS;
                current = nextAttribute;
            }

        }

        return result;
    }

    @Override
    public void youWin() {
        reset();
        status = Status.FINISHED_WIN;
    }

    @Override
    public void learnAttributeForAnimal(Attribute newAttributeAnimal, Animal animal) {
        newAttributeAnimal.setAnimal(animal);
        current.setNextAttribute(newAttributeAnimal);
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getAlternativeAnimal() {
        return current.getAlternativeAnimal().getName();
    }
}

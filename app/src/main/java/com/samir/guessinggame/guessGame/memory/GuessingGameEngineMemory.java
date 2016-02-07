package com.samir.guessinggame.guessGame.memory;


import android.support.annotation.NonNull;

import com.samir.guessinggame.guessGame.engine.GuessingGameEngine;
import com.samir.guessinggame.guessGame.engine.Status;
import com.samir.guessinggame.guessGame.model.Animal;
import com.samir.guessinggame.guessGame.model.Attribute;
import com.samir.guessinggame.guessGame.model.Node;
import com.samir.guessinggame.guessGame.model.ResponseType;

public class GuessingGameEngineMemory implements GuessingGameEngine {

    private Node current;

    private Status status;


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
    public ResponseType yesForAttribute() {
        current = current.getYesNode();
        return getAttributeResponseType();
    }

    @NonNull
    private ResponseType getAttributeResponseType() {
        ResponseType result;
        if (current.isAttribute()) {
            result = ResponseType.CHECK_NEXT_ATTRIBUTE;
        } else {
            result = ResponseType.GO_TO_NEXT_GUESS;
            status = Status.WAITING_ANSWER_FOR_ANIMAL;
        }

        return result;
    }

    @Override
    public ResponseType noForAttribute() {
        current = current.getNoNode();
        return getAttributeResponseType();
    }

    @Override
    public String getNodeName() {
        return current.getName();
    }

    @Override
    public ResponseType youLost() {

        ResponseType result;

        result = ResponseType.GO_TO_LEARNING_MODE;
        status = Status.LEARNING;

        return result;
    }

    @Override
    public void youWin() {
        reset();
        status = Status.WAITING_ANSWER_FOR_ATTRIBUTE;
    }

    @Override
    public void learnAttributeForAnimal(Attribute newAttributeAnimal, Animal animal) {
        final Node parent = current.getParent();

        if (parent.getNoNode().equals(current)) {
            parent.setNoNode(newAttributeAnimal);
        } else {
            parent.setYesNode(newAttributeAnimal);
        }
        newAttributeAnimal.setYesNode(animal);
        newAttributeAnimal.setNoNode(current);

    }

    @Override
    public Status getStatus() {
        return status;
    }

}

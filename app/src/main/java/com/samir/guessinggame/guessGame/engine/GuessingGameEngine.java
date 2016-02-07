package com.samir.guessinggame.guessGame.engine;


import com.samir.guessinggame.guessGame.model.Animal;
import com.samir.guessinggame.guessGame.model.Attribute;
import com.samir.guessinggame.guessGame.model.ResponseType;

public interface GuessingGameEngine {


    void reset();

    void start();

    ResponseType yesForAttribute();

    ResponseType noForAttribute();

    String getNodeName();

    ResponseType youLost();

    void youWin();

    void learnAttributeForAnimal(Attribute newAttributeAnimal, Animal animal);

    Status getStatus();
}

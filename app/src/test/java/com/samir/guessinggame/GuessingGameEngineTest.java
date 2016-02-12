package com.samir.guessinggame;

import com.samir.guessinggame.guessGame.engine.GuessingGameEngine;
import com.samir.guessinggame.guessGame.engine.GuessingGameEngineFactory;
import com.samir.guessinggame.guessGame.engine.Status;
import com.samir.guessinggame.guessGame.model.Animal;
import com.samir.guessinggame.guessGame.model.Attribute;
import com.samir.guessinggame.guessGame.model.ResponseType;

import org.junit.Assert;
import org.junit.Test;


public class GuessingGameEngineTest {


    @Test
    public void addition_FistAttributeisCorrect() throws Exception {
        final GuessingGameEngine guessingGameEngine = GuessingGameEngineFactory.getGuessingGame();

        guessingGameEngine.start();

        guessingGameEngine.yesForAttribute();

        String animalName = guessingGameEngine.getNodeName();

        Assert.assertNotNull(animalName);

        guessingGameEngine.youWin();

        Status status = guessingGameEngine.getStatus();

        org.junit.Assert.assertEquals(Status.WAITING_ANSWER_FOR_ATTRIBUTE, status);

    }


    @Test
    public void after_learning() throws Exception {
        final GuessingGameEngine guessingGameEngine = GuessingGameEngineFactory.getGuessingGame();

        guessingGameEngine.reset();

        ResponseType responseType = startAndGetFirstAnswerWrong(guessingGameEngine);

        org.junit.Assert.assertEquals(ResponseType.GO_TO_LEARNING_MODE, responseType);

        final String fly = "fly";
        final Attribute newAttributeAnimal = new Attribute(fly);

        final Animal animal = new Animal("Crow");

        guessingGameEngine.learnAttributeForAnimal(newAttributeAnimal, animal);

        ResponseType newResponseType = startAndGetFirstAnswerWrong(guessingGameEngine);

        org.junit.Assert.assertEquals(ResponseType.GO_TO_LEARNING_MODE, newResponseType);

        String attribute = guessingGameEngine.getNodeName();

        org.junit.Assert.assertEquals(fly, attribute);


    }

    private ResponseType startAndGetFirstAnswerWrong(GuessingGameEngine guessingGameEngine) {
        guessingGameEngine.start();

        String attribute = guessingGameEngine.getNodeName();

        Assert.assertNotNull(attribute);

        guessingGameEngine.yesForAttribute();

        String animalName = guessingGameEngine.getNodeName();

        Assert.assertNotNull(animalName);

        return guessingGameEngine.youLost();
    }


}
package com.samir.guessinggame;

import com.samir.guessinggame.guessGame.GuessGameDelegate;
import com.samir.guessinggame.guessGame.GuessingGame;
import com.samir.guessinggame.guessGame.GuessingGameFactory;
import com.samir.guessinggame.guessGame.engine.GuessingGameEngine;
import com.samir.guessinggame.guessGame.engine.GuessingGameEngineFactory;
import com.samir.guessinggame.guessGame.engine.Status;
import com.samir.guessinggame.guessGame.model.Animal;
import com.samir.guessinggame.guessGame.model.Attribute;
import com.samir.guessinggame.guessGame.model.ResponseType;

import org.junit.Assert;
import org.junit.Test;


public class GuessingGameTest {


    @Test
    public void start_game() throws Exception {


        final GuessingGame guessingGame = GuessingGameFactory.getGuessingGame(new GuessGameDelegate() {

            public void askForAttribute(final String attributeToTest){
                Assert.assertEquals("live on water", attributeToTest);
            }

            @Override
            public void takeAGuess(final String animal) {
                Assert.assertEquals("Shark", animal);
            }

        });

        guessingGame.start();

        guessingGame.yes();

        guessingGame.yes();

        final boolean finished = guessingGame.isFinished();
        Assert.assertTrue(finished);

    }

}
package com.samir.guessinggame;

import com.samir.guessinggame.guessGame.GuessGameDelegate;
import com.samir.guessinggame.guessGame.GuessingGame;
import com.samir.guessinggame.guessGame.GuessingGameFactory;

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

            @Override
            public void askNewAttribute() {

            }

            @Override
            public void askNewAnimal() {

            }

            @Override
            public void inputNewAttributeAnimal() {

            }

            @Override
            public void youWin() {

            }

        });

        guessingGame.start();

        guessingGame.yes();

        guessingGame.yes();


    }

}
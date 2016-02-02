package com.samir.guessinggame.guessGame.engine;


import com.samir.guessinggame.guessGame.memory.GuessingGameEngineMemory;

public class GuessingGameEngineFactory {

    public static GuessingGameEngine getGuessingGame() {
        return new GuessingGameEngineMemory();
    }
}

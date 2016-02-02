package com.samir.guessinggame.guessGame;


public class GuessingGameFactory {
    public static GuessingGame getGuessingGame(GuessGameDelegate delegate) {
        return new GuessingGameImp(delegate);
    }
}

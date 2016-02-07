package com.samir.guessinggame.guessGame.model;


public class Animal extends Node {

    public Animal(String name) {
        super(name);
    }

    @Override
    public boolean isAttribute() {
        return false;
    }
}

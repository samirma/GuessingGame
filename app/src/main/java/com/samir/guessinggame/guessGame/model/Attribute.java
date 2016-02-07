package com.samir.guessinggame.guessGame.model;


public class Attribute extends Node {

    public Attribute(String name) {
        super(name);
    }

    @Override
    public boolean isAttribute() {
        return true;
    }

}
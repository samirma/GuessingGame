package com.samir.guessinggame.guessGame.engine.memory;


import android.support.annotation.NonNull;

import com.samir.guessinggame.guessGame.model.Animal;
import com.samir.guessinggame.guessGame.model.Attribute;

public class GuessGameRepository {

    private static Attribute saved;

    public static Attribute getLiveInLive() {

        if (saved == null) {
            saved = getNewAttribute();
        }

        return saved;

    }

    @NonNull
    private static Attribute getNewAttribute() {
        final Attribute attribute = new Attribute("live on water");

        final Animal animal = new Animal("Shark");
        attribute.setYesNode(animal);

        final Animal alternativeAnimal = new Animal("Monkey");
        attribute.setNoNode(alternativeAnimal);

        return attribute;
    }
}

package com.samir.guessinggame.guessGame.model;


public class Attribute {
    private String name;
    private Attribute nextAttribute;
    private Animal animal;

    private Animal alternativeAnimal;

    public Attribute(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getName() {
        return name;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Attribute getNextAttribute() {
        return nextAttribute;
    }

    public void setNextAttribute(Attribute nextAttribute) {
        this.nextAttribute = nextAttribute;
    }

    public Animal getAlternativeAnimal() {
        return alternativeAnimal;
    }

    public void setAlternativeAnimal(Animal alternativeAnimal) {
        this.alternativeAnimal = alternativeAnimal;
    }
}

package com.samir.guessinggame.guessGame.model;


/**
 * Parent Node for tree
 */
public abstract class Node {
    private String name;

    private Node parent;

    private Node yesNode;
    private Node noNode;

    public Node(String name) {
        this.name = name;
    }

    public abstract boolean isAttribute();

    public Node getYesNode() {
        return yesNode;
    }

    public void setYesNode(Node yesNode) {
        this.yesNode = yesNode;
        yesNode.parent = this;
    }

    public Node getNoNode() {
        return noNode;
    }

    public void setNoNode(Node noNode) {
        this.noNode = noNode;
        noNode.parent = this;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.diagram;

public class ElementConnectionData {

    private String connection;
    private boolean move;

    public ElementConnectionData(String connection, boolean move) {
        this.connection = connection;
        this.move = move;
    }

    public String getConnection() {
        return connection;
    }

    public boolean isMove() {
        return move;
    }
}
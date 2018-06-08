package com.example.shivam.tic_tac_toeonline;

public class Game {

    boolean isStarted;
    Host host;
    Away away;
    int lastMove;
    boolean isHostZero;
    Box box ;





    public Game(){

    }

    public boolean isHostZero() {
        return isHostZero;
    }

    public void setHostZero(boolean zero) {
        isHostZero = zero;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Away getAway() {
        return away;
    }

    public void setAway(Away away) {
        this.away = away;
    }

    public int getLastMove() {
        return lastMove;
    }

    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }
}

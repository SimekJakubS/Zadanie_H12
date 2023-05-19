package com.company;

public class Predmet {
    int cena;
    int hmotnost;
    int index;

    public Predmet(int cena, int hmotnost, int index) {
        this.cena = cena;
        this.hmotnost = hmotnost;
        this.index = index;

    }

    public int getCena() {
        return cena;
    }

    public int getHmotnost() {
        return hmotnost;
    }

    public int getIndex() {
        return index;
    }


}

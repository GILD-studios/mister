package com.gildstudios.mister.models;

import com.gildstudios.mister.enums.Position;


public class Player {

    public String name;
    public Position position;
    public int rating;

    public Player() {
        this.name = null;
        this.position = null;
        this.rating = 0;
    }

    public static Player with(String name, Position position, int rating) {
        return new Player()
                .name(name)
                .position(position)
                .rating(rating);
    }

    public Player name(String name) {
        this.name = name;
        return this;
    }

    public Player position(Position position) {
        this.position = position;
        return this;
    }

    public Player rating(int rating) {
        this.rating = rating;
        return this;
    }

}

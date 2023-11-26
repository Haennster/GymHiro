package com.example.gymhiro.classes;

public class Serie {
    int numberOfRepetitions;
    int weight;

    public Serie(int numberOfRepetitions, int weight) {
        this.numberOfRepetitions = numberOfRepetitions;
        this.weight = weight;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public void setNumberOfRepetitions(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

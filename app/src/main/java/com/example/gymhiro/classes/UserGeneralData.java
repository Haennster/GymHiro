package com.example.gymhiro.classes;

public class UserGeneralData {
    int id;
    String date;
    double weight;
    int height;

    public UserGeneralData(int id, String date, double weight, int height) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

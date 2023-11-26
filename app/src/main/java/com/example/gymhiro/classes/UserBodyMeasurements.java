package com.example.gymhiro.classes;

public class UserBodyMeasurements {
    int Id;
    String date;
    double lBiceps;
    double rBiceps;
    double chest;
    double waist;
    double lThigh;
    double rThigh;
    double lCalf;
    double rCalf;

    public UserBodyMeasurements(int id, String date, double lBiceps, double rBiceps, double chest, double waist, double lThigh, double rThigh, double lCalf, double rCalf) {
        Id = id;
        this.date = date;
        this.lBiceps = lBiceps;
        this.rBiceps = rBiceps;
        this.chest = chest;
        this.waist = waist;
        this.lThigh = lThigh;
        this.rThigh = rThigh;
        this.lCalf = lCalf;
        this.rCalf = rCalf;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getlBiceps() {
        return lBiceps;
    }

    public void setlBiceps(double lBiceps) {
        this.lBiceps = lBiceps;
    }

    public double getrBiceps() {
        return rBiceps;
    }

    public void setrBiceps(double rBiceps) {
        this.rBiceps = rBiceps;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getlThigh() {
        return lThigh;
    }

    public void setlThigh(double lThigh) {
        this.lThigh = lThigh;
    }

    public double getrThigh() {
        return rThigh;
    }

    public void setrThigh(double rThigh) {
        this.rThigh = rThigh;
    }

    public double getlCalf() {
        return lCalf;
    }

    public void setlCalf(double lCalf) {
        this.lCalf = lCalf;
    }

    public double getrCalf() {
        return rCalf;
    }

    public void setrCalf(double rCalf) {
        this.rCalf = rCalf;
    }
}

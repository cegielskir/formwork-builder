package com.cegielskir.formwork.builder.computing;

public class GirdersDetails {
    private int numberOfRows;
    private float distanceBetweenRows;

    public GirdersDetails(int numberOfRows, float distanceBetweenRows) {
        this.numberOfRows = numberOfRows;
        this.distanceBetweenRows = distanceBetweenRows;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public float getDistanceBetweenRows() {
        return distanceBetweenRows;
    }
}

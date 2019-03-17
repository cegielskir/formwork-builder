package com.cegielskir.formwork.builder.computing;

import java.util.ArrayList;
import java.util.List;

public class FormworkedRoom {

    private int length;
    private int width;
    private int upperRows;
    private int bottomRows;
    private int upperDistance;
    private int bottomDistance;
    private List<Integer> upperGirders;
    private List<Integer> bottomGirders;

    public FormworkedRoom(){
        upperGirders = new ArrayList<>();
        bottomGirders = new ArrayList<>();
    }


    public void setUpperRows(int upperRows) {
        this.upperRows = upperRows;
    }


    public void setBottomRows(int bottomRows) {
        this.bottomRows = bottomRows;
    }


    public void setUpperGirders(List<Float> upperGirders) {
        for(Float fl : upperGirders) this.upperGirders.add((int)(fl * 100));
    }


    public void setBottomGirders(List<Float> bottomGirders) {
        for(Float fl : bottomGirders) this.bottomGirders.add((int)(fl * 100));
    }


    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public void setUpperDistance(int upperDistance) {
        this.upperDistance = upperDistance;
    }


    public void setBottomDistance(int bottomDistance) {
        this.bottomDistance = bottomDistance;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getUpperRows() {
        return upperRows;
    }

    public int getBottomRows() {
        return bottomRows;
    }

    public int getUpperDistance() {
        return upperDistance;
    }

    public int getBottomDistance() {
        return bottomDistance;
    }

    public List<Integer> getUpperGirders() {
        return upperGirders;
    }

    public List<Integer> getBottomGirders() {
        return bottomGirders;
    }

    @Override
    public String toString() {
        return "{len : " + (int) (length * 100) + ",wid : " + (int) (width * 100) +
                ", upperRows : " + upperRows +
                ", bottomRows : " + bottomRows +
                ", upperDistance : " + (int) (upperDistance * 100) +
                ", bottomDistance : " + (int) (bottomDistance * 100) +
                ", upperGirders : " + upperGirders +
                ", bottomGirders : " + bottomGirders +
                '}';
    }
}

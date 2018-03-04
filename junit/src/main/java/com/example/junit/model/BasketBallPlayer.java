package com.example.junit.model;

import java.util.ArrayList;
import java.util.List;

public class BasketBallPlayer {

    private String name;
    private double size;
    private float weight;
    private double avgPointsPerGame;
    private double avgAssistsPerGame;
    private double avgReboundsPerGame;
    private String team;
    private boolean rookie;
    private List<BasketBallPlayer> teamMates = new ArrayList<BasketBallPlayer>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getAvgPointsPerGame() {
        return avgPointsPerGame;
    }

    public void setAvgPointsPerGame(double avgPointsPerGame) {
        this.avgPointsPerGame = avgPointsPerGame;
    }

    public double getAvgAssistsPerGame() {
        return avgAssistsPerGame;
    }

    public void setAvgAssistsPerGame(double avgAssistsPerGame) {
        this.avgAssistsPerGame = avgAssistsPerGame;
    }

    public double getAvgReboundsPerGame() {
        return avgReboundsPerGame;
    }

    public void setAvgReboundsPerGame(double avgReboundsPerGame) {
        this.avgReboundsPerGame = avgReboundsPerGame;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public boolean isRookie() {
        return rookie;
    }

    public void setRookie(boolean rookie) {
        this.rookie = rookie;
    }

    public List<BasketBallPlayer> getTeamMates() {
        return teamMates;
    }

    public void setTeamMates(List<BasketBallPlayer> teamMates) {
        this.teamMates = teamMates;
    }


}




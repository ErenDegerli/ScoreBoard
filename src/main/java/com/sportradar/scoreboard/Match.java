package com.sportradar.scoreboard;

import java.time.LocalDateTime;
import java.util.Objects;

public class Match {

    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final LocalDateTime startTime;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = Objects.requireNonNull(homeTeam);
        this.awayTeam = Objects.requireNonNull(awayTeam);
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = LocalDateTime.now();
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void updateScore(int homeScore, int awayScore) {
        if(homeScore < 0 || awayScore < 0) throw new IllegalArgumentException();

        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int totalScore() {
        return homeScore + awayScore;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    @Override
    public String toString() {
        return String.format("%s %d - %d %s", homeTeam, homeScore, awayScore, awayTeam);
    }
}

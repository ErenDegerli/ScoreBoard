package com.sportradar.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<Match> matches = new ArrayList<>();

    public ScoreBoard() {}

    public void startMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam));
    }

    public List<Match> getSummary() {
        return matches;
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Match match = matches.stream()
                .filter(mtc -> mtc.getHomeTeam().equals(homeTeam) && mtc.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        matches.remove(match);
    }
}

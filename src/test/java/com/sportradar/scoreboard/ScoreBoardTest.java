package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard;
    private final String ARGENTINA = "Argentina";
    private final String AUSTRALIA = "Australia";

    @Test
    void startMatch() {
        scoreBoard.startMatch(ARGENTINA, AUSTRALIA);
        List<Match> summary = scoreBoard.getSummary();

        Match match = summary.get(0);

        assertAll(
                () -> assertEquals(1, summary.size()),
                () -> assertEquals(ARGENTINA, match.getHomeTeam()),
                () -> assertEquals(AUSTRALIA, match.getAwayTeam()),
                () -> assertEquals(0, match.getHomeScore()),
                () -> assertEquals(0, match.getAwayScore())
        );
    }
}

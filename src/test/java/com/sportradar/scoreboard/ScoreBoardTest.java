package com.sportradar.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard;
    private final String ARGENTINA = "Argentina";
    private final String AUSTRALIA = "Australia";

    @BeforeEach
    void setup() {
        scoreBoard = new ScoreBoard();
    }
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

    @Test
    void finishMatch() {
        scoreBoard.startMatch(ARGENTINA, AUSTRALIA);
        scoreBoard.finishMatch();

        assertTrue(scoreBoard.getSummary().isEmpty());
    }
}

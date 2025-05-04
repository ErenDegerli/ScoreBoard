package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void startMatch() {
        Match match = new Match("Spain", "Brazil");

        assertEquals("Spain", match.getHomeTeam());
        assertEquals("Brazil", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void updateMatch() {
        Match match = new Match("Spain", "Brazil");
        match.updateScore(1, 3);

        assertEquals(1, match.getHomeScore());
        assertEquals(3, match.getAwayScore());
    }

    @Test
    void updateMatchHomeNegativeScore() {
        Match match = new Match("Spain", "Brazil");
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1, 2));
    }

    @Test
    void updateMatchAwayNegativeScore() {
        Match match = new Match("Spain", "Brazil");
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(1, -3));
    }

    @Test
    void homeTeamCannotBeNull() {
        assertThrows(NullPointerException.class, () -> new Match(null, "Spain"));
    }

    @Test
    void awayTeamCannotBeNull() {
        assertThrows(NullPointerException.class, () -> new Match("Spain", null));
    }

    @Test
    void finishMatch() {
        Match match = new Match("Spain", "Brazil");
        match.finishMatch();

        assertNull(match.getMatch());
    }
}

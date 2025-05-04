package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        match.updateMatch(1, 0);

        assertEquals(1, match.getHomeScore());
        assertEquals(3, match.getAwayScore());
    }

    @Test
    void finishMatch() {
        Match match = new Match("Spain", "Brazil");
        match.finishMatch();

        assertNull(match.getMatch());
    }
}

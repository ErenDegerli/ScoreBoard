package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;

import static com.sportradar.scoreboard.TestConstants.BRAZIL;
import static com.sportradar.scoreboard.TestConstants.SPAIN;
import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {

    @Test
    void startMatch() {
        Match match = new Match(SPAIN, BRAZIL);

        assertEquals(SPAIN, match.getHomeTeam());
        assertEquals(BRAZIL, match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void updateMatch() {
        Match match = new Match(SPAIN, BRAZIL);
        match.updateScore(1, 3);

        assertEquals(1, match.getHomeScore());
        assertEquals(3, match.getAwayScore());
    }

    @Test
    void calculateTotalScore() {
        Match match = new Match(SPAIN, BRAZIL);
        match.updateScore(1, 3);

        assertEquals(4, match.totalScore());
    }

    @Test
    void updateMatchHomeNegativeScore() {
        Match match = new Match(SPAIN, BRAZIL);
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1, 2));
    }

    @Test
    void updateMatchAwayNegativeScore() {
        Match match = new Match(SPAIN, BRAZIL);
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(1, -3));
    }

    @Test
    void homeTeamCannotBeNull() {
        assertThrows(NullPointerException.class, () -> new Match(null, SPAIN));
    }

    @Test
    void awayTeamCannotBeNull() {
        assertThrows(NullPointerException.class, () -> new Match(SPAIN, null));
    }
}

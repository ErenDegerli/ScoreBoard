package com.sportradar.scoreboard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

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

    private static List<Arguments> negativeScores() {
        return List.of(
                Arguments.of(-1, 0),
                Arguments.of(0, -1),
                Arguments.of(-10, -5)
        );
    }
    @ParameterizedTest
    @MethodSource("negativeScores")
    void updateMatchWithNegativeScores() {
        Match match = new Match(SPAIN, BRAZIL);
        assertThrows(IllegalArgumentException.class, () -> match.updateScore(-1, 2));
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

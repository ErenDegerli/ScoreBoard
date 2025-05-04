package com.sportradar.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard;
    private final String ARGENTINA = "Argentina";
    private final String AUSTRALIA = "Australia";
    private final String SPAIN = "Spain";
    private final String BRAZIL = "Brazil";

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
        scoreBoard.finishMatch(ARGENTINA, AUSTRALIA);

        assertTrue(scoreBoard.getSummary().isEmpty());
    }

    @Test
    void finishNonExistingMatch() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.finishMatch(ARGENTINA, AUSTRALIA));
    }

    @Test
    void updateScore() {
        scoreBoard.startMatch(ARGENTINA, AUSTRALIA);
        scoreBoard.updateScore(ARGENTINA, AUSTRALIA, 1, 0);

        Match match = scoreBoard.getSummary().get(0);

        assertAll(
                () -> assertEquals(ARGENTINA, match.getHomeTeam()),
                () -> assertEquals(AUSTRALIA, match.getAwayTeam()),
                () -> assertEquals(1, match.getHomeScore()),
                () -> assertEquals(0, match.getAwayScore())
        );
    }

    @Test
    void updateNonExistingMatch() {
        assertThrows(IllegalArgumentException.class, () -> scoreBoard.updateScore(ARGENTINA, AUSTRALIA, 1, 0));
    }

    @Test
    void orderMatchesByScore() {
        scoreBoard.startMatch(ARGENTINA, AUSTRALIA);
        scoreBoard.startMatch(SPAIN, BRAZIL);
        scoreBoard.updateScore(SPAIN, BRAZIL, 1, 1);

        assertSummaryOrder(
                formatMatchSummary(SPAIN, 1, 1, BRAZIL),
                formatMatchSummary(ARGENTINA, 0, 0, AUSTRALIA)
        );
    }

    private String formatMatchSummary(String homeTeam, int homeScore, int awayScore, String awayTeam) {
        return String.format("%s %d - %d %s", homeTeam, homeScore, awayScore, awayTeam);
    }

    private void assertSummaryOrder(String... expectedMatches) {
        List<String> actual = scoreBoard.getSummary().stream()
                .map(Match::toString)
                .toList();

        assertEquals(List.of(expectedMatches), actual);
    }
}

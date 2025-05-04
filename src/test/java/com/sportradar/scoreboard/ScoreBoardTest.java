package com.sportradar.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sportradar.scoreboard.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard;

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

    @Test
    void orderMatchesByScoreAndStartTime() {
        startAndScoreMatch(MEXICO, CANADA, 0, 5);
        startAndScoreMatch(SPAIN, BRAZIL, 10, 2);
        startAndScoreMatch(GERMANY, FRANCE, 2, 2);
        startAndScoreMatch(URUGUAY, ITALY, 6, 6);
        startAndScoreMatch(ARGENTINA, AUSTRALIA, 3, 1);

        assertSummaryOrder(
                formatMatchSummary(URUGUAY, 6, 6, ITALY),
                formatMatchSummary(SPAIN, 10, 2, BRAZIL),
                formatMatchSummary(MEXICO, 0, 5, CANADA),
                formatMatchSummary(ARGENTINA, 3, 1, AUSTRALIA),
                formatMatchSummary(GERMANY, 2, 2, FRANCE)
        );
    }

    @Test
    void finishOneMatchAndVerifySummaryOrder() {
        startAndScoreMatch(MEXICO, CANADA, 0, 5);
        startAndScoreMatch(SPAIN, BRAZIL, 10, 2);
        startAndScoreMatch(GERMANY, FRANCE, 2, 2);

        scoreBoard.finishMatch(SPAIN, BRAZIL);
        startAndScoreMatch(URUGUAY, ITALY, 6, 6);

        assertSummaryOrder(
                formatMatchSummary(URUGUAY, 6, 6, ITALY),
                formatMatchSummary(MEXICO, 0, 5, CANADA),
                formatMatchSummary(GERMANY, 2, 2, FRANCE)
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

    private void startAndScoreMatch(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        scoreBoard.startMatch(homeTeam, awayTeam);
        scoreBoard.updateScore(homeTeam, awayTeam, homeScore, awayScore);
    }
}

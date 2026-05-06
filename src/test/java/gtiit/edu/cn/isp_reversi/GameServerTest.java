package gtiit.edu.cn.isp_reversi;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class GameServerTest {

    @Test
    void test_newServerRankingIsEmpty() {
        GameServer s = new GameServer();
        ArrayList<String> r = s.ranking();

        assertEquals(0, r.size());
    }

    @Test
    void test_startNewGameAndGetGame() {
        GameServer s = new GameServer();

        int id1 = s.startNewGame("Alice", "Bob", 4);
        int id2 = s.startNewGame("Carol", "David", 6);

        assertEquals(0, id1);
        assertEquals(1, id2);

        Reversi g1 = s.getGame(id1);
        Reversi g2 = s.getGame(id2);

        assertEquals(4, g1.getSize());
        assertEquals(6, g2.getSize());

        assertNotSame(g1, g2);

        assertEquals(PlayerColor.Black, g1.currentTurn());
        assertEquals(PlayerColor.Black, g2.currentTurn());
    }

    @Test
    void test_rankingCountsOnlyEndedGames() {
        GameServer s = new GameServer();

        int id1 = s.startNewGame("Alice", "Bob", 4);
        int id2 = s.startNewGame("Carol", "Bob", 4);
        int id3 = s.startNewGame("Dave", "Eve", 4);

        Reversi g1 = s.getGame(id1);
        Reversi g2 = s.getGame(id2);
        Reversi g3 = s.getGame(id3);

        // Game 1 ends, Black wins
        g1.playAt(0, 2);
        g1.playAt(0, 1);
        g1.playAt(0, 0);
        g1.playAt(0, 3);
        g1.playAt(1, 3);
        g1.playAt(2, 3);
        g1.playAt(3, 0);
        g1.playAt(1, 0);
        g1.playAt(2, 0);
        g1.playAt(3, 2);
        g1.playAt(3, 1);
        g1.playAt(3, 3);

        assertTrue(g1.gameEnded());
        assertEquals(12, g1.numberOfPieces(PlayerColor.Black));
        assertEquals(4, g1.numberOfPieces(PlayerColor.White));

        // Game 2 ends, Black wins -> Carol wins
        g2.playAt(0, 2);
        g2.playAt(0, 1);
        g2.playAt(0, 0);
        g2.playAt(0, 3);
        g2.playAt(1, 3);
        g2.playAt(2, 3);
        g2.playAt(3, 0);
        g2.playAt(1, 0);
        g2.playAt(2, 0);
        g2.playAt(3, 2);
        g2.playAt(3, 1);
        g2.playAt(3, 3);

        assertTrue(g2.gameEnded());
        assertEquals(12, g2.numberOfPieces(PlayerColor.Black));
        assertEquals(4, g2.numberOfPieces(PlayerColor.White));

        // Game 3 has not ended, so it should not count as a win
        g3.playAt(0, 2);
        assertFalse(g3.gameEnded());

        ArrayList<String> ranking = s.ranking();

        assertEquals(5, ranking.size());

        // Alice and Carol each have 1 win, order between them is arbitrary
        assertTrue(ranking.get(0).equals("Alice") || ranking.get(0).equals("Carol"));
        assertTrue(ranking.get(1).equals("Alice") || ranking.get(1).equals("Carol"));
        assertNotEquals(ranking.get(0), ranking.get(1));

        // Bob has 0 wins and lost twice, so he cannot be above both winners
        assertFalse(ranking.get(0).equals("Bob"));
        assertFalse(ranking.get(1).equals("Bob"));

        assertTrue(ranking.contains("Bob"));
        assertTrue(ranking.contains("Dave"));
        assertTrue(ranking.contains("Eve"));
    }

    @Test
    void test_rankingSingleFinishedGame() {
        GameServer s = new GameServer();

        int id = s.startNewGame("Alice", "Bob", 4);
        Reversi g = s.getGame(id);

        g.playAt(0, 2);
        g.playAt(0, 1);
        g.playAt(0, 0);
        g.playAt(0, 3);
        g.playAt(1, 3);
        g.playAt(2, 3);
        g.playAt(3, 0);
        g.playAt(1, 0);
        g.playAt(2, 0);
        g.playAt(3, 2);
        g.playAt(3, 1);
        g.playAt(3, 3);

        assertTrue(g.gameEnded());
        assertEquals(12, g.numberOfPieces(PlayerColor.Black));
        assertEquals(4, g.numberOfPieces(PlayerColor.White));

        ArrayList<String> ranking = s.ranking();

        assertEquals(2, ranking.size());
        assertEquals("Alice", ranking.get(0));
        assertEquals("Bob", ranking.get(1));
    }
}
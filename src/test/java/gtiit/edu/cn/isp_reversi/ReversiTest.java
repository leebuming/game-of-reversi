package gtiit.edu.cn.isp_reversi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ReversiTest {

    @Test
    void test_newReversi() {
        Reversi r = new Reversi(4);
        assertEquals(4, r.getSize());
    }

    @Test
    void test_initialPattern() {
        Reversi r = new Reversi(4);
        assertFalse(r.hasPieceAt(0, 0));
        assertFalse(r.hasPieceAt(0, 1));
        assertFalse(r.hasPieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));
        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertEquals(PlayerColor.White, r.pieceAt(1, 2));
        assertTrue(r.hasPieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));
        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));
        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }
    
    //a
    @Test
    void test_playOutsideBoardDoesNothing() {
        Reversi r = new Reversi(4);
        r.playAt(-1, 0);
        assertFalse(r.hasPieceAt(-1, 0));
        assertFalse(r.hasPieceAt(0, 0));
        assertFalse(r.hasPieceAt(0, 1));
        assertFalse(r.hasPieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));
        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertEquals(PlayerColor.White, r.pieceAt(1, 2));
        assertTrue(r.hasPieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));
        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));
        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }
    
    //b
    @Test
    void test_playOnOccupiedCellDoesNothing() {
        Reversi r = new Reversi(4);
        r.playAt(1 , 2);
        assertFalse(r.hasPieceAt(0, 0));
        assertFalse(r.hasPieceAt(0, 1));
        assertFalse(r.hasPieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));
        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertEquals(PlayerColor.White, r.pieceAt(1, 2));
        assertTrue(r.hasPieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));
        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));
        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }
    
    //c
    @Test
    void test_invalidMoveDoesNothing() {
        Reversi r = new Reversi(4);
        r.playAt(0, 0);
        assertFalse(r.hasPieceAt(2, 0));
        assertFalse(r.hasPieceAt(0, 0));
        assertFalse(r.hasPieceAt(0, 1));
        assertFalse(r.hasPieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));
        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertEquals(PlayerColor.White, r.pieceAt(1, 2));
        assertTrue(r.hasPieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));
        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));
        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }
    
    //d
    @Test
    void test_validMove_horizontal_onePiece_oneLine() {
        Reversi r = new Reversi(4);
        r.playAt(0, 2);   // Black, horizontal flip of one piece

        assertEquals(PlayerColor.White, r.currentTurn());
        assertFalse(r.gameEnded());
        assertEquals(4, r.numberOfPieces(PlayerColor.Black));
        assertEquals(1, r.numberOfPieces(PlayerColor.White));

        assertFalse(r.hasPieceAt(0, 0));
        assertFalse(r.hasPieceAt(0, 1));
        assertTrue(r.hasPieceAt(0, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));

        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));

        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));

        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }

    @Test
    void test_validMove_vertical_onePiece_oneLine() {
        Reversi r = new Reversi(4);
        r.playAt(2, 0);   // Black, vertical flip of one piece

        assertEquals(PlayerColor.White, r.currentTurn());
        assertFalse(r.gameEnded());
        assertEquals(4, r.numberOfPieces(PlayerColor.Black));
        assertEquals(1, r.numberOfPieces(PlayerColor.White));

        assertFalse(r.hasPieceAt(0, 0));
        assertFalse(r.hasPieceAt(0, 1));
        assertFalse(r.hasPieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));

        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.White, r.pieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));

        assertTrue(r.hasPieceAt(2, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));

        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }

    @Test
    void test_validMove_diagonal_onePiece_oneLine() {
        Reversi r = new Reversi(4);
        r.playAt(0, 2);   // Black
        r.playAt(0, 3);   // White, diagonal flip of one piece

        assertEquals(PlayerColor.Black, r.currentTurn());
        assertFalse(r.gameEnded());
        assertEquals(3, r.numberOfPieces(PlayerColor.Black));
        assertEquals(3, r.numberOfPieces(PlayerColor.White));

        assertFalse(r.hasPieceAt(0, 0));
        assertFalse(r.hasPieceAt(0, 1));
        assertTrue(r.hasPieceAt(0, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 2));
        assertTrue(r.hasPieceAt(0, 3));
        assertEquals(PlayerColor.White, r.pieceAt(0, 3));

        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.White, r.pieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));

        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));

        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }

    @Test
    void test_validMove_horizontal_manyPieces_oneLine() {
        Reversi r = new Reversi(4);
        r.playAt(0, 2);   // Black
        r.playAt(0, 1);   // White
        r.playAt(2, 0);   // Black
        r.playAt(3, 1);   // White, flips two black pieces in one horizontal line

        assertEquals(PlayerColor.Black, r.currentTurn());
        assertFalse(r.gameEnded());
        assertEquals(4, r.numberOfPieces(PlayerColor.Black));
        assertEquals(4, r.numberOfPieces(PlayerColor.White));

        assertFalse(r.hasPieceAt(0, 0));
        assertTrue(r.hasPieceAt(0, 1));
        assertEquals(PlayerColor.White, r.pieceAt(0, 1));
        assertTrue(r.hasPieceAt(0, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));

        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.White, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));

        assertTrue(r.hasPieceAt(2, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));

        assertFalse(r.hasPieceAt(3, 0));
        assertTrue(r.hasPieceAt(3, 1));
        assertEquals(PlayerColor.White, r.pieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }

    @Test
    void test_validMove_manyLinesAtOnce() {
        Reversi r = new Reversi(4);
        assertEquals(PlayerColor.Black, r.currentTurn());
        r.playAt(0, 2);   // Black
        assertEquals(PlayerColor.White, r.currentTurn());
        r.playAt(0, 1);   // White
        assertEquals(PlayerColor.Black, r.currentTurn());
        r.playAt(0, 0);   // Black, flips in two directions at once

        assertEquals(PlayerColor.White, r.currentTurn());
        assertEquals(PlayerColor.White, r.currentTurn());
        assertFalse(r.gameEnded());
        assertEquals(6, r.numberOfPieces(PlayerColor.Black));
        assertEquals(1, r.numberOfPieces(PlayerColor.White));

        assertTrue(r.hasPieceAt(0, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 0));
        assertTrue(r.hasPieceAt(0, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 1));
        assertTrue(r.hasPieceAt(0, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));

        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));

        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));

        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }
    
    //e
    @Test
    void test_currentTurnAfterValidMove() {
        Reversi r = new Reversi(4);

        assertEquals(PlayerColor.Black, r.currentTurn());

        r.playAt(0, 2);   // Black valid move

        assertEquals(PlayerColor.White, r.currentTurn());
        assertFalse(r.gameEnded());
        assertEquals(4, r.numberOfPieces(PlayerColor.Black));
        assertEquals(1, r.numberOfPieces(PlayerColor.White));

        assertFalse(r.hasPieceAt(0, 0));
        assertFalse(r.hasPieceAt(0, 1));
        assertTrue(r.hasPieceAt(0, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));

        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));

        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));

        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }

    @Test
    void test_currentTurnPassesAutomatically() {
        Reversi r = new Reversi(4);

        r.playAt(0, 2);   // Black
        r.playAt(0, 1);   // White
        r.playAt(0, 0);   // Black
        r.playAt(0, 3);   // White
        r.playAt(1, 3);   // Black
        r.playAt(2, 3);   // White
        r.playAt(3, 0);   // Black
        r.playAt(1, 0);   // White

        assertEquals(PlayerColor.Black, r.currentTurn());

        r.playAt(2, 0);   // Black valid move, White has no valid move afterwards

        assertEquals(PlayerColor.Black, r.currentTurn());
        assertFalse(r.gameEnded());
        assertEquals(8, r.numberOfPieces(PlayerColor.Black));
        assertEquals(5, r.numberOfPieces(PlayerColor.White));

        assertTrue(r.hasPieceAt(0, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 0));
        assertTrue(r.hasPieceAt(0, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 1));
        assertTrue(r.hasPieceAt(0, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 2));
        assertTrue(r.hasPieceAt(0, 3));
        assertEquals(PlayerColor.White, r.pieceAt(0, 3));

        assertTrue(r.hasPieceAt(1, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.White, r.pieceAt(1, 2));
        assertTrue(r.hasPieceAt(1, 3));
        assertEquals(PlayerColor.White, r.pieceAt(1, 3));

        assertTrue(r.hasPieceAt(2, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.White, r.pieceAt(2, 2));
        assertTrue(r.hasPieceAt(2, 3));
        assertEquals(PlayerColor.White, r.pieceAt(2, 3));

        assertTrue(r.hasPieceAt(3, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }

    //f
    @Test
    void test_numberOfPiecesAfterValidMove() {
        Reversi r = new Reversi(4);

        assertEquals(2, r.numberOfPieces(PlayerColor.Black));
        assertEquals(2, r.numberOfPieces(PlayerColor.White));

        r.playAt(0, 2);   // Black flips one white piece

        assertEquals(4, r.numberOfPieces(PlayerColor.Black));
        assertEquals(1, r.numberOfPieces(PlayerColor.White));
        assertEquals(PlayerColor.White, r.currentTurn());
        assertFalse(r.gameEnded());

        assertFalse(r.hasPieceAt(0, 0));
        assertFalse(r.hasPieceAt(0, 1));
        assertTrue(r.hasPieceAt(0, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 2));
        assertFalse(r.hasPieceAt(0, 3));

        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 2));
        assertFalse(r.hasPieceAt(1, 3));

        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertFalse(r.hasPieceAt(2, 3));

        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }

    @Test
    void test_numberOfPiecesAfterSeveralMoves() {
        Reversi r = new Reversi(4);

        r.playAt(0, 2);   // Black
        r.playAt(0, 1);   // White
        r.playAt(0, 0);   // Black
        r.playAt(0, 3);   // White
        r.playAt(1, 3);   // Black
        r.playAt(2, 3);   // White

        assertEquals(5, r.numberOfPieces(PlayerColor.Black));
        assertEquals(5, r.numberOfPieces(PlayerColor.White));
        assertEquals(PlayerColor.Black, r.currentTurn());
        assertFalse(r.gameEnded());

        assertTrue(r.hasPieceAt(0, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 0));
        assertTrue(r.hasPieceAt(0, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 1));
        assertTrue(r.hasPieceAt(0, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 2));
        assertTrue(r.hasPieceAt(0, 3));
        assertEquals(PlayerColor.White, r.pieceAt(0, 3));

        assertFalse(r.hasPieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 2));
        assertTrue(r.hasPieceAt(1, 3));
        assertEquals(PlayerColor.White, r.pieceAt(1, 3));

        assertFalse(r.hasPieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.White, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.White, r.pieceAt(2, 2));
        assertTrue(r.hasPieceAt(2, 3));
        assertEquals(PlayerColor.White, r.pieceAt(2, 3));

        assertFalse(r.hasPieceAt(3, 0));
        assertFalse(r.hasPieceAt(3, 1));
        assertFalse(r.hasPieceAt(3, 2));
        assertFalse(r.hasPieceAt(3, 3));
    }

    //g
    @Test
    void test_gameEndedAfterManyValidMoves() {
        Reversi r = new Reversi(4);

        assertFalse(r.gameEnded());

        r.playAt(0, 2);   // Black
        assertFalse(r.gameEnded());

        r.playAt(0, 1);   // White
        assertFalse(r.gameEnded());

        r.playAt(0, 0);   // Black
        assertFalse(r.gameEnded());

        r.playAt(0, 3);   // White
        assertFalse(r.gameEnded());

        r.playAt(1, 3);   // Black
        assertFalse(r.gameEnded());

        r.playAt(2, 3);   // White
        assertFalse(r.gameEnded());

        r.playAt(3, 0);   // Black
        assertFalse(r.gameEnded());

        r.playAt(1, 0);   // White
        assertFalse(r.gameEnded());

        r.playAt(2, 0);   // Black, White has no move so turn stays Black
        assertFalse(r.gameEnded());
        assertEquals(PlayerColor.Black, r.currentTurn());

        r.playAt(3, 2);   // Black
        assertFalse(r.gameEnded());

        r.playAt(3, 1);   // White
        assertFalse(r.gameEnded());

        r.playAt(3, 3);   // Black, game ends
        assertTrue(r.gameEnded());

        assertEquals(12, r.numberOfPieces(PlayerColor.Black));
        assertEquals(4, r.numberOfPieces(PlayerColor.White));

        assertTrue(r.hasPieceAt(0, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 0));
        assertTrue(r.hasPieceAt(0, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 1));
        assertTrue(r.hasPieceAt(0, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(0, 2));
        assertTrue(r.hasPieceAt(0, 3));
        assertEquals(PlayerColor.White, r.pieceAt(0, 3));

        assertTrue(r.hasPieceAt(1, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 0));
        assertTrue(r.hasPieceAt(1, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 1));
        assertTrue(r.hasPieceAt(1, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(1, 2));
        assertTrue(r.hasPieceAt(1, 3));
        assertEquals(PlayerColor.White, r.pieceAt(1, 3));

        assertTrue(r.hasPieceAt(2, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 0));
        assertTrue(r.hasPieceAt(2, 1));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 1));
        assertTrue(r.hasPieceAt(2, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(2, 2));
        assertTrue(r.hasPieceAt(2, 3));
        assertEquals(PlayerColor.White, r.pieceAt(2, 3));

        assertTrue(r.hasPieceAt(3, 0));
        assertEquals(PlayerColor.Black, r.pieceAt(3, 0));
        assertTrue(r.hasPieceAt(3, 1));
        assertEquals(PlayerColor.White, r.pieceAt(3, 1));
        assertTrue(r.hasPieceAt(3, 2));
        assertEquals(PlayerColor.Black, r.pieceAt(3, 2));
        assertTrue(r.hasPieceAt(3, 3));
        assertEquals(PlayerColor.Black, r.pieceAt(3, 3));
    }
}
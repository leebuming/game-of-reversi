package gtiit.edu.cn.isp_reversi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void test_newBoard() {
        Board b = new Board(4);
        assertEquals(4, b.getSize());
    }

    @Test
    void test_inRange() {
        Board b = new Board(3);
        assertTrue(b.inRange(0, 0));
        assertTrue(b.inRange(0, 1));
        assertTrue(b.inRange(0, 2));
        assertTrue(b.inRange(1, 0));
        assertTrue(b.inRange(1, 1));
        assertTrue(b.inRange(1, 2));
        assertTrue(b.inRange(2, 0));
        assertTrue(b.inRange(2, 1));
        assertTrue(b.inRange(2, 2));
        assertFalse(b.inRange(-1, 1));
        assertFalse(b.inRange(3, 1));
        assertFalse(b.inRange(1, -1));
        assertFalse(b.inRange(1, 3));
        //(b)hasPiece
        assertFalse(b.hasPieceAt(0, 0));
        assertFalse(b.hasPieceAt(0, 1));
        assertFalse(b.hasPieceAt(0, 2));
        assertFalse(b.hasPieceAt(1, 0));
        assertFalse(b.hasPieceAt(1, 1));
        assertFalse(b.hasPieceAt(1, 2));
        assertFalse(b.hasPieceAt(2, 0));
        assertFalse(b.hasPieceAt(2, 1));
        assertFalse(b.hasPieceAt(2, 2));
        //(c)(d)addAPicec
        b.putPieceAt(1, 1, PlayerColor.Black);
        assertTrue(b.hasPieceAt(1, 1));
        assertEquals(b.pieceAt(1, 1), PlayerColor.Black);
        //(d)varirousColor
        b.putPieceAt(1, 2, PlayerColor.White);
        b.putPieceAt(1, 0, PlayerColor.White);
        b.putPieceAt(0, 1, PlayerColor.Black);
        b.putPieceAt(2, 1, PlayerColor.Black);
        assertEquals(b.pieceAt(1, 2), PlayerColor.White);
        assertEquals(b.pieceAt(1, 0), PlayerColor.White);
        assertEquals(b.pieceAt(0, 1), PlayerColor.Black);
        assertEquals(b.pieceAt(2, 1), PlayerColor.Black);
        //(e)removeColor
        b.removePieceFrom(1, 1);
        assertFalse(b.hasPieceAt(1, 1));
        //(f)removeEmpty
        b.removePieceFrom(0, 0);
        assertFalse(b.hasPieceAt(0, 0));
        //(g)rewriteColorSame
        b.putPieceAt(1, 2, PlayerColor.White);
        assertEquals(b.pieceAt(1, 2), PlayerColor.White);
        //(h)rewriteColorDifferent
        b.putPieceAt(1, 0, PlayerColor.Black);
        assertEquals(b.pieceAt(1, 0), PlayerColor.Black);
    }
}
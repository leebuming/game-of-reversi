package gtiit.edu.cn.isp_reversi;

class Board {

    private int size;
    private PlayerColor[][] pieces;

    Board(int n) {
        this.size = n;
        this.pieces = new PlayerColor[n][n];
    }

    int getSize() {
        return this.size;
    }

    boolean inRange(int i, int j) {
        return 0 <= i && i < this.size && 0 <= j && j < this.size;
    }

    boolean hasPieceAt(int i, int j) {
        return this.inRange(i, j) && this.pieces[i][j] != null;
    }

    PlayerColor pieceAt(int i, int j) {
        if (!inRange(i, j)){
            return null;
        }
        return this.pieces[i][j];
    }

    void removePieceFrom(int i, int j) {
        if (this.inRange(i, j)) {
            this.pieces[i][j] = null;
        }
    }

    void putPieceAt(int i, int j, PlayerColor color) {
        if (this.inRange(i, j)) {
            this.pieces[i][j] = color;
        }
    }
}
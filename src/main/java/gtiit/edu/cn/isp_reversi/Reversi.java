package gtiit.edu.cn.isp_reversi;

class Reversi {

    Reversi(int n) {
        // n is an even number such that n > 2
        this.size = n;
        this.board = new Board(n);
        this.turn = PlayerColor.Black;

        int a = n / 2 - 1;
        int b = n / 2;

        board.putPieceAt(a, a, PlayerColor.Black);
        board.putPieceAt(b, b, PlayerColor.Black);
        board.putPieceAt(a, b, PlayerColor.White);
        board.putPieceAt(b, a, PlayerColor.White);
    }

    int getSize() {
        return this.size;
    }

    boolean hasPieceAt(int i, int j) {
        return board.hasPieceAt(i, j);
    }

    PlayerColor pieceAt(int i, int j) {
        return board.pieceAt(i, j);
    }

    PlayerColor currentTurn() {
        return this.turn;
    }

    PlayerColor changeColor(PlayerColor color) {
        if (color == PlayerColor.Black) {
            return PlayerColor.White;
        }
        return PlayerColor.Black;
    }

    boolean islegal(int i, int j) {
        return islegalForColor(i, j, currentTurn());
    }

    boolean islegalForColor(int i, int j, PlayerColor color) {
        if (!board.inRange(i, j)) {
            return false;
        }
        if (hasPieceAt(i, j)) {
            return false;
        }

        int dx = -1;
        while (dx <= 1) {
            int dy = -1;
            while (dy <= 1) {
                if (!(dx == 0 && dy == 0)) {
                    if (countChange(i, j, dx, dy, color) > 0) {
                        return true;
                    }
                }
                dy++;
            }
            dx++;
        }
        return false;
    }

    int countChange(int i, int j, int dx, int dy, PlayerColor color) {
        int x = i + dx;
        int y = j + dy;
        int count = 0;
        PlayerColor other = changeColor(color);

        while (board.inRange(x, y) && hasPieceAt(x, y) && pieceAt(x, y) == other) {
            count++;
            x += dx;
            y += dy;
        }

        if (count >= 1 && board.inRange(x, y) && hasPieceAt(x, y) && pieceAt(x, y) == color) {
            return count;
        }

        return 0;
    }

    void continueChange(int i, int j, int dx, int dy, PlayerColor color) {
        int count = countChange(i, j, dx, dy, color);
        int k = 1;

        while (k <= count) {
            board.putPieceAt(i + k * dx, j + k * dy, color);
            k++;
        }
    }

    void playRule(int i, int j) {
        int dx = -1;
        while (dx <= 1) {
            int dy = -1;
            while (dy <= 1) {
                if (!(dx == 0 && dy == 0)) {
                    continueChange(i, j, dx, dy, currentTurn());
                }
                dy++;
            }
            dx++;
        }
    }

    boolean hasLegalMove(PlayerColor color) {
        int i = 0;
        while (i < this.size) {
            int j = 0;
            while (j < this.size) {
                if (islegalForColor(i, j, color)) {
                    return true;
                }
                j++;
            }
            i++;
        }
        return false;
    }

    void playAt(int i, int j) {
        if (!islegal(i, j)) {
            return;
        }

        board.putPieceAt(i, j, currentTurn());
        playRule(i, j);

        PlayerColor other = changeColor(currentTurn());
        if (hasLegalMove(other)) {
            this.turn = other;
        }
        else if (hasLegalMove(currentTurn())) {
            this.turn = currentTurn();
        }
    }

    boolean gameEnded() {
        return !hasLegalMove(PlayerColor.Black) && !hasLegalMove(PlayerColor.White);
    }

    int numberOfPieces(PlayerColor color) {
        int piece = 0;
        int i = 0;
        while (i < this.size) {
            int j = 0;
            while (j < this.size) {
                if (hasPieceAt(i, j) && pieceAt(i, j) == color) {
                    piece++;
                }
                j++;
            }
            i++;
        }
        return piece;
    }

    private int size;
    private Board board;
    private PlayerColor turn;
}
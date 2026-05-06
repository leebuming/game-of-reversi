package gtiit.edu.cn.isp_reversi;

import java.util.ArrayList;

class GameServer {

    GameServer() {
        this.games = new ArrayList<Reversi>();
        this.blackPlayers = new ArrayList<String>();
        this.whitePlayers = new ArrayList<String>();
    }

    int startNewGame(String player1, String player2, int n) {
        Reversi r = new Reversi(n);
        this.games.add(r);
        this.blackPlayers.add(player1);
        this.whitePlayers.add(player2);
        return this.games.size() - 1;
    }

    Reversi getGame(int gameId) {
        return this.games.get(gameId);
    }

    ArrayList<String> ranking() {
        ArrayList<String> players = new ArrayList<String>();
        ArrayList<Integer> wins = new ArrayList<Integer>();

        int i = 0;
        while (i < this.games.size()) {
            String black = this.blackPlayers.get(i);
            String white = this.whitePlayers.get(i);

            if (!containsPlayer(players, black)) {
                players.add(black);
                wins.add(0);
            }
            if (!containsPlayer(players, white)) {
                players.add(white);
                wins.add(0);
            }

            i++;
        }

        i = 0;
        while (i < this.games.size()) {
            Reversi game = this.games.get(i);

            if (game.gameEnded()) {
                int blackPieces = game.numberOfPieces(PlayerColor.Black);
                int whitePieces = game.numberOfPieces(PlayerColor.White);

                if (blackPieces > whitePieces) {
                    String winner = this.blackPlayers.get(i);
                    int k = indexOfPlayer(players, winner);
                    wins.set(k, wins.get(k) + 1);
                }
                else if (whitePieces > blackPieces) {
                    String winner = this.whitePlayers.get(i);
                    int k = indexOfPlayer(players, winner);
                    wins.set(k, wins.get(k) + 1);
                }
            }

            i++;
        }

        int a = 0;
        while (a < players.size()) {
            int best = a;
            int b = a + 1;
            while (b < players.size()) {
                if (wins.get(b) > wins.get(best)) {
                    best = b;
                }
                b++;
            }

            if (best != a) {
                String tempPlayer = players.get(a);
                players.set(a, players.get(best));
                players.set(best, tempPlayer);

                int tempWins = wins.get(a);
                wins.set(a, wins.get(best));
                wins.set(best, tempWins);
            }

            a++;
        }

        return players;
    }

    boolean containsPlayer(ArrayList<String> players, String name) {
        int i = 0;
        while (i < players.size()) {
            if (players.get(i).equals(name)) {
                return true;
            }
            i++;
        }
        return false;
    }

    int indexOfPlayer(ArrayList<String> players, String name) {
        int i = 0;
        while (i < players.size()) {
            if (players.get(i).equals(name)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private ArrayList<Reversi> games;
    private ArrayList<String> blackPlayers;
    private ArrayList<String> whitePlayers;
}
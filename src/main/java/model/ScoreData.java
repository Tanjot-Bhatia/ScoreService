package model;

public class ScoreData implements Comparable<ScoreData> {
    private final int score;
    private final Player player;
    private final String gameName;
    private final int gameId;

    public ScoreData(int score, Player player, String gameName, int gameId) {
        this.score = score;
        this.player = player;
        this.gameName = gameName;
        this.gameId = gameId;
    }

    public int getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }

    public String getGameName() {
        return gameName;
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public int compareTo(ScoreData o) {
        return o.score - this.score;
    }

    @Override
    public String toString() {
        return "ScoreCard{" +
                "score=" + score +
                ", playerId=" + player.getPlayerId() +
                ", gameName='" + gameName + '\'' +
                ", gameId=" + gameId +
                "}\n";
    }
}

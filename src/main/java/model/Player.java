package model;

public class Player {

    int playerId;
    String name;

    public Player(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

}

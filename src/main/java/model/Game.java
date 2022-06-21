package model;

import java.util.HashMap;
import java.util.Map;

public class Game {
    int id;
    String name;

    Map<Integer, Player> playerMap; // playerId -> Player
    Map<Integer, Integer> scoreMap; // playerId -> score

    public Game(int id, String name) {
        this.id = id;
        this.name = name;
        this.playerMap = new HashMap<>();
        this.scoreMap = new HashMap<>();
    }

    public void registerNewPlayer(Player player) {
        playerMap.put(player.getPlayerId(), player);
        scoreMap.put(player.getPlayerId(), 0);
    }

    public boolean setScore(int playerId, int score) {
        if(scoreMap.containsKey(playerId)) {
            scoreMap.put(playerId, score);
            return true;
        }
        return false;
    }

    public Player getPlayerById(Integer playerId){
        return playerMap.getOrDefault(playerId, null);
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

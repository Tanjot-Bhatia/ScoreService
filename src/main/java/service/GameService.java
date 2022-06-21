package service;

import interfaces.Topic;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameService {
    private final List<Topic<ScoreData>> topics;
    private final Map<Integer, Game> gameMap;

    public GameService() {
        gameMap = new HashMap<>();
        topics = new ArrayList<>();
    }

    public void registerScoreTopic(Topic<ScoreData> topic) {
        this.topics.add(topic);
    }

    public void publishScore(int gameId, int playerId, int score) {
        if(gameMap.containsKey(gameId)) {
            Game game = gameMap.get(gameId);
            if( game.setScore(playerId,score)){
                this.publishToTopics(new ScoreData(score, game.getPlayerById(playerId), game.getName(), game.getId()));
            } else{
                System.out.println("Player not found with id: "+playerId);
            }
        } else {
            System.out.println("Game not found with id: " + gameId);
        }
    }

    public void registerGame(Game game) {
        gameMap.put(game.getId(), game);
    }

    public void addPlayer(int gameId, Player player) {
        if(gameMap.containsKey(gameId)) {
            gameMap.get(gameId).registerNewPlayer(player);
        } else {
            System.out.println("Game not found with id: " + gameId);
        }
    }

    private void publishToTopics(ScoreData scoreData) {
        if(!topics.isEmpty()) {
            topics.forEach((topic)->topic.publish(scoreData));
        } else {
            System.out.println("Not registered to any topic yet");
        }
    }
}

package service;

import interfaces.Consumer;
import model.Player;
import model.ScoreData;

import java.util.*;

public class ScoreService implements Consumer<ScoreData> {

    private final Map<Integer, Set<ScoreData>> gameScoreCards;
    private final Map<Integer, Map<Integer, ScoreData>> gamePlayerScoreCardMap;

    public ScoreService() {
        gameScoreCards = new HashMap<>();
        gamePlayerScoreCardMap = new HashMap<>();
    }

    public List<ScoreData> topNScorers(int gameId, int n){
        List<ScoreData> scoreDataList = new ArrayList<>();
        for (ScoreData scoreData : gameScoreCards.get(gameId)) {
            scoreDataList.add(scoreData);
            if (scoreDataList.size() == n) {
                break;
            }
        }
        return scoreDataList;
    }

    private void consume(int gameId, Player player, int score, String gameName) {
        if(!gameScoreCards.containsKey(gameId)) {
           gameScoreCards.put(gameId, new TreeSet<>());
           gamePlayerScoreCardMap.put(gameId, new HashMap<>());
        }

        if(gamePlayerScoreCardMap.get(gameId).containsKey(player.getPlayerId())) {
            ScoreData scoreData = gamePlayerScoreCardMap.get(gameId).get(player.getPlayerId());
            gameScoreCards.get(gameId).remove(scoreData);
            gamePlayerScoreCardMap.get(gameId).remove(player.getPlayerId());
        }

        ScoreData scoreData = new ScoreData(score, player, gameName, gameId);
        gameScoreCards.get(gameId).add(scoreData);
        gamePlayerScoreCardMap.get(gameId).put(player.getPlayerId(), scoreData);
    }

    public void consume(ScoreData scoreData) {
        consume(
                scoreData.getGameId(),
                scoreData.getPlayer(),
                scoreData.getScore(),
                scoreData.getGameName()
        );
    }
}

package service;

import model.Game;
import model.Player;
import model.ScoreData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class GameServiceTest {

    GameService gameService;

    TopicService<ScoreData> topic;

    @Before
    public void load() {
        gameService = new GameService();
        topic = mock(TopicService.class);
        gameService.registerScoreTopic(topic);
    }

    @Test
    public void testPublishScore() {
        gameService.registerGame(new Game(1, "Chess"));
        gameService.addPlayer(1, new Player(11, "11"));
        gameService.publishScore(1, 11, 20);

        ArgumentCaptor<ScoreData> argumentCaptor = ArgumentCaptor.forClass(ScoreData.class);
        Mockito.verify(topic).publish(argumentCaptor.capture());

        Mockito.verify(topic, times(1)).publish(any());

        Assert.assertEquals(1, argumentCaptor.getValue().getGameId());
        Assert.assertEquals(20, argumentCaptor.getValue().getScore());
        Assert.assertEquals(11, argumentCaptor.getValue().getPlayer().getPlayerId());
    }
}

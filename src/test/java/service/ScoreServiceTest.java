package service;

import model.Player;
import model.ScoreData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScoreServiceTest {

    ScoreService scoreService;

    @Before
    public void prepareForTest() {
        scoreService = new ScoreService();
    }

    @Test
    public void testConsume() {
        scoreService.consume(new ScoreData(30, new Player(11, "11"), "dummyGame", 1));
        Assert.assertEquals(1, scoreService.topNScorers(1, 1).size());
        Assert.assertEquals(1, scoreService.topNScorers(1, 1).get(0).getGameId());
        Assert.assertEquals(30, scoreService.topNScorers(1, 1).get(0).getScore());
        Assert.assertEquals(11, scoreService.topNScorers(1, 1).get(0).getPlayer().getPlayerId());
        Assert.assertEquals("dummyGame", scoreService.topNScorers(1, 1).get(0).getGameName());
    }

    @Test
    public void testTopNScores() {
        scoreService.consume(new ScoreData(30, new Player(11, "11"), "dummyGame", 1));
        scoreService.consume(new ScoreData(20, new Player(12, "12"), "dummyGame", 1));
        scoreService.consume(new ScoreData(40, new Player(13, "13"), "dummyGame", 1));
        Assert.assertEquals(2, scoreService.topNScorers(1, 2).size());
        Assert.assertEquals(3, scoreService.topNScorers(1, 4).size());

        Assert.assertEquals(40, scoreService.topNScorers(1, 2).get(0).getScore());
        Assert.assertEquals(13, scoreService.topNScorers(1, 2).get(0).getPlayer().getPlayerId());

        Assert.assertEquals(30, scoreService.topNScorers(1, 2).get(1).getScore());
        Assert.assertEquals(11, scoreService.topNScorers(1, 2).get(1).getPlayer().getPlayerId());
    }
}

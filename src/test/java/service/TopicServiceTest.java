package service;

import model.Player;
import model.ScoreData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class TopicServiceTest {

    TopicService<ScoreData> topicService;
    ScoreService scoreService;

    @Before
    public void before(){
        topicService = new TopicService<>();
        scoreService = mock(ScoreService.class);
        topicService.addConsumer(scoreService);
    }

    @Test
    public void testPublish(){
        ScoreData scoreTopicData = new ScoreData(19, new Player(11,"11"),"chess", 1);
        topicService.publish(scoreTopicData);
        Mockito.verify(scoreService, Mockito.times(1)).consume(scoreTopicData);
    }
}

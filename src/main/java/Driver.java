import model.Game;
import model.Player;
import model.ScoreData;
import service.GameService;
import service.ScoreService;
import service.TopicService;

public class Driver {
    public static void main (String [] args) {
        TopicService<ScoreData> topicService = new TopicService<>();

        GameService gameService = new GameService();
        gameService.registerScoreTopic(topicService);

        ScoreService scoreService = new ScoreService();
        topicService.addConsumer(scoreService);

        gameService.registerGame(new Game(1, "Snake and ladder"));
        gameService.registerGame(new Game(2, "Ludo"));

        Player c1 = new Player(31, "31");
        gameService.addPlayer(1, new Player(11, "11"));
        gameService.addPlayer(1, new Player(12, "12"));
        gameService.addPlayer(1, new Player(13, "13"));
        gameService.addPlayer(1, new Player(14, "14"));
        gameService.addPlayer(1, new Player(15, "15"));
        gameService.addPlayer(1, new Player(16, "16"));
        gameService.addPlayer(1, c1);

        gameService.addPlayer(2, new Player(21, "21"));
        gameService.addPlayer(2, new Player(22, "22"));
        gameService.addPlayer(2, new Player(23, "23"));
        gameService.addPlayer(2, new Player(24, "24"));
        gameService.addPlayer(2, new Player(25, "25"));
        gameService.addPlayer(2, new Player(26, "26"));
        gameService.addPlayer(2, c1);

        gameService.publishScore(1, 11, 50);
        gameService.publishScore(1, 12, 20);
        gameService.publishScore(1, 13, 10);
        gameService.publishScore(1, 14, 60);
        gameService.publishScore(1, 15, 40);
        gameService.publishScore(1, 16, 90);
        gameService.publishScore(1, 31, 80);

        gameService.publishScore(2, 21, 50);
        gameService.publishScore(2, 22, 120);
        gameService.publishScore(2, 23, 140);
        gameService.publishScore(2, 24, 60);
        gameService.publishScore(2, 25, 160);
        gameService.publishScore(2, 26, 90);
        gameService.publishScore(2, 31, 110);

        System.out.println(scoreService.topNScorers(1,4));
        System.out.println();
        System.out.println(scoreService.topNScorers(2,4));

        gameService.publishScore(1, 16, 0);
        System.out.println();
        System.out.println(scoreService.topNScorers(1,7));

    }
}

package service;

import interfaces.Consumer;
import interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicService<Data> implements Topic<Data> {
    private final List<Consumer<Data>> consumers;

    public TopicService() {
        this.consumers = new ArrayList<>();
    }

    public void addConsumer(Consumer<Data> consumer) {
        this.consumers.add(consumer);
    }

    public void publish(Data data) {
        this.broadcast(data);
    }

    private void broadcast(Data data) {
        consumers.forEach((consumer)-> consumer.consume(data));
    }
}

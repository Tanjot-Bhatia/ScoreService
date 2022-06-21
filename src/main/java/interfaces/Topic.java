package interfaces;

public interface Topic<Data> {
    void publish(Data data);
    void addConsumer(Consumer<Data> consumer);
}

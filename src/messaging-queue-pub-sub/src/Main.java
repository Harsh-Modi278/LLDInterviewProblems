import models.Message;
import models.Topic;
import publicInterfaces.ISubscriber;
import publicInterfaces.Queue;

public class Main {
    public static void main(String[] args) {
        final Queue queue = new Queue();

        final Topic topic1 = queue.createTopic("myTopic 1");
        final Topic topic2 = queue.createTopic("myTopic 2");

        final ISubscriber subscriber1 = new PrintSubscriber("1", 1000);
        final ISubscriber subscriber2 = new PrintSubscriber("2", 1000);
        queue.subscribe(topic1, subscriber1);
        queue.subscribe(topic1, subscriber2);

        final ISubscriber subscriber3 = new PrintSubscriber("2", 5000);
        queue.subscribe(topic2, subscriber3);

        queue.publish(topic1, new Message("test message 1"));
        queue.publish(topic2, new Message("test message 2"));
    }
}
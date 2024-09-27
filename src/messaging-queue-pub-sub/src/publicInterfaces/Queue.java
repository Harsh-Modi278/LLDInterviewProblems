package publicInterfaces;

import handlers.TopicHandler;
import models.Message;
import models.Topic;
import models.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    Map<String, TopicHandler> topicHandlerMap;

    public Queue() {
        topicHandlerMap = new HashMap<>();
    }

    public Topic createTopic(final String topicName) {
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlerMap.put(topicName, topicHandler);
        System.out.println("Topic " + topicName + " created");
        return topic;
    }

    public void subscribe(final Topic topic, final ISubscriber subscriber) {
        TopicSubscriber topicSubscriber = new TopicSubscriber(subscriber);
        topic.addSubscriber(topicSubscriber);
        System.out.println(subscriber.getId() + " subscribed to " + topic.getTopicName());
    }

    public void publish(final Topic topic, final Message message) {
        topic.addMessage(message);
        System.out.println(message.getMessage() + " published to topic " + topic.getTopicName());

        // using a non-persistent thread to publish messages for fan out to all subscribers
        // using a thread to not block the Main thread from executing while publishing happens
        new Thread(() -> topicHandlerMap.get(topic.getTopicName()).publish()).start();
    }
}

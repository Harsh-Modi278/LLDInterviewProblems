package handlers;

import models.Message;
import models.Topic;
import models.TopicSubscriber;

public class SubscriberWorker implements Runnable {
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    public void run() {
        synchronized (topicSubscriber) {
            do {
                int currOffset = topicSubscriber.getOffset().get();
                while (currOffset >= topic.getMessages().size()) {
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Message message = topic.getMessages().get(currOffset);
                try {
                    topicSubscriber.getSubscriber().consume(message);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                topicSubscriber.getOffset().compareAndSet(currOffset, currOffset + 1);
            } while (true);
        }
    }

    synchronized public void wakeupIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }

    public Topic getTopic() {
        return topic;
    }

    public TopicSubscriber getTopicSubscriber() {
        return topicSubscriber;
    }
}

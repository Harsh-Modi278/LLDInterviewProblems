package handlers;

import models.Topic;
import models.TopicSubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicHandler {
    private final Topic topic;
    private final Map<String, SubscriberWorker> subscriberWorkerMap;

    public TopicHandler(Topic topic) {
        this.topic = topic;
        subscriberWorkerMap = new HashMap<>();
    }

    public void publish() {
        // get all subscribers for this topic
        List<TopicSubscriber> topicSubscribers = topic.getSubscribers();
        for (TopicSubscriber topicSubscriber : topicSubscribers) {
            // all these subscribers should run in parallel, so we run that logic in separate threads
            // one thread for each topicSubscriber
            startSubscriberWorker(topicSubscriber);
        }
    }

    public void startSubscriberWorker(TopicSubscriber topicSubscriber) {
        final String subscriberId = topicSubscriber.getSubscriber().getId();
        if (!subscriberWorkerMap.containsKey(subscriberId)) {
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic, topicSubscriber);
            subscriberWorkerMap.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start(); // one thread for each topicSubscriber.
        }

        SubscriberWorker subscriberWorker = subscriberWorkerMap.get(subscriberId);
        subscriberWorker.wakeupIfNeeded(); // if thread is already there, just waking up is enough.
    }

    public Topic getTopic() {
        return topic;
    }
}

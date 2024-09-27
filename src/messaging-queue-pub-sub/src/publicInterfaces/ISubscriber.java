package publicInterfaces;

import models.Message;

public interface ISubscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}

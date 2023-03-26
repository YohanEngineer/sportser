package com.sportser.sportsernotificationchannelmanager.redis.queue;


public interface MessagePublisher {
    void publish(final String message);
}
package com.shams.spring.poller.listeners;

/**
 * Created by shams on 11/21/16.
 */
public interface PollerListener {

    void listen();

    String id();

    void stop();
}

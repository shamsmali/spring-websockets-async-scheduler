package com.shams.spring.poller;

import com.shams.spring.poller.listeners.PollerListener;

import java.util.Set;

/**
 * Created by shams on 11/21/16.
 */
public interface Poller {

    boolean poll(long reccur);

    void addPollerListener(PollerListener pollerListener);

    void removePollerListener(String id);

    boolean stop();

    Set<String> getListeners();
}

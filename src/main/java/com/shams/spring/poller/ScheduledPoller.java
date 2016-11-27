package com.shams.spring.poller;

import com.shams.spring.poller.listeners.PollerListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shams on 11/21/16.
 */
@Service
public class ScheduledPoller implements Poller {

    private Map<String, PollerListener> pollerListeners = new ConcurrentHashMap<>();
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    private Lock lock = new ReentrantLock();

    @Override
    public void addPollerListener(PollerListener pollerListener) {
        this.pollerListeners.put(pollerListener.id(), pollerListener);
    }

    @Override
    public void removePollerListener(String id) {
        Optional<Map.Entry<String,PollerListener>> pollerListener = this.pollerListeners.entrySet().stream().filter(ll -> ll.getValue().id().equalsIgnoreCase(id)).findFirst();
        if (pollerListener.isPresent()) {
            PollerListener pollerL = pollerListener.get().getValue();
            this.pollerListeners.remove(id);
            pollerL.stop();
        }
    }

    @Override
    public boolean poll(final long reccur) {
        executorService.scheduleAtFixedRate(() -> publish(), 0, reccur, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public boolean stop() {
        lock.lock();
        if (!this.executorService.isShutdown()) {
            System.out.println("Going to shutdown the service now");
            this.executorService.shutdown();
            this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        }
        lock.unlock();
        return true;

    }

    @Override
    public Set<String> getListeners() {
        return this.pollerListeners.keySet();
    }

    private void publish() {
        this.pollerListeners.entrySet().forEach(ll -> ll.getValue().listen());
    }
}

package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager { // Only work with subscriber interface - not concrete subscriber
    Map<String, List<Subscriber>> subscribers = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.subscribers.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, Subscriber subscriber) {
        List<Subscriber> users = subscribers.get(eventType);
        users.add(subscriber);
    }

    public void unsubscribe(String eventType, Subscriber subscriber) {
        List<Subscriber> users = subscribers.get(eventType);
        users.remove(subscriber);
    }

    public void notify(String eventType, Subscriber data) {
        List<Subscriber> users = subscribers.get(eventType);
        for (Subscriber subscriber : users) {
            subscriber.update();
        }
    }
}

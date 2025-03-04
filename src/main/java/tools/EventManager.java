package tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager implements Serializable { // This is concreate Publisher - Only work with subscriber interface - not concrete subscriber
    Map<String, List<Subscriber>> subscribers = new HashMap<>();
    List<String> operations = new ArrayList<>();
    public EventManager(String... operations) {
        for (String operation : operations) {
            this.subscribers.put(operation, new ArrayList<>());
            this.operations.add(operation);
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

    public void unsubscribeAll(String eventType) {
        List<Subscriber> users = subscribers.get(eventType);
        users.clear();
    }

    public void unsubscribeAllEventType() {
        for (String operation: operations) {
            List<Subscriber> users = subscribers.get(operation);
            users.clear();
        }
    }

    public void notify(String eventType) {
        List<Subscriber> users = subscribers.get(eventType);
        for (Subscriber subscriber : users) {
            subscriber.update();
        }
    }
}

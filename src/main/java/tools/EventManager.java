package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<String, List<Subscriber>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, Subscriber listener) {
        List<Subscriber> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, Subscriber listener) {
        List<Subscriber> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, Object data) {
        List<Subscriber> users = listeners.get(eventType);
        for (Subscriber listener : users) {
            listener.update();
        }
    }
}

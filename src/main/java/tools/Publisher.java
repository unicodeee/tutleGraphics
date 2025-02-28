package tools;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

     List<Subscriber> subscribers = new ArrayList<>();

    public Publisher(Subscriber... subs) {
        for (Subscriber s : subs) {
            this.subscribers.add(s);
        }
    }

    public void subscribe(Subscriber subscriber) {

        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }
}

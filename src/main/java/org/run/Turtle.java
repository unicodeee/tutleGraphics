package org.run;

import org.run.Point;
import tools.EventManager;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;

public class Turtle {
    private Deque<Point> path = new ArrayDeque<>();
    private EventManager events;

    public Turtle(Point firstPoint) {
        this.path.add(firstPoint);
        this.events = new EventManager("move", "penToggle", "colorChange");
    }

    public Point copyLast(){
        return new Point(path.getLast());
    }

    public Deque<Point> getPath(){
        return path;
    }

    public void moveNorth(int distance) {
        Point newPoint = copyLast();
        newPoint.y = Math.max(0, newPoint.y - distance);
        path.add(newPoint);
        events.notify("move", null);
    }

    public void moveSouth(int distance) {
        Point newPoint = copyLast();
        newPoint.y = Math.min(250, newPoint.y + distance);
        path.add(newPoint);
        events.notify("move", null);
    }

    public void moveEast(int distance) {
        Point newPoint = copyLast();
        newPoint.x = Math.min(250, newPoint.x + distance);
        path.add(newPoint);
        events.notify("move", null);
    }

    public void moveWest(int distance) {
        Point newPoint = copyLast();
        newPoint.x = Math.max(0, newPoint.x - distance);
        path.add(newPoint);
        events.notify("move", null);
    }

    public void togglePen() {
        path.getLast().penDown = !path.getLast().penDown;
        events.notify("penToggle", null);
    }

    public void clear() {
        Point tempt = path.getFirst();
        path.clear();
        path.add(tempt);
        events.notify("move", null); // trigger move event to repaint after clearing the path
    }

    public void setPenColor(Color color) {
        this.path.getLast().penColor = color;
        events.notify("colorChange", null);
    }

    public boolean isPenDown() {
        return path.getLast().penDown;
    }

    public Color getPenColor() {
        return path.getLast().penColor;
    }

    public int getX() {
        return path.getLast().x;
    }

    public int getY() {
        return path.getLast().y;
    }

    public EventManager getEvents() {
        return events;
    }
}

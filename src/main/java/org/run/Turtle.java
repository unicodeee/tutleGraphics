package org.run;

import tools.EventManager;
import tools.Model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class Turtle extends Model implements Serializable {
    private Deque<Point> path = new ArrayDeque<>();
    private EventManager eventManager;

    public Turtle(Point firstPoint) {
        this.path.add(firstPoint);
        this.eventManager = new EventManager("move", "penToggle", "colorChange");
    }

    public Point copyLast(){
        return new Point(path.getLast());
    }

    public Deque<Point> getPath(){
        return path;
    }

    public void refreshPath(){
        eventManager.unsubscribeAll("move");
        eventManager.notify("move", null);
    }

    public void moveNorth(int distance) {
        Point newPoint = copyLast();
        newPoint.y = Math.max(0, newPoint.y - distance);
        path.add(newPoint);
        eventManager.notify("move", null);
    }

    public void moveSouth(int distance) {
        Point newPoint = copyLast();
        newPoint.y = Math.min(250, newPoint.y + distance);
        path.add(newPoint);
        eventManager.notify("move", null);
    }

    public void moveEast(int distance) {
        Point newPoint = copyLast();
        newPoint.x = Math.min(250, newPoint.x + distance);
        path.add(newPoint);
        eventManager.notify("move", null);
    }

    public void moveWest(int distance) {
        Point newPoint = copyLast();
        newPoint.x = Math.max(0, newPoint.x - distance);
        path.add(newPoint);
        eventManager.notify("move", null);
    }

    public void togglePen() {
        path.getLast().penDown = !path.getLast().penDown;
        eventManager.notify("penToggle", null);
    }

    public void clear() {
        Point tempt = path.getFirst();
        path.clear();
        path.add(tempt);
        eventManager.notify("move", null); // trigger move event to repaint after clearing the path
        eventManager.notify("penToggle", null);
        eventManager.notify("colorChange", null);
    }


    public void setPenColor(Color color) {
        this.path.getLast().penColor = color;
        eventManager.notify("colorChange", null);
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

    public EventManager getEventManager() {
        return eventManager;
    }

    // Overriding getFileName method from Model class
    @Override
    public String getFileName() {
        return super.getFileName();  // Call the superclass method to get the file name
    }

    // Overriding setFileName method from Model class
    @Override
    public void setFileName(String fName) {
        super.setFileName(fName);  // Call the superclass method to set the file name
    }

    public void startOver() {
        clear();
        this.setFileName(null);
        this.setSaveAs(true);
    }
}

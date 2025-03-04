package org.run;

import tools.EventManager;
import tools.Model;
import tools.SETTINGS;

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
        this.setUnsavedChanges(false);
    }

    public Point copyLast(){
        return new Point(path.getLast());
    }

    public Deque<Point> getPath(){
        return path;
    }


    public void moveNorth(int distance) {
        this.setUnsavedChanges(true);
        Point newPoint = copyLast();
        newPoint.y = Math.max(0, newPoint.y - distance);
        path.add(newPoint);
        eventManager.notify("move");
    }

    public void moveSouth(int distance) {
        this.setUnsavedChanges(true);
        Point newPoint = copyLast();
        newPoint.y = Math.min(SETTINGS.WORLD_SIZE, newPoint.y + distance);
        path.add(newPoint);
        eventManager.notify("move");
    }

    public void moveEast(int distance) {
        this.setUnsavedChanges(true);
        Point newPoint = copyLast();
        newPoint.x = Math.min(SETTINGS.WORLD_SIZE, newPoint.x + distance);
        path.add(newPoint);
        eventManager.notify("move");
    }

    public void moveWest(int distance) {
        this.setUnsavedChanges(true);
        Point newPoint = copyLast();
        newPoint.x = Math.max(0, newPoint.x - distance);
        path.add(newPoint);
        eventManager.notify("move");
    }

    public void togglePen() {
        path.getLast().penDown = !path.getLast().penDown;
        eventManager.notify("penToggle");
    }

    public void clear() {
        Point tempt = path.getLast();
        path.clear();
        path.add(tempt);
        eventManager.notify("move"); // trigger move event to repaint after clearing the path
        eventManager.notify("penToggle");
        eventManager.notify("colorChange");
    }

    public void nofifyAllEvents() {
        eventManager.notify("move"); // trigger move event to repaint after clearing the path
        eventManager.notify("penToggle");
        eventManager.notify("colorChange");
    }


    public void setPenColor(Color color) {
        this.setUnsavedChanges(true);
        this.path.getLast().penColor = color;
        eventManager.notify("colorChange");
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
        this.setUnsavedChanges(false);
    }
}

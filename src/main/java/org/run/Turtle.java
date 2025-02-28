package org.run;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

import org.run.Point;

public class Turtle implements Serializable {  // Pulisher
    Deque<Point> path = new ArrayDeque<>();

    public Turtle(Point firstPoint) {
        this.path.add(firstPoint); // Default
    }// new Point(125, 125, true, Color.BLACK


    public Point copyLast(){
        return new Point(path.getLast());
    }

    public Point getSecondLastPoint() {
        // Check if the deque has at least 2 points
        if (path.size() >= 2) {
            return (Point) path.toArray()[path.size() - 2];
        }
        // If there are less than 2 points, return null or throw an exception as needed
        return path.getLast();
    }


    public void moveNorth(int distance) {
        Point newPoint = copyLast();
        newPoint.y = Math.min(250, newPoint.y - distance);
        path.add(newPoint);
    }

    public void moveSouth(int distance) {
        Point newPoint = copyLast();
        newPoint.y = Math.min(250, newPoint.y + distance);
        path.add(newPoint);
    }

    public void moveEast(int distance) {
        Point newPoint = copyLast();
        newPoint.x = Math.min(250, newPoint.x + distance);
        path.add(newPoint);
    }

    public void moveWest(int distance) {
        Point newPoint = copyLast();
        newPoint.x = Math.max(0, newPoint.x - distance);
        path.add(newPoint);
    }

    public void togglePen() {
        path.getLast().penDown = !path.getLast().penDown;
    }

    public boolean isPenDown() {
        return path.getLast().penDown;
    }

    public void setPenColor(Color color) {
        this.path.getLast().penColor = color;
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
}

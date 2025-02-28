package org.run;

import java.awt.*;
import java.io.Serializable;

public class Point implements Serializable {
    protected int x, y;
    protected boolean penDown;
    protected Color penColor;

    public Point(int x, int y, boolean penDown, Color penColor) {
        this.x = x;  // Starting position at the center
        this.y = y;
        this.penDown = penDown;
        this.penColor = penColor;
    }

    // Copy constructor
    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
        this.penDown = other.penDown;
        this.penColor = other.penColor;
    }
}

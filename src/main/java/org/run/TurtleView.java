package org.run;

import tools.Subscriber;

import javax.swing.*;
import java.awt.*;

public class TurtleView extends JPanel implements Subscriber { // Subscriber
    private Turtle turtle;

    public TurtleView(Turtle turtle) {
        this.turtle = turtle;
        this.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point prevPoint = null;

        // Draw the path, considering penDown state for each point
        for (Point p : turtle.path) {
            if (prevPoint != null && prevPoint.penDown) {
                // Set the pen color for the line and draw it only if pen is down
                g.setColor(prevPoint.penColor);  // Use the previous point's pen color
                g.drawLine(prevPoint.x, prevPoint.y, p.x, p.y);
            }
            prevPoint = p;
        }

        // Draw the current turtle position based on whether the pen is down or up
        g.setColor(turtle.getPenColor()); // Set the color for the current turtle position
        if (turtle.isPenDown()) {
            g.fillOval(turtle.getX() - 5, turtle.getY() - 5, 10, 10);  // Pen down, draw a circle
        } else {
            g.fillOval(turtle.getX() - 5, turtle.getY() - 5, 10, 5);  // Pen up, draw a smaller oval
        }
    }

    @Override
    public void update() {
        repaint();
    }
}

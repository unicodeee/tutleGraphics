package org.run;


import tools.Subscriber;

import javax.swing.*;
import java.awt.*;

public class TurtleView extends JPanel implements Subscriber {
    private Turtle turtle;

    public TurtleView(Turtle turtle) {
        this.turtle = turtle;
        this.setBackground(Color.WHITE);
    }

    public void setTurtle(Turtle newTurtle) {
        turtle = newTurtle;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point prevPoint = null;

        for (Point p : turtle.getPath()) {
            if (prevPoint != null && prevPoint.penDown) {
                g.setColor(prevPoint.penColor);
                g.drawLine(prevPoint.x, prevPoint.y, p.x, p.y);
            }
            prevPoint = p;
        }

        g.setColor(turtle.getPenColor());
        if (turtle.isPenDown()) {
            g.fillOval(turtle.getX() - 5, turtle.getY() - 5, 10, 10);
        } else {
            g.fillOval(turtle.getX() - 5, turtle.getY() - 5, 10, 5);
        }
    }

    @Override
    public void update() {
        repaint();
    }
}

package org.run;

import javax.swing.*;
import java.awt.*;

public class TurtleComponent extends JComponent {
    private Turtle turtle;

    public TurtleComponent(Turtle turtle) {
        this.turtle = turtle;
        this.setBackground(Color.WHITE);
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
}

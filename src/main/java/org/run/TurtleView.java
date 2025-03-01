package org.run;

import tools.SETTINGS;
import tools.Subscriber;

import javax.swing.*;
import java.awt.*;

public class TurtleView extends JPanel implements Subscriber {
    private Turtle turtle;
    private TurtleComponent turtleComponent;

    public TurtleView(Turtle turtle) {
        this.turtle = turtle;
        this.turtleComponent = new TurtleComponent(turtle);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.add(turtleComponent, BorderLayout.CENTER);
    }

    public void setTurtle(Turtle newTurtle) {
        turtle = newTurtle;
        turtleComponent = new TurtleComponent(newTurtle); // Update the component
        removeAll(); // Clear previous components
        add(turtleComponent, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SETTINGS.WORLD_SIZE, SETTINGS.WORLD_SIZE);  // Setting the preferred size to 250x250
    }

    @Override
    public void update() {
        repaint();
    }
}

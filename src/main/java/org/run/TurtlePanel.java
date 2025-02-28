package org.run;


import tools.Utilities;

import javax.swing.*;
import java.awt.*;

public class TurtlePanel {
    public static void main(String[] args) {
        Point startPoint = new Point(125, 125, true, Color.BLACK);
        Turtle turtle = new Turtle(startPoint);

        TurtleView view = new TurtleView(turtle);
        TurtleController controller = new TurtleController(turtle);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2));
        frame.add(controller.getControls());
        frame.add(view);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
}

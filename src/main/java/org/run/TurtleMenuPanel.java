package org.run;


import tools.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TurtleMenuPanel extends JPanel implements ActionListener {
    private Turtle turtle;
    private TurtleView view;
    private TurtleControllerPanel controller;

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public TurtleMenuPanel(){
        Point startPoint = new Point(125, 125, true, Color.BLACK);
        Turtle turtle = new Turtle(startPoint);
        TurtleView view = new TurtleView(turtle);
        TurtleControllerPanel controller = new TurtleControllerPanel(turtle);

        this.turtle = turtle;
        this.view = view;
        this.controller = controller;


        turtle.getEventManager().subscribe("move", view);
        turtle.getEventManager().subscribe("penToggle", view);
        turtle.getEventManager().subscribe("colorChange", view);

        JFrame frame = new JFrame();
        frame.setJMenuBar(this.createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2));
        frame.add(controller.getControls());
        frame.add(view);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TurtleMenuPanel t = new TurtleMenuPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "New": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        turtle.startOver();
                    }
                    break;
                }

                case "Save": {
                    Utilities.save(turtle, false);
                    break;
                }

                case "Save As": {
                    Utilities.save(turtle, true);
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        turtle = (Turtle) is.readObject();
                        turtle.getEventManager().unsubscribeAllEventType();
                        // Re-subscribe the view to the new turtle's events
                        turtle.getEventManager().subscribe("move", view);
                        turtle.getEventManager().subscribe("penToggle", view);
                        turtle.getEventManager().subscribe("colorChange", view);

                        controller.setTurtle(turtle);
                        view.setTurtle(turtle);
                        is.close();
                    }
                    break;
                }

                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        System.exit(0);
                    }
                    break;
                }

                case "About": {
                    Utilities.inform("QuocThangNguyen Designs Turtle Graphics, 2025. All rights reserved.");
                    break;
                }

                case "Help": {
                    String[] cmmds = new String[]{
                            "North: move North",
                            "South: move North",
                            "West: move North",
                            "East: move North",
                            "Clear: clear all and move back to original position",
                            "Set Color: pick a different color",
                            "Distance: pick a distance",
                    };
                    Utilities.inform(cmmds);
                    break;
                }

                default: {
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }
}

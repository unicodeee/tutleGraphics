package org.run;


import tools.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurtleController implements ActionListener, Subscriber {
    private Turtle turtle;
    private JPanel controls;
    private JTextField distanceInput;
    private JButton northButton, southButton, eastButton, westButton, penToggle, colorButton;

    public TurtleController(Turtle turtle) {
        this.turtle = turtle;
        controls = new JPanel();
        controls.setBackground(Color.PINK);
        turtle.getEvents().subscribe("move", this);

        createControlPanel();
    }

    private void createControlPanel() {
        JPanel controlPanel = new JPanel();

        northButton = new JButton("North");
        southButton = new JButton("South");
        eastButton = new JButton("East");
        westButton = new JButton("West");
        penToggle = new JButton("Pen Up/Down");
        colorButton = new JButton("Set Color");

        northButton.addActionListener(this);
        southButton.addActionListener(this);
        eastButton.addActionListener(this);
        westButton.addActionListener(this);
        penToggle.addActionListener(this);
        colorButton.addActionListener(this);

        controlPanel.add(northButton);
        controlPanel.add(southButton);
        controlPanel.add(eastButton);
        controlPanel.add(westButton);
        controlPanel.add(penToggle);
        controlPanel.add(colorButton);

        distanceInput = new JTextField(5);
        controlPanel.add(new JLabel("Distance:"));
        controlPanel.add(distanceInput);

        controlPanel.setLayout(new GridLayout(3, 2));
        controls.add(controlPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int distance = Integer.parseInt(distanceInput.getText().isEmpty() ? "0" : distanceInput.getText());

        switch (command) {
            case "North":
                turtle.moveNorth(distance);
                break;
            case "South":
                turtle.moveSouth(distance);
                break;
            case "East":
                turtle.moveEast(distance);
                break;
            case "West":
                turtle.moveWest(distance);
                break;
            case "Pen Up/Down":
                turtle.togglePen();
                break;
            case "Set Color":
                Color newColor = JColorChooser.showDialog(null, "Choose Pen Color", Color.BLACK);
                if (newColor != null) {
                    turtle.setPenColor(newColor);
                }
                break;
        }
    }

    @Override
    public void update() {
        // Handle updates
    }

    public JPanel getControls() {
        return controls;
    }
}

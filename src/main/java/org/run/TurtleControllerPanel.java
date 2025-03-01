package org.run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurtleControllerPanel implements ActionListener {
    private Turtle turtle;
    private final JPanel controls;
    private JTextField distanceInput;
    private JButton northButton, southButton, eastButton, westButton, penToggle, colorButton, clear;

    public TurtleControllerPanel(Turtle turtle) {
        this.turtle = turtle;
        controls = new JPanel();
        controls.setBackground(Color.PINK);

        createControlPanel();
    }

    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }

    private void createControlPanel() {
        JPanel controlPanel = new JPanel();

        northButton = new JButton("North");
        southButton = new JButton("South");
        eastButton = new JButton("East");
        westButton = new JButton("West");
        penToggle = new JButton("Pen Up/Down");
        colorButton = new JButton("Set Color");
        clear = new JButton("Clear");

        northButton.addActionListener(this);
        southButton.addActionListener(this);
        eastButton.addActionListener(this);
        westButton.addActionListener(this);
        penToggle.addActionListener(this);
        colorButton.addActionListener(this);
        clear.addActionListener(this);

        controlPanel.add(northButton);
        controlPanel.add(southButton);
        controlPanel.add(eastButton);
        controlPanel.add(westButton);
        controlPanel.add(penToggle);
        controlPanel.add(colorButton);
        controlPanel.add(clear);

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
            case "Clear":
                turtle.clear();
                break;
        }
    }

    public JPanel getControls() {
        return controls;
    }
}

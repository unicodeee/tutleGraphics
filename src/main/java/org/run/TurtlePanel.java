package org.run;

import tools.Utilities;
import tools.Publisher;
import tools.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurtlePanel extends JPanel implements ActionListener, Subscriber {
    private Point firstPoint;
    private Turtle turtle;
    private JPanel controls;
    private TurtleView view;
    private Publisher publisher;
    private JTextField distanceInput;
    private JButton northButton, southButton, eastButton, westButton, penToggle, colorButton;

    public TurtlePanel() {
        firstPoint = new Point(125, 125, true, Color.BLACK);
        turtle = new Turtle(firstPoint);
        view = new TurtleView(turtle);
        controls = new JPanel();
        controls.setBackground(Color.PINK);
        publisher = new Publisher(this);

        JPanel controlPanel = new JPanel();



        // Buttons for directions
        northButton = new JButton("North");
        southButton = new JButton("South");
        eastButton = new JButton("East");
        westButton = new JButton("West");

        // Button for pen up/down and color change
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

        distanceInput = new JTextField(5); // Input field for distance
        controlPanel.add(new JLabel("Distance:"));
        controlPanel.add(distanceInput);

        controlPanel.setLayout((new GridLayout(3, 2)));
        controls.add(controlPanel);


        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        this.add(view);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("Turtle Simulator");
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Change"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int distance = Integer.parseInt(distanceInput.getText().isEmpty() ? "0" : distanceInput.getText());
        distance = Math.min(distance, 250); // Ensure the turtle doesn’t move beyond 250 pixels

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
                Color newColor = JColorChooser.showDialog(this, "Choose Pen Color", Color.BLACK);
                if (newColor != null) {
                    turtle.setPenColor(newColor);
                }
                break;
        }
        publisher.notifySubscribers();  // Notify subscribers of any turtle movement changes
    }

    @Override
    public void update() {
        // Update the turtle view when notified
        view.repaint();
    }

    public static void main(String[] args) {
        TurtlePanel app = new TurtlePanel();
    }
}

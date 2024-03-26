package view;

import javax.swing.*;

public class AddressView extends JFrame {

    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel streetLabel;
    private JTextField streetField;
    private JLabel cityLabel;
    private JTextField cityField;
    private JLabel zipLabel;
    private JTextField zipField;

    public AddressView() {
        super("Address View"); // Set window title

        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);  // Set text field width
        streetLabel = new JLabel("Street:");
        streetField = new JTextField(20);
        cityLabel = new JLabel("City:");
        cityField = new JTextField(20);
        zipLabel = new JLabel("Zip Code:");
        zipField = new JTextField(10);  // Adjust width for zip code

        // Add components to the frame (layout can be customized)
        add(nameLabel);
        add(nameField);
        add(streetLabel);
        add(streetField);
        add(cityLabel);
        add(cityField);
        add(zipLabel);
        add(zipField);


        pack(); // Adjust window size to fit components
        setVisible(true); // Make the window visible
    }
}


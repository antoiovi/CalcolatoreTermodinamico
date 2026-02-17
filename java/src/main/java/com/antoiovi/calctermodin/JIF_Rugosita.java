package com.antoiovi.calctermodin;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Roughness Selection Dialog
 *
 * This dialog allows the user to select typical internal wall roughness values
 * for different construction materials.
 *
 * The values are based on UNI 9615 standard.
 *
 * The selected roughness value (in meters) can be:
 *  - Chosen from predefined minimum/maximum values
 *  - Entered manually by the user
 *
 * The dialog returns:
 *  - "ok"  → if a valid value is confirmed
 *  - "esc" → if cancelled or invalid
 *
 * Used by the Moody Diagram module.
 *
 * License: MIT
 */
public class JIF_Rugosita extends JDialog implements ActionListener {

    private JTextField textFieldValue;

    /** Dialog return state ("ok" or "esc") */
    private String value = "esc";

    /** Selected roughness numeric value */
    private double doblevalue;

    /**
     * Construction type labels
     */
    String labels[] = {
        "Smooth plastic pipe (condensing boiler exhaust)",
        "Corrugated plastic pipe (condensing boiler exhaust)",
        "Moderately rusted steel pipe",
        "Sheet metal duct",
        "Concrete duct",
        "Refractory duct",
        "Masonry chimney"
    };

    /**
     * Minimum roughness values [m]
     */
    double minimo[] = {
        0.008e-3,
        2.85e-3,
        0.0005,
        0.0015,
        0.001,
        0.001,
        0.003
    };

    /**
     * Maximum roughness values [m]
     */
    double massimo[] = {
        0.008e-3,
        6.78e-3,
        0.001,
        0.002,
        0.003,
        0.002,
        0.005
    };

    JLabel jlabales[] = new JLabel[7];
    JButton jbuttonsMin[] = new JButton[7];
    JButton jbuttonsMax[] = new JButton[7];

    /**
     * Constructor with parent frame
     */
    public JIF_Rugosita(JFrame parent, boolean modal) {
        super(parent, true);
        setTitle("Average Internal Wall Roughness");
        setResizable(false);
        setBounds(100, 100, 600, 500);
        init();
    }

    /**
     * Default constructor
     */
    public JIF_Rugosita() {
        super();
        this.setModal(true);
        setTitle("Average Internal Wall Roughness");
        setResizable(false);
        setBounds(100, 100, 600, 700);
        init();
    }

    /**
     * Initializes GUI components and layout
     */
    private void init() {

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 273, 52, 53, 0 };
        gridBagLayout.rowHeights = new int[] { 0,0,0,0,0,0,0,0,0,0,0 };
        getContentPane().setLayout(gridBagLayout);

        GridBagConstraints gbl = new GridBagConstraints();
        gbl.insets = new Insets(0, 0, 5, 5);

        // Header labels
        JLabel lblConstruction = new JLabel("Construction type");
        gbl.gridx = 0;
        gbl.gridy = 0;
        getContentPane().add(lblConstruction, gbl);

        JLabel lblRoughness = new JLabel("Roughness [m]");
        gbl.gridwidth = 2;
        gbl.gridx = 1;
        gbl.gridy = 0;
        getContentPane().add(lblRoughness, gbl);

        JLabel lblFrom = new JLabel("from");
        gbl.gridwidth = 1;
        gbl.gridx = 1;
        gbl.gridy = 1;
        getContentPane().add(lblFrom, gbl);

        JLabel lblTo = new JLabel("to");
        gbl.gridx = 2;
        gbl.gridy = 1;
        getContentPane().add(lblTo, gbl);

        int gridY = 1;

        // Create rows dynamically
        for (int x = 0; x < labels.length; x++) {

            gridY++;

            // Material label
            jlabales[x] = new JLabel(labels[x]);
            gbl.gridx = 0;
            gbl.gridy = gridY;
            getContentPane().add(jlabales[x], gbl);

            // Minimum roughness button
            jbuttonsMin[x] = new JButton(Double.toString(minimo[x]));
            jbuttonsMin[x].addActionListener(e ->
                textFieldValue.setText(((JButton)e.getSource()).getText())
            );
            gbl.gridx = 1;
            getContentPane().add(jbuttonsMin[x], gbl);

            // Maximum roughness button
            jbuttonsMax[x] = new JButton(Double.toString(massimo[x]));
            jbuttonsMax[x].addActionListener(e ->
                textFieldValue.setText(((JButton)e.getSource()).getText())
            );
            gbl.gridx = 2;
            getContentPane().add(jbuttonsMax[x], gbl);
        }

        gridY++;

        JLabel lblNote = new JLabel(
            "<html>Values provided by UNI 9615 standard.<br>" +
            "These values may increase in case of improper installation<br/>" +
            "of flue ducts and chimneys.</html>"
        );

        gbl.gridwidth = 3;
        gbl.gridx = 0;
        gbl.gridy = gridY;
        getContentPane().add(lblNote, gbl);

        gridY++;

        // Manual input panel
        JPanel panel = new JPanel(new FlowLayout());
        gbl.gridy = gridY;
        getContentPane().add(panel, gbl);

        JLabel lblInsert = new JLabel("Insert value:");
        panel.add(lblInsert);

        textFieldValue = new JTextField();
        textFieldValue.setColumns(10);
        panel.add(textFieldValue);

        gridY++;

        // Buttons panel
        JPanel panelButtons = new JPanel();
        gbl.gridy = gridY;
        getContentPane().add(panelButtons, gbl);

        JButton okButton = new JButton("Ok");
        okButton.setActionCommand("ok");
        okButton.addActionListener(this);
        panelButtons.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("esc");
        cancelButton.addActionListener(this);
        panelButtons.add(cancelButton);
    }

    /**
     * Returns selected numeric value
     */
    public double getDoblevalue() {
        return doblevalue;
    }

    /**
     * Returns dialog result ("ok" or "esc")
     */
    public String getValue() {
        return value;
    }

    /**
     * Handles OK / Cancel actions
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("ok")) {

            try {
                String s = textFieldValue.getText();
                doblevalue = Double.valueOf(s);
                value = "ok";
            } catch (Exception ex) {
                value = "esc";
            }

            this.setVisible(false);

        } else if (e.getActionCommand().equals("esc")) {

            value = "esc";
            this.setVisible(false);
        }
    }
}

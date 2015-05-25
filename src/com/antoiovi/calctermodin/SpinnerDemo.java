package com.antoiovi.calctermodin;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class SpinnerDemo extends JFrame 
{
    JSpinner s;
    SpinnerNumberModel model ;
    JSpinner.NumberEditor editor;
    JTextField stepText;
    JButton bStepSet;
    public void prepareAndShowGUI()
    {
        model = new SpinnerNumberModel(0.0,-1000.0 ,1000.0,0.1);
        s = new JSpinner(model);
        editor = new JSpinner.NumberEditor(s) ;
        s.setEditor(editor);
        stepText = new JTextField(10);
        bStepSet = new JButton("Set Step");
        bStepSet.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                try
                {
                    /* You can apply your stepSize deduction logic here*/
                    Double val = Double.parseDouble(stepText.getText().trim()); 
                    /*Setting the stepSize*/
                    model.setStepSize(val);
                }
                catch (Exception ex){}
            }
        });
        Container c = getContentPane();
        c.add(s);
        JPanel southPanel = new JPanel();
        southPanel.add(stepText);southPanel.add(bStepSet);
        c.add(southPanel,BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) 
    {
        SwingUtilities.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                SpinnerDemo sd = new SpinnerDemo();
                sd.prepareAndShowGUI();
            }
        });
    }
}
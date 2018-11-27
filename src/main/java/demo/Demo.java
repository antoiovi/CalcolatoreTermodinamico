package demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import  javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import javax.swing.UIManager;



import java.awt.Component;


public class Demo extends JFrame   {

private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 UIManager.setLookAndFeel(
					            UIManager.getSystemLookAndFeelClassName());
					Demo frame = new Demo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Demo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// tabbedPane.setSelectedIndex(0);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setToolTipText(" ");
		panel.add(tabbedPane);

	/*	APanelMoodyDiagram panel_moody= new APanelMoodyDiagram();
		tabbedPane.addTab("Diagramma di Moody", null, panel_moody, null);
		**/
		APCombCaldaia apcomb=new APCombCaldaia();
		JPanel panel_1=new JPanel(new BorderLayout());
		JScrollPane spane=new JScrollPane(apcomb);
		panel_1.add(spane,BorderLayout.CENTER);
		tabbedPane.addTab("AP comb caldaia", null, panel_1 , null);



	}



}

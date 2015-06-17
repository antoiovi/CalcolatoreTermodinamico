package com.antoiovi.caminob;

import it.iovino.utilita.Mylogger;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Myloggerpanel extends JTextArea implements Mylogger{

	/**
	 * Create the panel.
	 */
	public Myloggerpanel() {
		setWrapStyleWord(true);

	}

	int level;
	@Override
	public void appendMessage(int level, String message) {
		if(level>=this.level){
			this.append(message);
		}
	}
	@Override
	public void setLevel(int level) {
		this.level=level;
		
	}
	@Override
	public void clean() {
		this.setText("");
		
	}

}

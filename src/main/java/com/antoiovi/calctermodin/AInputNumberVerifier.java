package com.antoiovi.calctermodin;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AInputNumberVerifier extends InputVerifier implements  ActionListener{
	private JTextField jcomponent;
	private double DEFAULT_AMOUNT=0;
	private double MIN_AMOUNT;
	private double MAX_AMOUNT;
	private int DIGITS=8;
	private DecimalFormat decimalFormat;
	private int ACTION_IF_WRONG=0;
	private boolean SHOW_ERROR_MESSAGE=true;
	private boolean BEEP_IF_WRONG=true;
	private boolean RED_IF_WRONG=true;
	private boolean YELD_FOCUS_IF_WRONG=false;
	private Color wrong_backColor=Color.RED;
	private Color componentColor;
		public AInputNumberVerifier(JTextField jcomponent, double mIN_AMOUNT,
			double mAX_AMOUNT) {
		super();
		this.jcomponent = jcomponent;
		MIN_AMOUNT = mIN_AMOUNT;
		MAX_AMOUNT = mAX_AMOUNT;
		DEFAULT_AMOUNT=mIN_AMOUNT;
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ITALIAN);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator((char)0x000B7); 
				
		 decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance();
	     //decimalFormat.setMinimumFractionDigits(DIGITS);
	     decimalFormat.setDecimalFormatSymbols(otherSymbols);
	     componentColor=jcomponent.getBackground();
		}

		/**
		 * YIELD=CEDERE rendere, dare la precedenza
		 * Imn abase al valore restituito da questa funzione decido se cedere il focus
		 * anche quando l'input non è valido;
		 *When a component has an input verifier, this method is called by the system to determine whether the focus can leave this component.
		 * This method may cause side effects, such as bringing up a dialog-box. 
		 * If this method returns false, the focus remains on the component passed in to the method
		 *
		 */
		public boolean shouldYieldFocus(JComponent input) {
	        boolean inputOK = verify(input);
	        makeItPretty(input);
	        //updatePayment();
	        
	        if (inputOK) {
	        	input.setBackground(componentColor);
	        	return true;
	        } else {
	        	if(BEEP_IF_WRONG){        	
	            Toolkit.getDefaultToolkit().beep();
	        	}
	        	if(RED_IF_WRONG){
	        		input.setBackground(wrong_backColor);
	        	}
	if(SHOW_ERROR_MESSAGE){
	        	JOptionPane.showMessageDialog(null,
	                     "Valori validi da "+MIN_AMOUNT+" a "+MAX_AMOUNT,
	                     "Errore input",
	                     JOptionPane.ERROR_MESSAGE);
	                      
				}
	        	
	        	return YELD_FOCUS_IF_WRONG;
	        }
	    }
		

	    public void actionPerformed(ActionEvent e) {
	        JTextField source = (JTextField)e.getSource();
	        shouldYieldFocus(source); //ignore return value
	        source.selectAll();
	    }
	    
	    /**
	     * You need to override this method to check that the component's input is valid. 
	     * It should return true if valid, otherwise return false. 
	     * This method should not cause any side effects, such as bringing up a dialog-box.
	     *  This method is called by shouldYieldFocus.
	     */
		@Override
		public boolean verify(JComponent input) {
	        return checkField(input, false);
		}
		/**
		 * Richiamato p
		 * @param input
		 */
		protected void makeItPretty(JComponent input) {
	        // chiamo il metodi checkField dopo che è stato validato e/o cambiato
			// quindi metto changeIt=true
			checkField(input, true);
	    }
	    
		
		protected boolean checkField(JComponent input, boolean changeIt) {
		            return checkDoubleInRange(changeIt);
		        }
		 protected boolean checkDoubleInRange(boolean change) {
		            boolean wasValid = true;
		            double amount= DEFAULT_AMOUNT;

		            //Parse the value.
		            try {
		            	String s=jcomponent.getText();
		                amount = decimalFormat.parse(jcomponent.getText()).doubleValue();
		            } catch (ParseException pe) {
		               // pe.printStackTrace();
		                wasValid = false;
		            }

		            //Value was invalid.
		            if ((amount < MIN_AMOUNT) || (amount > MAX_AMOUNT)) {
		                wasValid = false;
		            }

		            //Whether was was valid , format it nicely.
		          if (change && wasValid) {
		        	  jcomponent.setText(decimalFormat.format(amount));
		              jcomponent.selectAll();
		            	        }
		          return wasValid;
		 }

}

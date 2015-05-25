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
/**
 * A component's input verifier is consulted whenever the component is about to lose the focus. 
 *  If the component's value is not acceptable, the input verifier can take appropriate action, 
 *  	such as refusing to yield (CEDERE)the focus on the component or replacing the user's input with the last valid value and then allowing 
 *  	the focus to transfer to the next component. 
 *  However, InputVerifier is not called when the focus is transferred to another toplevel component.
 *  
 *  
 * in JComponent 
 *  setInputVerifier(inputVerifier) getInputVerifier()
 	Sets or gets the input verifier assigned to the component. 
 	By default, components have no input verifier.
	(in JComponent) 	 	
		setVerifyInputWhenFocusTarget(boolean)
		getVerifyInputWhenFocusTarget()
	Sets or gets whether the input verifier for the current focus owner is called 
	before this component requests the focus. The default is true. 
	This method should be set to false for components, such as a Cancel button or a scroll bar, 
	that should receive the focus even if input is invalid.
 
 *  
 * @author antoiovi
 *
 */
public class InputDoubleMaxMin extends InputVerifier implements  ActionListener{
private JTextField jcomponent;
private double DEFAULT_AMOUNT=0;
private double MIN_AMOUNT;
private double MAX_AMOUNT;
private int DIGITS=8;
private String numberformat="%f";
private DecimalFormat decimalFormat;
private int ACTION_IF_WRONG=0;
private boolean SHOW_ERROR_MESSAGE=true;
private boolean BEEP_IF_WRONG=true;
private boolean RED_IF_WRONG=true;
private boolean YELD_FOCUS_IF_WRONG=false;
private Color wrong_backColor=Color.RED;
private Color componentColor;
	public InputDoubleMaxMin(JTextField jcomponent, double mIN_AMOUNT,
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
    
	
	public String getNumberformat() {
		return numberformat;
	}

	public void setNumberformat(String numberformat) {
		this.numberformat = numberformat;
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
	        	  
	        	  //jcomponent.setText(decimalFormat.format(amount));
	        	  jcomponent.setText(String.format(numberformat,amount));
	        	  
	              jcomponent.selectAll();
	            	        }
	          return wasValid;
	 }

	public JTextField getJcomponent() {
		return jcomponent;
	}

	public void setJcomponent(JTextField jcomponent) {
		this.jcomponent = jcomponent;
	}

	public double getDEFAULT_AMOUNT() {
		return DEFAULT_AMOUNT;
	}

	public void setDEFAULT_AMOUNT(double dEFAULT_AMOUNT) {
		DEFAULT_AMOUNT = dEFAULT_AMOUNT;
	}

	public double getMIN_AMOUNT() {
		return MIN_AMOUNT;
	}

	public void setMIN_AMOUNT(double mIN_AMOUNT) {
		MIN_AMOUNT = mIN_AMOUNT;
	}

	public double getMAX_AMOUNT() {
		return MAX_AMOUNT;
	}

	public void setMAX_AMOUNT(double mAX_AMOUNT) {
		MAX_AMOUNT = mAX_AMOUNT;
	}

	public int getDIGITS() {
		return DIGITS;
	}

	public void setDIGITS(int dIGITS) {
		DIGITS = dIGITS;
	}

	public DecimalFormat getDecimalFormat() {
		return decimalFormat;
	}

	public void setDecimalFormat(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	public int getACTION_IF_WRONG() {
		return ACTION_IF_WRONG;
	}

	public void setACTION_IF_WRONG(int aCTION_IF_WRONG) {
		ACTION_IF_WRONG = aCTION_IF_WRONG;
	}

	public boolean isSHOW_ERROR_MESSAGE() {
		return SHOW_ERROR_MESSAGE;
	}

	public void setSHOW_ERROR_MESSAGE(boolean sHOW_ERROR_MESSAGE) {
		SHOW_ERROR_MESSAGE = sHOW_ERROR_MESSAGE;
	}

	public boolean isBEEP_IF_WRONG() {
		return BEEP_IF_WRONG;
	}

	public void setBEEP_IF_WRONG(boolean bEEP_IF_WRONG) {
		BEEP_IF_WRONG = bEEP_IF_WRONG;
	}

	public boolean isRED_IF_WRONG() {
		return RED_IF_WRONG;
	}

	public void setRED_IF_WRONG(boolean rED_IF_WRONG) {
		RED_IF_WRONG = rED_IF_WRONG;
	}

	public boolean isYELD_FOCUS_IF_WRONG() {
		return YELD_FOCUS_IF_WRONG;
	}

	public void setYELD_FOCUS_IF_WRONG(boolean yELD_FOCUS_IF_WRONG) {
		YELD_FOCUS_IF_WRONG = yELD_FOCUS_IF_WRONG;
	}

	public Color getWrong_backColor() {
		return wrong_backColor;
	}

	public void setWrong_backColor(Color wrong_backColor) {
		this.wrong_backColor = wrong_backColor;
	}

	public Color getComponentColor() {
		return componentColor;
	}

	public void setComponentColor(Color componentColor) {
		this.componentColor = componentColor;
	}
	 
}//class 

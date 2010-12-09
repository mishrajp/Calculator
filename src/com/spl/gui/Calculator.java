package com.spl.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.spl.input.InputManager;
import com.spl.model.IModel;
import com.spl.model.Model;
import com.spl.model.ModelObserver;

public class Calculator extends JFrame implements ModelObserver {
		
	private int gridNumbersX = 3;
	private int gridNumbersY = 4;
	private int gridOperatorsX = 3;
	private int gridOperatorsY = 4;
	
	private static Object[] UNDO = {"UNDO","u"};
	private static Object[] CLEAR = {"CLEAR","c"};
	private static Object[] EQUAL = {"EQUAL", "="};
	
	private JPanel jplGeneral, jplOutput, jplButtons, jplNumbers, jplOperators;
	private JTextField jtfOutput1;
		
	private IModel model;
	private InputManager inputMgr;
	

	public Calculator() {
		// 1. Subscribes to the model (model-observer pattern)
		model = Model.getInstance();
		model.subscribe(this);
		
		// 2. Connects with the input manager
		inputMgr = new InputManager();
		inputMgr.load_valid_operators();
		
		// 3. Creates and sets up the window
        setTitle("SPL Calculator");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/4, screenSize.height/4);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 4. Sets up the content pane
		addComponents();
		
		// 5. Displays the window
        pack();
        setVisible(true);
	}
	
	public void addComponents() {
		// 1. Creates the common structure of a generic calculator
		jplGeneral = new JPanel();		
		jplGeneral.setLayout(new BoxLayout(jplGeneral, BoxLayout.Y_AXIS));
		jplOutput = new JPanel();
		jplGeneral.setLayout(new BoxLayout(jplGeneral, BoxLayout.Y_AXIS));
		jplButtons = new JPanel();
		jplButtons.setLayout(new BoxLayout(jplButtons, BoxLayout.X_AXIS));
		
		// 2. Sets the theme of the calculator
		setTheme();

		// 3. Creates the variable content of the pads
		addOutputPad();
		addNumericPad();
		addOperatorsPad();
		
		// 4. Adds the structure to the main frame
		jplGeneral.add(jplOutput);
		jplGeneral.add(jplButtons);
		getContentPane().add(jplGeneral);
	}

	private void setTheme() {
		/* TEST Theme
		 * Variability Point: Theme configuration parameters
		 * should be the ones on the configuration files only
		 */
	}
	
	private void addOutputPad() {
		/* TEST OUTPUT
		 * Variability Point: Output(s) should be the ones on the configuration files only
		 */
		jtfOutput1 = new JTextField("0");
		jplOutput.add(jtfOutput1);

	}

	private void addNumericPad() {
		jplNumbers = new JPanel();
		jplNumbers.setLayout(new GridLayout(gridNumbersY, gridNumbersX));
		ArrayList<String> arrayOfButtonTags = new ArrayList<String>(); 
		
		// 1.- Fills the array of button tags				
		// 1.1.- COMMONALIY: Fixed numbers
		for (int i = 9; i >= 0; i --) {
			arrayOfButtonTags.add(String.valueOf(i));
		}
		// 1.2 - VARIABILITY POINT, punctuation: Extra symbols (if any, ie '.')
		for (int i = 0; i < inputMgr.symbols.length; i++) {
			if (inputMgr.symbols[i] == '.') {
				arrayOfButtonTags.add(".");
			}
		}
		
		// 2.- Creates the buttons and links them to the buttonPressed generic
		//		actionPerformed method
		for (final String s : arrayOfButtonTags) {
			JButton jbtButton = new JButton(s);
			jbtButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
				            buttonPressed(s);
				        }
				    }
			);
			jplNumbers.add(jbtButton);
		}
		
		// 3.- Adds the numbers pad to the general button pad of the calculator
		jplButtons.add(jplNumbers);
	}
	
	private void addOperatorsPad() {
		jplOperators = new JPanel();
		jplOperators.setLayout(new GridLayout(gridOperatorsY, gridOperatorsX));
		ArrayList<String> arrayOfButtonTags = new ArrayList<String>(); 
		
		// 1.- Fills the array of button tags				
		// 1.1.- COMMONALIY: buttons UNDO, CLEAR and =
		arrayOfButtonTags.add((String)UNDO[0]);
		arrayOfButtonTags.add((String)CLEAR[0]);
		arrayOfButtonTags.add((String)EQUAL[1]);
			
		// 1.2.- VARIABILITY POINT: operators supported by the calculator
		for (int i = 0; i < inputMgr.symbols.length; i++) {
			char symbol = inputMgr.symbols[i];
			if ((!Character.isDigit(symbol)) && (symbol != '.')) {
				arrayOfButtonTags.add(Character.toString(inputMgr.symbols[i]));
			}
		}
		
		// 2.- Creates the buttons and links them to the buttonPressed generic
		//		actionPerformed method
		for (final String s : arrayOfButtonTags) {
			JButton jbtButton = new JButton(s);
			jbtButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
				            buttonPressed(s);
				        }
				    }
			);
			jplOperators.add(jbtButton);
		}
		
		// 3.- Adds the operators pad to the general button pad of the calculator		
		jplButtons.add(jplOperators);
	}
	
	private void buttonPressed(String buttonLabel) {
		char c;
				
		if (buttonLabel.equals((String)UNDO[0])) {
			c = ((String)UNDO[1]).charAt(0);
		}
		else if (buttonLabel.equals((String)CLEAR[0])) {
			c = ((String)CLEAR[1]).charAt(0);
		}
		else {
			c = buttonLabel.charAt(0);
		}
		
		inputMgr.addSymbol(c);
	}
	
	@Override
	public void notifyObserver(String output) {
		// TODO Auto-generated method stub
		if (output.equals(Model.RESULT)) {
			jtfOutput1.setText(model.getResult());
		}
		else {
			jtfOutput1.setText(model.getStatement());
		}
		
	} 
	
    public static void main(String[] args) {
    	
    	// 1.- Sets ative look & feel of the OS
		try {
		    UIManager.setLookAndFeel(
		        UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException ex) {
		  System.out.println("Unable to load native look and feel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2.- Instantiates the calculator
    	Calculator jfDummyCalculator = new Calculator();
    }


}

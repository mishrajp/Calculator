package com.spl.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
		
	private int gridNumbersX = 3;
	private int gridNumbersY = 4;
	private int gridOperatorsX = 3;
	private int gridOperatorsY = 4;
	
	private JPanel jplGeneral, jplOutput, jplButtons, jplNumbers, jplOperators, jplMessages;
	private JTextField jtfOutput1;
	private JLabel jlbMessages;

	public Calculator() {
		// 1. Creates and sets up the window
        setTitle("SPL Calculator");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/4, screenSize.height/4);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 2. Sets up the content pane
		addComponents();
		
		// 3. Displays the window
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
		jplMessages = new JPanel();
		jplMessages.setLayout(new BoxLayout(jplGeneral, BoxLayout.X_AXIS));
		
		// 2. Sets the theme of the calculator
		setTheme();

		// 3. Creates the variable content of the pads
		addOutputPad();
		addNumericPad();
		addOperatorsPad();
		addMessagesPad();
		
		// 4. Adds the structure to the main frame
		jplGeneral.add(jplOutput);
		jplGeneral.add(jplButtons);
		jplGeneral.add(jlbMessages);
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
		jtfOutput1 = new JTextField("Input/Ouput textfield");
		jplOutput.add(jtfOutput1);
	}

	private void addNumericPad() {
		jplNumbers = new JPanel();
		jplNumbers.setLayout(new GridLayout(gridNumbersY, gridNumbersX));
		for (int i = 9; i >= 0; i--)
		{
			jplNumbers.add(new JButton(String.valueOf(i)));
		}
		jplButtons.add(jplNumbers);
	}
	
	private void addOperatorsPad() {
		jplOperators = new JPanel();
		jplOperators.setLayout(new GridLayout(gridOperatorsY, gridOperatorsX));
		
		
		/* TEST BUTTONS
		 * Variability Point: Buttons should be the ones on the configuration files only
		 */
		jplOperators.add(new JButton("Undo"));
		jplOperators.add(new JButton("Clean"));
		jplOperators.add(new JButton("="));
		jplOperators.add(new JButton("+"));
		jplOperators.add(new JButton("-"));
		
		
		jplButtons.add(jplOperators);
	}
	
	private void addMessagesPad() {
		jlbMessages = new JLabel("Output messages.");
		jlbMessages.setHorizontalTextPosition(JLabel.LEFT);
	}
	
    public static void main(String[] args) {
    	JFrame jfDummyCalculator = new Calculator();
    } 
}

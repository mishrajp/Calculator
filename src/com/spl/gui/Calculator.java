package com.spl.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

import com.spl.input.InputManager;
import com.spl.model.IModel;
import com.spl.model.Model;
import com.spl.model.ModelObserver;
import com.spl.properties.ConfigReader;

public class Calculator implements ModelObserver {
	
	private class MyDispatcher implements KeyEventDispatcher { 
	    @Override 
	    public boolean dispatchKeyEvent(KeyEvent e) { 
	    	return false;
	    } 
	} 
		
	private final boolean FLAG_CHILDREN = false;
	
	private final int gridNumbersX = 3;
	private final int gridNumbersY = 4;
	private final int gridOperatorsX = 3;
	private final int gridOperatorsY = 0;
	
	private final String THEME_PROPERTIES_FILE = "theme";
	private final String TRANSPARENT_BG = "pixel_trans.png";
	
	private static final String UNDO_BUTTON = "UNDO";
	private static final String CLEAR_BUTTON = "CLEAR";
	private static final String EQUAL_BUTTON = "=";
	private static final char DEL_CHAR = '\b';
	private static final char ENTER_CHAR = '\n';
	private static final char EQUAL_CHAR = '=';
	
	private ImagePanel pane, jplNumbers, jplOperators;
	private JTextField jtfOutput1, jtfOutput2;
		
	private IModel model;
	private InputManager inputMgr;
	
	private JFrame frame;
	private Theme theme;

	public Calculator() {
		// 1. Subscribes to the model (model-observer pattern)
		model = Model.getInstance();
		model.subscribe(this);
		
		// 2. Connects with the input manager
		inputMgr = new InputManager();
		inputMgr.load_valid_operators();
		
		// 3. Loads the theme configuration
		loadThemeConfiguration();
		
		// 4. Creates and sets up the window
        frame = new JFrame("SPL Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width/4, screenSize.height/4);
        frame.setResizable(false);
		
		// 5. Sets up the content pane
        addComponents();
		
		// 6. Displays the window
        frame.setVisible(true);
	}
	
	public void loadThemeConfiguration() {
		ConfigReader configReader = new ConfigReader(THEME_PROPERTIES_FILE);
		String inputMethod = configReader.getProperty("input_method");
		String outputMode = configReader.getProperty("output_mode");
		String bgImage = configReader.getProperty("bg_image");
		String bgColor = configReader.getProperty("bg_color");
		String fgBut = configReader.getProperty("fg_button");
		String bgDis = configReader.getProperty("bg_display");
		String fgDis = configReader.getProperty("fg_display");
		
		
		theme = new Theme(inputMethod, outputMode, bgImage,
				bgColor, fgBut, bgDis, fgDis);
	}
	
	public void addComponents() {		
		// 1. Sets the theme of the calculator
		setTheme();
		
		// 2. Creates the variable content of the pads
		addOutputPad();
		addNumericPad();
		addOperatorsPad();
		
		// 3. Place components attending to their bounds
		if (FLAG_CHILDREN) {
			frame.setSize(410, 475);
			pane = new ImagePanel(theme.getBgImage());
			jtfOutput1.setBounds(50,95,305,25);
			jplNumbers.setBounds(50,150,150,150);
			jplOperators.setBounds(205,150,150,175);
		}
		else {
			frame.setSize(315,300);
			pane = new ImagePanel(null);
			jtfOutput1.setBounds(5,5,305,25);
			if (theme.getOutputMode().equals(theme.OM_TEXT_2ROWS)) {
				jtfOutput2.setBounds(5,35,305,25);
				pane.add(jtfOutput2);
			}
			jplNumbers.setBounds(5,70,150,150);
			jplOperators.setBounds(160,70,150,175);
		}
		pane.add(jtfOutput1);
		pane.add(jplNumbers);
		pane.add(jplOperators);
		pane.setLayout(null);

		// 4. Adds the structure to the main frame
		frame.add(pane);
	}

	private void setTheme() {
		// VARIABILITIES
		// 1.- BG COLOR
		UIManager.put("Panel.background",new ColorUIResource(Integer.parseInt(theme.getBgColor(), 16)));
		// 2.- BUTTON FOREGROUND
		UIManager.put("Button.foreground",new ColorUIResource(Integer.parseInt(theme.getFgButton(), 16)));
		// 3.- DISPLAY BACKGROUND
		UIManager.put("TextField.background",new ColorUIResource(Integer.parseInt(theme.getBgDisplay(), 16)));
		// 4.- DISPLAY FOREGROUND
		UIManager.put("TextField.foreground",new ColorUIResource(Integer.parseInt(theme.getFgDisplay(), 16)));
	}
	
	private void addOutputPad() {		
		// 1.- COMMONALITY: output display -> 1 row
		jtfOutput1 = new JTextField("0");
		jtfOutput1.setHorizontalAlignment(JTextField.RIGHT);
		jtfOutput1.setEditable(false);
		
		// 2.- VARIABILITY POINT: output display -> 2 rows
		if (theme.getOutputMode().equals(theme.OM_TEXT_2ROWS)) {
			jtfOutput2 = new JTextField();
			jtfOutput2.setHorizontalAlignment(JTextField.RIGHT);
			jtfOutput2.setEditable(false);
		}
		
		// 3.- Sets the key listener of the calculator
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager(); 
	    manager.addKeyEventDispatcher(new MyDispatcher() {
		    public boolean dispatchKeyEvent(KeyEvent e) { 
		    	if (e.getID() == KeyEvent.KEY_PRESSED) { 
			    	char keyCode = e.getKeyChar();
					keyStroke(keyCode); 
		        }
		    	
		        return false; 
		    }
	    }); 	
	}

	private void addNumericPad() {
		jplNumbers = new ImagePanel(TRANSPARENT_BG);
		jplNumbers.setLayout(new GridLayout(gridNumbersY,gridNumbersX));
		ArrayList<String> arrayOfButtonTags = new ArrayList<String>(); 
		
		// 1.- Fills the array of button tags				
		// 1.1.- COMMONALIY: Fixed numbers
		for (int i = 9; i >= 0; i --) {
			arrayOfButtonTags.add(String.valueOf(i));
		}
		// 1.2.- VARIABILITY POINT, punctuation: Extra symbols (if any, ie '.')
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
	}
	
	private void addOperatorsPad() {
		jplOperators = new ImagePanel(TRANSPARENT_BG);
		jplOperators.setLayout(new GridLayout(gridOperatorsY, gridOperatorsX));
		ArrayList<String> arrayOfButtonTags = new ArrayList<String>(); 
		
		// 1.- Fills the array of button tags				
		// 1.1.- COMMONALIY: buttons UNDO, CLEAR and =
		arrayOfButtonTags.add(UNDO_BUTTON);
		arrayOfButtonTags.add(CLEAR_BUTTON);
		arrayOfButtonTags.add(EQUAL_BUTTON);
			
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
	}
	
	private void buttonPressed(String buttonLabel) {
		char c;
				
		if (buttonLabel.equals(UNDO_BUTTON)) {
			inputMgr.clear();
		}
		else if (buttonLabel.equals(CLEAR_BUTTON) ){
			inputMgr.clearall();
			jtfOutput1.setText("0");
		}
		else {
			c = buttonLabel.charAt(0);
			inputMgr.addSymbol(c);
		}
	}
	
	private void keyStroke(char k) {
System.out.print(k);
		if (k == DEL_CHAR) {
			inputMgr.clear();
		}
		else if (k == ENTER_CHAR) {
			inputMgr.addSymbol(EQUAL_CHAR);
		}
		else {
			inputMgr.addSymbol(k);
		}
	}
	
	@Override
	public void notifyObserver(String output) {
		if (output.equals(Model.RESULT)) {
			if (theme.getOutputMode().equals(theme.OM_TEXT_1ROW)) {
				jtfOutput1.setText(model.getResult());
			}
			else if (theme.getOutputMode().equals(theme.OM_TEXT_2ROWS)) {
				jtfOutput2.setText(model.getResult());
			}
			
		}
		else if (output.equals(Model.STATEMENT)){
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
		
		// 2.- Instances the calculator
    	Calculator jfDummyCalculator = new Calculator();
    }


}

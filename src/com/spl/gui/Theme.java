package com.spl.gui;

public class Theme {
	public final String OM_TEXT_1ROW = "text1row";
	public final String OM_TEXT_2ROWS = "text2rows";
	public final String IM_TEXT = "text";
	public final String IM_BUTTONS = "buttons";
	
	private String input_method;
	private String output_mode;
	private String bg_image;
	private String bg_color;
	private String fg_button;
	private String bg_display;
	private String fg_display;
	
	public Theme(String iM, String oM, String img, String bgColor, 
			String fgBut, String bgDis, String fgDis) {
		input_method = iM;
		output_mode = oM;
		bg_image = img;
		bg_color= bgColor;
		fg_button = fgBut;
		bg_display = bgDis;
		fg_display = fgDis;
	}
	
	public void setIputMethod(String iM) {
		input_method = iM;
	}
	
	public String getInputMethod() {
		return input_method;
	}
	
	public void setOutputMode(String oM) {
		output_mode = oM;
	}
	
	public String getOutputMode() {
		return output_mode;
	}
	
	public void setBgImage(String img) {
		bg_image = img;
	}
	
	public String getBgImage() {
		return bg_image;
	}
	
	public void setBgColor(String col) {
		bg_color = col;
	}
	
	public String getBgColor() {
		return bg_color;
	}
	
	public void setFgButton(String fgBut) {
		fg_button= fgBut;
	}
	
	public String getFgButton() {
		return fg_button;
	}
	
	public void setFgDisplay(String fgDisplay) {
		fg_display = fgDisplay;
	}
	
	public String getFgDisplay() {
		return fg_display;
	}
	
	public void setBgDisplay(String bgDisplay) {
		bg_display = bgDisplay;
	}
	
	public String getBgDisplay() {
		return bg_display;
	}
	
}

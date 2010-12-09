package com.spl.gui;

public class Theme {
	public final String OM_TEXT_1ROW = "text1row";
	public final String OM_TEXT_2ROWS = "text2rows";
	public final String IM_TEXT = "text";
	public final String IM_BUTTONS = "buttons";
	
	private String inputMethod;
	private String outputMode;
	private String bgImage;
	
	public Theme(String iM, String oM, String img) {
		inputMethod = iM;
		outputMode = oM;
		bgImage = img;
	}
	
	public void setIputMethod(String iM) {
		inputMethod = iM;
	}
	
	public String getInputMethod() {
		return inputMethod;
	}
	
	public void setOutputMode(String oM) {
		outputMode = oM;
	}
	
	public String getOutputMode() {
		return outputMode;
	}
	
	public void setBgImage(String img) {
		bgImage = img;
	}
	
	public String getBgImage() {
		return bgImage;
	}
}

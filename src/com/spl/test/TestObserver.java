package com.spl.test;

import com.spl.model.IModel;
import com.spl.model.Model;
import com.spl.model.ModelObserver;

public class TestObserver implements ModelObserver {
	public static void main(String[] args) {
		IModel model = Model.getInstance();
		TestObserver testObserver = new TestObserver();
		model.subscribe(testObserver);
		model.setResult("Martininho");
	}
	
	
	@Override
	public void notifyObserver(String output) {
		System.out.println(Model.getInstance().getResult());
	}

}

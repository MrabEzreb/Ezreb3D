package com.ezreb.ezreb3D;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {
	public static Frame create() {
		windo.setResizable(false);
		windo.setSize(300, 300);
		windo.addWindowListener(l);
		return windo;
	}
	static Frame windo = new Frame("Ezreb3D");
	static WindowAdapter l = new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent we) {
			windo.dispose();
			System.exit(0);
		}
	};
}

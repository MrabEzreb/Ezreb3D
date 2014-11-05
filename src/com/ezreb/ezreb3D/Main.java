package com.ezreb.ezreb3D;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Frame grid = Window.create();
		grid.setSize(150*2,150*2);
		grid.setVisible(true);
		Graphics2D graph = (Graphics2D) grid.getGraphics();
		Color c = new Color(0);
		graph.setColor(c);
		graph.drawString("Test", 50, 50);
		graph.drawRect(50, 50, 100, 100);
		Thread.sleep(2000);
		graph.clearRect(50, 40, 105, 120);
		graph.drawRect(50, 50, 100, 30);
		graph.drawRect(50, 80, 100, 70);
		Thread.sleep(2000);
		graph.clearRect(50, 40, 105, 200);
		graph.drawRect(50, 40, 100, 50);
		graph.drawRect(50, 90, 100, 60);
	}
}

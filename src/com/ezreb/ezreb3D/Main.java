package com.ezreb.ezreb3D;

import com.ezreb.ezreb3D.grid.Grid;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Grid g = new Grid(new XYZPoint(0, 0, 0), 100, "Number One");
		g.viewGrid();
	}
}

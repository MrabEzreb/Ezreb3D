package com.ezreb.ezreb3D.grid;

import java.awt.Frame;
import java.awt.Graphics2D;

import com.ezreb.ezreb3D.Cube;
import com.ezreb.ezreb3D.Window;
import com.ezreb.ezreb3D.XYZPoint;

public class Grid extends Cube {
	public Grid(XYZPoint point2, int gridSize, String name) {
		super(point2, "Grid_"+name);
		size = gridSize;
	}
	int size;
	String[][][][] grid = new String[8][size][size][size];
	public void viewGrid() {
		Frame grid = Window.create();
		grid.setSize(size*2,size*2);
		grid.setVisible(true);
		Graphics2D graph = (Graphics2D) grid.getGraphics();
		
	}
	public XYZPoint getCoords(XYZPoint point3d) {
		
	}
	public void addCube(Cube cube, XYZPoint point) {
		for(int x = 0; x <= cube.getxLength(); x++) {
			for(int y = 0; y <= cube.getyLength(); y++) {
				for(int z = 0; z <= cube.getzLength(); z++) {
					int x1 = point.getX()+x;
					int y1 = point.getY()+y;
					int z1 = point.getZ()+z;
					XYZPoint newPoint = new XYZPoint(x1, y1, z1);
					this.addPoint(newPoint, cube.getName());
				}
			}
		}
	}
	public void addPoint(XYZPoint newPoint, String name) {
		int x = newPoint.getX();
		int y = newPoint.getY();
		int z = newPoint.getZ();
		if(Math.abs(x)==x) {
			if(Math.abs(y)==y) {
				if(Math.abs(z)==z) {
					grid[1][x][y][z] = name;
				} else {
					grid[5][x][y][z] = name;
				}
			} else {
				if(Math.abs(z)==z) {
					grid[4][x][y][z] = name;
				} else {
					grid[8][x][y][z] = name;
				}
			}
		} else {
			if(Math.abs(y)==y) {
				if(Math.abs(z)==z) {
					grid[2][x][y][z] = name;
				} else {
					grid[6][x][y][z] = name;
				}
			} else {
				if(Math.abs(z)==z) {
					grid[3][x][y][z] = name;
				} else {
					grid[7][x][y][z] = name;
				}
			}
		}
	}
}

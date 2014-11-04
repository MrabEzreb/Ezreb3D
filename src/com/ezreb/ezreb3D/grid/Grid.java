package com.ezreb.ezreb3D.grid;

import com.ezreb.ezreb3D.Cube;
import com.ezreb.ezreb3D.XYZPoint;

public class Grid extends Cube {
	public Grid(XYZPoint point2, int gridSize, String name) {
		super(point2, "Grid_"+name);
		size = gridSize;
	}
	int size;
	String[][][][] grid = new String[8][size][size][size];
	
	public void addCube(Cube cube, XYZPoint point) {
		for(int x = 0; x <= cube.getxLength(); x++) {
			for(int y = 0; y <= cube.getyLength(); y++) {
				for(int z = 0; z <= cube.getzLength(); z++) {
					int x = point.getX()+x;
					XYZPoint newPoint = new XYZPoint(x, y, z);
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

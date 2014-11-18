package com.ezreb.ezreb3D.grid;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Polygon;

import aleksPack10.tools.Colors;

import com.ezreb.ezreb3D.Camera;
import com.ezreb.ezreb3D.Cube;
import com.ezreb.ezreb3D.Window;
import com.ezreb.ezreb3D.XYZPoint;

public class Grid extends Cube {
	public Grid(XYZPoint point2, int gridSize, String name) {
		super(point2, "Grid_"+name);
		size = gridSize;
		grid = new Cube[8][size][size][size];
	}
	public int size;
	public Cube[][][][] grid;
	public void viewGrid() throws InterruptedException {
		Frame grid = Window.create();
		grid.setSize((size*2)+6,(size*2)+29);
		grid.setVisible(true);
		Graphics2D graph = (Graphics2D) grid.getGraphics();
		graph.drawRect(0, 0, 5, 5);
		for (int i = -150; i < 151; i = i+4) {
			Thread.sleep(200);
			graph.clearRect(0,0,size*2+6,size*2+28);
			Camera c = new Camera(this, new XYZPoint(i, 50, 50), new XYZPoint(0, 0, 0));
			//Cube c2 = new Cube(new XYZPoint(75,75,50), "Cube1");
			//this.addCube(c2, new XYZPoint(0,0,0));
			//Polygon shape1 = c.getCubeShapes(c2);
			XYZPoint xp = c.getPoint(new XYZPoint(size,0,0));
			System.out.println("xp="+xp.getX()+","+xp.getY()+","+xp.getZ());
			XYZPoint xn = c.getPoint(new XYZPoint(0-size,0,0));
			xn.setAll(-xn.getX()+size*2+6, -xn.getY()+size*2+50, xn.getZ());
			System.out.println("xn="+xn.getX()+","+xn.getY()+","+xn.getZ());
			XYZPoint yp = c.getPoint(new XYZPoint(0,size,0));
			XYZPoint yn = c.getPoint(new XYZPoint(0,0-size,0));
			yn.setAll(-yn.getX()+size*2+6, -yn.getY()+size*2+50, yn.getZ());
			XYZPoint zp = c.getPoint(new XYZPoint(0,0,size-(i/2)));
			XYZPoint zn = c.getPoint(new XYZPoint(0,0,-size-(i/2)));
			zn.setAll(-zn.getX()+size*2+6, -zn.getY()+size*2+50, zn.getZ());
			graph.setColor(Colors.blue2);
			graph.drawLine(xp.getX(), xp.getY(), xn.getX(), xn.getY());
			graph.setColor(Colors.orange5);
			graph.drawLine(yp.getX(), yp.getY(), yn.getX(), yn.getY());
			graph.setColor(Colors.pink0);
			graph.drawLine(zp.getX(), zp.getY(), zn.getX(), zn.getY());
			graph.setColor(Colors.lightBlue0);
			graph.drawString("+x", xp.getX(), xp.getY());
			graph.drawString("-x", xn.getX(), xn.getY());
			graph.drawString("+y", yp.getX(), yp.getY());
			graph.drawString("-y", yn.getX(), yn.getY());
			graph.drawString("+z", zp.getX(), zp.getY());
			graph.drawString("-z", zn.getX(), zn.getY());
		}
		/*graph.setColor(Colors.brown0);
		graph.fillPolygon(shape1);
		graph.setColor(Colors.green1);
		graph.drawPolygon(shape1);*/
		//Thread.sleep(2000);
		//graph.clearRect(0, 0, 300, 300);
//		for (int i = 0; i < 45; i++) {
//			c = new Camera(this, new XYZPoint(150-i, 150, 150), new XYZPoint(i, 0, 0));
//			c2 = new Cube(new XYZPoint(50,50,50), "Cube1");
//			this.addCube(c2, new XYZPoint(0,0,0));
//			shape1 = c.getCubeShapes(c2);
//			graph.setColor(Colors.brown0);
//			graph.fillPolygon(shape1);
//			graph.setColor(Colors.green1);
//			graph.drawPolygon(shape1);
//			graph.drawLine(xp.getX(), xp.getY(), xn.getX(), xn.getY());
//			graph.drawLine(yp.getX(), yp.getY(), yn.getX(), yn.getY());
//			Thread.sleep(50);
//			graph.clearRect(0, 0, 300, 300);
//		}
	}
	public XYZPoint getCoords(XYZPoint point3d, String direction) {
		XYZPoint retVal = new XYZPoint();
		int xVal=0;
		int yVal=0;
		int zVal=0;
		if(direction=="z") {
			int x = point3d.getX();
			int y = point3d.getY();
			int z = point3d.getZ();
			if(Math.abs(x)==x) {
				if(Math.abs(y)==y) {
					if(Math.abs(z)==z) {
						xVal = x+size;
						yVal = y+size;
						zVal = z+size;
					} else {
						xVal = x+size;
						yVal = y+size;
						zVal = size-z;
					}
				} else {
					if(Math.abs(z)==z) {
						xVal = x+size;
						yVal = size-y;
						zVal = z+size;
					} else {
						xVal = x+size;
						yVal = size-y;
						zVal = size-z;
					}
				}
			} else {
				if(Math.abs(y)==y) {
					if(Math.abs(z)==z) {
						xVal = size-x;
						yVal = size+y;
						zVal = size+z;
					} else {
						xVal = size-x;
						yVal = size+y;
						zVal = size-z;
					}
				} else {
					if(Math.abs(z)==z) {
						xVal = size-x;
						yVal = size-y;
						zVal = size+z;
					} else {
						xVal = size-x;
						yVal = size-y;
						zVal = size-z;
					}
				}
			}
		}
		retVal.setAll(xVal, yVal, zVal);
		return retVal;
	}
	public void addCube(Cube cube, XYZPoint point) {
		int x = point.getX();
		int y = point.getY();
		int z = point.getZ();
		int q = 0;
		if(Math.abs(x)==x) {
			if(Math.abs(y)==y) {
				if(Math.abs(z)==z) {
					q = 1;
				} else {
					q = 5;
				}
			} else {
				if(Math.abs(z)==z) {
					q = 4;
				} else {
					q = 8;
				}
			}
		} else {
			if(Math.abs(y)==y) {
				if(Math.abs(z)==z) {
					q = 2;
				} else {
					q = 6;
				}
			} else {
				if(Math.abs(z)==z) {
					q = 3;
				} else {
					q = 7;
				}
			}
		}
		q = q-1;
		grid[q][x][y][z] = cube;
	}
}

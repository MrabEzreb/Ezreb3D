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
	public Grid(XYZPoint point1, XYZPoint point2, int gridSize, String name, int windowsize) {
		super(point1, point2, "Grid_"+name);
		size = gridSize;
		grid = new Cube[8][size][size][size];
		this.wsize = windowsize;
	}
	public int size;
	public int wsize;
	public Cube[][][][] grid;
	public void viewGrid() throws InterruptedException {
		Frame grid = Window.create();
		grid.setSize((wsize*2)+6,(wsize*2)+29);
		grid.setVisible(true);
		Graphics2D graph = (Graphics2D) grid.getGraphics();
		graph.drawRect(0, 0, 5, 5);
		for (int i = 0; i < 361; i = i+2) {
			if(i==0) {
				continue;
			} else if(i==1) {
				continue;
			}
			System.out.println(i+"rot");
			Camera c = new Camera(this, new XYZPoint(100, 100, 100), new XYZPoint(0, 0, 0), new XYZPoint(0, 0, 0));
			Cube c2 = new Cube(new XYZPoint(0, 0, 0), new XYZPoint(75,75,50), "Cube1");
			//this.addCube(c2, new XYZPoint(0,0,0));
			Polygon[] shape1 = c.getCubeShapes(c2);
			Cube c1 = new Cube(new XYZPoint(0, 0, 0), new XYZPoint(-10, -10, -10), "Cube 1");
			//this.addCube(c1, new XYZPoint(0, 0, 0));
			Polygon[] p1 = c.getCubeShapes(c1);
			int size = this.size;
			XYZPoint xp = c.getPoint(new XYZPoint(size,0,0));
			//System.out.println("xp="+xp.getX()+","+xp.getY()+","+xp.getZ());
			XYZPoint xn = c.getPoint(new XYZPoint(0-size,0,0));
			//System.out.println("xn="+xn.getX()+","+xn.getY()+","+xn.getZ());
			XYZPoint yp = c.getPoint(new XYZPoint(0,size,0));
			XYZPoint yn = c.getPoint(new XYZPoint(0,0-size,0));
			XYZPoint zp = c.getPoint(new XYZPoint(0, 0, size));
			XYZPoint zn = c.getPoint(new XYZPoint(0, 0, -size));
			XYZPoint origin = c.getPoint(new XYZPoint(0,0,0));
			Thread.sleep(50);
			graph.clearRect(0,0,wsize*2+6,wsize*2+28);
			graph.setColor(Colors.green1);
			//this.fill3DPolygon(p1, graph);
			//this.fill3DPolygon(shape1, graph);
			graph.setColor(Colors.brown0);
			//this.draw3DPolygon(shape1, graph);
			//this.draw3DPolygon(p1, graph);
			graph.setColor(Colors.blue2);
			graph.drawLine(xp.getX(), xp.getY(), origin.getX(), origin.getY());
			graph.drawLine(xn.getX(), xn.getY(), origin.getX(), origin.getY());
			graph.drawLine(0, wsize+25, wsize*2+3, wsize+25);
			graph.setColor(Colors.orange5);
			graph.drawLine(yp.getX(), yp.getY(), origin.getX(), origin.getY());
			graph.drawLine(yn.getX(), yn.getY(), origin.getX(), origin.getY());
			graph.drawLine(wsize+3, 0+25, wsize+3, wsize*2+25);
			graph.setColor(Colors.pink0);
			graph.drawLine(zp.getX(), zp.getY(), origin.getX(), origin.getY());
			graph.drawLine(zn.getX(), zn.getY(), origin.getX(), origin.getY());
			graph.setColor(Colors.lightBlue0);
			graph.drawString("+x", xp.getX(), xp.getY());
			graph.drawString("-x", xn.getX(), xn.getY());
			graph.drawString("+y", yp.getX(), yp.getY()+10);
			graph.drawString("-y", yn.getX(), yn.getY()-3);
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
	private void fill3DPolygon(Polygon[] ps, Graphics2D g) {
		for (Polygon polygon : ps) {
			g.fillPolygon(polygon);
		}
	}
	private void draw3DPolygon(Polygon[] ps, Graphics2D g) {
		for (Polygon polygon : ps) {
			g.drawPolygon(polygon);
		}
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
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);
		grid[q][x][y][z] = cube;
	}
}

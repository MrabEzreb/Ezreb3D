package com.ezreb.ezreb3D;

import java.awt.Polygon;

import com.ezreb.ezreb3D.grid.Grid;

public class Camera extends XYZPoint {

	public Camera(Grid grid, XYZPoint camPos, XYZPoint camRot) {
		this.camPos = camPos;
		this.camRot = new XYZPoint(camRot.getX(), camRot.getY(), camRot.getZ());
		this.grid = grid;
		int x = camPos.getX();
		int y = camPos.getY();
		int z = camPos.getZ();
		System.out.println("Created New Camera For Grid "+grid.getName()+" at coordinates "+x+","+y+","+z);
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
		this.posQuadrant = q;
		x = -(camRot.getX());
		y = -(camRot.getY());
		z = -(camRot.getZ());
		q = 0;
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
		if(x==0) {
			
		}
		this.rotQuadrant = q;
	}
	Grid grid;
	XYZPoint camPos;
	XYZPoint camRot;
	int posQuadrant;
	int rotQuadrant;
	boolean[] axisRot = new boolean[6];
	public XYZPoint getPoint(XYZPoint point) {
		int x = point.getX()-this.camPos.getX();
		if (Math.abs(x)!=x) {
			x = -x;
		}
		int y = point.getY()-this.camPos.getY();
		if (Math.abs(y)!=y) {
			y = -y;
		}
		int z = point.getZ()-this.camPos.getZ();
		if (Math.abs(z)!=z) {
			z = -z;
		}
		System.out.println(x+","+y+","+z);
		int ex = -this.camPos.getX();
		int ey = -this.camPos.getY();
		int ez = -this.camPos.getZ();
		//int ex = (int) Math.floor(Math.sqrt(Math.pow(this.camPos.getX(), 2)+Math.pow(this.camPos.getY(), 2)+Math.pow(this.camPos.getZ(), 2)));
		//int ey = (int) Math.floor(Math.sqrt(Math.pow(this.camPos.getX(), 2)+Math.pow(this.camPos.getY(), 2)+Math.pow(this.camPos.getZ(), 2)));
		//int ez = (int) Math.floor(Math.sqrt(Math.pow(this.camPos.getX(), 2)+Math.pow(this.camPos.getY(), 2)+Math.pow(this.camPos.getZ(), 2)));
		double cx = Math.cos(0-this.camRot.X);
		double cy = Math.cos(0-this.camRot.Y);
		double cz = Math.cos(0-this.camRot.Z);
		double sx = Math.sin(0-this.camRot.X);
		double sy = Math.sin(0-this.camRot.Y);
		double sz = Math.sin(0-this.camRot.Z);
		double dx = cy*((sz*y)+(cz*x))-(sy*z);
		double dy = sx*((cy*z)+(sy*((sz*y)+(cz*x))))+(cx*((cz*y)-(sz*x)));
		double dz = cx*((cy*z)+(sy*((sz*y)+(cz*x))))-(sx*((cz*y)-(sz*x)));
		int bx = (int) Math.floor(((ez/dz)*dx)-ex);
		int by = (int) Math.floor(((ez/dz)*dy)-ey);
		XYZPoint retVal = new XYZPoint(bx+grid.size+3, by+grid.size+25, 0);
		return retVal;
	}
	public Polygon getCubeShapes(Cube c) {
		Polygon retVal;
		XYZPoint[][] xyzs1 = new XYZPoint[6][4];
		int[] xes = new int[24];
		int[] ys = new int[24];
		int currentPoint = 0;
		//Boolean[] sides = new Boolean[6];
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 5; j++) {
				xyzs1[i-1][j-1] = c.allPoints[i][j];
				currentPoint++;
			}
		}
		currentPoint = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
			xes[currentPoint] = this.getPoint(xyzs1[i][j]).getX()+50;
			System.out.println(xes[currentPoint]+"x");
			ys[currentPoint] = this.getPoint(xyzs1[i][j]).getY()+50;
			System.out.println(ys[currentPoint]+"y");
			currentPoint++;
			}
		}
		retVal = new Polygon(xes, ys, 24);
		return retVal;
	}
	
}

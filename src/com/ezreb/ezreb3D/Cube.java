package com.ezreb.ezreb3D;


public class Cube {
	public Cube(XYZPoint point2, String name) {
		firstPoint = new XYZPoint(0,0,0);
		secondPoint = point2;
		xLength = -(firstPoint.X-point2.X);
		yLength = -(firstPoint.Y-point2.Y);
		zLength = -(firstPoint.Z-point2.Z);
		this.name = name;
		XYZPoint[][] p = new XYZPoint[7][5];
		p[1][1] = firstPoint;
		p[1][2] = new XYZPoint(xLength, 0, 0);
		p[1][3] = new XYZPoint(xLength, yLength, 0);
		p[1][4] = new XYZPoint(0, yLength, 0);
		p[2][1] = firstPoint;
		p[2][2] = new XYZPoint(0, 0, zLength);
		p[2][3] = new XYZPoint(0, yLength, zLength);
		p[2][4] = new XYZPoint(0, yLength, 0);
		p[3][1] = firstPoint;
		p[3][2] = new XYZPoint(xLength, 0, 0);
		p[3][3] = new XYZPoint(xLength, 0, zLength);
		p[3][4] = new XYZPoint(0, 0, zLength);
		p[4][1] = secondPoint;
		p[4][2] = new XYZPoint(xLength, 0, zLength);
		p[4][3] = new XYZPoint(xLength, yLength, zLength);
		p[4][4] = new XYZPoint(0, yLength, zLength);
		p[5][1] = secondPoint;
		p[5][2] = new XYZPoint(xLength, 0, zLength);
		p[5][3] = new XYZPoint(xLength, yLength, zLength);
		p[5][4] = new XYZPoint(xLength, yLength, 0);
		p[6][1] = secondPoint;
		p[6][2] = new XYZPoint(xLength, yLength, 0);
		p[6][3] = new XYZPoint(xLength, yLength, zLength);
		p[6][4] = new XYZPoint(0, yLength, zLength);
		int x = xLength;
		int y = yLength;
		int z = zLength;
		int q;
		if(Math.abs(x)==x) {
			if(Math.abs(y)==y) {
				if(Math.abs(z)==z) {
					q = 7;
				} else {
					q = 3;
				}
			} else {
				if(Math.abs(z)==z) {
					q = 6;
				} else {
					q = 2;
				}
			}
		} else {
			if(Math.abs(y)==y) {
				if(Math.abs(z)==z) {
					q = 8;
				} else {
					q = 4;
				}
			} else {
				if(Math.abs(z)==z) {
					q = 5;
				} else {
					q = 1;
				}
			}
		}
		this.originQuadrant = q;
		this.allPoints = p;
	}
	int originQuadrant;
	XYZPoint firstPoint;
	XYZPoint secondPoint;
	XYZPoint[][] allPoints = new XYZPoint[7][5];
	int xLength;
	int yLength;
	int zLength;
	String name;
	public int getxLength() {
		return xLength;
	}
	public int getyLength() {
		return yLength;
	}
	public int getzLength() {
		return zLength;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
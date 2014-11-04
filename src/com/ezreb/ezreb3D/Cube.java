package com.ezreb.ezreb3D;


public class Cube {
	public Cube(XYZPoint point2, String name) {
		firstPoint = new XYZPoint(0,0,0);
		secondPoint = point2;
		xLength = firstPoint.X-point2.X;
		yLength = firstPoint.Y-point2.Y;
		zLength = firstPoint.Z-point2.Z;
		this.name = name;
	}
	XYZPoint firstPoint;
	XYZPoint secondPoint;
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
	public void setxLength(int xLength) {
		this.xLength = xLength;
	}
	public void setyLength(int yLength) {
		this.yLength = yLength;
	}
	public void setzLength(int zLength) {
		this.zLength = zLength;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
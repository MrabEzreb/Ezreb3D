package com.ezreb.ezreb3D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Line {

	public Line(XYZPoint p1, XYZPoint p2) {
		this(p1.X, p1.Y, p1.Z, p2.X, p2.Y, p2.Z);
	}
	public Line(int x, int y, int x2, int y2) {
		this(x, y, 0, x2, y2, 0);
	}
	public Line(int x, int y, int z, int x2, int y2, int z2) {
		this.p1 = new XYZPoint(x, y, z);
		this.p2 = new XYZPoint(x2, y2, z2);
	}
	public XYZPoint p1;
	public XYZPoint p2;
	public void drawLine(Graphics2D graph, Color color) {
		Color oldColor = graph.getColor();
		graph.setColor(color);
		graph.drawLine(p1.X, p1.Y, p2.X, p2.Y);
		graph.setColor(oldColor);
	}
	public void to2D(Camera c) {
		Line replacer = c.getLine(this);
		this.p1 = replacer.p1;
		this.p2 = replacer.p2;
	}
	public Polygon getRender(Camera c) {
		Polygon retVal;
		int[] xes = {this.p1.X,this.p2.X};
		int[] yes = {this.p1.Y,this.p2.Y};
		retVal = new Polygon(xes, yes, 2);
		return retVal;
	}

}

package com.ezreb.ezreb3D;

import java.awt.Polygon;

public class Square {

	public Square(XYZPoint p1, XYZPoint p2) {
		this.p1 = new XYZPoint(p1.X, p1.Y, p1.Z);
		this.p2 = new XYZPoint(p2.X, p2.Y, p2.Z);
		p1 = this.p1;
		p2 = this.p2;
		if(p1.X==p2.X) {
			this.p15 = new XYZPoint(p1.X, p1.Y, p2.Z);
			this.p25 = new XYZPoint(p1.X, p2.Y, p1.Z);
		} else if(p1.Y==p2.Y) {
			this.p15 = new XYZPoint(p1.X, p1.Y, p2.Z);
			this.p25 = new XYZPoint(p2.X, p1.Y, p1.Z);
		} else if(p1.Z==p2.Z) {
			this.p15 = new XYZPoint(p2.X, p1.Y, p1.Z);
			this.p25 = new XYZPoint(p1.X, p2.Y, p1.Z);
		} else {
			this.p15 = p1;
			this.p25 = p2;
		}
	}
	public XYZPoint p1;
	public XYZPoint p2;
	public XYZPoint p15;
	public XYZPoint p25;
	public Polygon getRender(Camera c) {
		Polygon retVal;
		XYZPoint[] zes = new XYZPoint[4];
		int[] xes = new int[4];
		int[] yes = new int[4];
		zes[0] = c.getPoint(p1);
		zes[1] = c.getPoint(p15);
		zes[2] = c.getPoint(p2);
		zes[3] = c.getPoint(p25);
		xes[0] = zes[0].getX();
		yes[0] = zes[0].getY();
		xes[1] = zes[1].getX();
		yes[1] = zes[1].getY();
		xes[2] = zes[2].getX();
		yes[2] = zes[2].getY();
		xes[3] = zes[3].getX();
		yes[3] = zes[3].getY();
		retVal = new Polygon(xes, yes, 4);
		return retVal;
	}

}

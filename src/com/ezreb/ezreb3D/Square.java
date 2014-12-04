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
		Line[] sides = new Line[4];
		sides[0] = new Line(p1, p15);
		sides[1] = new Line(p15, p2);
		sides[2] = new Line(p2, p25);
		sides[3] = new Line(p25, p1);
		Polygon[] zes = new Polygon[4];
		for (Line line : sides) {
			line.to2D(c);
		}
		for (int i = 0; i < zes.length; i++) {
			zes[i] = sides[i].getRender(c);
		}
		int[] xes = new int[4];
		int[] yes = new int[4];
		for (int i = 0; i < 4; i++) {
			if(i==0) {
				xes[0] = zes[0].xpoints[0];
				yes[0] = zes[0].ypoints[0];
			} else {
				for (int j = 0; j < 2; j++) {
					if(zes[i].xpoints[j]==zes[i-1].xpoints[j]) {
						xes[i] = zes[i].xpoints[j];
					}
				}
			}
		}
		retVal = new Polygon(xes, yes, 4);
		return retVal;
	}

}

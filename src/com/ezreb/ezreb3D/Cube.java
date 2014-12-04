/**
 * 
 */
package com.ezreb.ezreb3D;

import java.awt.Polygon;
import java.util.Scanner;

import org.json.JSONObject;

/**
 * @author bram.zerbe
 *
 */
public class Cube {

	/**
	 * 
	 */
	public Cube(XYZPoint p1, int xLength, int yLength, int zLength) {
		XYZPoint p3 = new XYZPoint(p1.X, p1.Y, p1.Z);
		XYZPoint p2 = new XYZPoint(p1.X, p1.Y, p1.Z);
		p2.X = p1.X+xLength;
		p2.Y = p1.Y+yLength;
		//System.out.println(p2.Z);
		this.faces[0] = new Square(p3, p2);
		p2.Z = p1.Z+zLength;
		p3.Z = p1.Z+zLength;
		this.faces[1] = new Square(p3, p2);
		p3 = new XYZPoint(p1.X, p1.Y, p1.Z);
		p2 = new XYZPoint(p1.X, p1.Y, p1.Z);
		if(this.faces[0]==this.faces[1]) {
			System.out.println("It's Broke");
		}
		p2 = new XYZPoint(p1.X, p1.Y+yLength, p1.Z+zLength);
		p3 = new XYZPoint(p1.X, p1.Y, p1.Z);
		this.faces[2] = new Square(p3, p2);
		p2.X = p1.X+xLength;
		p3.X = p1.X+xLength;
		this.faces[3] = new Square(p3, p2);
		p2 = new XYZPoint(p1.X+xLength, p1.Y, p1.Z+zLength);
		p3 = new XYZPoint(p1.X, p1.Y, p1.Z);
		if(this.faces[2]==this.faces[3]) {
			System.out.println("It's Broke");
		}
		this.faces[4] = new Square(p3, p2);
		System.out.println(p3.Y+" "+p2.Y+" face 4");
		p2.Y = p1.Y+yLength;
		p3.Y = p1.Y+yLength;
		this.faces[5] = new Square(p3, p2);
		System.out.println(p3.Y+" "+p2.Y+" face 5");
		if(this.faces[4]==this.faces[5]) {
			System.out.println("It's Broke");
		}
		//System.out.println("face 0 p1z2 is "+this.faces[0].p1.Z);
		//System.out.println("face 0 p2z2 is "+this.faces[0].p2.Z);
	}
	Square[] faces = new Square[6];
	public Square[] getVisibleSides(Camera c) {
		Square[] vFaces = new Square[3];
		int[] zFaces = new int[2];
		int[] yFaces = new int[2];
		int[] xFaces = new int[2];
		zFaces[0] = (-c.camPos.getZ()+Math.abs(this.faces[0].p1.getZ())); //0 = 100
		System.out.println(zFaces[0]+"z");
		zFaces[1] = (-c.camPos.getZ()+Math.abs(this.faces[1].p1.getZ())); //10 = 90
		System.out.println(zFaces[1]+"z");
		if(Math.abs(zFaces[0])!=Math.abs(zFaces[1])) {
			zFaces[0] = Math.abs(zFaces[0]);
			zFaces[1] = Math.abs(zFaces[1]);
		}
		xFaces[0] = (-c.camPos.getX()+Math.abs(this.faces[2].p1.getX()));
		System.out.println(xFaces[0]+"x -"+this.faces[2].p1.getX()+"+"+c.camPos.getX());
		xFaces[1] = (-c.camPos.getX()+Math.abs(this.faces[3].p1.getX()));
		System.out.println(xFaces[1]+"x -"+this.faces[3].p1.getX()+"+"+c.camPos.getX());
		if(Math.abs(xFaces[0])!=Math.abs(xFaces[1])) {
			xFaces[0] = Math.abs(xFaces[0]);
			xFaces[1] = Math.abs(xFaces[1]);
		}
		yFaces[0] = (-c.camPos.getY()+Math.abs(this.faces[4].p1.getY()));
		System.out.println(yFaces[0]+"y "+this.faces[4].p1.getY()+"-"+c.camPos.getY());
		yFaces[1] = (-c.camPos.getY()+Math.abs(this.faces[5].p1.getY()));
		System.out.println(yFaces[1]+"y "+this.faces[5].p1.getY()+"-"+c.camPos.getY());
		if(Math.abs(yFaces[0])!=Math.abs(yFaces[1])) {
			yFaces[0] = Math.abs(yFaces[0]);
			yFaces[1] = Math.abs(yFaces[1]);
		}
//		if(zFaces!=null) {
//			System.out.println("it might have worked");
//			System.out.println("zFaces[0]z is "+zFaces[0]);
//			System.out.println("zFaces[1]z is "+zFaces[1]);
//			System.out.println("faces[0]z2 is "+this.faces[0].p1.Z);
//			System.out.println("faces[1]z2 is "+this.faces[1].p1.Z);
//		}
		if(zFaces[0]<zFaces[1]) {
			vFaces[0] = this.faces[0];
			System.out.println("got the first onelessz");
			System.out.println(vFaces[0].p1.Z);
		}
		if(zFaces[0]>zFaces[1]) {
			vFaces[0] = this.faces[1];
			System.out.println("got the first onemorez");
			System.out.println(vFaces[0].p1.Z);
		}
		if(yFaces[0]<yFaces[1]) {
			vFaces[2] = this.faces[4];
			System.out.println("got the first onelessy");
		}
		if(yFaces[0]>yFaces[1]) {
			vFaces[2] = this.faces[5];
			System.out.println("got the first onemorey");
		}
		if(xFaces[0]<xFaces[1]) {
			vFaces[1] = this.faces[2];
			System.out.println("got the first onelessx");
		}
		if(xFaces[0]>xFaces[1]) {
			vFaces[1] = this.faces[3];
			System.out.println("got the first onemorex");
		}
		return vFaces;
	}
	public Polygon[] getRender(Camera c) {
		Square[] visSides = new Square[3];
		Polygon[] retVal = new Polygon[3];
		visSides = this.getVisibleSides(c);
		for (int i = 0; i < 3; i++) {
			retVal[i] = visSides[i].getRender(c);
			System.out.println("Got Render for "+i);
		}
		return retVal;
	}

}

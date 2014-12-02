/**
 * 
 */
package com.ezreb.ezreb3D;

import java.awt.Polygon;
import java.util.Scanner;

/**
 * @author bram.zerbe
 *
 */
public class Cube {

	/**
	 * 
	 */
	public Cube(XYZPoint p1, int xLength, int yLength, int zLength) {
		XYZPoint p3 = p1;
		XYZPoint p2 = new XYZPoint(p1.X, p1.Y, p1.Z);
		p2.X = p1.X+xLength;
		p2.Y = p1.Y+yLength;
		//System.out.println(p2.Z);
		this.faces[0] = new Square(p1, p2);
		p2.Z = p1.Z+zLength;
		p3.Z = p1.Z+zLength;
		this.faces[1] = new Square(p3, p2);
		p2.Z = p1.Z;
		p3.Z = p1.Z;
		if(this.faces[0]==this.faces[1]) {
			System.out.println("It's Broke");
		}
		p2 = new XYZPoint(p1.X, p1.Y+yLength, p1.Z+zLength);
		p3 = p1;
		this.faces[2] = new Square(p3, p2);
		p2.X = p1.X+xLength;
		p3.X = p1.X+xLength;
		this.faces[3] = new Square(p3, p2);
		p2 = new XYZPoint(p1.X+xLength, p1.Y, p1.Z+zLength);
		p3 = p1;
		if(this.faces[2]==this.faces[3]) {
			System.out.println("It's Broke");
		}
		this.faces[4] = new Square(p3, p2);
		p2.Y = p1.Y+yLength;
		p3.Y = p1.Y+yLength;
		this.faces[5] = new Square(p3, p2);
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
		zFaces[0] = -c.getZ()+this.faces[0].p1.getZ(); //0 = 100
		System.out.println(zFaces[0]+"z");
		zFaces[1] = -c.getZ()+this.faces[1].p1.getZ(); //10 = 90
		System.out.println(zFaces[1]+"z");
		yFaces[0] = -c.getY()+this.faces[2].p1.getX();
		System.out.println(yFaces[0]+"x");
		yFaces[1] = -c.getY()+this.faces[3].p1.getX();
		System.out.println(yFaces[1]+"x");
		xFaces[0] = -c.getX()+this.faces[4].p1.getY();
		System.out.println(xFaces[0]+"y");
		xFaces[1] = -c.getX()+this.faces[5].p1.getY();
		Scanner s = new Scanner("hello");
		s.
		System.out.println(xFaces[1]+"y");
//		if(zFaces!=null) {
//			System.out.println("it might have worked");
//			System.out.println("zFaces[0]z is "+zFaces[0]);
//			System.out.println("zFaces[1]z is "+zFaces[1]);
//			System.out.println("faces[0]z2 is "+this.faces[0].p1.Z);
//			System.out.println("faces[1]z2 is "+this.faces[1].p1.Z);
//		}
		if(zFaces[0]>zFaces[1]) {
			vFaces[0] = this.faces[0];
			System.out.println("got the first oneless");
			System.out.println(vFaces[0].p1.Z);
		}
		if(zFaces[0]<zFaces[1]) {
			vFaces[0] = this.faces[1];
			System.out.println("got the first onemore");
			System.out.println(vFaces[0].p1.Z);
		}
		if(yFaces[0]>yFaces[1]) {
			vFaces[1] = this.faces[2];
		}
		if(yFaces[0]<yFaces[1]) {
			vFaces[1] = this.faces[3];
		}
		if(xFaces[0]>xFaces[1]) {
			vFaces[2] = this.faces[4];
		}
		if(xFaces[0]<xFaces[1]) {
			vFaces[2] = this.faces[5];
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

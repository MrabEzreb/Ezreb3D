/**
 * 
 */
package com.ezreb.ezreb3D;

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
		faces[0] = new Square(p1, p2);
		p2.Z = p1.Z+zLength;
		p3.Z = p1.Z+zLength;
		faces[1] = new Square(p3, p2);
		p2 = new XYZPoint(p1.X, p1.Y+yLength, p1.Z+zLength);
		p3 = p1;
		faces[2] = new Square(p3, p2);
		p2.X = p1.X+xLength;
		p3.X = p1.X+xLength;
		faces[3] = new Square(p3, p2);
		p2 = new XYZPoint(p1.X+xLength, p1.Y, p1.Z+zLength);
		p3 = p1;
		faces[4] = new Square(p3, p2);
		p2.Y = p1.Y+yLength;
		p3.Y = p1.Y+yLength;
		faces[5] = new Square(p3, p2);
	}
	Square[] faces = new Square[6];
	public Square[] getVisibleSides(Camera c) {
		Square[] vFaces = new Square[3];
		int[] zFaces = new int[2];
		int[] yFaces = new int[2];
		int[] xFaces = new int[2];
		zFaces[0] = this.faces[0].p1.getZ()-c.getZ();
		zFaces[1] = this.faces[1].p1.getZ()-c.getZ();
		yFaces[0] = this.faces[2].p1.getY()-c.getY();
		yFaces[1] = this.faces[3].p1.getY()-c.getY();
		xFaces[0] = this.faces[4].p1.getX()-c.getX();
		xFaces[1] = this.faces[5].p1.getX()-c.getX();
		if(zFaces[0]>zFaces[1]) {
			vFaces[0] = this.faces[0];
		}
		if(zFaces[0]<zFaces[1]) {
			vFaces[0] = this.faces[1];
		}
	}

}

package com.ezreb.ezreb3D;

import com.ezreb.ezreb3D.grid.Grid;

public class Camera extends XYZPoint {

	public Camera(Grid grid, XYZPoint camPos, XYZPoint camRot) {
		
	}
	Grid grid;
	XYZPoint camPos;
	XYZPoint camRot;
	public XYZPoint getPoint(XYZPoint point) {
		int x = point.X-camPos.X;
		int y = point.Y-camPos.Y;
		int z = point.Z-camPos.Z;
		double cx = Math.cos(0-camRot.X);
		double cy = Math.cos(0-camRot.Y);
		double cz = Math.cos(0-camRot.Z);
		double sx = Math.sin(0-camRot.X);
		double sy = Math.sin(0-camRot.Y);
		double sz = Math.sin(0-camRot.Z);
		double dx = cy*((sz*y)+(cz*x))-(sy*z);
		double dy = sx*((cy*z)+(sy*((sz*y)+(cz*x))))+(cx*((cz*y)-(sz*x)));
		double dz = cx*((cy*z)+(sy*((sz*y)+(cz*x))))-(sx*((cz*y)-(sz*x)));
		int bx = (int) ((1/dz)*(dx-1));
		int by = (int) ((1/dz)*(dy-1));
		XYZPoint retVal = new XYZPoint(bx, by, 0);
		return retVal;
	}
	public void draw() {
		for (String[][][] quadrant : grid.grid) {
			for (String[][] strings : quadrant) {
				for (String[] strings2 : strings) {
					for (String name : strings2) {
						
					}
				}
			}
		}
	}

}

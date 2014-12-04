package com.ezreb.ezreb3D;

import java.awt.Polygon;

import com.ezreb.ezreb3D.grid.Grid;

/**
 * @author bram.zerbe
 *
 */
public class Camera extends XYZPoint {

	public Camera(Grid grid, XYZPoint camPos, XYZPoint camRot) {
		this(grid, camPos, camRot, new XYZPoint(0, 0, 0));
	}
	public Camera(Grid grid, XYZPoint camPos, XYZPoint camRot, XYZPoint focus) {
		this.camPos.X = camPos.getX();
		this.camPos.Y = camPos.getY();
		this.camPos.Z = -camPos.getZ();
		if(focus.X==0) {
			focus.X = camPos.X;
		}
		if(focus.Y==0) {
			focus.Y = camPos.Y;
		}
		if(focus.Z==0) {
			focus.Z = -camPos.getZ();
		}
		this.camFocus.X = camPos.X-focus.X;
		this.camFocus.Y = camPos.Y-focus.Y;
		this.camFocus.Z = camPos.Z-focus.Z;
		this.camRot = new XYZPoint(camRot.getX(), camRot.getY(), camRot.getZ());
		this.grid = grid;
		int x = camPos.getX();
		int y = camPos.getY();
		int z = -camPos.getZ();
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
	public XYZPoint camPos = new XYZPoint();
	public XYZPoint camFocus = new XYZPoint();
	XYZPoint camRot;
	int posQuadrant;
	int rotQuadrant;
	boolean[] axisRot = new boolean[6];
	public XYZPoint getPoint(XYZPoint point) {
		if(point.getZ()<this.camPos.getZ()+1) {
			point.Z = this.camPos.getZ()+1;
		}
		if(point.getZ()>-this.camPos.getZ()-1) {
			point.Z = -this.camPos.getZ()+1;
		}
		int x = point.getX()-this.camPos.getX();
		int y = point.getY()-this.camPos.getY();
		int z = point.getZ()-(this.camPos.getZ());
		//System.out.println(x+","+y+","+z);
		int ex = this.camFocus.getX();
		//System.out.println("ex for the getpoint is "+ex);
		int ey = this.camFocus.getY();
		int ez = this.camFocus.getZ();
		double cx = Math.cos(0-(this.camRot.X*(3.141592653F/180)));
		double cy = Math.cos(0-(this.camRot.Y*(3.141592653F/180)));
		double cz = Math.cos(0-(this.camRot.Z*(3.141592653F/180)));
		double sx = Math.sin(0-(this.camRot.X*(3.141592653F/180)));
		double sy = Math.sin(0-(this.camRot.Y*(3.141592653F/180)));
		double sz = Math.sin(0-(this.camRot.Z*(3.141592653F/180)));
		double dx = cy*((sz*y)+(cz*x))-(sy*z);
		//System.out.println("dx for the getpoint is "+dx);
		double dy = sx*((cy*z)+(sy*((sz*y)+(cz*x))))+(cx*((cz*y)-(sz*x)));
		double dz = cx*((cy*z)+(sy*((sz*y)+(cz*x))))-(sx*((cz*y)-(sz*x)));
		int bx = (int) Math.floor(((ez/dz)*dx)-ex);
		int by = (int) Math.floor(((ez/dz)*dy)-ey);
		XYZPoint retVal = new XYZPoint(bx+grid.wsize+3, -by+grid.wsize+25, 0);
		if(retVal.getX()>grid.wsize*2-2) {
			retVal.X = this.grid.wsize*2-5;
		}
		if(retVal.getY()>this.grid.wsize*2-2) {
			retVal.Y = this.grid.wsize*2-5;
		}
		if(retVal.getX()<3) {
			retVal.X = 5;
		}
		if(retVal.getY()<25) {
			retVal.Y = 30;
		}
		//System.out.println("bx for the getpoint is "+bx);
		return retVal;
	}
	
	/**
	 * @deprecated
	 * @param c A cube
	 * @return an array of polygons
	 */
	public Polygon[] getCubeShapes(CubeDep c) {
		Polygon[] retVal = new Polygon[6];
		XYZPoint[][] xyzs1 = new XYZPoint[6][4];
		int[] noOfPolygons = new int[3];
		int[] xes = new int[24];
		int[] ys = new int[24];
		int currentPoint = 0;
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 5; j++) {
				xyzs1[i-1][j-1] = c.allPoints[i][j];
				currentPoint++;
			}
		}
		currentPoint = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				xes[j] = this.getPoint(xyzs1[i][j]).getX();
				ys[j] = this.getPoint(xyzs1[i][j]).getY();
				currentPoint++;
			}
			retVal[i] = new Polygon(xes, ys, 4);
		}
		return retVal;
	}
	public Line getLine(Line l) {
		XYZPoint p1 = this.getPoint(l.p1);
		XYZPoint p2 = this.getPoint(l.p2);
		int p1x;
		int p2x;
		int p1y;
		int p2y;
		int p1z;
		int p2z;
		XYZPoint p12;
		XYZPoint p22;
		if(p1.X>p2.X) {
			p1x = p1.X;
			p1y = p1.Y;
			p1z = p1.Z;
			p2x = p2.X;
			p2y = p2.Y;
			p2z = p2.Z;
			p12 = p1;
			p22 = p2;
		} else {
			p1x = p2.X;
			p1y = p2.Y;
			p1z = p2.Z;
			p2x = p1.X;
			p2y = p1.Y;
			p2z = p1.Z;
			p12 = p2;
			p22 = p1;
		}
		int[] points = {p1x,p1y,p2x,p2y};
		int dy = p2y-p1y;
		int dx = p2x-p1x;
		boolean pos;
		if(Math.abs(dy)!=dy && Math.abs(dx)!=dx) {
			dy = Math.abs(dy);
			dx = Math.abs(dx);
		} else if(Math.abs(dy)==dy && Math.abs(dx)!=dx) {
			dy = 0-dy;
			dx = Math.abs(dx);
		}
		if(Math.abs(dy)==dy){
			pos = true;
		} else {
			pos = false;
		}
		int current = 0;
		Line retVal = new Line(1,1,1,1);
		for (int i : points) {
			if(i>0 && i<this.grid.wsize) {
				current++;
			} else if(i<1) {
				if(current<2) {
					p12.X = 1;
					p12.Y = 1;
					p22.X = 1;
					p22.Y = 1;
					break;
				} else {
					//while(p22.X<1 || p22.Y<1) {
						p22.X = p22.X + dx;
						p22.Y = p22.Y + dy;
					//}
				}
			} else if(i>this.grid.wsize) {
				if(current>1) {
					p12.X = 1;
					p12.Y = 1;
					p22.X = 1;
					p22.Y = 1;
					break;
				} else {
					//while(p12.X>this.grid.wsize || p12.Y>this.grid.wsize) {
						p12.X = p12.X - dx;
						p12.Y = p12.Y - dy;
					//}
				}
			}
		}
		retVal = new Line(p12.X,p12.Y,p22.X,p22.Y);
		return retVal;
	}
	
}

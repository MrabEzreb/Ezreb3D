package com.ezreb.ezreb3D;

public class XYZPoint {
	public XYZPoint(int XValue, int YValue, int ZValue) {
		X = XValue;
		Y = YValue;
		Z = ZValue;
	}
	public XYZPoint() {}
	public void setAll(int XValue, int YValue, int ZValue) {
		X = XValue;
		Y = YValue;
		Z = ZValue;
	}
	int X;
	int Y;
	int Z;
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	public int getZ() {
		return Z;
	}
	public String tostring() {
		String retVal = "("+X+","+Y+","+Z+")";
		return retVal;
	}
}

package com.niubaisui.matrix;

public class Point4D{
	public  double x;
	public  double y;
	public  double z;
	public  double w;
	
	public Point4D(double x,double y,double z,double w){
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;
	}
	
	public String toString(){
		return "x:"+x+" y:"+y+" z:"+z+" w:"+w;
	}
}

package com.niubaisui.matrix;

public class Point4dUtil {
	
	public static Point4D add(Point4D p1,Point4D p2){
		return new Point4D(p1.x+p2.x,p1.y+p2.y,p1.z+p2.z,p1.w+p2.w);
	}
	
	public static Point4D sub(Point4D p1,Point4D p2){
		return new Point4D(p1.x-p2.x,p1.y-p2.y,p1.z-p2.z,p1.w-p2.w);
	}
	
	public static double dot(Point4D p1,Point4D p2){
		return p1.x*p2.x+p1.y*p2.y+p1.z*p2.z;
	}
	
	public static Point4D cross(Point4D p1,Point4D p2){
		return new Point4D(p1.y*p2.z-p2.y*p1.z,p1.z*p2.x-p1.x*p2.z,
				p1.x*p2.y-p2.x*p1.y,1);
	}
	public static Point4D normalize(Point4D p){
		double d=Math.sqrt(p.x*p.x+p.y*p.y+p.z*p.z);
		return new Point4D(p.x/d,p.y/d,p.z/d,1);
	}
	public static void main(String[] args) {
		Point4D p=new Point4D(3,4,5,1);
		System.out.println(Point4dUtil.normalize(p));
	}
}

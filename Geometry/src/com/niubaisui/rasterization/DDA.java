/**
 * 
 */
package com.niubaisui.rasterization;

import com.niubaisui.tree.KdPoint;

/**
 * @author Administrator
 *
 * 2014年9月22日
 */
public class DDA {

	private KdPoint left_point;
	private KdPoint right_point;
	
	public DDA(){
		
	}
	public DDA(KdPoint left_point,KdPoint  right_point){
		this.left_point=left_point;
		this.right_point=right_point;
	}
	
	public KdPoint getLeft_point() {
		return left_point;
	}
	public void setLeft_point(KdPoint left_point) {
		this.left_point = left_point;
	}
	public KdPoint getRight_point() {
		return right_point;
	}
	public void setRight_point(KdPoint right_point) {
		this.right_point = right_point;
	}
	
	public void caculate(){
		double dx=right_point.getX()-left_point.getX();
		double dy=right_point.getY()-left_point.getY();
		double k=dy/dx;
		double y=left_point.getY();
		for(double x=left_point.getX();x<=right_point.getX();x++){
			System.out.println("x:"+x+",y:"+Math.round(y));
			y+=k;
		}
	}
	public static void main(String[] args) {
		System.out.println(Math.round(10.4));
		DDA dda=new DDA(new KdPoint(0, 0),new KdPoint(5, 2));
		dda.caculate();
	}
}

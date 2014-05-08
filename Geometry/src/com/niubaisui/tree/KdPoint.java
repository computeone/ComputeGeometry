/**
 * 
 */
package com.niubaisui.tree;

/**
 * @author Administrator
 *
 * 2014Äê4ÔÂ9ÈÕ
 */
public class KdPoint {
	private double x;
	private double y;
	
	public KdPoint(){
		
	}
	public KdPoint(double x,double y){
		this.setX(x);
		this.setY(y);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	@Override
	public boolean equals(Object point){
		KdPoint data=((KdPoint)point);
		if(data.getX()==x&&data.getY()==y){
			return true;
		}
		return false;
	}

}

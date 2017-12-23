/**
 * 
 */
package com.algorithm.kdtree;

/**
 * @author Administrator
 *
 * 2014年4月8日
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

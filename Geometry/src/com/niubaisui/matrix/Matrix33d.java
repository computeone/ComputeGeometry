package com.niubaisui.matrix;

public class Matrix33d {
	private double[][] m=new double[3][3];

	public Matrix33d(double[][] matrix){
		m=matrix;
	}	
	public Matrix33d getMatrix(){
		return new Matrix33d(m);
	}
	
	public void setElement(int row,int coll,double d){
		m[row][coll]=d;
	}
	public double getElement(int row,int coll){
		return m[row][coll];
	}

}

package com.algorithm.matrix;

public class Matrix44d {
	private double[][] m=new double[4][4];

	public Matrix44d(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				m[i][j]=0;
			}
		}
	}
	public Matrix44d(double[][] matrix){
		m=matrix;
	}	
	public Matrix44d getMatrix(){
		return new Matrix44d(m);
	}
	
	public void setElement(int row,int coll,double d){
		m[row][coll]=d;
	}
	public double getElement(int row,int coll){
		return m[row][coll];
	}
	
	public String toString(){
		return m[0][0]+" "+m[0][1]+" "+m[0][2]+" "+m[0][3]+"\n"+
			   m[1][0]+" "+m[1][1]+" "+m[1][2]+" "+m[1][3]+"\n"+
			   m[2][0]+" "+m[2][1]+" "+m[2][2]+" "+m[2][3]+"\n"+
			   m[3][0]+" "+m[3][1]+" "+m[3][2]+" "+m[3][3]+"\n";
	}
}

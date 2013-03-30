package com.niubaisui;

import java.math.BigInteger;

public class Point {
	private BigInteger x;
	private BigInteger y;
	public Point(BigInteger x,BigInteger y){
		this.x=x;
		this.y=y;
	}
	
	public void setX(BigInteger x){
		this.x=x;
	}
	
	public BigInteger getX(){
		return x;
	}
	
	public void setY(BigInteger y){
		this.y=y;
	}
	
	public BigInteger getY(){
		return y;
	}
		
}

package com.niubaisui.rsa;

import java.util.LinkedList;

public class Convex {
	
	
	public boolean isRight(Point a,Point b,Point c){
		//���a��b��ɵ�ֱ�ߴ�ֱ��x��
		if(a.x==b.x){
			if(a.y>b.y){
				if(c.x<a.x){
					return true;
				}
				if(c.x==a.x){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(c.x<a.x){
					return true;
				}
				if(c.x==a.x){
					return true;
				}
				else{
					return false;
				}
			}
		}
		
		//ƽ����y��
		if(a.y==b.y){
			if(a.x>b.x){
				if(a.y>c.y){
					return true;
				}
				if(a.y==c.y){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(a.y>c.y){
					return false;
				}
				if(a.y==c.y){
					return true;
				}
				else{
					return true;
				}
			}
		}
		//���y=kx+p
		else{
			double k,p;
			if(a.x<b.x){
				k=(a.y-b.y)/(a.x-b.x);
				p=b.y-k*b.x;
				
				if(c.y>(k*c.x+p)){
					return false;
				}
				if(c.y==(k*c.x+p)){
					return true;
				}
				else{
					return true;
				}
			}
			else{
				k=(a.y-b.y)/(a.x-b.x);
				p=b.y-k*b.x;
				if(c.y>(k*c.x+p)){
					return true;
				}
				if(c.y==(k*c.x+p)){
					return true;
				}
				else{
					return false;
				}
			}
		}
	}
	//��LinkedList<Integer> ת��Ϊint[]
	public Point[] getArray(LinkedList<Point> list){
		Point[] array=new Point[list.size()];
		for(int i=0;i<list.size();i++){
			array[i]=list.get(i);
		}
		return array;
	}
	//�򵥵���͹�����㷨
	public Point[] getConvex1(LinkedList<Point> list){
		Point[] array=getArray(list);
		LinkedList<Point> result=new LinkedList<Point>();
		result.add(array[0]);
		boolean isRight=false;
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array.length;j++){
				for(int k=0;k<array.length;k++){
					if(isRight(array[i], array[j], array[k])){
						isRight=true;
					}
					else{
						break;
					}
				}
				if(isRight==true){
					result.add(array[i]);
					isRight=false;
				}
				isRight=false;			
			}
		}
		return (Point[])result.toArray();
	}
	
	//����ʽ��͹��
	public Point[] getConvex2(LinkedList<Point> list){
		Point[] array=getArray(list);
		
		return array;
	}

	class Point{
		private double x;
		private double y;
		public Point(double x,double y){
			this.x=x;
			this.y=y;
		}
		public void setX(double x){
			this.x=x;
		}
		public double getX(){
			return x;
		}
		public void setY(double y){
			this.y=y;
		}
		public double getY(){
			return y;
		}
	}
	public static void main(String[] args) {
		System.out.println("��͹���ĳ���");
		Convex convex=new Convex();
		Convex.Point a=convex.new Point(100l,100l);
		Convex.Point b=convex.new Point(200l,200l);
		Convex.Point c=convex.new Point(300l,700l);
		System.out.println(convex.isRight(a, b, c));
		
	}
}

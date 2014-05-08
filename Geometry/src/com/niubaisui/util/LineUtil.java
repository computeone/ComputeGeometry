/**
 * 
 */
package com.niubaisui.util;

import java.util.Collections;
import java.util.List;

import com.niubaisui.tree.KdPoint;
import com.niubaisui.triangulation.Line;

/**
 * @author Administrator
 *
 * 2014年5月5日
 */
public class LineUtil {
	// 判断一条线段包含这个点
	public static boolean isContain(Line line, KdPoint p) {
		double a, b, c, d;

		// 确定区间
		a = line.getStart().getY();
		b = line.getEnd().getY();
		if (a > b) {
			double tmp = a;
			a = b;
			b = tmp;
		}
		c = line.getStart().getX();
		d = line.getEnd().getX();
		if (c > d) {
			double tmp = c;
			c = d;
			d = tmp;
		}

		if (line.getStart().equals(p)) {
			return true;
		}
		if (line.getEnd().equals(p)) {
			return true;
		}

		// 判断在不在这个区间
		if (!(p.getX() < d && p.getX() > c) || !(p.getY() < b && p.getY() > a)) {
			return false;
		}
		
		//判断线段的斜率是否为90度
		if(line.getEnd().getX()==line.getStart().getX()){
			if(p.getX()==line.getStart().getX()){
				return true;
			}
			else{
				return false;
			}
		}
		
		//斜率不为90度
		double k = (line.getEnd().getY() - line.getStart().getY())
				/ (line.getEnd().getX() - line.getStart().getX());
		double m = line.getStart().getY() - k * line.getStart().getX();

		double y0 = k * p.getX() + m;
		if (Math.abs(y0 - p.getY())>0.001) {
			return false;
		}

		return true;
	}
	
	//水平扫描线与线段求交
	public static KdPoint getIntersection(Line line,Line scan){
		KdPoint point=new KdPoint();
		
		//判断斜率是否为90度
		if(line.getEnd().getX()==line.getStart().getX()){
			if(scan.getStart().getY()>=scan.getEnd().getY()&&scan.getStart().getY()<=scan.getStart().getY()){
				point.setX(line.getStart().getX());
				point.setY(scan.getStart().getY());
				return point;
			}
			else{
				return null;
			}
		}
		
		
		//判断是否在线段的范围内
		if(scan.getStart().getY()>=line.getEnd().getY()&&scan.getStart().getY()<=line.getStart().getY()){
			double k=(line.getEnd().getY()-line.getStart().getY())/(line.getEnd().getX()-line.getStart().getX());
			double b=line.getStart().getY()-k*line.getStart().getX();
			double x=(scan.getStart().getY()-b)/k;
			point.setX(x);
			point.setY(scan.getStart().getY());
			return point;
		}
		else{
			return null;
		}
	}
	
	//两条线段求交
	public static KdPoint getIntersect(Line line1,Line line2){
		KdPoint p=new KdPoint();
		//判断斜率是否为90度
		if(line1.getEnd().getX()==line1.getStart().getX()){
			
			if(line2.getEnd().getX()==line2.getStart().getX()){
				if(line1.getStart().getX()==line2.getStart().getX()){
					return null;
				}
				else{
					return null;
				}
			}
			else{
				double k=(line2.getEnd().getY()-line2.getStart().getY())/(line2.getEnd().getX()-line2.getStart().getY());
				double b=line2.getStart().getY()-k*line2.getStart().getX();
				double y=line1.getStart().getX()*k+b;
				if(y<=line1.getStart().getY()&&y>=line1.getEnd().getY()){
					p.setX(line1.getStart().getX());
					p.setY(y);
					return p;
				}
			}
		}
		
		if (line2.getEnd().getX() == line2.getStart().getX()) {

			if (line1.getEnd().getX() == line1.getStart().getX()) {
				if (line1.getStart().getX() == line2.getStart().getX()) {
					return null;
				} else {
					return null;
				}
			}
			else{
				double k=(line1.getEnd().getY()-line1.getStart().getY())/(line1.getEnd().getX()-line1.getStart().getY());
				double b=line1.getStart().getY()-k*line1.getStart().getX();
				double y=line2.getStart().getX()*k+b;
				if(y<=line2.getStart().getY()&&y>=line2.getEnd().getY()){
					p.setX(line1.getStart().getX());
					p.setY(y);
					return p;
				}
			}
		}
		
		//求交
		double k1=(line1.getEnd().getY()-line1.getStart().getY())/(line1.getEnd().getX()-line1.getStart().getX());
		double b1=line1.getStart().getY()-k1*line1.getStart().getX();
		double k2=(line2.getEnd().getY()-line2.getStart().getY())/(line2.getEnd().getX()-line2.getStart().getX());
		double b2=line2.getStart().getY()-k2*line2.getStart().getX();
		
		if(k1==k2){
			return null;
		}
		else{
			double x=(b2-b1)/(k1-k2);
			double y=k1*x+b1;
			if(y>=line1.getEnd().getY()&&y<=line1.getStart().getY()){
				
				if(y>=line2.getEnd().getY()&&y<=line2.getStart().getY()){
					p.setX(x);
					p.setY(y);
					return p;
				}
			}
			return null;
		}
		
	}
	//在一条扫描线上，对所有线段进行排序
	public static List<Line> sortLine(List<Line> lines,Line scan){
		LineCompare compare=new LineCompare();
		compare.setScan(scan);
		Collections.sort(lines, compare);
		return lines;
	}
}

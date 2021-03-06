/**
 * 
 */
package com.algorithm.decl;

import java.util.List;

import com.algorithm.kdtree.*;
import com.algorithm.triangle.*;

/**
 * @author Administrator
 *
 * 2014年5月5日
 */
public class Event {
	private KdPoint point;
	private List<Line> lines;
	
	public Event(){
		
	}
	public Event(KdPoint point,List<Line> lines){
		this.setPoint(point);
		this.setLines(lines);
	}
	public List<Line> getLines() {
		return lines;
	}
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}
	public KdPoint getPoint() {
		return point;
	}
	public void setPoint(KdPoint point) {
		this.point = point;
	}
	
	@Override
	public boolean equals(Object obj){
		Event e=(Event) obj;
		if(Math.abs(point.getX()-e.getPoint().getX())<0.00001&&Math.abs(point.getY()-e.getPoint().getY())<0.0001){
			return true;
		}
		return false;
	}

}

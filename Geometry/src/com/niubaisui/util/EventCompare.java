/**
 * 
 */
package com.niubaisui.util;

import java.util.Comparator;

import com.niubaisui.tree.KdPoint;


/**
 * @author Administrator
 *
 * 2014Äê5ÔÂ6ÈÕ
 */
public class EventCompare implements Comparator<Event>{

	@Override
	public int compare(Event o1, Event o2) {
		KdPoint point1=o1.getPoint();
		KdPoint point2=o2.getPoint();
		
		if(point1.getY()>point2.getY()){
			return -1;
		}
		if(point1.getY()<point2.getY()){
			return 1;
		}
		else{
			if(point1.getX()>point2.getX()){
				return 1;
			}
			if(point1.getX()<point2.getX()){
				return -1;
			}
			return 0;
		}
	}



}

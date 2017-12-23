/**
 * 
 */
package com.algorithm.decl;

import java.util.Comparator;

import com.algorithm.kdtree.*;


/**
 * @author Administrator
 *
 * 2014年5月6日
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

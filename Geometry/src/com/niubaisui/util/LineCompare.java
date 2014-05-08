/**
 * 
 */
package com.niubaisui.util;

import java.util.Comparator;

import com.niubaisui.tree.KdPoint;
import com.niubaisui.triangulation.Line;

/**
 * @author Administrator
 *
 * 2014Äê5ÔÂ6ÈÕ
 */
public class LineCompare implements Comparator<Line>{

	private Line scan;
	@Override
	public int compare(Line line1, Line line2) {
		KdPoint point1=LineUtil.getIntersection(line1, scan);
		KdPoint point2=LineUtil.getIntersection(line2, scan);
		
		if(point1.getX()>point2.getX()){
			return 1;
		}
		if(point1.getX()<point2.getX()){
			return -1;
		}
		return 0;
	}
	public Line getScan() {
		return scan;
	}
	public void setScan(Line scan) {
		this.scan = scan;
	}
}

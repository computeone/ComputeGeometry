/**
 * 
 */
package com.algorithm.decl;

import java.util.Comparator;

import com.algorithm.kdtree.*;
import com.algorithm.triangle.*;
import com.algorithm.plane.*;

/**
 * @author Administrator
 *
 * 2014年5月5日
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

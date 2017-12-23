/**
 * 
 */
package com.algorithm.kdtree;

/**
 * @author Administrator
 *
 * 2014年4月8日
 */
public class KdRange {
	private KdPoint start;
	private KdPoint end;
	
	public KdRange(KdPoint start,KdPoint end){
		this.start=start;
		this.end=end;
	}
	public KdPoint getStart() {
		return start;
	}

	public void setStart(KdPoint start) {
		this.start = start;
	}

	public KdPoint getEnd() {
		return end;
	}

	public void setEnd(KdPoint end) {
		this.end = end;
	}

	
	
	

}

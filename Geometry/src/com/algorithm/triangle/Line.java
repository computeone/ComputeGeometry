package com.algorithm.triangle;

import com.algorithm.kdtree.*;

public class Line {

	private KdPoint start;
	private KdPoint end;
	
	public Line(KdPoint start,KdPoint end){
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

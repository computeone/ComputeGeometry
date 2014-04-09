/**
 * 
 */
package com.niubaisui.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 * 2014Äê4ÔÂ8ÈÕ
 */
public class KdTree {
	
	private List<KdPoint> points;
	private KdNode root;
	public KdTree(List<KdPoint> points){
		this.setPoints(points);
	}

	public KdTree(List<KdPoint> points,KdRange range){
		this.points=points;
		root.setRange(range);
	}
	public List<KdPoint> getPoints() {
		return points;
	}

	public void setPoints(List<KdPoint> points) {
		this.points = points;
	}
	
	public void buildKdTree(){
		
	}
	
	public List<KdNode> findRange(KdNode point,double distance){
		ArrayList<KdNode> result=new ArrayList<KdNode>();
		return result;
	}
	
	public List<KdNode> findKNeighbor(KdNode point,int k){
		ArrayList<KdNode> result=new ArrayList<KdNode>();
		
		return result;
	}

}

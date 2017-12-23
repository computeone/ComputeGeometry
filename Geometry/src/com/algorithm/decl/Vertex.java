/**
 * 
 */
package com.algorithm.decl;

/**
 * @author Administrator
 *
 * 2014年5月5日
 */
public class Vertex {
	private double x;
	private double y;
	private HalfEdge  incidentEdge;//指向以v为起点的某条半边
	public Vertex(){
		
	}
	public Vertex(double x,double y){
		this.setX(x);
		this.setY(y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	public HalfEdge getIncidentEdge() {
		return incidentEdge;
	}
	public void setIncidentEdge(HalfEdge incidentEdge) {
		this.incidentEdge = incidentEdge;
	}
}

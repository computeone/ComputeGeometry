/**
 * 
 */
package com.niubaisui.util;

/**
 * @author Administrator
 *
 * 2014��5��5��
 */
public class Vertex {
	private double x;
	private double y;
	private HalfEdge  incidentEdge;//ָ����vΪ����ĳ�����
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

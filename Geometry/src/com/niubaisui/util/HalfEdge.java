/**
 * 
 */
package com.niubaisui.util;

/**
 * @author Administrator
 *
 * 2014年5月5日
 */
public class HalfEdge {
	private Vertex origin;//该半边的起点
	private HalfEdge twin;//指向其孪生半边
	private Face incidentFace;//指向其参与围成的那张面
	private HalfEdge  next;//以e的终点为起点的半边只有next
	private HalfEdge  prev;//以e的起点为终点的半边只有prev
	
	
	public Vertex getOrigin() {
		return origin;
	}
	public void setOrigin(Vertex origin) {
		this.origin = origin;
	}
	public Face getIncidentFace() {
		return incidentFace;
	}
	public void setIncidentFace(Face incidentFace) {
		this.incidentFace = incidentFace;
	}
	public HalfEdge getPrev() {
		return prev;
	}
	public void setPrev(HalfEdge prev) {
		this.prev = prev;
	}
	public HalfEdge getNext() {
		return next;
	}
	public void setNext(HalfEdge next) {
		this.next = next;
	}
	public HalfEdge getTwin() {
		return twin;
	}
	public void setTwin(HalfEdge twin) {
		this.twin = twin;
	}
	
	
	
}

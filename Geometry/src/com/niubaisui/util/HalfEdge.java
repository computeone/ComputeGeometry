/**
 * 
 */
package com.niubaisui.util;

/**
 * @author Administrator
 *
 * 2014��5��5��
 */
public class HalfEdge {
	private Vertex origin;//�ð�ߵ����
	private HalfEdge twin;//ָ�����������
	private Face incidentFace;//ָ�������Χ�ɵ�������
	private HalfEdge  next;//��e���յ�Ϊ���İ��ֻ��next
	private HalfEdge  prev;//��e�����Ϊ�յ�İ��ֻ��prev
	
	
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

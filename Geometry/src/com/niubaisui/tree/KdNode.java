/**
 * 
 */
package com.niubaisui.tree;

/**
 * @author Administrator
 *
 * 2014Äê4ÔÂ9ÈÕ
 */
public class KdNode {
	private KdPoint nodeData;
	private KdRange range;
	private int split;
	private KdNode left;
	private KdNode right;
	private KdNode parent;
	
	public KdNode(){	
	}
	public KdNode(KdPoint nodeData){
		this.nodeData=nodeData;
	}
	public KdPoint getNodeData() {
		return nodeData;
	}
	public void setNodeData(KdPoint nodeData) {
		this.nodeData = nodeData;
	}
	public KdRange getRange() {
		return range;
	}
	public void setRange(KdRange range) {
		this.range = range;
	}
	public int getSplit() {
		return split;
	}
	public void setSplit(int split) {
		this.split = split;
	}
	public KdNode getLeft() {
		return left;
	}
	public void setLeft(KdNode left) {
		this.left = left;
	}
	public KdNode getRight() {
		return right;
	}
	public void setRight(KdNode right) {
		this.right = right;
	}
	public KdNode getParent() {
		return parent;
	}
	public void setParent(KdNode parent) {
		this.parent = parent;
	}

	@Override
	public boolean equals(Object node){
		KdPoint data=((KdNode)node).getNodeData();
		if(data.getX()==nodeData.getX()&&data.getY()==nodeData.getY()){
			return true;
		}
		return false;
	}
	
}

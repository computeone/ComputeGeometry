/**
 * 
 */
package com.niubaisui.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author Administrator
 * 
 *         2014年4月8日
 */

public class KdTree {

	private KdPoint points[];
	private KdNode root;

	public KdTree(KdPoint[] points) {
		this.setPoints(points);
	}

	public KdTree(KdPoint points[], KdRange range) {
		this.points = points;
		root = new KdNode();
		root.setRange(range);
	}

	public KdPoint[] getPoints() {
		return points;
	}

	public void setPoints(KdPoint[] points) {
		this.points = points;
	}

	public void createKDTree(KdNode node, int rleft, int rright, int split) {

		// 节点按照X轴划分
		if (split == 0) {

			// 当空间中剩下两个元素时
			if (rright - rleft < 3) {
				if (rright - rleft == 2) {
					node.setNodeData(points[rright-1]);
					node.setRight(null);

					// 生成左节点
					KdNode leftNode = new KdNode();
					leftNode.setNodeData(points[rleft]);
					leftNode.setRange(new KdRange(points[rleft], points[rleft]));
					leftNode.setParent(node);
					leftNode.setSplit(1);

					node.setLeft(leftNode);
					return;
				}
				node.setNodeData(points[rleft]);
				node.setLeft(null);
				node.setRight(null);
				node.setRange(new KdRange(points[rleft], points[rleft]));
				return;
			}

			int mid = (rright + rleft) / 2;

			// node节点设置
			Arrays.sort(points, rleft, rright, new XKdPointComparator());
			node.setNodeData(points[mid]);
			KdNode leftNode = new KdNode();
			leftNode.setRange(new KdRange(points[rleft], points[mid]));
			leftNode.setParent(node);
			leftNode.setSplit(1);
			KdNode rightNode = new KdNode();
			rightNode.setRange(new KdRange(points[mid], points[rright-1]));
			rightNode.setParent(node);
			rightNode.setSplit(1);

			node.setLeft(leftNode);
			node.setRight(rightNode);

			// 递归创建子节点
			createKDTree(leftNode, rleft, mid, 1);
			createKDTree(rightNode, mid +1, rright, 1);
		}

		// 节点按照Y轴划分
		if (split == 1) {

			// 当空间中剩下两个元素时
			if (rright - rleft < 3) {
				if (rright - rleft == 2) {
					node.setNodeData(points[rright-1]);
					node.setRight(null);

					// 生成左节点
					KdNode leftNode = new KdNode();
					leftNode.setNodeData(points[rleft]);
					leftNode.setRange(new KdRange(points[rleft], points[rleft]));
					leftNode.setParent(node);
					leftNode.setSplit(1);

					node.setLeft(leftNode);
					return;
				}
				node.setNodeData(points[rleft]);
				node.setLeft(null);
				node.setRight(null);
				node.setRange(new KdRange(points[rleft], points[rleft]));
				return;
			}
			int mid = (rright + rleft) / 2;

			// node节点设置
			Arrays.sort(points, rleft, rright, new YKdPointComparator());
			node.setNodeData(points[mid]);
			KdNode leftNode = new KdNode();
			leftNode.setRange(new KdRange(points[rleft], points[mid]));
			leftNode.setParent(node);
			leftNode.setSplit(0);

			KdNode rightNode = new KdNode();
			rightNode.setRange(new KdRange(points[mid], points[rright-1]));
			rightNode.setParent(node);
			rightNode.setSplit(0);

			node.setLeft(leftNode);
			node.setRight(rightNode);
			// 递归创建子节点
			createKDTree(leftNode, rleft, mid , 0);
			createKDTree(rightNode, mid+1 , rright, 0);
		}

	}

	public void buildKdTree() {
		Arrays.sort(points, 0, points.length - 1, new XKdPointComparator());

		// 设置根节点
		root.setSplit(0);
		root.setParent(null);
		int mid = points.length / 2;
		root.setNodeData(new KdPoint(points[mid].getX(), points[mid].getY()));

		if (points.length < 3) {
			if (points.length == 1) {
				root.setLeft(null);
				root.setRight(null);
				return;
			}

			// 添加左节点
			KdNode leftNode = new KdNode();
			leftNode.setRange(new KdRange(points[0], points[0]));
			leftNode.setParent(root);
			leftNode.setSplit(1);
			leftNode.setNodeData(points[0]);

		}

		// root节点设置
		KdNode leftNode = new KdNode();
		leftNode.setRange(new KdRange(points[0], points[mid]));
		leftNode.setParent(root);
		leftNode.setSplit(1);

		KdNode rightNode = new KdNode();
		rightNode.setRange(new KdRange(points[mid], points[points.length - 1]));
		rightNode.setParent(root);
		rightNode.setSplit(1);

		root.setLeft(leftNode);
		root.setRight(rightNode);

		// 递归创建子节点
		createKDTree(leftNode, 0, mid , 1);
		createKDTree(rightNode, mid +1, points.length , 1);

		System.out.println("完成");
	}

	@Override
	public String toString(){
		
		return null;
	}
	
	public boolean isRange(KdRange range,KdPoint point){
		double startX=range.getStart().getX()<range.getEnd().getX()?range.getStart().getX():range.getEnd().getX();
		double endX=range.getStart().getX()>range.getEnd().getX()?range.getStart().getX():range.getEnd().getX();
		double startY=range.getStart().getY()<range.getEnd().getY()?range.getStart().getY():range.getEnd().getY();
		double endY=range.getStart().getY()<range.getEnd().getY()?range.getStart().getY():range.getEnd().getY();
		double x=point.getX();
		double y=point.getY();
		if(x>startX&&x<endX){
			if(y>startY&&y<endY){
				return true;
			}
			return false;
		}
		return false;
	}
	
	public double distance(KdPoint p1,KdPoint p2){
		double x1=p1.getX();
		double x2=p2.getX();
		double y1=p1.getY();
		double y2=p2.getY();
		return Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
	}
	public List<KdNode> findRange(KdPoint point, double distance) {
		ArrayList<KdNode> result = new ArrayList<KdNode>();
		return result;
	}

	public KdNode findNeighbor(KdPoint point){
		KdNode node=new KdNode();
		Stack<KdNode> backStack=new Stack<KdNode>(); 
		node=root;
		
		//二叉树查找
		while(node.getLeft()!=null&&node.getRight()!=null){
			
			if(node.getNodeData().equals(point)){
				return node;
			}
			if(isRange(node.getLeft().getRange(), point)){
				backStack.push(node);
				node=node.getLeft();
				
				continue;
			}
			backStack.push(node);
			node=node.getRight();
		}
		
		
		double distance=distance(node.getNodeData(),point);
		//回溯
		while(!backStack.isEmpty()){
			KdNode p=backStack.pop();
		}
		return node;
	}
	
	public boolean isNear(KdRange range,KdPoint p,double distance){
		
		return false;
	}
	public List<KdNode> findKNeighbor(KdNode point, int k) {
		ArrayList<KdNode> result = new ArrayList<KdNode>();

		return result;
	}

	public static void main(String[] args) {
		KdPoint points[] = new KdPoint[7];
		points[0] = new KdPoint(2, 3);
		points[1] = new KdPoint(4, 7);
		points[2] = new KdPoint(5, 4);
		points[3] = new KdPoint(7, 2);
		points[4] = new KdPoint(8, 1);
		points[5] = new KdPoint(9, 6);
		points[6] = new KdPoint(12,2);
		KdRange range = new KdRange(new KdPoint(0, 0), new KdPoint(100, 100));
		System.out.println(range.getStart().getX());
		System.out.println(range.getEnd().getX());
		KdTree tree = new KdTree(points, range);
		tree.buildKdTree();
	}

}

class XKdPointComparator implements Comparator<KdPoint> {

	@Override
	public int compare(KdPoint p1, KdPoint p2) {
		if (p1.getX() > p2.getX()) {
			return 1;
		}
		if (p1.getX() < p2.getX()) {
			return -1;
		}
		return 0;
	}

}

class YKdPointComparator implements Comparator<KdPoint> {

	@Override
	public int compare(KdPoint p1, KdPoint p2) {
		// TODO Auto-generated method stub
		if (p1.getY() > p2.getY()) {
			return 1;
		}
		if (p1.getY() < p2.getY()) {
			return -1;
		}
		return 0;
	}

}

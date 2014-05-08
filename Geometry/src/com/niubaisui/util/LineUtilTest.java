package com.niubaisui.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.niubaisui.tree.KdPoint;
import com.niubaisui.triangulation.Line;

public class LineUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsContain() {
		KdPoint p3=new KdPoint(300,120);
		KdPoint p4=new KdPoint(400,130);
		Line line3=new Line(p3,new KdPoint(410,-120));
		Line line4=new Line(p4, new KdPoint(210,-130));
		KdPoint p=new KdPoint(335.7277628, 42.0485175);
		
		System.out.println(LineUtil.isContain(line3, p));
		System.out.println(LineUtil.isContain(line4, p));
	}

	
	public void testGetIntersection() {
		Line scan=new Line(new KdPoint(Double.MIN_VALUE, 48),new KdPoint(Double.MAX_VALUE, 48));
		Line line3=new Line(new KdPoint(25, 303),new KdPoint(125, 4));
		KdPoint p=LineUtil.getIntersection(line3, scan);
		System.out.println(p.getX());
		System.out.println(p.getY());
	}
    
	public void testGetIntersect(){
		Line line1=new Line(new KdPoint(410, 130),new KdPoint(210, -130));
		Line line3=new Line(new KdPoint(410, 130),new KdPoint(210, -130));
		KdPoint p=LineUtil.getIntersect(line1, line3);
		if(p==null){
			System.out.println("null");
		}
		else{
			System.out.println(p.getX());
			System.out.println(p.getY());
		}
		
	}
	
	public void testSortLine() {
		Line scan=new Line(new KdPoint(Double.MIN_VALUE, 46),new KdPoint(Double.MAX_VALUE, 46));
		Line line1=new Line(new KdPoint(100, 100),new KdPoint(10, 10));
		Line line2=new Line(new KdPoint(41, 345),new KdPoint(13,44));
		Line line3=new Line(new KdPoint(25, 303),new KdPoint(231, 40));
		KdPoint point=new KdPoint(50, 50);
		List<Line> lines=new ArrayList<Line>();
		lines.add(line1);
		lines.add(line2);
		lines.add(line3);
		LineUtil.sortLine(lines, scan);
		
		for(Line line:lines){
			if(LineUtil.isContain(line, point)){
				System.out.println("--->"+line.getEnd().getX());
			}
			System.out.println(line.getStart().getX());
			System.out.println(line.getStart().getY());
		}
		
	}

}

/**
 * 
 */
package com.algorithm.plane;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algorithm.kdtree.*;
import com.algorithm.triangle.*;

/**
 * @author Administrator
 *
 * 2014ƒÍ5‘¬8»’
 */
public class PlanScanInterSectionTest {

	/**
	 * @throws java.lang.Exception
	 */
	public static Event event1;
	public static Event event2;
	public static Event event3;
	public static Event event4;
	public static Event event5;
	public static List<Event> events=new ArrayList<Event>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		KdPoint p1=new KdPoint(100,100);
		KdPoint p2=new KdPoint(200,110);
		KdPoint p3=new KdPoint(300,120);
		KdPoint p4=new KdPoint(400,130);
		KdPoint p5=new KdPoint(1000, 40);
		
		Line line1=new Line(p1,new KdPoint(310,-100));
		Line line2=new Line(p2,new KdPoint(110,-110));
		Line line3=new Line(p3,new KdPoint(410,-120));
		Line line4=new Line(p4, new KdPoint(210,-130));
		Line line5=new Line(p5,new KdPoint(-100, -50));
		List<Line> list1=new ArrayList<Line>();
		List<Line> list2=new ArrayList<Line>();
		List<Line> list3=new ArrayList<Line>();
		List<Line> list4=new ArrayList<Line>();
		List<Line> list5=new ArrayList<Line>();
		
		list1.add(line1);
		list2.add(line2);
		list3.add(line3);
		list4.add(line4);
		list5.add(line5);
		
		event1=new Event(p1, list1);
		event2=new Event(p2, list2);
		event3=new Event(p3, list3);
		event4=new Event(p4, list4);
		event5=new Event(p5, list5);
		events.add(event1);
		events.add(event2);
		events.add(event3);
		events.add(event4);
		//events.add(event5);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.niubaisui.util.PlanScanInterSection#FindIntersections()}.
	 */
	@Test
	public void testFindIntersections() {
		PlanScanInterSection planscan=new PlanScanInterSection(null, events);
		planscan.FindIntersections();
		List<KdPoint> pp=planscan.getIntersection();
		for(KdPoint p:pp){
			System.out.println("-------------------");
			System.out.println(p.getX());
			System.out.println(p.getY());
			System.out.println("--------------------");
		}
	}

	/**
	 * Test method for {@link com.niubaisui.util.PlanScanInterSection#HandleEventPoint(com.niubaisui.util.Event)}.
	 */
	
	public void testHandleEventPoint() {
		
		Event event1=new Event();
		Event event2=new Event();
		KdPoint p1=new KdPoint(169.01869158878503,34.2679127725857);
		KdPoint p2=new KdPoint(169.01869158878503,34.267912772585646);
		
		event1.setPoint(p1);
		event2.setPoint(p2);
		EventCompare compare=new EventCompare();
		System.out.println(compare.compare(event1, event2));
		PriorityQueue<Event> queue=new PriorityQueue<Event>(10,new EventCompare());
		queue.add(event1);
		System.out.println(event1.equals(event2));
		System.out.println(queue.contains(event2));
	}

	/**
	 * Test method for {@link com.niubaisui.util.PlanScanInterSection#FindNewEvent(com.niubaisui.triangulation.Line, com.niubaisui.triangulation.Line, com.niubaisui.tree.KdPoint)}.
	 */
	@Test
	public void testFindNewEvent() {
		
	}

	/**
	 * Test method for {@link com.niubaisui.util.PlanScanInterSection#getLeft(com.niubaisui.tree.KdPoint)}.
	 */
	@Test
	public void testGetLeftKdPoint() {
	}

	/**
	 * Test method for {@link com.niubaisui.util.PlanScanInterSection#getRight(com.niubaisui.tree.KdPoint)}.
	 */
	@Test
	public void testGetRightKdPoint() {
	}

	/**
	 * Test method for {@link com.niubaisui.util.PlanScanInterSection#getLeft(java.util.List, java.util.List, com.niubaisui.tree.KdPoint)}.
	 */
	@Test
	public void testGetLeftListOfLineListOfLineKdPoint() {
		
	}

	/**
	 * Test method for {@link com.niubaisui.util.PlanScanInterSection#getRight(java.util.List, java.util.List, com.niubaisui.tree.KdPoint)}.
	 */
	@Test
	public void testGetRightListOfLineListOfLineKdPoint() {
		
	}

	/**
	 * Test method for {@link com.niubaisui.util.PlanScanInterSection#getLeft(com.niubaisui.triangulation.Line)}.
	 */
	@Test
	public void testGetLeftLine() {
		
	}

	/**
	 * Test method for {@link com.niubaisui.util.PlanScanInterSection#getRight(com.niubaisui.triangulation.Line)}.
	 */
	@Test
	public void testGetRightLine() {
	}

}

/**
 * 
 */
package com.algorithm.plane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.algorithm.kdtree.*;
import com.algorithm.triangle.*;

/**
 * @author Administrator
 *
 * 2014年5月6日
 */
public class PlanScanInterSection {

	private List<KdPoint> intersection=new ArrayList<KdPoint>();
	private List<Line> state=new ArrayList<Line>();
	private List<Event> processedEvent=new ArrayList<Event>();
	private PriorityQueue<Event> eventQueue=new PriorityQueue<Event>(10,new EventCompare());
	public PlanScanInterSection(List<Line> lines,List<Event> events){
		for(Event e:events){
			eventQueue.add(e);
		}
		
	}
	
	/*
	 * 线段求交
	 */
	public void FindIntersections(){
		
		while(!eventQueue.isEmpty()){
			Event e=eventQueue.poll();
			processedEvent.add(e);
			HandleEventPoint(e);
			
		}
		
		
	}
	
	//处理事件
	public void HandleEventPoint(Event e){
		
		/*
		 * up为以p为上端点的线段的集合， lp是以p为下端点的线段的集合，cp是包含p的线段的集合
		 */
		List<Line> up=new ArrayList<Line>();
		List<Line> cp=new ArrayList<Line>();
		List<Line> lp=new ArrayList<Line>();
		up=e.getLines();
		for(Line l:state){
			boolean result=LineUtil.isContain(l, e.getPoint());
			if(result){
				if(e.getPoint().getX()==l.getEnd().getX()&&e.getPoint().getY()==l.getEnd().getY()){
					lp.add(l);
				}
				else{
					cp.add(l);
				}
			}
		}
		
	
		/*
		 * 如果up、cp、lp中包含的线段不止一条,发现交点p
		 */
		if((up.size()+lp.size()+cp.size())>1){
			intersection.add(e.getPoint());
		}
		
		/*
		 * 将L(P)和C(P)中的线段从T中删除
		 */
		for(Line line:lp){
			state.remove(line);
		}
		
		for(Line line:cp){
			state.remove(line);
		}
		
		/*
		 * 将U(P)和C(P)中的线段插入到T中
		 */
		
		for(Line line:up){
			state.add(line);
		}
		
		for(Line line:cp){
			state.add(line);
		}
		
		//扫描线维持有序
		LineUtil.sortLine(state,new Line(new KdPoint(Double.MIN_VALUE,e.getPoint().getY()-0.1),new KdPoint(Double.MAX_VALUE,e.getPoint().getY()-0.1)));
		
		if(up.size()+cp.size()==0){
			
			/*
			 * 在T中找出p的左右邻居sl和sr
			 */
			Line sl = getLeft(e.getPoint());
			Line sr = getRight(e.getPoint());
			FindNewEvent(sl, sr, e.getPoint());
		}
		else{
			/*
			 * 在T中找出U(P)UC(P)里最左边的sa
			 * 在T中找出与sa紧邻于左侧的sl
			 */
			Line sa=getLeft(up, cp, e.getPoint());
			Line sl=getLeft(sa);
			FindNewEvent(sl, sa, e.getPoint());
			
			/*
			 * 在T中找出U(P)UC(P)里最右边的sb
			 * 在T中找出与s紧邻于右侧的sr
			 */
			Line sb=getRight(up, cp, e.getPoint());
			Line sr=getRight(sb);
			FindNewEvent(sb, sr, e.getPoint());
			
			
		}
		
	}
	
	/*
	 * 找到新的事件
	 */
	public void FindNewEvent(Line line1,Line line2,KdPoint p){
		KdPoint point=LineUtil.getIntersect(line1, line2);
		
		/*
		 * sl和sr的交点，如果在当前扫描线的下方，或者落在扫描线上，并且在当前事件点的右侧
		 * 
		 */
		if(point!=null){
			if(point.getY()<p.getY()){
				Event e=new Event();
				e.setPoint(point);
				e.setLines(new ArrayList<Line>());
				if(!eventQueue.contains(e)){
					if(!processedEvent.contains(e)){
						eventQueue.add(e);
					}
					
				}
			}
			else if(point.getY()==p.getY()){
				if(point.getX()>p.getX()){
					Event e=new Event();
					e.setPoint(point);
					e.setLines(new ArrayList<Line>());
					if(!eventQueue.contains(e)){
						if(!processedEvent.contains(e)){
							eventQueue.add(e);
						}
					}
				}
			}
		}
		
	}

	/*
	 * 
	 */
	public Line getLeft(KdPoint p){
		Line scan=new Line(new KdPoint(Double.MIN_VALUE,p.getY()-0.1),new KdPoint(Double.MAX_VALUE,p.getY()-0.1));
		Line result=state.get(0);
		for(Line line:state){
			KdPoint point=LineUtil.getIntersection(line, scan);
			if(point.getY()>p.getY()){
				return result;
			}
			result=line;
		}
		return null;
	}
	
	/*
	 * 
	 */
	public Line getRight(KdPoint p){
		Line scan=new Line(new KdPoint(Double.MIN_VALUE,p.getY()-0.1),new KdPoint(Double.MAX_VALUE,p.getY()-0.1));
		for(Line line:state){
			KdPoint point=LineUtil.getIntersection(line, scan);
			if(point.getY()>p.getY()){
				return line;
			}
		}
		return null;
	}
	
	public Line getLeft(List<Line> up,List<Line> cp,KdPoint p){
		Line scan=new Line(new KdPoint(Double.MIN_VALUE,p.getY()-0.1),new KdPoint(Double.MAX_VALUE,p.getY()-0.1));
		LineCompare compare=new LineCompare();
		compare.setScan(scan);
		List<Line> lines=new ArrayList<Line>();
		lines.addAll(up);
		lines.addAll(cp);
		Collections.sort(lines, compare);
		return lines.get(0);
	}
	
	public Line getRight(List<Line> up,List<Line> cp,KdPoint p){
		Line scan=new Line(new KdPoint(Double.MIN_VALUE,p.getY()-0.1),new KdPoint(Double.MAX_VALUE,p.getY()-0.1));
		LineCompare compare=new LineCompare();
		compare.setScan(scan);
		List<Line> lines=new ArrayList<Line>();
		lines.addAll(up);
		lines.addAll(cp);
		Collections.sort(lines, compare);
		return lines.get(lines.size()-1);
	}
	
	public Line getLeft(Line line){
		
		for(int i=state.size();i>0;i--){
			Line l=state.get(i-1);
			if(l.equals(line)){
				if(i>1){
					return state.get(i-2);
				}
				else{
					return state.get(i-1);
				}
			}
		}
		return null;
	}
	
	public Line getRight(Line line){
		
		for(int i=0;i<state.size();i++){
			Line l=state.get(i);
			if(l.equals(line)){
				if(i!=state.size()-1){
					return state.get(i+1);
				}
				return state.get(i);
			}
		}
		return null;
	}
	public List<KdPoint> getIntersection() {
		return intersection;
	}

	public void setIntersection(List<KdPoint> intersection) {
		this.intersection = intersection;
	}
}
/**
 * 
 */
package com.niubaisui.util;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.niubaisui.tree.KdPoint;
import com.niubaisui.triangulation.Line;

/**
 * @author Administrator
 *
 * 2014Äê5ÔÂ8ÈÕ
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

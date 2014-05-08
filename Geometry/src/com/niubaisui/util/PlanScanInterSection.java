/**
 * 
 */
package com.niubaisui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.niubaisui.tree.KdPoint;
import com.niubaisui.triangulation.Line;

/**
 * @author Administrator
 *
 * 2014��5��6��
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
	 * �߶���
	 */
	public void FindIntersections(){
		
		while(!eventQueue.isEmpty()){
			Event e=eventQueue.poll();
			processedEvent.add(e);
			HandleEventPoint(e);
			
		}
		
		
	}
	
	//�����¼�
	public void HandleEventPoint(Event e){
		
		/*
		 * upΪ��pΪ�϶˵���߶εļ��ϣ� lp����pΪ�¶˵���߶εļ��ϣ�cp�ǰ���p���߶εļ���
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
		 * ���up��cp��lp�а������߶β�ֹһ��,���ֽ���p
		 */
		if((up.size()+lp.size()+cp.size())>1){
			intersection.add(e.getPoint());
		}
		
		/*
		 * ��L(P)��C(P)�е��߶δ�T��ɾ��
		 */
		for(Line line:lp){
			state.remove(line);
		}
		
		for(Line line:cp){
			state.remove(line);
		}
		
		/*
		 * ��U(P)��C(P)�е��߶β��뵽T��
		 */
		
		for(Line line:up){
			state.add(line);
		}
		
		for(Line line:cp){
			state.add(line);
		}
		
		//ɨ����ά������
		LineUtil.sortLine(state,new Line(new KdPoint(Double.MIN_VALUE,e.getPoint().getY()-0.1),new KdPoint(Double.MAX_VALUE,e.getPoint().getY()-0.1)));
		
		if(up.size()+cp.size()==0){
			
			/*
			 * ��T���ҳ�p�������ھ�sl��sr
			 */
			Line sl = getLeft(e.getPoint());
			Line sr = getRight(e.getPoint());
			FindNewEvent(sl, sr, e.getPoint());
		}
		else{
			/*
			 * ��T���ҳ�U(P)UC(P)������ߵ�sa
			 * ��T���ҳ���sa����������sl
			 */
			Line sa=getLeft(up, cp, e.getPoint());
			Line sl=getLeft(sa);
			FindNewEvent(sl, sa, e.getPoint());
			
			/*
			 * ��T���ҳ�U(P)UC(P)�����ұߵ�sb
			 * ��T���ҳ���s�������Ҳ��sr
			 */
			Line sb=getRight(up, cp, e.getPoint());
			Line sr=getRight(sb);
			FindNewEvent(sb, sr, e.getPoint());
			
			
		}
		
	}
	
	/*
	 * �ҵ��µ��¼�
	 */
	public void FindNewEvent(Line line1,Line line2,KdPoint p){
		KdPoint point=LineUtil.getIntersect(line1, line2);
		
		/*
		 * sl��sr�Ľ��㣬����ڵ�ǰɨ���ߵ��·�����������ɨ�����ϣ������ڵ�ǰ�¼�����Ҳ�
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

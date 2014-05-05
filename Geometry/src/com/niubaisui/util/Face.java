/**
 * 
 */
package com.niubaisui.util;

import java.util.List;

/**
 * @author Administrator
 *
 * 2014年5月5日
 */
public class Face {
	private HalfEdge outerComponent;//指向该面的外边界,无界面为nil
	private List<HalfEdge> innerComponents;//指向该面的各个空洞的任意一条边
	public HalfEdge getOuterComponent() {
		return outerComponent;
	}
	public void setOuterComponent(HalfEdge outerComponent) {
		this.outerComponent = outerComponent;
	}
	public List<HalfEdge> getInnerComponents() {
		return innerComponents;
	}
	public void setInnerComponents(List<HalfEdge> innerComponents) {
		this.innerComponents = innerComponents;
	}
	
}

/**
 * 
 */
package com.niubaisui.util;

import java.util.List;

/**
 * @author Administrator
 *
 * 2014��5��5��
 */
public class Face {
	private HalfEdge outerComponent;//ָ��������߽�,�޽���Ϊnil
	private List<HalfEdge> innerComponents;//ָ�����ĸ����ն�������һ����
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

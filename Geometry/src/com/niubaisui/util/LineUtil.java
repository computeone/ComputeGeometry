/**
 * 
 */
package com.niubaisui.util;

import com.niubaisui.tree.KdPoint;
import com.niubaisui.triangulation.Line;

/**
 * @author Administrator
 *
 * 2014��5��5��
 */
public class LineUtil {
	// �ж�һ���߶ΰ��������
	public static boolean isContain(Line line, KdPoint p) {
		double a, b, c, d;

		// ȷ������
		a = line.getStart().getY();
		b = line.getEnd().getY();
		if (a > b) {
			double tmp = a;
			a = b;
			b = tmp;
		}
		c = line.getStart().getX();
		d = line.getEnd().getX();
		if (c > d) {
			double tmp = c;
			c = d;
			d = tmp;
		}

		if (line.getStart().equals(p)) {
			return true;
		}
		if (line.getEnd().equals(p)) {
			return true;
		}

		// �ж��ڲ����������
		if (!(p.getX() < d && p.getX() > c) || !(p.getY() < b && p.getY() > a)) {
			return false;
		}
		
		
		double k = (line.getEnd().getY() - line.getStart().getY())
				/ (line.getEnd().getX() - line.getStart().getX());
		double m = line.getStart().getY() - k * line.getStart().getX();

		double y0 = k * p.getX() + m;
		if (y0 != p.getY()) {
			return false;
		}

		return true;
	}
}

package com.algorithm.matrix;

public class Matrix44Util {
	
	public static Matrix44d addMatrix44d(Matrix44d m1,Matrix44d m2){
		Matrix44d m=new Matrix44d();
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				m.setElement(i, j, m1.getElement(i, j)+m2.getElement(i, j));
			}
		}
		return m;
	}
	
	public static Matrix44d subMatrix44d(Matrix44d m1,Matrix44d m2){
		Matrix44d m=new Matrix44d();
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				m.setElement(i, j, m1.getElement(i, j)-m2.getElement(i, j));
			}
		}
		return m;
	}
	public static Matrix44d mutilMatrix44d(Matrix44d m1,Matrix44d m2){
		Matrix44d m=new Matrix44d();
		for(int i=0;i<4;i++){
			for(int j=0,k=0;j<4;j++,k++){
				m.setElement(i, j, m.getElement(i, j)+m1.getElement(i, j)*m2.getElement(j, k));
			}
		}
		return m;
		
	}
	
	public static Point4D scale(Point4D p,double x,double y,double z){
		Matrix44d m=new Matrix44d();
		m.setElement(0, 0, x);
		m.setElement(1, 1, y);
		m.setElement(2, 2, z);
		m.setElement(3, 3, 1);
		
		double[] d=new double[4];
		double[] dest=new double[4];
		d[0]=p.x;
		d[1]=p.y;
		d[2]=p.z;
		d[3]=p.w;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				dest[i]=d[j]*m.getElement(j, i)+dest[i];
			}
		}
		p.x=dest[0];
		p.y=dest[1];
		p.z=dest[2];
		p.w=dest[3];
		return p;
	}
	
	/* {c+(1-c)x^2   (1-c)xy+sz   (1-c)xz-sy   0}
	 * {(1-c)xy-sz   c+(1-c)y^2   (1-c)yz+sx   0}
	 * {(1-c)xz+sy  (1-c)yz-sx    c+(1-c)z^2   0}
	 * {0             0            0           1}
	 */
	public static Point4D rotation(Point4D p,double x,double y,double z,double angle){
		Matrix44d m=new Matrix44d();
		m.setElement(0, 0, Math.cos(angle)+(1-Math.cos(angle))*Math.pow(x,2));
		m.setElement(0, 1, (1-Math.cos(angle))*x*y+Math.sin(angle)*z);
		m.setElement(0, 2, (1-Math.cos(angle))*x*z-Math.sin(angle)*y);
		
		m.setElement(1, 0, (1-Math.cos(angle))*x*y-Math.sin(angle)*z);
		m.setElement(1, 1, Math.cos(angle)+(1-Math.cos(angle))*Math.pow(y,2));
		m.setElement(1, 2, (1-Math.cos(angle))*y*z+Math.sin(angle)*x);
		
		m.setElement(2, 0, (1-Math.cos(angle))*x*z+Math.sin(angle)*y);
		m.setElement(2, 1, (1-Math.cos(angle))*y*z-Math.sin(angle)*x);
		m.setElement(2, 2, Math.cos(angle)+(1-Math.cos(angle))*Math.pow(z, 2));
		
		m.setElement(3, 3, 1);
		
		double[] d=new double[4];
		double[] dest=new double[4];
		d[0]=p.x;
		d[1]=p.y;
		d[2]=p.z;
		d[3]=p.w;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				dest[i]=d[j]*m.getElement(j, i)+dest[i];
			}
		}
		p.x=dest[0];
		p.y=dest[1];
		p.z=dest[2];
		p.w=dest[3];
		return p;
	}
	/*
	 * {1  0  0  0}
	 * {0  1  0  0}
	 * {0  0  1  0}
	 * {bx by bz 1}
	 */
	public static Point4D transform(Point4D p,double x,double y,double z){
		
		Matrix44d m=new Matrix44d();
		m.setElement(0, 0, 1);
		m.setElement(1, 1, 1);
		m.setElement(2, 2, 1);
		m.setElement(3, 3, 1);
		
		m.setElement(3, 0, x);
		m.setElement(3, 1, y);
		m.setElement(3, 2, z);
		
		
		double[] d=new double[4];
		double[] dest=new double[4];
		d[0]=p.x;
		d[1]=p.y;
		d[2]=p.z;
		d[3]=p.w;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				dest[i]=d[j]*m.getElement(j, i)+dest[i];
			}
		}
		p.x=dest[0];
		p.y=dest[1];
		p.z=dest[2];
		p.w=dest[3];
		return p;
	}
	/*
	 * {2/r-l   0        0     -(r+l)/(r-l)}
	 * {0       2/t-b    0     -(t+b)/(t-b)}
	 * {0       0        1/f-n -n/(f-n)    }
	 * {0       0        0      1          }      
	 * 
	 */
	
	public static Point4D Orthographic(Point4D p,double xMin,double xMax,double yMin,double yMax,double zMin
			,double zMax){
		Matrix44d  m=new Matrix44d();
		m.setElement(0, 0, 2);
		m.setElement(0, 3, 2);
		m.setElement(1, 1, 2);
		m.setElement(1, 3, 2);
		m.setElement(2, 2, 1);
		m.setElement(2, 3, 2);
		m.setElement(3, 3, 1);
		
		
		return p;
		
	}
	
	/*
	 * {2n/r-l      0        -(r+l)/(r-l)     0}
	 * {0           2n/t-b   -(t+b)/(t-b)     0}
	 * {0           0        f/f-n    -fn/(f-n)}
	 * {0           0        1                0}
	 */
	public static Point4D prespective(Point4D p,double fFov,double fAspect,double zMin,double zMax){
		Matrix44d m=new Matrix44d();
		m.setElement(0, 0, 1);
		m.setElement(0, 2, 1);
		m.setElement(1, 1, 2);
		m.setElement(1, 2, 2);
		m.setElement(2, 2, 1);
		m.setElement(2, 3, 3);
		m.setElement(3,2,1);
		
		
		return p;
	}
	/*
	 * {rx      ux    dx    0}
	 * {ry      uy    dy    0}
	 * {rz      uz    dz    0}
	 * {-pr     -pu   -pd   1}
	 * 
	 */
	public static Point4D viewTranslate(Point4D  p){
		
		return p;
	}
	/*
	 * {width/2      0      0       0}
	 * {0        -height/2  0       0}
	 * {0            0    maxz-minz 0}
	 * {x+width/2 y+height/2 minz   0}
	 */
	public static Point4D viewPortTranslate(Point4D p){
		return p;
	}
	public static void main(String[] args) {
		double[][] d1=new double[4][4];
		for(int i=0;i<4;i++){
			d1[i][i]=1;
		}
		double[][] d2=new double[4][4];
		d2[0][0]=1;
		d2[1][1]=2;
		d2[2][2]=4;
		d2[3][0]=1;
		d2[3][1]=2;
		d2[3][2]=3;
		d2[3][3]=1;
		Point4D p=new Point4D(1,0,1,1);
		Point4D p1=new Point4D(1,0,1,1);
		Point4D p2=new Point4D(-8,2,0,1);
		Matrix44d m1=new Matrix44d(d2);
		Matrix44d m2=new Matrix44d(d1);
		System.out.println(Matrix44Util.mutilMatrix44d(m1, m2));
		System.out.println(Matrix44Util.addMatrix44d(m1, m2));
		System.out.println(Matrix44Util.subMatrix44d(m1, m2));
		System.out.println(Matrix44Util.scale(p, 2, 2, 1));
		System.out.println(Matrix44Util.rotation(p1, 0, 1, 0, Math.PI/-6));
		System.out.println(Matrix44Util.transform(p2, 12, -10, 0));
	}

}

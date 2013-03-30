package com.niubaisui;

import java.math.BigInteger;
import java.util.LinkedList;
/*
 * @author niubaisui
 * 
 */
public class NTheory {
	private LinkedList<BigInteger> list=new LinkedList<BigInteger>();
	//�����������
	public int[] getTrigle(int s,int t){
		int a,b,c;
		a=s*t;
		b=(s*s-t*t)/2;
		c=(s*s+t*t)/2;
		int[] result=new int[3];
		result[0]=a;
		result[1]=b;
		result[2]=c;
		return result;
	}
	
	//�õ���ָ���������ڵ����й�����
	public LinkedList<int[]> getTrigle(int limt){
		LinkedList<int[]> list=new LinkedList<int[]>();
		for(int i=1;i<limt;i=i+2){
			for(int j=i+2;j<limt;j=j+2){
				int[] result=getTrigle(j,i);
				list.add(result);
			}
		}
		return list;
	}
	
	//����������
	private BigInteger getGcd(BigInteger a,BigInteger b){
		if(a.compareTo(b)==1){
			return getDiv(a,b);
		}
		if(a==b){
			return a;
		}
		else{
			return getDiv(b,a);
		}
	}
	
	//��longת��ΪBigInteger
	public BigInteger getBigInteger_Long(long s){
		String str=String.valueOf(s);
		return new BigInteger(str);
	}
	
	
	//��Intת��ΪBigInteger
	public BigInteger getBigInteger_Int(int s){
		String str=String.valueOf(s);
		return new BigInteger(str);
	}
	
	
	//ŷ������㷨,���������
	private BigInteger getDiv(BigInteger a,BigInteger b){
		BigInteger s=a.remainder(b);
		BigInteger q=a.divide(b);
		list.addLast(q);
		if(s.equals(BigInteger.ZERO)){
			return b;
		}
		else{
			return getDiv(b,s);
		}
	}
	
	//������������ṩ���нӿ�
	public BigInteger getMax_Common_Factor(BigInteger a,BigInteger b){
		BigInteger s=a.remainder(b);
		if(s.equals(BigInteger.ZERO)){
			return b;
		}
		else{
			return getMax_Common_Factor(b, s);
		}
	}
	
	//��gcd
	public BigInteger getGCD(BigInteger a,BigInteger b){
		if(a.compareTo(b)==1){
			return getMax_Common_Factor(a, b);
		}
		if(a==b){
			return a;
		}
		else{
			return getMax_Common_Factor(b, a);
		}
	}
	//������������,�����ֽ�
	public LinkedList<Long> getFactor(long s){
		LinkedList<Long> list=new LinkedList<Long>();
		while(!isPrime(s)){
			for(long i=2;i<=s;i++){
				long r=s%i;
				if(r==0&&isPrime(i)){
					list.add(i);
					break;
				}
			}
		}
		list.add(s);
		return list;
	}
	
	/*
	 * �ж�һ�����ǲ�������
	 * �д����Ż�
	 */
	public boolean isPrime(long s){
		double sqrt=Math.sqrt(s);
		for(long i=2;i<=(long)Math.abs(sqrt);i++){
			long r=s%i;
			if(r==0){
				return  false;
			}
		}
		return true;
	}
	//��ͬ��ʽ����ax=c(mod m)�����н�
	public LinkedList<BigInteger> getLiners(BigInteger a,BigInteger m,BigInteger c){
		BigInteger g=getGCD(a,m.multiply(new BigInteger("-1")));
		if(!c.remainder(g).equals(BigInteger.ZERO)){
			return null;
		}
		Point r=getLiner(a,m.multiply(new BigInteger("-1")));
			
		LinkedList<BigInteger> p=new LinkedList<BigInteger>();
		BigInteger x0=r.getX().multiply(c.divide(g));
		p.add(x0);
		for(long k=1;k<Math.abs(g.longValue());k++){
			p.add(x0.add(this.getBigInteger_Long(k*(m.divide(g).longValue()))));
		}
		//���е���ʹ�ø�����Ϊ����
		for(int i=0;i<p.size();i++){
			while(p.get(i).compareTo(BigInteger.ZERO)==-1){
				p.set(i, p.get(i).add(m.abs()));
			}
			while(p.get(i).compareTo(m.abs())==1){
				p.set(i, p.get(i).subtract(m.abs()));
			}
		}
		return p;
	}
	
	//������Է��̵�һ����ax+by=gcd(a,b)  
	/*
	 * ͨ��
	 */
	public Point getLiner(BigInteger a,BigInteger b){
		getGcd(a, b);
		int n=list.size();
		//��ʼ��r1��r2
		BigInteger q1=list.pollFirst();
		Point r1=new Point(BigInteger.ONE,new BigInteger("-1").multiply(q1));
		BigInteger q2=list.pollFirst();
		Point r2=new Point(new BigInteger("-1").multiply(q2),q1.multiply(q2).add(BigInteger.ONE));
		
		//���ĵļ�����̣�����ŷ������㷨
		Point r=r2;;
		for(int i=0;i<n-3;i++){
			BigInteger q=list.pollFirst();
			Point temp=new Point(r2.getX(),r2.getY());
			r2.setX(new BigInteger("-1").multiply(q).multiply(r2.getX()));
			r2.setY(new BigInteger("-1").multiply(q).multiply(r2.getY()));
			r=pointAdd(r1, r2);
			r1=temp;
			r2=r;
		}
		list.poll();
		return r;
	}
	//����ʽ����
	private Point pointAdd(Point a,Point b){
		Point result=new Point(BigInteger.ZERO,BigInteger.ZERO);
		result.setX(a.getX().add(b.getX()));
		result.setY(a.getY().add(b.getY()));
		return result;
	}
	
	/*
	 * ��ŷ����ʽ����m���ص����ĸ�����1<=a<=m  ($(m))
	 * �д����Ż�
	 */
	public long getPrimeNum(long p){
		long n=0;
		
		//���������������$(p)=p
		if(isPrime(p)){
			return p-1;
		}
		//��ٱ���
		for(long i=1;i<=p;i++){
			if(isRelatePrime(this.getBigInteger_Long(i), this.getBigInteger_Long(p))){
				n++;
			}
		}
		return n;
	}
	
//	//�ж��������ǲ��ǻ���
	public boolean isRelatePrime(BigInteger a,BigInteger b){
		if(getGCD(a, b).equals(BigInteger.ONE)){
			return true;
		}
		else{
			return false;
		}
	}
	
	//��$(mn)���gcd(m,n)=1 ����$(mn)=$(m)*$(n)
	public BigInteger getPrime_Num(BigInteger m,BigInteger n){
		
		if(getGCD(m, n).equals(BigInteger.ONE)){		
			return this.getBigInteger_Long(getPrimeNum(m.longValue())*getPrimeNum(n.longValue()));
		}
		else{
			return new BigInteger("-1");
		}
	}
	
	
	//��$(p^k)=p^k-p^(k-1)
	public BigInteger getNum_Prime_Steps(BigInteger p,int k){
		if(isPrime(p.longValue())){
			return p.pow(k).subtract(p.pow(k-1));
		}
		return new BigInteger("-1");
	}
	

	//n����������֮��
	public BigInteger getAll_Factor_Sum(BigInteger n){
		long sum=0;
		for(long i=1;i<=n.longValue();i++){
			if(n.longValue()%i==0){
				sum=sum+i;
			}
		}
		return this.getBigInteger_Long(sum);
	}
	
	//���ƽ��������a^x(mod b)
	public BigInteger getGradualy_Square(BigInteger a,BigInteger x,BigInteger b){
		//������չ��
		LinkedList<Integer> binary=new LinkedList<Integer>();
		BigInteger t=new BigInteger("1");
		for(int i=0;i<x.bitLength();i++){
			BigInteger temp=t.and(x);
			if(temp.compareTo(t)>-1){
				binary.add(i);
			}
			t=t.shiftLeft(1);
		}
		
		//�����ݴα�
		LinkedList<BigInteger> min=new LinkedList<BigInteger>();
		min.add(a);
		for(int i=0;i<binary.peekLast();i++){
			BigInteger temp=min.peekLast().pow(2);
			BigInteger remainder=temp.remainder(b);
			min.add(remainder);
		}
		
		//��ý��
		BigInteger sum=new BigInteger("1");
		int i=0;
		while(i<binary.size()){
			sum=sum.multiply(min.get(binary.get(i)));
			i++;
		}
		sum=sum.remainder(b);
		return sum;
	}
	
	//��ģm��k�θ�
	public BigInteger getK_Root(BigInteger k,BigInteger b,BigInteger m){
		//b��mҪ����
		if(!getGCD(b, m).equals(BigInteger.ONE)){
			return new BigInteger("-1");
		}
		//k��$(m)����
		BigInteger num=this.getBigInteger_Long(getPrimeNum(m.longValue()));//�õ�ŷ������ֵ
		if(!getGCD(k, num).equals(BigInteger.ONE)){
			return new BigInteger("-1");
		}
		//��ku-$(m)v=1��������u��v
		Point r=getLiner(k, new BigInteger("-1").multiply(num));
		r.setX(r.getX().multiply(new BigInteger("-1")));
		r.setY(r.getY().multiply(new BigInteger("-1")));
		
		//����ʹ��u��v��Ϊ����
		if(r.getX().compareTo(BigInteger.ZERO)<0){
			r.setX(r.getX().add(k.abs()));			
		}
		if(r.getY().compareTo(BigInteger.ZERO)<0){
			r.setY(r.getY().add(k.abs()));
		}
		//�����ƽ�������
		BigInteger root=getGradualy_Square(b, r.getX(), m);
		
		return root;
	}
	
	//��ģm��k�η���������
	public BigInteger getK_Root(BigInteger k, BigInteger b, BigInteger p,
			BigInteger q) {
		// b��mҪ����
		BigInteger m = p.multiply(q);
		// b��mҪ����
		if (!getGCD(b, m).equals(BigInteger.ONE)) {
			return new BigInteger("-1");
		}
		// k��$(m)����
		BigInteger num = getPrime_Num(p,q);// �õ�ŷ������ֵ
		if (!getGCD(k, num).equals(BigInteger.ONE)) {
			return new BigInteger("-1");
		}
		// ��ku-$(m)v=1��������u��v
		Point r = getLiner(k, new BigInteger("-1").multiply(num));
		r.setX(r.getX().multiply(new BigInteger("-1")));
		r.setY(r.getY().multiply(new BigInteger("-1")));

		// ����ʹ��u��v��Ϊ����
		if (r.getX().compareTo(BigInteger.ZERO) < 0) {
			r.setX(r.getX().add(num.abs()));
		}
		if (r.getY().compareTo(BigInteger.ZERO) < 0) {
			r.setY(r.getY().add(k.abs()));
		}
		// �����ƽ�������
		BigInteger root = getGradualy_Square(b, r.getX(), m);

		return root;
	}
	public static void main(String[] args) {
		NTheory ntheory=new NTheory();
//		BigInteger big=new BigInteger("1313423432434");
//		BigInteger big1=new BigInteger("7583434234343");
//		BigInteger big2=new BigInteger("29");
//		BigInteger q=new BigInteger("37");
//		LinkedList<BigInteger> b1=ntheory.getLiners(big, big1,big2);
//		System.out.println(b1.peekFirst());
//		BigInteger b=ntheory.getGradualy_Square(big, big1, big2);
//		System.out.println(b);
//		BigInteger f=ntheory.getK_Root(big, big1, big2, q);
//		System.out.println(f);
//		
		
		//
		String s=new String("rld");
		byte[] by=s.getBytes();
		BigInteger bi=new BigInteger(by);
		byte[] bi1=bi.toByteArray();
		System.out.println(s);
		for(byte by1:by){
			System.out.print(by1+" ");
		}
		System.out.println();
		System.out.println(new BigInteger(by));
		BigInteger dest=ntheory.getGradualy_Square(new BigInteger(by), new BigInteger("48611"), new BigInteger("30796045883"));
		System.out.println(dest);
		BigInteger src=ntheory.getK_Root(new BigInteger("48611"),dest, new BigInteger("187963"),new BigInteger("163841"));
		byte[] bb=src.toByteArray();
//		String str=new String(bb);
//		System.out.println(str);
		for(byte b1:bb){
			System.out.print(b1+" ");
		}
		System.out.println();
		System.out.println(new String(bb));
//		System.out.println(ntheory.getGCD(new BigInteger("189"), new BigInteger("837488646")));
//		for(int i=1000;i<100000;i++){
//			if(ntheory.isPrime(i)){
//				System.out.println(i);
//			}
//		}
//		BigInteger r=new BigInteger("2401");
//		BigDecimal d=new BigDecimal(r);
//		System.out.println(r.bitLength());
//		
//		System.out.println(ntheory.getPrimeNum(r.longValue()));
//		System.out.println(ntheory.isRelatePrime(new BigInteger("477466"), new BigInteger("21312")));
		
	}

}

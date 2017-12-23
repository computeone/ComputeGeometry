package com.algorithm.rsa;

import java.math.BigInteger;
import java.util.LinkedList;
/*
 * @author niubaisui
 * 
 */
public class NTheory {
	private LinkedList<BigInteger> list=new LinkedList<BigInteger>();
	//求出勾股数组
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
	
	//得到在指定数字以内的所有勾股数
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
	
	//求得最大公因数
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
	
	//将long转化为BigInteger
	public BigInteger getBigInteger_Long(long s){
		String str=String.valueOf(s);
		return new BigInteger(str);
	}
	
	
	//将Int转化为BigInteger
	public BigInteger getBigInteger_Int(int s){
		String str=String.valueOf(s);
		return new BigInteger(str);
	}
	
	
	//欧几里得算法,求最大公因数
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
	
	//求最大公因数，提供公有接口
	public BigInteger getMax_Common_Factor(BigInteger a,BigInteger b){
		BigInteger s=a.remainder(b);
		if(s.equals(BigInteger.ZERO)){
			return b;
		}
		else{
			return getMax_Common_Factor(b, s);
		}
	}
	
	//求gcd
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
	//算数基本定理,因数分解
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
	 * 判断一个数是不是素数
	 * 有待于优化
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
	//求同余式方程ax=c(mod m)的所有解
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
		//进行调整使得负数变为正数
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
	
	//求得线性方程的一个解ax+by=gcd(a,b)  
	/*
	 * 通过
	 */
	public Point getLiner(BigInteger a,BigInteger b){
		getGcd(a, b);
		int n=list.size();
		//初始化r1和r2
		BigInteger q1=list.pollFirst();
		Point r1=new Point(BigInteger.ONE,new BigInteger("-1").multiply(q1));
		BigInteger q2=list.pollFirst();
		Point r2=new Point(new BigInteger("-1").multiply(q2),q1.multiply(q2).add(BigInteger.ONE));
		
		//核心的计算过程，基于欧几里得算法
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
	//多项式运算
	private Point pointAdd(Point a,Point b){
		Point result=new Point(BigInteger.ZERO,BigInteger.ZERO);
		result.setX(a.getX().add(b.getX()));
		result.setY(a.getY().add(b.getY()));
		return result;
	}
	
	/*
	 * 求欧拉公式中与m互素的数的个数，1<=a<=m  ($(m))
	 * 有待于优化
	 */
	public long getPrimeNum(long p){
		long n=0;
		
		//如果是素数，则有$(p)=p
		if(isPrime(p)){
			return p-1;
		}
		//穷举遍历
		for(long i=1;i<=p;i++){
			if(isRelatePrime(this.getBigInteger_Long(i), this.getBigInteger_Long(p))){
				n++;
			}
		}
		return n;
	}
	
//	//判断两个数是不是互素
	public boolean isRelatePrime(BigInteger a,BigInteger b){
		if(getGCD(a, b).equals(BigInteger.ONE)){
			return true;
		}
		else{
			return false;
		}
	}
	
	//求$(mn)如果gcd(m,n)=1 则有$(mn)=$(m)*$(n)
	public BigInteger getPrime_Num(BigInteger m,BigInteger n){
		
		if(getGCD(m, n).equals(BigInteger.ONE)){		
			return this.getBigInteger_Long(getPrimeNum(m.longValue())*getPrimeNum(n.longValue()));
		}
		else{
			return new BigInteger("-1");
		}
	}
	
	
	//求$(p^k)=p^k-p^(k-1)
	public BigInteger getNum_Prime_Steps(BigInteger p,int k){
		if(isPrime(p.longValue())){
			return p.pow(k).subtract(p.pow(k-1));
		}
		return new BigInteger("-1");
	}
	

	//n的所有因数之和
	public BigInteger getAll_Factor_Sum(BigInteger n){
		long sum=0;
		for(long i=1;i<=n.longValue();i++){
			if(n.longValue()%i==0){
				sum=sum+i;
			}
		}
		return this.getBigInteger_Long(sum);
	}
	
	//逐次平方法，求a^x(mod b)
	public BigInteger getGradualy_Square(BigInteger a,BigInteger x,BigInteger b){
		//二进制展开
		LinkedList<Integer> binary=new LinkedList<Integer>();
		BigInteger t=new BigInteger("1");
		for(int i=0;i<x.bitLength();i++){
			BigInteger temp=t.and(x);
			if(temp.compareTo(t)>-1){
				binary.add(i);
			}
			t=t.shiftLeft(1);
		}
		
		//制作幂次表
		LinkedList<BigInteger> min=new LinkedList<BigInteger>();
		min.add(a);
		for(int i=0;i<binary.peekLast();i++){
			BigInteger temp=min.peekLast().pow(2);
			BigInteger remainder=temp.remainder(b);
			min.add(remainder);
		}
		
		//求得结果
		BigInteger sum=new BigInteger("1");
		int i=0;
		while(i<binary.size()){
			sum=sum.multiply(min.get(binary.get(i)));
			i++;
		}
		sum=sum.remainder(b);
		return sum;
	}
	
	//求模m的k次根
	public BigInteger getK_Root(BigInteger k,BigInteger b,BigInteger m){
		//b与m要互素
		if(!getGCD(b, m).equals(BigInteger.ONE)){
			return new BigInteger("-1");
		}
		//k与$(m)互素
		BigInteger num=this.getBigInteger_Long(getPrimeNum(m.longValue()));//得到欧拉函数值
		if(!getGCD(k, num).equals(BigInteger.ONE)){
			return new BigInteger("-1");
		}
		//求ku-$(m)v=1的正整数u与v
		Point r=getLiner(k, new BigInteger("-1").multiply(num));
		r.setX(r.getX().multiply(new BigInteger("-1")));
		r.setY(r.getY().multiply(new BigInteger("-1")));
		
		//修正使得u、v都为正数
		if(r.getX().compareTo(BigInteger.ZERO)<0){
			r.setX(r.getX().add(k.abs()));			
		}
		if(r.getY().compareTo(BigInteger.ZERO)<0){
			r.setY(r.getY().add(k.abs()));
		}
		//用逐次平方法求根
		BigInteger root=getGradualy_Square(b, r.getX(), m);
		
		return root;
	}
	
	//求模m的k次方根的重载
	public BigInteger getK_Root(BigInteger k, BigInteger b, BigInteger p,
			BigInteger q) {
		// b与m要互素
		BigInteger m = p.multiply(q);
		// b与m要互素
		if (!getGCD(b, m).equals(BigInteger.ONE)) {
			return new BigInteger("-1");
		}
		// k与$(m)互素
		BigInteger num = getPrime_Num(p,q);// 得到欧拉函数值
		if (!getGCD(k, num).equals(BigInteger.ONE)) {
			return new BigInteger("-1");
		}
		// 求ku-$(m)v=1的正整数u与v
		Point r = getLiner(k, new BigInteger("-1").multiply(num));
		r.setX(r.getX().multiply(new BigInteger("-1")));
		r.setY(r.getY().multiply(new BigInteger("-1")));

		// 修正使得u、v都为正数
		if (r.getX().compareTo(BigInteger.ZERO) < 0) {
			r.setX(r.getX().add(num.abs()));
		}
		if (r.getY().compareTo(BigInteger.ZERO) < 0) {
			r.setY(r.getY().add(k.abs()));
		}
		// 用逐次平方法求根
		BigInteger root = getGradualy_Square(b, r.getX(), m);

		return root;
	}
	public static void main(String[] args) {
		
	}

}

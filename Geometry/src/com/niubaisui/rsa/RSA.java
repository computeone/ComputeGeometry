/**
 * 
 */
package com.niubaisui.rsa;

import java.math.BigInteger;
import java.util.LinkedList;

/**
 * @author niubaisui
 *
 */
public class RSA {
	private int wrap_size=4;
	private int encrypt_wrap_size=5;
	private BigInteger k=new BigInteger("48611");
	private BigInteger p=new BigInteger("187963");
	private BigInteger q=new BigInteger("163841");
	private BigInteger m=this.p.multiply(this.q);
	//����
	public byte[] setEncrypt(byte[] bs){
		int n=0;
		LinkedList<BigInteger> goal=new LinkedList<BigInteger>();
		//���ĸ�byte�����һ��BigInteger��
		while(true){
						
			//���ĸ��ֽڴ��ΪBigInteger
				for(int i=0;i<bs.length/wrap_size*wrap_size;i=i+wrap_size){
					byte[] temp=new byte[wrap_size];
					for(int j=0;j<wrap_size;j++){
						temp[j]=bs[i+j];
						n++;
					}
					goal.addLast(new BigInteger(temp));
				}
				//�������ȫ������꣬���˳�ѭ��	
				if(bs.length%wrap_size==0){
					break;
				}
				//�Ѳ����ĸ��ֽڴ��Ϊһ��BigInteger
				else{
					byte[] temp=new byte[bs.length-n];
					for(int i=0;i<bs.length%wrap_size;i++){
						temp[i]=bs[n++];
					}
					goal.addLast(new BigInteger(temp));
					break;
				}
							
			
		}
		
		LinkedList<BigInteger> src=new LinkedList<BigInteger>();
		NTheory ntheory=new NTheory();
		//���м���
		while(!goal.isEmpty()){
			BigInteger s=ntheory.getGradualy_Square(goal.pollFirst(),this.k, this.m);
			src.addLast(s);
		}
		
		//��BigInteger���Ϊ�ĸ��ֽ�
		LinkedList<Byte> bd = new LinkedList<Byte>();
		
		// ��BigInteger���Ϊencrypt_wrap_size���ֽ�
		while (!src.isEmpty()) {
			BigInteger s = src.pollFirst();
			byte[] bb = s.toByteArray();
			//���bb�ĳ��ȵ�����������ǰ���һ���ֽ�
			if(bb.length==wrap_size){
				bd.addLast(new Byte("0"));
			}
			
			for (int i = 0; i < bb.length; i++) {
				bd.addLast(bb[i]);
			}
		}
		
		byte[] encrypt=new byte[bd.size()];
		for(int i=0;i<encrypt.length;i++){
			encrypt[i]=bd.pollFirst();
		}
		return encrypt;
	}
	
	// ����
	public byte[] getEncrypt(byte[] bs) {

		int n = 0;
		LinkedList<BigInteger> goal = new LinkedList<BigInteger>();

		// ���ĸ�byte�����һ��BigInteger��
		while (true) {

			// ���ĸ��ֽڴ��ΪBigInteger
			for (int i = 0; i < bs.length /encrypt_wrap_size* encrypt_wrap_size; i = i + encrypt_wrap_size) {
				byte[] temp = new byte[encrypt_wrap_size];
				for (int j = 0; j < encrypt_wrap_size; j++) {
					temp[j] = bs[i + j];
					n++;
				}
				goal.addLast(new BigInteger(temp));
			}
			// �������ȫ������꣬���˳�ѭ��
			if (bs.length % encrypt_wrap_size == 0) {
				break;
			}
			// �Ѳ����ĸ��ֽڴ��Ϊһ��BigInteger
			else {
				byte[] temp = new byte[bs.length - n];
				for (int i = 0; i < bs.length % encrypt_wrap_size; i++) {
					temp[i] = bs[n++];
				}
				goal.addLast(new BigInteger(temp));
				break;
			}

		}
		// ����
		LinkedList<BigInteger> src = new LinkedList<BigInteger>();
		NTheory ntheory = new NTheory();
		while (!goal.isEmpty()) {
			BigInteger l = ntheory.getK_Root(this.k, goal.pollFirst(), this.p,
					this.q);
			src.addLast(l);
		}

		LinkedList<Byte> bd = new LinkedList<Byte>();
		
		// ��BigInteger���Ϊ�ĸ��ֽ�
		while (!src.isEmpty()) {
			BigInteger s = src.pollFirst();
			byte[] bb = s.toByteArray();
			for (int i = 0; i < bb.length; i++) {
				bd.addLast(bb[i]);
			}
		}
		
		byte[] encrypt=new byte[bd.size()];
		for(int i=0;i<encrypt.length;i++){
			encrypt[i]=bd.pollFirst();
		}
		return encrypt;
	}

	public BigInteger getK() {
		return k;
	}

	public void setK(BigInteger k) {
		this.k = k;
	}


	public BigInteger getM() {
		return m;
	}

	public void setM(BigInteger m) {
		this.m = m;
	}

	public BigInteger getP() {
		return p;
	}

	public void setP(BigInteger p) {
		this.p = p;
	}

	public BigInteger getQ() {
		return q;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}
	
	
	public static void main(String[] args){
		RSA rsa=new RSA();		
		String str=new String("hello world niubaisui computer");
		

		byte[] encypt=rsa.setEncrypt(str.getBytes());
		String s=new String(encypt);
		System.out.println("���ģ�"+s);
		byte[] d=s.getBytes();
		byte[] src=rsa.getEncrypt(encypt);
		System.out.println("����:"+new String(src));
		
	}
}
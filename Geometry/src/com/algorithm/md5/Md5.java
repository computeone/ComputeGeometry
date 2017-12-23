package com.algorithm.md5;

public class Md5 {
	
	private int A=0x67452301;
	private int B=0xefcdab89;
	private int C=0x98badcfe;
	private int D=0x10325476;
	
	
	public byte[] group(byte[] str){
		long length=str.length*8;
		byte[] ss=null;
		int byteNum=0;
		if(length%512!=448){
			
			//当超过448位时
			if(length%512>448){
				byteNum=(512-(int)(length%512)+448)/8;
				byte[] bb=new byte[byteNum];
				bb[0]=-128;
				for(int i=1;i<byteNum;i++){
					bb[i]=0;
				}
				
				//填充bit
				ss=new byte[(int)(length/8)+byteNum+8];
				
				for(int i=0;i<str.length;i++){
					ss[i]=str[i];
				}
				for(int i=0;i<byteNum;i++){
					ss[str.length+i]=bb[i];
				}
			}
			//不足448位时
			else{
				byteNum=(448-(int)(length%512))/8;
				byte[] bb=new byte[byteNum];
				bb[0]=-128;
				for(int i=1;i<byteNum;i++){
					bb[i]=0;
				}
				//填充bit
				ss=new byte[(int)(length/8)+byteNum+8];
				for(int i=0;i<str.length;i++){
					ss[i]=str[i];
				}
				for(int i=0;i<byteNum;i++){
					ss[str.length+i]=bb[i];
				}
				
			}
		}
		
		//填充64位的长度
		byte[] bs=new byte[8];
		for(int i=0;i<8;i++){
			bs[i]=(byte)(length|(byte)0);
			length=length>>8;
		}
		
		for(int i=0;i<8;i++){
			ss[(int)(str.length)+byteNum+i]=bs[i];
		}
		return ss;
		
	}
	public int F(int x,int y,int z){
		
		return (x&y)|((~x)&z);
	}
	public int G(int x,int y,int z){
		
		return (x&z)|(y&(~z));
	}
	
	public int H(int x,int y,int z){
		
		return (x^y^z);
	}
	
	public int I(int x,int y,int z){
		
		return y^(x|(~z));
	}
	
	public int FF(int a,int b,int c,int d,int Mj,int s,int ti){
		
		return b+((a+F(b,c,d)+Mj+ti)<<s);
	}
	public int GG(int a,int b,int c,int d,int Mj,int s,int ti){
		
		return b+((a+G(b,c,d)+Mj+ti)<<s);
	}
	
	public int HH(int a,int b,int c,int d,int Mj,int s,int ti){
		
		return b+((a+H(b,c,d)+Mj+ti)<<s);
	}
	
	public int II(int a,int b,int c,int d,int Mj,int s,int ti){
		
		return b+((a+I(b,c,d)+Mj+ti)<<s);
	}
	
	public void md5(byte[] bb){
		int a=A;
		int b=B;
		int c=C;
		int d=D;
		int[] M=new int[16];
		for(int i=0;i<bb.length;i=i+4){
			int temp=0;
			for(int j=0;j<4;j++){
				temp=temp|bb[i+j];
				temp=temp<<8;
			}
			M[i/4]=temp;
			System.out.println(i/4+":"+M[i/4]);
		}
		//第一轮
		
		a=FF(a,b,c,d,M[0],7,0xd76aa478);
		d=FF(d,a,b,c,M[1],12,0xe8c7b756);
		c=FF(c,d,a,b,M[2],17,0x242070db);
		b=FF(b,c,d,a,M[3],22,0xc1bdceee);
	
		a=FF(a,b,c,d,M[4],7,0xf57c0faf);
		d=FF(d,a,b,c,M[5],12,0x4787c62a);
		c=FF(c,d,a,b,M[6],17,0xa8304613);
		b=FF(b,c,d,a,M[7],22,0xfd469501);
		
		a=FF(a,b,c,d,M[8],7,0x698098d8);
		d=FF(d,a,b,c,M[9],12,0x8b44f7af);
		c=FF(c,d,a,b,M[10],17,0xffff5bb1);
		b=FF(b,c,d,a,M[11],22,0x895cd7be);
		
		a=FF(a,b,c,d,M[12],7,0x6b901122);
		d=FF(d,a,b,c,M[13],12,0xfd987193);
		c=FF(c,d,a,b,M[14],17,0xa679438e);
		b=FF(b,c,d,a,M[15],22,0x49b40821);
		//第二轮
		a=GG(a,b,c,d,M[1],5,0xf61e2562);
		d=GG(d,a,b,c,M[6],9,0xc040b340);
		c=GG(c,d,a,b,M[11],14,0x265e5a51);
		b=GG(b,c,d,a,M[0],20,0xe9b6c7aa);
		
		a=GG(a,b,c,d,M[5],5,0xd62f105d);
		d=GG(d,a,b,c,M[10],9,0x02441453);
		c=GG(c,d,a,b,M[15],14,0xd8a1e681);
		b=GG(b,c,d,a,M[4],20,0xe7d3fbc8);
		
		a=GG(a,b,c,d,M[9],5,0x21e1cde6);
		d=GG(d,a,b,c,M[14],9,0xc33707d6);
		c=GG(c,d,a,b,M[3],14,0xf4d50d87); 
		b=GG(b,c,d,a,M[8],20,0x455a14ed);
		
		a=GG(a,b,c,d,M[13],5,0xa9e3e905);
		d=GG(d,a,b,c,M[2],9,0xfcefa3f8);
		c=GG(c,d,a,b,M[7],14,0x676f02d9);
		b=GG(b,c,d,a,M[12],20,0x8d2a4c8a);
		//绗笁杞�
		a=HH(a,b,c,d,M[5],4,0xfffa3942);
		d=HH(d,a,b,c,M[8],11,0x8771f681);
		c=HH(c,d,a,b,M[11],16,0x6d9d6122);
		b=HH(b,c,d,a,M[14],23,0xfde5380c);
		
		a=HH(a,b,c,d,M[1],4,0xa4beea44);
		d=HH(d,a,b,c,M[4],11,0x4bdecfa9);
		c=HH(c,d,a,b,M[7],16,0xf6bb4b60);
		b=HH(b,c,d,a,M[10],23,0xbebfbc70);
		
		a=HH(a,b,c,d,M[13],4,0x289b7ec6);
		d=HH(d,a,b,c,M[0],11,0xeaa127fa);
		c=HH(c,d,a,b,M[3],16,0xd4ef3085);
		b=HH(b,c,d,a,M[6],23,0x04881d05);
		
		a=HH(a,b,c,d,M[9],4,0xd9d4d039);
		d=HH(d,a,b,c,M[12],11,0xe6db99e5);
		c=HH(c,d,a,b,M[15],16,0x1fa27cf8);
		b=HH(b,c,d,a,M[2],23,0xc4ac5665);
		//第三轮
		
		a=II(a,b,c,d,M[0],6,0xf4292244);
		d=II(d,a,b,c,M[7],10,0x432aff97);
		c=II(c,d,a,b,M[14],15,0xab9423a7);
		b=II(b,c,d,a,M[5],21,0xfc93a039);
		
		a=II(a,b,c,d,M[12],6,0x655b59c3);
		d=II(d,a,b,c,M[3],10,0x8f0ccc92);
		c=II(c,d,a,b,M[10],15,0xffeff47d);
		b=II(b,c,d,a,M[1],21,0x85845dd1);
		
		a=II(a,b,c,d,M[8],6,0x6fa87e4f);
		d=II(d,a,b,c,M[15],10,0xfe2ce6e0);
		c=II(c,d,a,b,M[6],15,0xa3014314);
		b=II(b,c,d,a,M[13],21,0x4e0811a1);
		
		a=II(a,b,c,d,M[4],6,0xf7537e82);
		d=II(d,a,b,c,M[11],10,0xbd3af235);
		c=II(c,d,a,b,M[2],15,0x2ad7d2bb);
		b=II(b,c,d,a,M[9],21,0xeb86d391);
		
		A=A+a;
		B=B+b;
		C=C+c;
		D=D+d;
		
		System.out.print(Integer.toHexString(A));
		System.out.print(Integer.toHexString(B));
		System.out.print(Integer.toHexString(C));
		System.out.print(Integer.toHexString(D));
	}
	
	//MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661
	//MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72
	public static void main(String[] args) {
		Md5 md5=new Md5();
		String str="a";
		byte[] s=md5.group(str.getBytes());
		System.out.println(s.length*8);
		System.out.println("-----------------");
		md5.md5(s);
	}

}

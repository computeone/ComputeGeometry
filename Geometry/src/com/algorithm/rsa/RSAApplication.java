/**
 * 
 */
package com.algorithm.rsa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author niubaisui
 *
 * 2013-3-30
 */
public class RSAApplication {
	private byte[] src_content;
	private byte[] encrypt_content;
	private RSA rsa;
	/*
	 * 
	 */
	public RSAApplication(){
		rsa=new RSA();
	}
	
	/*
	 * 
	 */
	public RSAApplication(byte[] src_Content){
		src_content=src_Content;
	}
	
	/*
	 * 
	 */
	public RSAApplication(File file) throws IOException{
		FileInputStream fread=new FileInputStream(file);
		fread.read(src_content);
		fread.close();
	}
	
	/*
	 * 
	 */
	public RSAApplication(String path,String filename) throws IOException{
		File file=new File(path,filename);
		FileInputStream fread=new FileInputStream(file);
		fread.read(src_content);
		fread.close();
	}
	public byte[] encrypt(){
		encrypt_content=rsa.setEncrypt(src_content);
		return encrypt_content;
	}
	
	public byte[] disEncrypt(){
		src_content=rsa.getEncrypt(encrypt_content);
		return src_content;
	}
	public void setSrcContent(byte[] src_Content){
		src_content=src_Content;
	}
	
	public byte[] getSrcContent(){
		return src_content;
	}
	
	public byte[] getEncryptContent(){
		return encrypt_content;
	}
	public static void main(String[] args) {
		RSAApplication encrypt=new RSAApplication();
		String str=new String("hello world niubaisui rsa successd ");
		encrypt.setSrcContent(str.getBytes());
		encrypt.encrypt();
		encrypt.disEncrypt();
		System.out.println(new String(encrypt.getSrcContent()));
	}

}

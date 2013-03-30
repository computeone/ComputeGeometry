/**
 * 
 */
package com.niubaisui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author niubaisui
 *
 * 2013-3-30
 */
public class EncryptRSA {
	private byte[] src_content;
	private byte[] encrypt_content;
	private RSA rsa;
	/*
	 * 
	 */
	public EncryptRSA(){
		rsa=new RSA();
	}
	
	/*
	 * 
	 */
	public EncryptRSA(byte[] src_Content){
		src_content=src_Content;
	}
	
	/*
	 * 
	 */
	public EncryptRSA(File file) throws IOException{
		FileInputStream fread=new FileInputStream(file);
		fread.read(src_content);
		fread.close();
	}
	
	/*
	 * 
	 */
	public EncryptRSA(String path,String filename) throws IOException{
		File file=new File(path,filename);
		FileInputStream fread=new FileInputStream(file);
		fread.read(src_content);
		fread.close();
	}
	public byte[] encrypt(){
		encrypt_content=rsa.setEncrypt(src_content);
		return encrypt_content;
	}
	
	public byte[] getEncrypt(){
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
		EncryptRSA encrypt=new EncryptRSA();
		String str=new String("hello world niubaisui rsa successd ");
		encrypt.setSrcContent(str.getBytes());
		encrypt.encrypt();
		encrypt.getEncrypt();
		System.out.println(new String(encrypt.getSrcContent()));
	}

}

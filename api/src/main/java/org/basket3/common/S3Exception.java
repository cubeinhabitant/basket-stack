package org.basket3.common;

public class S3Exception extends RuntimeException {
	public S3Exception(){
		super();
	}
	public S3Exception(String message){
		super(message);
	}	
	public S3Exception(String message, Throwable t){
		super(message, t);
	}		
}

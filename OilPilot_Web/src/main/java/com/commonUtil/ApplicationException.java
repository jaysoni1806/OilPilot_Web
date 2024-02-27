package com.commonUtil;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public String getMessageName() {
		return message;
	}
	public void setMessageName(String messageName) {
		this.message = messageName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	String methodName;
	
	public ApplicationException()
	{
		
	}
	
	public ApplicationException(String methodName, String message)
	{
		super(message);
		this.methodName=methodName;
		this.message=message;
		
	}
}

package com.Listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;

import com.commonUtil.RetryAnalyzerManager;

public class IRetryAnalyzerListener extends TestListenerAdapter {
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
	{
		annotation.setRetryAnalyzer(RetryAnalyzerManager.class);
	}

}

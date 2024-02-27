package com.commonUtil;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import testBase.TestBase;

public class RetryAnalyzerManager implements IRetryAnalyzer {

	private static final int maxTry = 3;
	private int count = 0;

	public boolean retry(final ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {
			if (this.count < maxTry) {
				TestBase.log.info("Retrying test " + iTestResult.getName() + " with status "
						+ getResultStatusName(iTestResult.getStatus()) + " for the " + (this.count + 1) + " time(s).");
				this.count++;
				return true;
			}
		}
		return false;
	}

	public String getResultStatusName(final int status) {
		String resultName = null;
		if (status == 1) {
			resultName = "SUCCESS";
		}
		if (status == 2) {
			resultName = "FAILURE";
		}
		if (status == 3) {
			resultName = "SKIP";
		}
		return resultName;
	}

}

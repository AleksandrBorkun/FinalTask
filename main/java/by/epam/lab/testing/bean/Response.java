package by.epam.lab.testing.bean;

import java.util.List;

public class Response {

	private boolean errorStatus;
	private String errorMessage;
	private String resultMessage;
	private Object startTest[][];
	private List<String> subjList;
	private List<String> testList;
	
	

	public List<String> getSubjList() {
		return subjList;
	}

	public void setSubjList(List<String> subjList) {
		this.subjList = subjList;
	}

	public List<String> getTestList() {
		return testList;
	}

	public void setTestList(List<String> testList) {
		this.testList = testList;
	}

	public Object[][] getStartTest() {
		return startTest;
	}

	public void setStartTest(Object[][] startTest) {
		this.startTest = startTest;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}

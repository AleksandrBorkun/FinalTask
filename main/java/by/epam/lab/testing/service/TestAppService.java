package by.epam.lab.testing.service;

import java.util.List;

import by.epam.lab.testing.service.exception.ServiceException;

public interface TestAppService {

	public List<String> showTestList(String subjectID) throws ServiceException;

	public List<String> showSubject() throws ServiceException;

	public Object[][] goTesting(String subjectId) throws ServiceException;
	
	public boolean setNewSubject(String subjectName) throws ServiceException;
	
	public boolean setTestListBySubject(String subjectID, String question, int answer) throws ServiceException;
	
	public boolean authorization(String login, String password) throws ServiceException;
	
	public boolean registration(String login, String password) throws ServiceException;

}

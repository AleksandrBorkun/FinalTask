package by.epam.lab.testing.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.service.TestAppService;
import by.epam.lab.testing.service.exception.ServiceException;

public class TestAppServiceImpl implements TestAppService {

	@Override
	public List<String> showTestList(String subjectId) throws ServiceException {

		if (subjectId == null || subjectId.equals("") || subjectId.equals(" ") || subjectId.length() <= 3) {
			throw new ServiceException("ERROR! Write correct subject name!");
		}

		List<String> testList = new ArrayList<>();

		testList.clear();
		try {
			testList = DAOFactory.getInstance().getTestApp().getTestListBySubject(subjectId);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		if (testList != null) {
			return testList;
		} else {
			return null;
		}
	}

	@Override
	public List<String> showSubject() throws ServiceException {

		List<String> subjList = new ArrayList<>();

		subjList.clear();
		try {
			subjList = DAOFactory.getInstance().getTestApp().getSubjectList();

			if (subjList != null) {
				return subjList;
			} else {
				return null;
			}
		} catch (DAOException e) {

			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public Object[][] goTesting(String subjectId) throws ServiceException {

		if (subjectId == null || subjectId.equals("") || subjectId.equals(" ") || subjectId.length() <= 3) {
			throw new ServiceException("ERROR! Write correct subject name!");
		}

		List<Integer> answer = new ArrayList<>();
		List<String> list = new ArrayList<>();
		Object obj[][] = new Object[1][2];

		answer.clear();
		list.clear();

		try {

			answer = DAOFactory.getInstance().getTestApp().chekTestResult(subjectId);
			list = DAOFactory.getInstance().getTestApp().getTestListBySubject(subjectId);

			obj[0][0] = answer;
			obj[0][1] = list;

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return obj;
	}

	@Override
	public boolean setNewSubject(String subjectName) throws ServiceException {

		subjectName = subjectName.trim();

		if (subjectName == null || subjectName.length() < 1) {
			throw new ServiceException("You cant write an empty name of subject");
		}

		try {
			if (DAOFactory.getInstance().getTestApp().setNewSubject(subjectName)) {
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public boolean setTestListBySubject(String subjectID, String question, int answer) throws ServiceException {

		question = question.trim();
		subjectID = subjectID.trim();

		if (subjectID == null || question == null || answer <= 0 || answer >= 5 || subjectID.length() < 3
				|| question.length() < 24) {
			throw new ServiceException("Your question isn't added! Don't try to write a wrong data");
		}

		try {
			if (DAOFactory.getInstance().getTestApp().setTestListBySubject(subjectID, question, answer)) {
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public boolean authorization(String login, String password) throws ServiceException {

		login = login.trim();
		password = password.trim();

		if (login == null || password == null) {
			throw new ServiceException("It's can't be empty data");
		} else if (login.length() < 3 || login.length() < 3) {
			throw new ServiceException("It's can't be empty data");
		}

		try {
			if (DAOFactory.getInstance().getUserDAO().authorization(login, password)) {
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public boolean registration(String login, String password) throws ServiceException {

		login = login.trim();
		password = password.trim();

		if (login == null || password == null) {
			throw new ServiceException("Your login and password nedd to contain at last 3 letter");
		} else if (login.length() < 3 || password.length() < 3) {
			throw new ServiceException("Your login and password nedd to contain at last 3 letter");
		}

		try {
			if (DAOFactory.getInstance().getUserDAO().registration(login, password)) {
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

}

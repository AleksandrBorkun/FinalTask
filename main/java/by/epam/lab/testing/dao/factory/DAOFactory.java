package by.epam.lab.testing.dao.factory;

import by.epam.lab.testing.dao.TestApp;
import by.epam.lab.testing.dao.UserDAO;
import by.epam.lab.testing.dao.impl.TestAppImpl;
import by.epam.lab.testing.dao.impl.UserDAOImpl;

public class DAOFactory {

	private static final DAOFactory INSTANCE = new DAOFactory();

	private final UserDAO userDAO = new UserDAOImpl();
	private final TestApp testApp = new TestAppImpl();

	private DAOFactory() {

	}

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public TestApp getTestApp() {
		return testApp;
	}
}

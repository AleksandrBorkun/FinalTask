package by.epam.lab.testing.dao;

import by.epam.lab.testing.dao.exception.DAOException;

public interface UserDAO {

	boolean registration(String login, String password) throws DAOException;

	boolean authorization(String login, String password) throws DAOException;
	
	public void deleteUser(String login, String password) throws DAOException;

}

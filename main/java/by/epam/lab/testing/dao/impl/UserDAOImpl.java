package by.epam.lab.testing.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.lab.testing.bean.entity.GeneralInformation;
import by.epam.lab.testing.dao.UserDAO;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.impl.pool.ConnectionPool;

public class UserDAOImpl implements UserDAO {

	public boolean authorization(String login, String password) throws DAOException {


		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st
					.executeQuery("SELECT id FROM users where(name, password)=('" + login + "','" + password + "');");
			if (result.next() == false) {
				return false;
			} else {
				int userID = result.getInt("id");
				GeneralInformation.setUserId(userID);
				st.close();
				return true;
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getMessage());

		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					System.out.println("Some prodblem in block 'finally'");
					throw new DAOException(e.getMessage());
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con);
			} catch (InterruptedException | SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	public boolean registration(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			int result = st
					.executeUpdate("INSERT INTO users(name, password) VALUES('" + login + "','" + password + "');");
			if (result != 0) {
				st.close();
				return true;
			} else
				st.close();
			return false;
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DAOException(e.getMessage());
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con);
			} catch (InterruptedException | SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}

	}

	public void deleteUser(String login, String password) throws DAOException {

		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			int result = st
					.executeUpdate("DELETE FROM users Where(name, password)=('" + login + "','" + password + "');");
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getMessage());

		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			try {
				ConnectionPool.getInstance().returnConnection(con);
			} catch (InterruptedException | SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}

	}

}

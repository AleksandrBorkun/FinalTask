package by.epam.lab.testing.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;

import by.epam.lab.testing.dao.TestApp;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.impl.pool.ConnectionPool;

public class TestAppImpl implements TestApp {

	@Override
	public List<String> getSubjectList() throws DAOException {

		List<String> sybjList = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		sybjList.clear();
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM subject;");
			if (result.next() == false) {
				System.out.println("Sorry but we can't find any subjects! You can to add it");
				st.close();
				return null;
			} else {
				// result.next();
				do {
					sybjList.add(result.getString("subject_name"));
				} while (result.next());
				st.close();
			}
			return sybjList;
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getLocalizedMessage() + " \n" + e.getMessage());
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (InterruptedException | SQLException e) {
					throw new DAOException(e.getLocalizedMessage() + " \n" + e.getMessage());
				}
		}

	}

	@Override
	public boolean setNewSubject(String subjectName) throws DAOException {

		Connection con = null;
		PreparedStatement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.prepareStatement(subjectName);

			int result = st.executeUpdate("INSERT INTO subject(subject_name) VALUES('" + subjectName + "');");
			st.close();
			if (result != 0) {
				return true;
			} else {
				return false;
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (con != null) {
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (InterruptedException | SQLException e) {
					throw new DAOException(e.getMessage());
				}
			}
		}

	}

	@Override
	public List<String> getTestListBySubject(String subjectID) throws DAOException {

		List<String> testList = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		testList.clear();
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM questions WHERE subject_id = '" + subjectID + "';");
			if (result.next() == false) {
				System.out.println("Sorry but we can't find any questions! You can to add it");
				st.close();
				return null;
			} else {
				do {
					testList.add(result.getString("question"));
				} while (result.next());
				st.close();
			}
			return testList;
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (InterruptedException | SQLException e) {
					throw new DAOException(e.getMessage());
				}
		}

	}

	@Override
	public boolean setTestListBySubject(String subjectID, String question, int answer) throws DAOException {

		Connection con = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			PreparedStatement st = con.prepareStatement(question);

			int result = st.executeUpdate("INSERT INTO questions(subject_id, question, answer) VALUES('" + subjectID
					+ "','" + question + "','" + answer + "');");
			st.close();
			if (result != 0) {
				return true;
			} else {
				return false;
			}
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (con != null) {
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (InterruptedException | SQLException e) {
					throw new DAOException(e.getMessage());
				}
			}
		}

	}

	@Override
	public List<Integer> chekTestResult(String subjectID) throws DAOException {

		List<Integer> answers = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		answers.clear();
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM questions WHERE subject_id = '" + subjectID + "';");
			if (result.next() == false) {
				System.out.println("Sorry but we can't find any answers for your question! You can to add it");
				st.close();
				return null;
			} else {
				do {
					answers.add(result.getInt("answer"));
				} while (result.next());
				st.close();
			}
			return answers;
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (con != null)
				try {
					ConnectionPool.getInstance().returnConnection(con);
				} catch (InterruptedException | SQLException e) {
					throw new DAOException(e.getMessage());
				}
		}

	}

	// This two method has been created only for some unit-tests

	@Override
	public void deleteAllQuestions() throws DAOException {
		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			int testKILL = st.executeUpdate("DELETE FROM questions Where id>=0");
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	@Override
	public void deleteAllSubjects() throws DAOException {
		Connection con = null;
		Statement st = null;

		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.createStatement();
			int subDEL = st.executeUpdate("DELETE FROM subject Where id>=0");
		} catch (InterruptedException | SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}
}

package by.epam.lab.testing.dao;

import java.util.List;

import by.epam.lab.testing.dao.exception.DAOException;

public interface TestApp {

	public List<String> getSubjectList() throws DAOException;

	public boolean setNewSubject(String subjectName) throws DAOException;

	public List<String> getTestListBySubject(String subjectId) throws DAOException;

	public boolean setTestListBySubject(String subjectId, String question, int answer) throws DAOException;

	public List<Integer> chekTestResult(String subjectID) throws DAOException;

	public void deleteAllSubjects() throws DAOException;

	public void deleteAllQuestions() throws DAOException;

}

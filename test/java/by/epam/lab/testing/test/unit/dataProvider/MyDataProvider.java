package by.epam.lab.testing.test.unit.dataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.xml.sax.SAXException;

import by.epam.lab.testing.test.unit.xmlparser.XMLPool;

public class MyDataProvider {

	@DataProvider(name = "authorizationPositiveTest")
	public static Object[][] authorizationPositiveTest() throws SAXException, IOException {
		Object obj[][] = new Object[1][2];
		obj[0][0] = XMLPool.getPositivTestData().get(0).getLogin();
		obj[0][1] = XMLPool.getPositivTestData().get(0).getPassword();

		return obj;
	}

	@DataProvider(name = "goTestingPositiveTest")
	public static Object[][] goTestingPositiveTest() throws SAXException, IOException {

		String subjectName = XMLPool.getPositivTestData().get(0).getSubjectName().toUpperCase();
		int answer = XMLPool.getPositivTestData().get(0).getAnswer();

		List<Integer> answers = new ArrayList<>();
		answers.add(answer);

		Object[][] obj = new Object[1][2];
		obj[0][0] = subjectName;
		obj[0][1] = answers;

		return obj;
	}

	@DataProvider(name = "registrationPositiveTest")
	public static Object[][] registrationPositiveTest() throws SAXException, IOException {
		
		 		
		Object obj[][] = new Object [1][3];
		
		obj[0][0] = XMLPool.getPositivTestData().get(0).getLogin();
		obj[0][1] = XMLPool.getPositivTestData().get(0).getPassword();
		obj[0][2] = XMLPool.getPositivTestData().get(0).getRegistrationCommand();

		return obj;
	}

	@DataProvider(name = "setNewQuestionPositiveTest")
	public static Object[][] setNewQuestionPositiveTest() throws SAXException, IOException {

		Object[][] obj = new Object[1][4];

		obj[0][0] = new String(XMLPool.getPositivTestData().get(0).getSubjectName().toUpperCase());
		obj[0][1] = new String(XMLPool.getPositivTestData().get(0).getQuestion());
		obj[0][2] = new Integer(XMLPool.getPositivTestData().get(0).getAnswer());
		obj[0][3] = new String(XMLPool.getPositivTestData().get(0).getNewTestCommand());

		return obj;
	}

	@DataProvider(name = "setNewSubjectPositiveTest")
	public static Object[][] setNewSubjectPositiveTest() throws SAXException, IOException {

		Object[][] obj = new Object[1][2];

		obj[0][0] = new String(XMLPool.getPositivTestData().get(0).getSubjectName().toUpperCase());
		obj[0][1] = new String(XMLPool.getPositivTestData().get(0).getNewSubjectCommand());

		return obj;

	}

	@DataProvider(name = "showQuestionPositiveTest")
	public static Object[][] showQuestionPositiveTest() throws SAXException, IOException {

		Object[][] obj = new Object[1][2];

		obj[0][0] = new String(XMLPool.getPositivTestData().get(0).getSubjectName().toUpperCase());
		obj[0][1] = new String(XMLPool.getPositivTestData().get(0).getShowQuestCommand());

		return obj;
		
	}
	
	@DataProvider(name = "showQuestionPositiveTestSecond")
	public static Object[][] showQuestionPositiveTestSecond() throws SAXException, IOException {

		Object[][] obj = new Object[1][2];
		List<String> actual = new ArrayList<>();
		actual.add(XMLPool.getPositivTestData().get(0).getQuestion());
		
		obj[0][0] = new String(XMLPool.getPositivTestData().get(0).getSubjectName().toUpperCase());
		obj[0][1] = actual;

		return obj;
		
	}

	@DataProvider(name = "showSubjectPositiveTest")
	public static Object[][] showSubjectPositiveTest() throws SAXException, IOException {

		String commandName = XMLPool.getPositivTestData().get(0).getShowSubjectCommand();

		Object obj[][] = new Object[1][1];
		obj[0][0] = commandName;

		return obj;
	}
	
	@DataProvider(name = "showSubjectPositiveTestSecond")
	public static Object[][] showSubjectPositiveTestSecond() throws SAXException, IOException {

		String quest = XMLPool.getPositivTestData().get(0).getSubjectName().toUpperCase();
		List<String> questions = new ArrayList<>();
		questions.add(quest);
		
		Object obj[][] = new Object[1][1];
		obj[0][0] = questions;

		return obj;
	}
	
	
	@DataProvider(name = "authorizationNegativeTest")
	public static Object[][] authorizationNegativeTest() throws SAXException, IOException {
		Object obj[][] = new Object[1][2];
		obj[0][0] = XMLPool.getNegativTestData().get(0).getLogin();
		obj[0][1] = XMLPool.getNegativTestData().get(0).getPassword();

		return obj;
	}

	@DataProvider(name = "setNewSubjectNegativeTestOne")
	public static Object[][] setNewSubjectNegativeTestOne() throws SAXException, IOException {

		Object[][] obj = new Object[1][2];

		obj[0][0] = new String(XMLPool.getNegativTestData().get(0).getSubjectName().toUpperCase());
		obj[0][1] = new String(XMLPool.getNegativTestData().get(0).getNewSubjectCommand());

		return obj;

	}
	
	@DataProvider(name = "setNewQuestionNegativeTest")
	public static Object[][] setNewQuestionNegetiveTest() throws SAXException, IOException {

		Object[][] obj = new Object[1][4];

		obj[0][0] = new String(XMLPool.getNegativTestData().get(0).getSubjectName().toUpperCase());
		obj[0][1] = new String(XMLPool.getNegativTestData().get(0).getQuestion());
		obj[0][2] = new Integer(XMLPool.getNegativTestData().get(0).getAnswer());
		obj[0][3] = new String(XMLPool.getNegativTestData().get(0).getNewTestCommand());

		return obj;
	}

	@DataProvider(name = "showQuestionNegativeTest")
	public static Object[][] showQuestionNegativeTest() throws SAXException, IOException {

		Object[][] obj = new Object[1][2];

		obj[0][0] = new String(XMLPool.getNegativTestData().get(0).getSubjectName().toUpperCase());
		obj[0][1] = new String(XMLPool.getNegativTestData().get(0).getShowQuestCommand());

		return obj;
		
	}
	
	@DataProvider(name = "goTestingNegativeTest")
	public static Object[][] goTestingNegativeTest() throws SAXException, IOException {

		String subjectName = XMLPool.getNegativTestData().get(0).getSubjectName().toUpperCase();
		int answer = XMLPool.getNegativTestData().get(0).getAnswer();

		List<Integer> answers = new ArrayList<>();
		answers.add(answer);

		Object[][] obj = new Object[1][2];
		obj[0][0] = subjectName;
		obj[0][1] = answers;

		return obj;
	}
	
}

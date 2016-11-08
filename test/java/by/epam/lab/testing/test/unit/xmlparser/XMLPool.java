package by.epam.lab.testing.test.unit.xmlparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class XMLPool {

	private static Storage positivStorage = new Storage();
	private static Storage negativeStorage  = new Storage();
	private static List<Storage> positiveDataList = new ArrayList<Storage>();
	private static List<Storage> negativDataList = new ArrayList<Storage>();


	// this method return data for positive tests from XML File
	public static List<Storage> getPositivTestData() throws SAXException, IOException {

		NodeList positiveData = null;
		Element root = getTests();
		
		positiveDataList.clear();
		
		positiveData = root.getElementsByTagName("positive-test");
			
		for (int k = 0; k < positiveData.getLength(); k++) {
				Element temp = (Element) positiveData.item(k);
				positivStorage.setAnswer(Integer.parseInt((getSingleChild(temp, "answer").getTextContent().trim())));
				positivStorage.setLogin(getSingleChild(temp, "login").getTextContent().trim());
				positivStorage.setSubjectName(getSingleChild(temp, "subjectName").getTextContent().trim());
				positivStorage.setPassword(getSingleChild(temp, "password").getTextContent().trim());
				positivStorage.setQuestion(getSingleChild(temp, "question").getTextContent().trim());
				positivStorage
						.setSubjectID(Integer.parseInt(getSingleChild(temp, "subjectID").getTextContent().trim()));
				positivStorage
						.setAuthorizationCommand(getSingleChild(temp, "AUTHORIZATION-command").getTextContent().trim());
				positivStorage
						.setRegistrationCommand(getSingleChild(temp, "REGISTRATION-command").getTextContent().trim());
				positivStorage
						.setShowSubjectCommand(getSingleChild(temp, "SHOW_SUBJECT-command").getTextContent().trim());
				positivStorage.setGoTestCommand(getSingleChild(temp, "GO_TESTING-command").getTextContent().trim());
				positivStorage
						.setShowQuestCommand(getSingleChild(temp, "SHOW_QUESTION-command").getTextContent().trim());
				positivStorage
						.setNewTestCommand(getSingleChild(temp, "CREATE_NEW_QUESTION-command").getTextContent().trim());
				positivStorage.setNewSubjectCommand(
						getSingleChild(temp, "CREATE_NEW_SUBJECT-command").getTextContent().trim());

				positiveDataList.add(positivStorage);
			}
		

		return positiveDataList;

	}

	// this method return data for negative tests from XML File
	public static List<Storage> getNegativTestData() throws SAXException, IOException {

		NodeList negativeData = null;
		Element root = getTests();
	
		negativDataList.clear();
		
		negativeData = root.getElementsByTagName("negative-test");
		
		for (int k = 0; k < negativeData.getLength(); k++) {
			Element temp = (Element) negativeData.item(k);
			negativeStorage.setAnswer(Integer.parseInt((getSingleChild(temp, "answer").getTextContent().trim())));
			negativeStorage.setLogin(getSingleChild(temp, "login").getTextContent().trim());
			negativeStorage.setSubjectName(getSingleChild(temp, "subjectName").getTextContent().trim());
			negativeStorage.setPassword(getSingleChild(temp, "password").getTextContent().trim());
			negativeStorage.setQuestion(getSingleChild(temp, "question").getTextContent().trim());
			negativeStorage.setSubjectID(Integer.parseInt(getSingleChild(temp, "subjectID").getTextContent().trim()));
			negativeStorage
					.setAuthorizationCommand(getSingleChild(temp, "AUTHORIZATION-command").getTextContent().trim());
			negativeStorage
					.setRegistrationCommand(getSingleChild(temp, "REGISTRATION-command").getTextContent().trim());
			negativeStorage.setShowSubjectCommand(getSingleChild(temp, "SHOW_SUBJECT-command").getTextContent().trim());
			negativeStorage.setGoTestCommand(getSingleChild(temp, "GO_TESTING-command").getTextContent().trim());
			negativeStorage.setShowQuestCommand(getSingleChild(temp, "SHOW_QUESTION-command").getTextContent().trim());
			negativeStorage
					.setNewTestCommand(getSingleChild(temp, "CREATE_NEW_QUESTION-command").getTextContent().trim());
			negativeStorage
					.setNewSubjectCommand(getSingleChild(temp, "CREATE_NEW_SUBJECT-command").getTextContent().trim());

			negativDataList.add(negativeStorage);
		}

		return negativDataList;

	}

	// this method return child element of XML file
	private static Element getSingleChild(Element element, String childName) {
		NodeList nList = element.getElementsByTagName(childName);
		Element child = (Element) nList.item(0);
		return child;
	}

	// this method return root element of XML file
	private static Element getTests() throws SAXException, IOException {

		DOMParser parser = new DOMParser();
		parser.parse("../testing/res/storage.xml");
		Document document = parser.getDocument();
		Element root = document.getDocumentElement();
		return root;

	}
}

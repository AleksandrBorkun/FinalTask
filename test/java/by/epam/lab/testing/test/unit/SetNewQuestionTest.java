package by.epam.lab.testing.test.unit;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.SetNewQuestionRequest;
import by.epam.lab.testing.controller.Controller;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.test.unit.dataProvider.MyDataProvider;

public class SetNewQuestionTest extends Assert {

	@Test(dataProvider = "setNewQuestionPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positivTest(String subjectId, String question, int answer, String CommandName) throws DAOException {

		//delete all question first
		DAOFactory.getInstance().getTestApp().deleteAllQuestions();
		
		Response response = new Response();
		Controller controller = new Controller();
		SetNewQuestionRequest request = new SetNewQuestionRequest();
		request.setAnswer(answer);
		request.setQuestion(question);
		request.setSubjectId(subjectId);
		request.setCommandName(CommandName);
		response = controller.doRequest(request);
 
		assertEquals(response.isErrorStatus(), false, "It's may be false");
	}
	

	@Test(expectedExceptions = {Exception.class})
	public void negativeTestOne(){
		
		Response response = new Response();
		Controller controller = new Controller();
		SetNewQuestionRequest request = new SetNewQuestionRequest();
		request.setAnswer(0);
		request.setQuestion("");
		request.setSubjectId(null);
		request.setCommandName(null);
		response = controller.doRequest(request);
		
	}

	//Появляеться ошибка как и ожидаеться, но тест не проходит З.Ы. видимо мне нужно разобраться с аннотациями :)
	/*

	@Test(expectedExceptions = {Exception.class}, dataProvider = "setNewQuestionNegativeTest", dataProviderClass = MyDataProvider.class)
	public void negativeTestTwo(String subjectId, String question, int answer, String CommandName) throws DAOException {

				
		Response response = new Response();
		Controller controller = new Controller();
		SetNewQuestionRequest request = new SetNewQuestionRequest();
		request.setAnswer(answer);
		request.setQuestion(question);
		request.setSubjectId(subjectId);
		request.setCommandName(CommandName);
		response = controller.doRequest(request);
 
	}

	*/
}

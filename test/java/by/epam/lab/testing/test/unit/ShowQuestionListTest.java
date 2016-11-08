package by.epam.lab.testing.test.unit;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.ShowTestListRequest;
import by.epam.lab.testing.controller.Controller;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.test.unit.dataProvider.MyDataProvider;

public class ShowQuestionListTest extends Assert {

	@Test(dataProvider = "showQuestionPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String subjectName, String commandName) {

		Response response = new Response();
		Controller controller = new Controller();
		ShowTestListRequest request = new ShowTestListRequest();
		request.setCommandName(commandName);
		request.setSubjectId(subjectName);
		response = controller.doRequest(request);

		assertEquals(response.isErrorStatus(), false, "It's may be false");
	}

	@Test(dataProvider = "showQuestionPositiveTestSecond", dataProviderClass = MyDataProvider.class)
	public void positiveTestSecond(String subjectName, List<String> expected) throws DAOException {

		List<String> actual = DAOFactory.getInstance().getTestApp().getTestListBySubject(subjectName);

		assertEquals(actual, expected, "Here must be no different");
	}

	@Test(expectedExceptions = { Exception.class })
	public void negativeTest() {

		Response response = new Response();
		Controller controller = new Controller();
		ShowTestListRequest request = new ShowTestListRequest();
		request.setCommandName(null);
		request.setSubjectId(null);
		response = controller.doRequest(request);

	}

	/* 
	 * @Test(expectedExceptions = {ServiceException.class}, dataProvider =
	 * "showQuestionNegativeTest", dataProviderClass = MyDataProvider.class)
	 * public void negativeTest(String subjectName, String commandName) { //
	 * DAOFactory.getInstance().getTestApp().clearDB();
	 * 
	 * Response response = new Response(); Controller controller = new
	 * Controller(); ShowTestListRequest request = new ShowTestListRequest();
	 * request.setCommandName(commandName); request.setSubjectId(subjectName);
	 * response = controller.doRequest(request);
	 * 
	 * //assertEquals(response.isErrorStatus(), false, "It's may be false"); }
	 */

}

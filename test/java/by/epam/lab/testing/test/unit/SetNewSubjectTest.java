package by.epam.lab.testing.test.unit;

import org.testng.Assert;
import org.testng.annotations.Test;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.SetNewSubjectRequest;
import by.epam.lab.testing.controller.Controller;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.test.unit.dataProvider.MyDataProvider;

public class SetNewSubjectTest extends Assert {

	@Test(dataProvider = "setNewSubjectPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String subjectName, String commandName) throws DAOException {

		// delete all subjects for first
		DAOFactory.getInstance().getTestApp().deleteAllSubjects();

		Response response = new Response();
		Controller controller = new Controller();
		SetNewSubjectRequest request = new SetNewSubjectRequest();
		request.setSubjectName(subjectName);
		request.setCommandName(commandName);
		response = controller.doRequest(request);

		assertEquals(response.isErrorStatus(), false, "It's may be false");
	}

	@Test(expectedExceptions = { Exception.class })
	public void negativeTestOne() throws Exception {

		Response response = new Response();
		Controller controller = new Controller();
		SetNewSubjectRequest request = new SetNewSubjectRequest();
		request.setSubjectName(null);
		request.setCommandName(null);
		response = controller.doRequest(request);

		assertEquals(response.isErrorStatus(), true, "It's may be false");
	}
}

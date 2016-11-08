package by.epam.lab.testing.test.unit;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.ShowSubjectRequest;
import by.epam.lab.testing.controller.Controller;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.test.unit.dataProvider.MyDataProvider;

public class ShowSubjectListTest extends Assert {

	@Test(dataProvider = "showSubjectPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String commandName) throws DAOException {

		Response response = new Response();
		Controller controller = new Controller();
		ShowSubjectRequest request = new ShowSubjectRequest();
		request.setCommandName(commandName);
		response = controller.doRequest(request);

		assertEquals(response.isErrorStatus(), false, "It's may be false");

	}
	
	@Test(dataProvider = "showSubjectPositiveTestSecond", dataProviderClass = MyDataProvider.class)
	public void secondPositiveTest(List<String> expected) throws DAOException{
		
		List<String> actual = new ArrayList<>();
		actual = DAOFactory.getInstance().getTestApp().getSubjectList();
		
		assertEquals(actual, expected, "I thought here is no different");
		
	}


}

package by.epam.lab.testing.test.unit;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.lab.testing.bean.RegistrationRequest;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.controller.Controller;
import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.test.unit.dataProvider.MyDataProvider;

public class RegistrationTest extends Assert{

	
	@Test(dataProvider = "registrationPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String login, String password, String commandName) throws DAOException{
		
		//First you need to make sure that this user does not exist
		//So call deleteUser method
		DAOFactory.getInstance().getUserDAO().deleteUser(login, password);
		
		Controller controller = new Controller();
		RegistrationRequest request = new RegistrationRequest();
		request.setCommandName(commandName);
		request.setLogin(login);
		request.setPassword(password);
		Response response = controller.doRequest(request);
		
		assertEquals(response.isErrorStatus(), false, "Here must be false");
		
	}
	
}

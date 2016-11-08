package by.epam.lab.testing.test.unit;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.test.unit.dataProvider.MyDataProvider;
public class AuthirizationTest extends Assert {

	String negativName = "name";
	String negativPass = "pass";

	@Test(dataProvider = "authorizationPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String login, String password) throws DAOException, SAXException, IOException {

			boolean actual = DAOFactory.getInstance().getUserDAO().authorization(login, password);
			assertEquals(actual, true, "We expected true result");

	}

	
	@Test(dataProvider = "authorizationNegativeTest", dataProviderClass = MyDataProvider.class)
	public void negativTestOne(String login, String password) throws DAOException {

		boolean actual = DAOFactory.getInstance().getUserDAO().authorization(login, password);

		assertFalse(actual, "We expected false");

	}

	@Test(expectedExceptions = { DAOException.class })
	public void negativTestTwo() throws DAOException {

		boolean actual = DAOFactory.getInstance().getUserDAO().authorization(null, null);

		assertFalse(actual, "We expected exception");

	}

}

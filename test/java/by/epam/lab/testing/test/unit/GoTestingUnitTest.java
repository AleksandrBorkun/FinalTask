package by.epam.lab.testing.test.unit;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.epam.lab.testing.dao.exception.DAOException;
import by.epam.lab.testing.dao.factory.DAOFactory;
import by.epam.lab.testing.test.unit.dataProvider.MyDataProvider;

public class GoTestingUnitTest extends Assert{

	
	List<Integer> actual = new ArrayList<>();
	
	@Test(dataProvider = "goTestingPositiveTest", dataProviderClass = MyDataProvider.class)
	public void positiveTest(String subjectName, List<Integer> answer) throws DAOException{

		
		actual = DAOFactory.getInstance().getTestApp().chekTestResult(subjectName.toUpperCase());
	
	//	System.out.println(actual.equals(answer));
		
		assertEquals(actual, answer, "I hope that work");
	}
	
	@Test(dataProvider = "goTestingNegativeTest", dataProviderClass = MyDataProvider.class)
	public void negativTest(String subjectName, List<Integer> answer) throws DAOException{
	
		actual = DAOFactory.getInstance().getTestApp().chekTestResult(subjectName.toUpperCase());
		
		assertNotEquals(actual, answer, "I hoped that doesn't work");
		
	}
	
	
}

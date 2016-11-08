package by.epam.lab.testing.service;

import by.epam.lab.testing.service.impl.TestAppServiceImpl;

public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();

	private TestAppService testService = new TestAppServiceImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public TestAppService getTestAppService() {
		return testService;
	}

}

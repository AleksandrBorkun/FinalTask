package by.epam.lab.testing.command.impl;

import by.epam.lab.testing.bean.GoTestingRequest;
import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

public class GoTesting implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		GoTestingRequest req;
		Object test[][];
		if (request instanceof GoTestingRequest) {
			req = (GoTestingRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Response response = new Response();
		String subjectId = req.getSubjectId();

		try {
			test = ServiceFactory.getInstance().getTestAppService().goTesting(subjectId);
			if (test!=null) {
				response.setStartTest(test);
				response.setErrorStatus(false);
				response.setResultMessage("Test Completed. I hope you glad to see your result!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Something was wrong. Our command work on it problem. Try Again Later");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;
	}

}

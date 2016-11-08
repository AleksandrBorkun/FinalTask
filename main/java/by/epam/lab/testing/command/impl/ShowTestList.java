package by.epam.lab.testing.command.impl;

import java.util.List;

import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.ShowTestListRequest;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

public class ShowTestList implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		ShowTestListRequest req;

		if (request instanceof ShowTestListRequest) {
			req = (ShowTestListRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Response response = new Response();
		List<String> testList;
		String subjectID = req.getSubjectId();

		try {
			testList = ServiceFactory.getInstance().getTestAppService().showTestList(subjectID);			
			if (testList!=null) {
				response.setTestList(testList);
				response.setErrorStatus(false);
				response.setResultMessage("Completed Success!!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("There is no any question!");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}

		return response;

	}

}

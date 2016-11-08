package by.epam.lab.testing.command.impl;

import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.SetNewSubjectRequest;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

public class SetNewSubject implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		SetNewSubjectRequest req;

		if (request instanceof SetNewSubjectRequest) {
			req = (SetNewSubjectRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		Response response = new Response();

		String subjectName = req.getSubjectName();

		try {
			if (ServiceFactory.getInstance().getTestAppService().setNewSubject(subjectName)) {

				response.setErrorStatus(false);
				response.setResultMessage("Your subject is added to Test");

			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Something was wrong. Try Again!");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;
	}

}

package by.epam.lab.testing.command.impl;

import by.epam.lab.testing.bean.AuthorizationRequest;
import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

public class Authorization implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		AuthorizationRequest req;

		if (request instanceof AuthorizationRequest) {
			req = (AuthorizationRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Response response = new Response();

		String login = req.getLogin();
		String password = req.getPassword();

		try {
			if (ServiceFactory.getInstance().getTestAppService().authorization(login, password)) {
				response.setErrorStatus(false);
				response.setResultMessage("Hello " + login + "! Your authorization completed successfully!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Sorry we cant't find user with name " + login + " and password " + password);
			}
		} catch (ServiceException e) {

			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			// e.printStackTrace();
			return response;
		}

		return response;
	}

}

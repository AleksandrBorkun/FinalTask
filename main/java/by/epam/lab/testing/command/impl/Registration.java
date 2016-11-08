package by.epam.lab.testing.command.impl;

import by.epam.lab.testing.bean.RegistrationRequest;
import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

public class Registration implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		RegistrationRequest req;

		if (request instanceof RegistrationRequest) {
			req = (RegistrationRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		Response response = new Response();
		String login = req.getLogin();
		String password = req.getPassword();

		try {
			if (ServiceFactory.getInstance().getTestAppService().registration(login, password)) {
				response.setErrorStatus(false);
				response.setResultMessage("Hello " + login + "! Your registration completed successfully!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("Sorry we cant't add user with name " + login + " and password " + password);
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
		}

		return response;
	}

}

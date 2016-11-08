package by.epam.lab.testing.controller;

import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;

public class Controller {

	private CommandHelper helper = new CommandHelper();

	public Controller() {
	}

	public Response doRequest(Request request) {

		String commandName = request.getCommandName();

		Command command = helper.getCommand(commandName);

		Response response;
		try {
			response = command.execute(request);
		} catch (CommandException e) {
			// logging
			response = new Response();
			response.setErrorStatus(true);
			response.setErrorMessage("ERROR!");
		}

		return response;

	}

}

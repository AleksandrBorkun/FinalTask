package by.epam.lab.testing.command;

import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.command.exception.CommandException;

public interface Command {

	Response execute(Request request) throws CommandException;

}

package by.epam.lab.testing.controller;

import java.util.HashMap;
import java.util.Map;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.impl.Authorization;
import by.epam.lab.testing.command.impl.GoTesting;
import by.epam.lab.testing.command.impl.Registration;
import by.epam.lab.testing.command.impl.SetNewQuestion;
import by.epam.lab.testing.command.impl.SetNewSubject;
import by.epam.lab.testing.command.impl.ShowSubject;
import by.epam.lab.testing.command.impl.ShowTestList;

public class CommandHelper {

	private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHelper() {

		commands.put("REGISTRATION", new Registration());
		commands.put("AUTHORIZATION", new Authorization());
		commands.put("SHOW_SUBJECT", new ShowSubject());
		commands.put("GO_TESTING", new GoTesting());
		commands.put("SHOW_QUESTION", new ShowTestList());
		commands.put("CREATE_NEW_QUESTION", new SetNewQuestion());
		commands.put("CREATE_NEW_SUBJECT", new SetNewSubject());

	}

	public Command getCommand(String commandName) {

		Command command;

		command = commands.get(commandName);

		return command;
	}

}

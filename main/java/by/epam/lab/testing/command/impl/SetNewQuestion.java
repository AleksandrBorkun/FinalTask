package by.epam.lab.testing.command.impl;

import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.SetNewQuestionRequest;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

public class SetNewQuestion implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		SetNewQuestionRequest req;

		if (request instanceof SetNewQuestionRequest) {
			req = (SetNewQuestionRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		Response response = new Response();

		String subjectId = req.getSubjectId();
		int answer = req.getAnswer();
		String question = req.getQuestion();

		try {
			if (ServiceFactory.getInstance().getTestAppService().setTestListBySubject(subjectId, question, answer)) {

				response.setErrorStatus(false);
				response.setResultMessage("Your question is added to Test");

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

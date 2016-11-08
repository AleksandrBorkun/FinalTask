package by.epam.lab.testing.command.impl;

import java.util.List;

import by.epam.lab.testing.bean.Request;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.ShowSubjectRequest;
import by.epam.lab.testing.command.Command;
import by.epam.lab.testing.command.exception.CommandException;
import by.epam.lab.testing.service.ServiceFactory;
import by.epam.lab.testing.service.exception.ServiceException;

public class ShowSubject implements Command {

	@Override
	public Response execute(Request request) throws CommandException {

		ShowSubjectRequest req;

		if (request instanceof ShowSubjectRequest) {
			req = (ShowSubjectRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}
		
		
		List<String> subjList;
		
		Response response = new Response();
		try {
			
			subjList = ServiceFactory.getInstance().getTestAppService().showSubject();
			if (subjList!=null) {
				response.setSubjList(subjList);
				response.setErrorStatus(false);
				response.setResultMessage("Completed Success!!");
			} else {
				response.setErrorStatus(true);
				response.setErrorMessage("There is no any subject!");
			}
		} catch (ServiceException e) {
			response.setErrorStatus(true);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;
	}

}

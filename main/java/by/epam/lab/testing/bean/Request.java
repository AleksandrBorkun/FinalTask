package by.epam.lab.testing.bean;

import by.epam.lab.testing.bean.entity.GeneralInformation;

public class Request {

	private String commandName;
	private int userID;

	public int getUserID(){
		this.userID = GeneralInformation.getUserId();
		return userID;
	}
	
	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
}

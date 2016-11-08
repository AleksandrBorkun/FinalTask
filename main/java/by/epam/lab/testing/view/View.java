package by.epam.lab.testing.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.epam.lab.testing.bean.AuthorizationRequest;
import by.epam.lab.testing.bean.GoTestingRequest;
import by.epam.lab.testing.bean.RegistrationRequest;
import by.epam.lab.testing.bean.Response;
import by.epam.lab.testing.bean.SetNewQuestionRequest;
import by.epam.lab.testing.bean.SetNewSubjectRequest;
import by.epam.lab.testing.bean.ShowSubjectRequest;
import by.epam.lab.testing.bean.ShowTestListRequest;
import by.epam.lab.testing.bean.entity.GeneralInformation;
import by.epam.lab.testing.controller.Controller;

public class View {

	static Scanner in = new Scanner(System.in);
	private static boolean exit = true;
	private final static String help = "Take a list of command:\n0 - EXIT\n\'help\' - HELP\n1 - REGISTRATION\n2 - AUTHORIZATION\n3 - SHOW_SUBJECT\n4 - GO_TESTING\n5 - SHOW_QUESTION\n6 - CREATE_NEW_QUESTION\n7 - CREATE_NEW_SUBJECT";

	private final static String registration = "REGISTRATION";
	private final static String authorization = "AUTHORIZATION";
	private final static String showSubject = "SHOW_SUBJECT";
	private final static String goTesting = "GO_TESTING";
	private final static String showQuestion = "SHOW_QUESTION";
	private final static String createNewQuestion = "CREATE_NEW_QUESTION";
	private final static String createNewSubject = "CREATE_NEW_SUBJECT";

	public static void main(String[] args) {

		Object goTest[][];
		String login;
		String pass;
		String subjectId;
		String question;
		int answer;
		String subjectName;

		Controller controller = new Controller();
		System.out.println("Hello!!! It's NEW APP FOR TESTING by Aleksand_Br");
		System.out.println();
		System.out.println(help);

		while (exit) {
			System.out.println("Enter the command");
			String choise = in.nextLine();

			// CLOSE PROGRAM
			if (choise.equals("0")) {
				System.out.println("SYSTEM IS CLOSING...\nBEST REGARDS!");
				in.close();
				break;
			}
			switch (choise) {

			// CALL HELP
			case "help":
				System.out.println(help);
				break;

			// REGISTRATION
			case "1":
				RegistrationRequest regReq = new RegistrationRequest();
				regReq.setCommandName(registration);
				System.out.println("Please write your full name, or login");    //Login
				login = in.nextLine();
				System.out.println("Please write your password");    //Password
				pass = in.nextLine();
				regReq.setLogin(login);
				regReq.setPassword(pass);
				Response regResponse = controller.doRequest(regReq);
				if (regResponse.isErrorStatus() == true) {
					System.out.println(regResponse.getErrorMessage());
				} else {
					System.out.println(regResponse.getResultMessage());
				}
				break;

			// AUTHORIZATION
			case "2":
				AuthorizationRequest authReq = new AuthorizationRequest();
				authReq.setCommandName(authorization);
				System.out.println("Please write your full name, or login"); //Login
				login = in.nextLine();
				System.out.println("Please write your password"); //Password
				pass = in.nextLine();
				authReq.setLogin(login);
				authReq.setPassword(pass);
				Response authResponse = controller.doRequest(authReq);
				if (authResponse.isErrorStatus() == true) {
					System.out.println(authResponse.getErrorMessage());
				} else {
					System.out.println(authResponse.getResultMessage());
				}
				break;

			// SHOW_SUBJECT
			case "3":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				}

				List<String> subjList;
				ShowSubjectRequest showSubRequest = new ShowSubjectRequest();
				showSubRequest.setCommandName(showSubject);
				Response showSubResponse = controller.doRequest(showSubRequest);
				if (showSubResponse.isErrorStatus() == true) {

					System.out.println(showSubResponse.getErrorMessage());
				} else {

					subjList = showSubResponse.getSubjList();

					for (String sub : subjList) {
						System.out.println(sub + ". You can call it by name ");
						System.out.println();
					}

					System.out.println(showSubResponse.getResultMessage());
				}
				break;

			// GO_TESTING
			case "4":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				}
				List<Integer> goAnswer = new ArrayList<>();
				List<String> goList = new ArrayList<>();
				List<Integer> chek = new ArrayList<Integer>();
				int goChoise;

				GoTestingRequest goRequest = new GoTestingRequest();
				goRequest.setCommandName(goTesting);
				System.out.println("Please choose the subject by name");
				subjectId = in.nextLine();
				goRequest.setSubjectId(subjectId.toUpperCase());
				Response goResponse = controller.doRequest(goRequest);

				if (goResponse.isErrorStatus() == true) {
					System.out.println(goResponse.getErrorMessage());
				} else {
				
					goTest = goResponse.getStartTest();
					goAnswer = (List<Integer>) goTest[0][0];
					goList = (List<String>) goTest[0][1];

					for (int i = 0; i < goAnswer.size(); i++) {
						System.out.println(goList.get(i));
						System.out.println();
						System.out.println("Make your choose");
						goChoise = in.nextInt();
						chek.add(goChoise);
					}

					int point = 0;
					double mark = 0;

					for (int i = 0; i < goAnswer.size(); i++) {
						if (goAnswer.get(i) == chek.get(i)) {
							point++;
						}
					}
					mark = (double) point / goAnswer.size() * 100;
					System.out.println("You have " + point + " from " + goAnswer.size() + ". And your mark is " + mark
							+ " from 100.0");
					System.out.println(goResponse.getResultMessage());
				}
				break;

			// SHOW_QUESTION
			case "5":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				} else if (GeneralInformation.getUserId() != 1) {
					System.out.println("Sorry but for looking the questions you need to be TUTOR");
					break;
				}
				List<String> testList;
				ShowTestListRequest showTestReq = new ShowTestListRequest();
				showTestReq.setCommandName(showQuestion);
				System.out.println("Print the name of Test Subject");
				subjectId = in.nextLine();
				showTestReq.setSubjectId(subjectId.toUpperCase());
				Response showTestResponse = controller.doRequest(showTestReq);
				if (showTestResponse.isErrorStatus() == true) {
					System.out.println(showTestResponse.getErrorMessage());
				} else {

					testList = showTestResponse.getTestList();

					for (String quest : testList) {
						System.out.println(quest);
						System.out.println();
					}

					System.out.println(showTestResponse.getResultMessage());
				}
				break;

			// CREATE_NEW_QUESTION
			case "6":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				} else if (GeneralInformation.getUserId() != 1) {
					System.out.println("Sorry but for looking the questions you need to be TUTOR");
					break;
				}
				SetNewQuestionRequest setNewQuestReq = new SetNewQuestionRequest();
				setNewQuestReq.setCommandName(createNewQuestion);

				System.out.println("Please write the name of subject where you want to create a question");
				String subject = in.nextLine();

				// Creating a question with option

				System.out.println("Please write your question");
				String quest = in.nextLine().trim();
				System.out.println("Please write the first option of answer"); // option
																				// 1
				String first = in.nextLine().trim();
				System.out.println("Please write the second option of answer"); // option
																				// 2
				String second = in.nextLine().trim();
				System.out.println("Please write the third option of answer"); // option
																				// 3
				String third = in.nextLine().trim();
				System.out.println("Please write the last option of answer"); // option
																				// 4
				String last = in.nextLine().trim();

				question = quest + "\n1. " + first + "\n2. " + second + "\n3. " + third + "\n4. " + last;

				// creating a true answer
				System.out.println("Pls write a number of correct answer");
				answer = Integer.parseInt(in.nextLine());

				setNewQuestReq.setAnswer(answer);
				setNewQuestReq.setQuestion(question);
				setNewQuestReq.setSubjectId(subject);
				Response setNewQuestResponse = controller.doRequest(setNewQuestReq);
				if (setNewQuestResponse.isErrorStatus() == true) {
					System.out.println(setNewQuestResponse.getErrorMessage());
				} else {
					System.out.println(setNewQuestResponse.getResultMessage());
				}
				break;

			// CREATE_NEW_SUBJECT
			case "7":
				if (GeneralInformation.getUserId() == 0) {
					System.out.println("Pls login first");
					break;
				} else if (GeneralInformation.getUserId() != 1) {
					System.out.println("Sorry but for looking the questions you need to be TUTOR");
					break;
				}
				SetNewSubjectRequest setSubReq = new SetNewSubjectRequest();
				setSubReq.setCommandName(createNewSubject);
				System.out.println("Pls write a subject name that you want to create");
				subjectName = in.nextLine();
				setSubReq.setSubjectName(subjectName.toUpperCase());
				Response setSubResponse = controller.doRequest(setSubReq);
				if (setSubResponse.isErrorStatus() == true) {
					System.out.println(setSubResponse.getErrorMessage());
				} else {
					System.out.println(setSubResponse.getResultMessage());
				}
				break;
			default:
				System.out.println("Wrong Command! Try Again!");
				break;

			}

		}
	}
}

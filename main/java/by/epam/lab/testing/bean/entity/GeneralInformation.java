package by.epam.lab.testing.bean.entity;

public class GeneralInformation {

	private static int userId;
	private static int subjectId;
	private static int rightAnswer;

	public static int getUserId() {
		return userId;
	}

	public static void setUserId(int userId) {
		GeneralInformation.userId = userId;
	}

	public static int getSubjectId() {
		return subjectId;
	}

	public static void setSubjectId(int subjectId) {
		GeneralInformation.subjectId = subjectId;
	}

	public static int getRightAnswer() {
		return rightAnswer;
	}

	public static void setRightAnswer(int rightAnswer) {
		GeneralInformation.rightAnswer = rightAnswer;
	}

}
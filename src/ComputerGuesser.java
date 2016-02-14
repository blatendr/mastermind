import java.util.ArrayList;

public class ComputerGuesser {
	private static String FIRST_GUESS = "1111";
	private static String SECOND_GUESS = "2222";
	private static String THIRD_GUESS = "3333";
	private static String FOURTH_GUESS = "4444";
	private static String FIFTH_GUESS = "5555";
	public static int NUMBER_OF_ATTEMPTS_ALLOWED = 15;

	private static ArrayList<String> ballCombinations;
	private static boolean foundAnswer = false;
	private static int numberOfAttempts = 0;

	public static void generateAllCombinations() {
		ballCombinations = new ArrayList<>();
		for (String first : Ball.colors) {
			for (String second : Ball.colors) {
				for (String third : Ball.colors) {
					for (String fourth : Ball.colors) {
						String pattern = first + second + third + fourth;
						ballCombinations.add(pattern);
					}
				}
			}
		}
	}

	public static ArrayList<String> getCombinations() {
		return ballCombinations;
	}

	public static int getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public static boolean getFoundAnswer() {
		return foundAnswer;
	}
	
	public static void setNumberOfAttemptsAllowed(int allowed){
		NUMBER_OF_ATTEMPTS_ALLOWED = allowed;
	}

	protected static void firstPruneSearchSpace(String answerBall) {
		int numberOfPegsFound = 0;

		if (numberOfPegsFound != 4) {
			String result1 = PegsControler.checkAnswer(answerBall, FIRST_GUESS);
			numberOfAttempts++;
			Ball.printToConsole(FIRST_GUESS, result1, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf1s = Integer.parseInt(result1.substring(0, 1));
			removeChoicesThatDoNotHaveInAllPlaces(numberOf1s, "1");
			numberOfPegsFound += numberOf1s;
		}

		if (numberOfPegsFound != 4) {
			String result2 = PegsControler.checkAnswer(answerBall, SECOND_GUESS);
			numberOfAttempts++;
			Ball.printToConsole(SECOND_GUESS, result2, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf2s = Integer.parseInt(result2.substring(0, 1));

			removeChoicesThatDoNotHaveInAllPlaces(numberOf2s, "2");
			numberOfPegsFound += numberOf2s;
		}

		if (numberOfPegsFound != 4) {
			String result3 = PegsControler.checkAnswer(answerBall, THIRD_GUESS);
			numberOfAttempts++;
			Ball.printToConsole(THIRD_GUESS, result3, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf3s = Integer.parseInt(result3.substring(0, 1));
			removeChoicesThatDoNotHaveInAllPlaces(numberOf3s, "3");
			numberOfPegsFound += numberOf3s;
		}

		if (numberOfPegsFound != 4) {
			String result4 = PegsControler.checkAnswer(answerBall, FOURTH_GUESS);
			numberOfAttempts++;
			Ball.printToConsole(FOURTH_GUESS, result4, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf4s = Integer.parseInt(result4.substring(0, 1));
			removeChoicesThatDoNotHaveInAllPlaces(numberOf4s, "4");
			numberOfPegsFound += numberOf4s;
		}

		if (numberOfPegsFound != 4) {
			String result5 = PegsControler.checkAnswer(answerBall, FIFTH_GUESS);
			numberOfAttempts++;
			Ball.printToConsole(FIFTH_GUESS, result5, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf5s = Integer.parseInt(result5.substring(0, 1));
			removeChoicesThatDoNotHaveInAllPlaces(numberOf5s, "5");
			numberOfPegsFound += numberOf5s;
		}

		if (numberOfPegsFound != 4) {
			int numberOf6s = 4 - numberOfPegsFound;
			removeChoicesThatDoNotHaveInAllPlaces(numberOf6s, "6");

		}

	}

	public static void displayChoices() {
		for (String eachChoice : ballCombinations) {
			System.out.print(eachChoice + ",");
		}
	}

	public static void removeChoicesThatHas(String identified) {
		ArrayList<String> toRemove = new ArrayList<>();
		for (String eachChoice : ballCombinations) {
			if (eachChoice.contains(identified)) {
				toRemove.add(eachChoice);
			}
		}
		ballCombinations.removeAll(toRemove);
	}

	private static void removeChoicesThatDoNotHaveInAllPlaces(int number, String letterUnderConsideration) {
		ArrayList<String> toAdd = new ArrayList<>();
		for (String eachChoice : ballCombinations) {
			String result = eachChoice.replaceAll(letterUnderConsideration, "");
			if (result.trim().length() == (4 - number)) {
				toAdd.add(eachChoice);
			}
		}
		ballCombinations.clear();
		ballCombinations = toAdd;
	}

	public static void numberOfChoicesLeft() {
		System.out.println("\nChoices Left " + ballCombinations.size());
	}



}

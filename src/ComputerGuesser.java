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

	/**
	 * method that generates all possible combinations in the game. This should be the first thing to run in the program
	 */
	public static void generateAllCombinations() {
		ballCombinations = new ArrayList<>();
		for (String first : Pegs.colors) {
			for (String second : Pegs.colors) {
				for (String third : Pegs.colors) {
					for (String fourth : Pegs.colors) {
						String pattern = first + second + third + fourth;
						ballCombinations.add(pattern);
					}
				}
			}
		}
	}

	/**
	 * Returns all combinations in the arraylist so far
	 * @return an arraylist of all remaining and still possible combinations
	 */
	public static ArrayList<String> getCombinations() {
		return ballCombinations;
	}

	/**
	 * Method to get the number of attempts user has made on the game so far
	 * @return integer value of the number of attempts made in the game so far
	 */
	public static int getNumberOfAttempts() {
		return numberOfAttempts;
	}

	/**
	 * Return the boolean variable 
	 * @return true if the answer is found by the computer
	 */
	public static boolean getFoundAnswer() {
		return foundAnswer;
	}
	
	/**
	 * TO help adjust the number of moves according to the difficulty level
	 * @param allowed
	 */
	public static void setNumberOfAttemptsAllowed(int allowed){
		NUMBER_OF_ATTEMPTS_ALLOWED = allowed;
	}

	/**
	 * Method to prune through the search space immensely in a few steps - starting with 1111 and reaching 5555 making notes of how many of these are in the combination 
	 * @param answerBall
	 */
	protected static void firstPruneSearchSpace(String answerBall) {
		int numberOfPegsFound = 0;

		if (numberOfPegsFound != 4) {
			String result1 = PegsControler.checkAnswer(answerBall, FIRST_GUESS);
			numberOfAttempts++;
			Pegs.printToConsole(FIRST_GUESS, result1, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf1s = Integer.parseInt(result1.substring(0, 1));
			removeChoicesThatDoNotHaveInAllPlaces(numberOf1s, "1");
			numberOfPegsFound += numberOf1s;
		}

		if (numberOfPegsFound != 4) {
			String result2 = PegsControler.checkAnswer(answerBall, SECOND_GUESS);
			numberOfAttempts++;
			Pegs.printToConsole(SECOND_GUESS, result2, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf2s = Integer.parseInt(result2.substring(0, 1));

			removeChoicesThatDoNotHaveInAllPlaces(numberOf2s, "2");
			numberOfPegsFound += numberOf2s;
		}

		if (numberOfPegsFound != 4) {
			String result3 = PegsControler.checkAnswer(answerBall, THIRD_GUESS);
			numberOfAttempts++;
			Pegs.printToConsole(THIRD_GUESS, result3, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf3s = Integer.parseInt(result3.substring(0, 1));
			removeChoicesThatDoNotHaveInAllPlaces(numberOf3s, "3");
			numberOfPegsFound += numberOf3s;
		}

		if (numberOfPegsFound != 4) {
			String result4 = PegsControler.checkAnswer(answerBall, FOURTH_GUESS);
			numberOfAttempts++;
			Pegs.printToConsole(FOURTH_GUESS, result4, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf4s = Integer.parseInt(result4.substring(0, 1));
			removeChoicesThatDoNotHaveInAllPlaces(numberOf4s, "4");
			numberOfPegsFound += numberOf4s;
		}

		if (numberOfPegsFound != 4) {
			String result5 = PegsControler.checkAnswer(answerBall, FIFTH_GUESS);
			numberOfAttempts++;
			Pegs.printToConsole(FIFTH_GUESS, result5, NUMBER_OF_ATTEMPTS_ALLOWED- numberOfAttempts);
			int numberOf5s = Integer.parseInt(result5.substring(0, 1));
			removeChoicesThatDoNotHaveInAllPlaces(numberOf5s, "5");
			numberOfPegsFound += numberOf5s;
		}

		if (numberOfPegsFound != 4) {
			int numberOf6s = 4 - numberOfPegsFound;
			removeChoicesThatDoNotHaveInAllPlaces(numberOf6s, "6");

		}

	}

	/**
	 * This method prints all remaining choices in the command line console
	 */
	public static void displayChoices() {
		for (String eachChoice : ballCombinations) {
			System.out.print(eachChoice + ",");
		}
	}

	/** This method is used to prune search spaces based on a certain identified user options *
	 * 
	 * @param identified pattern which is figured out using the computer
	 */
	public static void removeChoicesThatHas(String identified) {
		ArrayList<String> toRemove = new ArrayList<>();
		for (String eachChoice : ballCombinations) {
			if (eachChoice.contains(identified)) {
				toRemove.add(eachChoice);
			}
		}
		ballCombinations.removeAll(toRemove);
	}

	/**
	 * Removes the different choices that do not have places anymore
	 * @param number number of certain elements, eg two 2s
	 * @param letterUnderConsideration 1, 2, 3, etc.
	 */
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

	/**
	 * Returns how many items are in the arraylist now
	 */
	public static void numberOfChoicesLeft() {
		System.out.println("\nChoices Left " + ballCombinations.size());
	}



}

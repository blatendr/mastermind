import java.util.ArrayList;

public class ComputerGuesser {
	private static String FIRST_GUESS = "1111";
	private static String SECOND_GUESS = "2222";
	private static String THIRD_GUESS = "3333";
	private static String FOURTH_GUESS = "4444";
	private static String FIFTH_GUESS = "5555";
	public static final int NUMBER_OF_ATTEMPTS_ALLOWED = 15;

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
	
	public static boolean getFoundAnswer(){
		return foundAnswer;
	}


	protected static void firstPruneSearchSpace(String answerBall) {
		int numberOfPegsFound = 0;

		if (numberOfPegsFound != 4) {
			String result1 = TestBall.checkAnswer(answerBall, FIRST_GUESS);
			System.out.println(result1);
			int numberOf1s = Integer.parseInt(result1.substring(0, 1));
			numberOfAttempts++;
			removeChoicesThatDoNotHaveInAllPlaces(numberOf1s, "1");
			numberOfPegsFound += numberOf1s;
		}

		if (numberOfPegsFound != 4) {
			String result2 = TestBall.checkAnswer(answerBall, SECOND_GUESS);
			System.out.println(result2);
			int numberOf2s = Integer.parseInt(result2.substring(0, 1));
			numberOfAttempts++;
			removeChoicesThatDoNotHaveInAllPlaces(numberOf2s, "2");
			numberOfPegsFound += numberOf2s;
		}

		if (numberOfPegsFound != 4) {
			String result3 = TestBall.checkAnswer(answerBall, THIRD_GUESS);
			System.out.println(result3);
			int numberOf3s = Integer.parseInt(result3.substring(0, 1));
			numberOfAttempts++;
			removeChoicesThatDoNotHaveInAllPlaces(numberOf3s, "3");
			numberOfPegsFound += numberOf3s;
		}

		if (numberOfPegsFound != 4) {
			String result4 = TestBall.checkAnswer(answerBall, FOURTH_GUESS);
			System.out.println(result4);
			int numberOf4s = Integer.parseInt(result4.substring(0, 1));
			numberOfAttempts++;
			removeChoicesThatDoNotHaveInAllPlaces(numberOf4s, "4");
			displayChoices();
			numberOfChoicesLeft();
			numberOfPegsFound += numberOf4s;
		}

		if (numberOfPegsFound != 4) {
			String result5 = TestBall.checkAnswer(answerBall, FIFTH_GUESS);
			System.out.println(result5);
			int numberOf5s = Integer.parseInt(result5.substring(0, 1));
			numberOfAttempts++;
			removeChoicesThatDoNotHaveInAllPlaces(numberOf5s, "5");
			displayChoices();
			numberOfChoicesLeft();
			numberOfPegsFound += numberOf5s;
		}

		if (numberOfPegsFound != 4) {
			int numberOf6s = 4 - numberOfPegsFound;
			removeChoicesThatDoNotHaveInAllPlaces(numberOf6s, "6");
			displayChoices();
			numberOfChoicesLeft();
			
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

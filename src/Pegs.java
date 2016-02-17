import java.util.ArrayList;
import java.util.Random;

/**
 * This class deals with the pegs
 *  @author Bijay Koirala with the help of Brad
 *
 */
public class Pegs {
	private String currentBalls = "";
	private static final String BLACK_DOT = "\u2022";
	private static final String WHITE_DOT = "\u25E6";
	private static int MAXGUESSES = 2; // we are just giving one hint right now
	private int numGuesses = 0;
	private static ArrayList<Integer> guesses;

	// public static String[] colors = {"B", "G", "R", "Y", "P", "O" };
	public static String[] colors = { "1", "2", "3", "4", "5", "6" };

	// get different random nuumbers, come up with diffeent combinations
	public String getRandomPegs() {
		String tempPattern = "";
		guesses = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			int rnd = new Random().nextInt(colors.length);
			guesses.add(i);
			tempPattern += colors[rnd];
		}
		currentBalls = tempPattern;
		return currentBalls;
	}

	public String getPegs() {
		return currentBalls;
	}

	/**
	 * This is going to set the balls as per user selection
	 * 
	 * @param balls
	 *            the combination of balls the user wants to give
	 */
	public void setBalls(String balls) {
		currentBalls = balls;
	}

	/**
	 * Get random hints for the user
	 * @return the color and position of a certain randomly selected ball
	 */
	public String getHint() {
		int rnd = new Random().nextInt(4);
		
		numGuesses++;
		if (numGuesses < MAXGUESSES) {
			return "hint " + (numGuesses) + " of 2: the ball in position " + (rnd + 1) + " is "
					+ currentBalls.charAt(rnd);
		} else {
			return "Sorry, you are out of hints";
		}
	}

	/**
	 * Exit the game at the current point
	 * @param balls the original solution objects
	 * @return the message saying what the actual pegs were
	 */
	public String giveUp(Pegs balls) {
		return "The solution was " + balls.getPegs();
	}

	/**
	 * Display the results to the user in a little attractive way
	 * @param input what the user inputted
	 * @param result what the output for result is (in terms of number of blacks and whites )
	 * @param attemptsLeft how many legal steps does the user have remaiing
	 */
	public static void printToConsole(String input, String result, int attemptsLeft) {
		System.out.println(input + "\t" + displayResultsToUser(result) + "\tAttempts Left: " + attemptsLeft);
	}

	// when displaying to user, replace the black dots with better values
	public static String displayResultsToUser(String result) {
		int numberOfBlacks = Integer.parseInt(result.substring(0, 1));
		int numberOfWhites = Integer.parseInt(result.substring(2, 3));
		String display;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < numberOfBlacks; i++) {
			buffer.append(BLACK_DOT);
		}
		display = buffer.toString();
		buffer = new StringBuffer();
		for (int i = 0; i < numberOfWhites; i++) {
			buffer.append(WHITE_DOT);
		}
		display += buffer.toString();
		return display;

	}

	/*
	 * Brad's codes to the south of this comment
	 

	public String getString() {
		return currentBalls;
	}

	public char checkCharAt(int i) {
		return currentBalls.charAt(i);

	}

	public static String checkAnswer(Pegs answer, Pegs userResponse) {
		int bothCorrect = 0;
		int correctColor = 0;
		StringBuilder answString = new StringBuilder(answer.getString());
		StringBuilder respString = new StringBuilder(userResponse.getString());
		for (int j = 0; j < 4; j++) {
			for (int k = 0; k < answString.length(); k++) {

				if (answString.charAt(k) == respString.charAt(j)) {
					correctColor++;

					answString.deleteCharAt(k);

				}
			}
		}
		for (int i = 0; i < 4; i++) {
			if (answer.checkCharAt(i) == userResponse.checkCharAt(i)) {
				bothCorrect++;
				correctColor--;
			}
		}

		return "black: " + bothCorrect + " red: " + correctColor;
	}
*/
}

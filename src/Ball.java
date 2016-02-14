import java.util.Random;

public class Ball {
	private String currentBalls = "";
	private static final String BLACK_DOT = "\u2022";
	private static final String WHITE_DOT = "\u25E6";
	
			

	// public static String[] colors = {"B", "G", "R", "Y", "P", "O" };
	public static String[] colors = { "1", "2", "3", "4", "5", "6" };

	public String getRandomPegs() {
		String tempPattern = "";
		for (int i = 0; i < 4; i++) {
			int rnd = new Random().nextInt(colors.length);
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

	public String getHint(Ball balls) {
		// give a random position out
		return "Hint will be determined and given here";
	}

	public String giveUp(Ball balls) {
		return "The solution was " + balls.getPegs();
	}
	
	public static void printToConsole(String input, String result, int attemptsLeft) {
		System.out.println(input + "\t" + displayResultsToUser(result) + "\tAttempts Left: " + attemptsLeft);
	}
	
	public static String displayResultsToUser(String result){
		int numberOfBlacks = Integer.parseInt(result.substring(0, 1));
		int numberOfWhites = Integer.parseInt(result.substring(2,3));
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
		display+=buffer.toString();
		return display;
				
		
	}

	/*
	 * Brad's codes to the south of this comment
	 */

	public String getString() {
		return currentBalls;
	}

	public char checkCharAt(int i) {
		return currentBalls.charAt(i);

	}

	public static String checkAnswer(Ball answer, Ball userResponse) {
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
			if (answer.checkCharAt(i) == userResponse.checkCharAt(i)) 
			{
				bothCorrect++;
				correctColor--;
			}
		}

		return "black: " + bothCorrect + " red: " + correctColor;
	}
	


}

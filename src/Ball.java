import java.util.Random;

public class Ball {
	private String currentBalls = "";

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
		Ball oldAnswer = answer;
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

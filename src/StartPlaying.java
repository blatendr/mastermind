import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StartPlaying {

	private static int attemptsLeft = 0;
	private static Mode mode;
	private static String ball;
	private static Scanner scanner = new Scanner(System.in);

	private static int NO_OF_MOVES_EASY = 15;
	private static int NO_OF_MOVES_MEDIUM = 10;
	private static int NO_OF_MOVES_DIFFICULT = 5;

	private static boolean gotAnswerRight = false;
	private static int attemptsMadeSoFar = 0;
	private static boolean playAgain = false;

	private static final String ENTER_GUESS_MESSAGE = "Enter your guess: ";
	private static final String ENTER_CODE_MESSAGE = "Enter your code: ";

	public static void main(String[] args) {

		do {
			attemptsLeft = getDifficultyLevel();
			mode = getGameMode();
			if (mode == Mode.Player) {
				ball = new Ball().getRandomPegs();
				System.out.println("The computer has thought of a pattern. Lets proceed");

				while (attemptsLeft != 0 || gotAnswerRight) {

					attemptsLeft--;
					String userAnswer = getInputFromUser(ENTER_GUESS_MESSAGE);
					String feedback = PegsControler.checkAnswer(ball, userAnswer);
					attemptsMadeSoFar++;
					Ball.printToConsole(userAnswer, feedback, attemptsLeft);
					if (feedback.equalsIgnoreCase("4b0w")) {
						gotAnswerRight = true;
						System.out.println("You won in " + attemptsMadeSoFar + " attempts.");
						break;
					}

				}

				if (attemptsLeft == 0 || !gotAnswerRight) {
					System.out.println("You Lost. Pattern was " + ball + " Play again?");
				}

			} else if (mode == Mode.Computer) {
				ComputerGuesser.generateAllCombinations();
				System.out.println("Human, please make a code for the computer to solve");
				String answerBall = StartPlaying.getInputFromUser(ENTER_CODE_MESSAGE);
				ComputerGuesser.firstPruneSearchSpace(answerBall);
				ComputerGuesser.setNumberOfAttemptsAllowed(attemptsLeft);

				ArrayList<String> ballCombinations = ComputerGuesser.getCombinations();
				int numberOfAttempts = ComputerGuesser.getNumberOfAttempts();
				boolean foundAnswer = ComputerGuesser.getFoundAnswer();

				attemptsLeft = attemptsLeft - numberOfAttempts;

				while (ballCombinations.size() != 0 || foundAnswer) {
					if (attemptsLeft <= 0) {
						break;
					}
					String randomResponse = ballCombinations.get(0);
					attemptsLeft--;
					String result = PegsControler.checkAnswer(answerBall, randomResponse);
					Ball.printToConsole(randomResponse, result, attemptsLeft);

					numberOfAttempts++;
					if (Integer.parseInt(result.substring(0, 1)) == 4) {
						foundAnswer = true;
						System.out.println("Computer Won !!!");
						break;

					} else {
						ballCombinations.remove(randomResponse);
					}
				}
				if (!foundAnswer) {
					System.out.println("Computer Lost !!!");
				}

			}

			playAgain = askUserToPlayAgain();

		} while (playAgain);
		scanner.close();

	}

	// if on computer mode, player will choose the colors, get the colors from
	// the user
	// TODO error checking and validation part remains
	private static String getBallsFromUser() {
		boolean incorrectColor = false;
		System.out.print("Please provide combination of 4 colors, valid choices are: " + Arrays.toString(Ball.colors));
		String color = scanner.nextLine();
		boolean inputLengthValid = false;
		boolean validColorsUsed = false;

		while (color.length() != 4) {
			System.out.println("Invalid input. Please choose exactly 4 colors.");
			color = changeString();
		}
		for (int i = 0; i < 4; i++) {
			while (color.charAt(i) != 'b' && color.charAt(i) != 'g' && color.charAt(i) != 'r' && color.charAt(i) != 'y'
					&& color.charAt(i) != 'p' && color.charAt(i) != 'o') {
				System.out.println("Invalid input. Please choose 4 colors out of: " + Arrays.toString(Ball.colors));
				color = changeString();
			}
		}
		return color;
	}

	private static String changeString() {
		return scanner.nextLine();
	}

	public static int getDifficultyLevel() {
		System.out.print("Choose difficulty level (E)asy, (M)edium or (D)ifficult: ");
		String difficultyLevel = scanner.nextLine();
		while (difficultyLevel != "e" && difficultyLevel != "m" && difficultyLevel != "d") {
			if (difficultyLevel.equalsIgnoreCase("e") || difficultyLevel.equalsIgnoreCase("easy")) {
				return NO_OF_MOVES_EASY;
			} else if (difficultyLevel.equalsIgnoreCase("m") || difficultyLevel.equalsIgnoreCase("medium")) {
				return NO_OF_MOVES_MEDIUM;
			} else if (difficultyLevel.equalsIgnoreCase("d") || difficultyLevel.equalsIgnoreCase("difficult")) {
				return NO_OF_MOVES_DIFFICULT;
			} else {
				System.out.print("Invalid input. Please choose (E)asy, (M)edium or (D)ifficult: ");
				difficultyLevel = changeString();
			}
		}
		return 0;
	}

	public static boolean askUserToPlayAgain() {
		System.out.print("Play again? (Y)es or (N)o: ");
		String playAgain = scanner.nextLine();
		while (playAgain != "y" || playAgain != "Y" || playAgain != "n" || playAgain != "N") {
			if (playAgain.equalsIgnoreCase("y") || playAgain.equalsIgnoreCase("Y")) {
				return true;
			} else if (playAgain.equalsIgnoreCase("n") || playAgain.equalsIgnoreCase("N")) {
				System.out.println("Thank you for playing.");
				return false;
			} else {
				System.out.print("Incorrect Response. Play again? (Y)es or (N)o:  ");
				playAgain = changeString();
			}
		}
		return false; // never reached
	}

	public static Mode getGameMode() {
		System.out.print("Play as a (P)layer or (C)omputer?: ");
		String mode = scanner.nextLine();
		while (mode != "p" || mode != "Player" || mode != "c" || mode != "Computer") {
			if (mode.equalsIgnoreCase("p") || mode.equalsIgnoreCase("Player")) {
				return Mode.Player;
			} else if (mode.equalsIgnoreCase("c") || mode.equalsIgnoreCase("Computer")) {
				return Mode.Computer;
			} else {
				System.out.print("Invalid input. Please select (P)layer or (C)omputer: ");
				mode = changeString();
			}
		}
		return Mode.Undefined;
	}

	public enum Mode {
		Player, Computer, Undefined;
	}

	// TODO organize the difficulty level for the game nicely
	public enum Difficulty {
		Easy, Medium, Difficult;
	}

	// REPLACE WITH LUKE's VALIDATED CODE
	protected static String getInputFromUser(String message) {
		System.out.print(message);
		String color = scanner.nextLine();
		try{
			while (color.length() != 4 || Integer.parseInt(color)<1111 || Integer.parseInt(color)>6666 ) {
				System.out.println("Invalid input. Please choose exactly 4 numbers out of " + Arrays.toString(Ball.colors));
				color = changeString();
			}
		}
		catch(NumberFormatException e) {
				System.out.println("Invalid input. Please choose exactly 4 numbers out of " + Arrays.toString(Ball.colors));
				getInputFromUser(message);
		}
		return color;
	}
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the starting class of The Mastermind game. This class uses other
 * objects throughout the program and uses them
 * 
 * @author Bijay Koirala, help from Luke, Brad and Jason
 *
 */
public class StartPlaying {

	private static int attemptsLeft = 0;
	private static Mode mode;
	private static String ball;
	private static Pegs ball_obj;
	private static Scanner scanner = new Scanner(System.in);

	private static int NO_OF_MOVES_EASY = 25;
	private static int NO_OF_MOVES_MEDIUM = 15;
	private static int NO_OF_MOVES_DIFFICULT = 10;

	private static boolean gotAnswerRight = false;
	private static int attemptsMadeSoFar = 0;
	private static boolean playAgain = false;
	private static boolean validatedFlag = false;

	private static final String ENTER_GUESS_MESSAGE = "Enter your guess: ";
	private static final String ENTER_CODE_MESSAGE = "Enter your code: ";

	/**
	 * The main running method of the project
	 * @param args the default parameters used (this value is not used in the program)
	 */
	public static void main(String[] args) {

		do {
			attemptsMadeSoFar = 0;
			attemptsLeft = getDifficultyLevel(); // step 1. get the difficulty
													// level, easy, medium,
													// difficult
			mode = getGameMode(); // step 2. get player mode or computer mode

			if (mode == Mode.Player) { // if it is player mode
				ball_obj = new Pegs(); // create pegs
				ball = ball_obj.getRandomPegs(); // get random pegs and put
													// their combination in a
													// variable
				System.out.println("The computer has thought of a pattern. Lets proceed");

				while (attemptsLeft != 0 || gotAnswerRight) { // run until no
																// attempts are
																// left or the
																// user gets the
																// answer right

					attemptsLeft--; // reduce the number of attempts left
					String userAnswer = getInputFromUser(ENTER_GUESS_MESSAGE, ball_obj, Mode.Player); // get feedback
					String feedback = PegsControler.checkAnswer(ball, userAnswer); // check answer
					attemptsMadeSoFar++;
					Pegs.printToConsole(userAnswer, feedback, attemptsLeft); //  print to shell
					if (feedback.equalsIgnoreCase("4b0w")) { // if the answer is all correct, break saying the user won
						gotAnswerRight = true;
						System.out.println("You won in " + attemptsMadeSoFar + " attempts.");
						StatsOnExit.addAndGetAverage(attemptsMadeSoFar);
						break;
					}

				}

				// if the user had broken out of the loop due to a faulure, say so, ask user if she/he wants to play again
				if (attemptsLeft == 0 || !gotAnswerRight) {
					System.out.println("You Lost. Pattern was " + ball + " Play again?");
					StatsOnExit.addAndGetAverage(attemptsMadeSoFar);
					}

			} 
			
			// if it is computer mode, make player guess a pattern and let the computer try to come up with solution
			else if (mode == Mode.Computer) {
				ComputerGuesser.generateAllCombinations(); // first generate all 6^4 combinations
				ComputerGuesser.setNumberOfAttemptsAllowed(attemptsLeft); // show attempts
				System.out.println("Human, please make a code for the computer to solve");
				String answerBall = StartPlaying.getInputFromUser(ENTER_CODE_MESSAGE, ball_obj, mode.Computer); // get desired code from user
				ComputerGuesser.firstPruneSearchSpace(answerBall, true); // prune search spaces based on the result of 1111
				

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
					Pegs.printToConsole(randomResponse, result, attemptsLeft);

					numberOfAttempts++;
					if (Integer.parseInt(result.substring(0, 1)) == 4) { // computer wins only if there are 4 blacks
						foundAnswer = true;
						System.out.println("Computer Won !!!");
						StatsOnExit.addAndGetAverage(numberOfAttempts);
						break;

					} else {
						ballCombinations.remove(randomResponse);
						
					}
				}
				if (!foundAnswer) {
					StatsOnExit.addAndGetAverage(numberOfAttempts);
					System.out.println("Computer Lost !!!");
					
				}

			}

			playAgain = askUserToPlayAgain();

		} while (playAgain);
		scanner.close();

	}

	/**
	 * Function to read a line from the scanner
	 * @return the next line read in shell
	 */
	private static String changeString() {
		return scanner.nextLine();
	}

	/**
	 * Method that asks user for the difficulty level, 
	 * @return returns enum values for easy, medium or difficult mode after validating
	 */
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

	/**
	 * Method that asks if the user wants to play again. validates the result as well
	 * @return returns enum representing yes or no
	 */
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

	/**
	 * Method that asks user for the game mode, 
	 * @return returns either player or computer
	 */
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

	/**
	 * DIFFERENT ENUM VALUES FOR MODE (Undefined is used for exceptional cases - not used in the program currently)
	 * @author Bijay Koirala
	 *
	 */
	public enum Mode {
		Player, Computer, Undefined;
	}

	/**
	 * DIFFERENT ENUM VALUES FOR DIFFICULTY LEVEL (Undefined is used for exceptional cases - not used in the program currently)
	 * @author Bijay Koirala
	 *
	 */
	public enum Difficulty {
		Easy, Medium, Difficult;
	}

	/**
	 * A method that asks input from the user. It validates the user input and also considers words like hint and quit in its execution
	 * @param message The message to print to the shell for promting the user for an input
	 * @param b The peg under consideration
	 * @param mode The mode  - plaeyer or computer mode
	 * @return
	 */
	protected static String getInputFromUser(String message, Pegs b, Mode mode) {
		System.out.print(message);
		String color = scanner.nextLine();
		if (mode == Mode.Player) {
			if (color.equalsIgnoreCase("hint")) {
				System.out.println(b.getHint());
				return color;
			}
			if (color.equalsIgnoreCase("quit") || color.equalsIgnoreCase("give up")) {
				System.out.print("The computers hand was " + b.getPegs());
				System.out.println("Thank you for playing");
				System.exit(0); // the player does not want to play so exit
			}
			while (validatedFlag == false) {
				for (int i = 0; i < 4; i++) {
					if (color.length() != 4 || Character.getNumericValue(color.charAt(i)) < 1
							|| Character.getNumericValue(color.charAt(i)) > 6) {
						System.out.println("Invalid input. Please choose exactly 4 numbers out of "
								+ Arrays.toString(Pegs.colors));
						validatedFlag = false;
						color = changeString();
						break;

					} else if (color.length() == 4 || Character.getNumericValue(color.charAt(i)) >= 1
							|| Character.getNumericValue(color.charAt(i)) <= 6) {
						validatedFlag = true;
					}
				}
			}
		}
		if (mode == Mode.Computer) {
			if (color.equalsIgnoreCase("quit") || color.equalsIgnoreCase("give up")) {
				System.exit(0);
			}
			while (validatedFlag == false) {
				for (int i = 0; i < 4; i++) {
					if (color.length() != 4 || Character.getNumericValue(color.charAt(i)) < 1
							|| Character.getNumericValue(color.charAt(i)) > 6) {
						System.out.println("Invalid input. Please choose exactly 4 numbers out of "
								+ Arrays.toString(Pegs.colors));
						validatedFlag = false;
						color = changeString();
						break;

					} else if (color.length() == 4 || Character.getNumericValue(color.charAt(i)) >= 1
							|| Character.getNumericValue(color.charAt(i)) <= 6) {
						validatedFlag = true;
					}
				}
			}
		}
		validatedFlag = false;
		return color;
	}
}

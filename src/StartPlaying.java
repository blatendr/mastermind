import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StartPlaying {

	private static int attemptsLeft = 0;
	private static Mode mode;
	private static String ball;
	private static Ball ball_obj;
   private static Scanner scanner = new Scanner(System.in);

	private static int NO_OF_MOVES_EASY = 15;
	private static int NO_OF_MOVES_MEDIUM = 10;
	private static int NO_OF_MOVES_DIFFICULT = 5;

	private static boolean gotAnswerRight = false;
	private static int attemptsMadeSoFar = 0;
	private static boolean playAgain = false;
	private static boolean validatedFlag=false;

	private static final String ENTER_GUESS_MESSAGE = "Enter your guess: ";
	private static final String ENTER_CODE_MESSAGE = "Enter your code: ";

	public static void main(String[] args) {

		do {
			attemptsLeft = getDifficultyLevel();
			mode = getGameMode();
			if (mode == Mode.Player) {
				ball_obj = new Ball();
            ball = ball_obj.getRandomPegs();
				System.out.println("The computer has thought of a pattern. Lets proceed");

				while (attemptsLeft != 0 || gotAnswerRight) {

					attemptsLeft--;
					String userAnswer = getInputFromUser(ENTER_GUESS_MESSAGE, ball_obj, Mode.Player);
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
				String answerBall = StartPlaying.getInputFromUser(ENTER_CODE_MESSAGE, ball_obj, mode.Computer);
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
	protected static String getInputFromUser(String message, Ball b, Mode mode) {
		System.out.print(message);
		String color = scanner.nextLine();
		if(mode==Mode.Player){
			if (color.equals("hint") || color.equals("Hint"))
	        {
	        System.out.println(b.getHint());
	        return color;
	        }
			if(color.equals("quit") || color.equals("Quit")||color.equals("give up") ||color.equals("Give up"))
            {
            System.out.print("The computers hand was "+b.getPegs());
            System.exit(0);
            }
		}
		if(mode==Mode.Computer){
			if(color.equals("quit") || color.equals("Quit")||color.equals("give up") ||color.equals("Give up"))
	        {
	        System.exit(0);
	        }
	         while(validatedFlag==false){
	 			for (int i = 0; i < 4; i++) {
	 				if(color.length() != 4 || Character.getNumericValue(color.charAt(i))<1 || Character.getNumericValue(color.charAt(i))>6){
	 					System.out.println("Invalid input. Please choose exactly 4 numbers out of " + Arrays.toString(Ball.colors));
	 					validatedFlag=false;
	 					color=changeString();
	 					break;
	 					
	 				}
	 				else if(color.length() == 4 || Character.getNumericValue(color.charAt(i))>=1 || Character.getNumericValue(color.charAt(i))<=6){
	 					System.out.println("validated");
	 					validatedFlag=true;
	 				}
	 			}
	 		}
		}
 		validatedFlag=false;
 		return color;
	}
}

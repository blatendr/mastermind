import java.util.Arrays;
import java.util.Scanner;

public class StartPlaying {

	private static int attemptsLeft = 0;
	private static Mode mode;
	private static String ball;
	private static Scanner scanner = new Scanner(System.in);
	
	private static int NO_OF_MOVES_EASY = 20;
	private static int NO_OF_MOVES_MEDIUM = 15;
	private static int NO_OF_MOVES_DIFFICULT = 10;

	public static void main(String[] args) {
		// step 1. ask the player if he wants to play easy, medium or hard level
		attemptsLeft = getDifficultyLevel();
		mode = getGameMode();
		if (mode == Mode.Player){
			ball = new Ball().getPegs();
		} 
		else if (mode == Mode.Computer){
			String ballCombination = getBallsFromUser();
			
			ball = ballCombination;
		}
		scanner.close();
		System.out.println(ball + attemptsLeft);
	}

	// if on computer mode, player will choose the colors, get the colors from the user
	// TODO error checking and validation part remains
	private static String getBallsFromUser() {
		System.out.print("Please provide combination of colors, valid choices are: "+ Arrays.toString(Ball.colors));
		String color = scanner.nextLine();
		return color;
	
	}

	// get the difficulty level user desires for
	// TODO error checking and validation part remains
	public static int getDifficultyLevel() {
		System.out.print("Choose difficulty level (E)asy, (M)edium or (D)ifficult: ");
		String difficultyLevel = scanner.nextLine();
		if (difficultyLevel.equalsIgnoreCase("e") || difficultyLevel.equalsIgnoreCase("easy")) {
			return NO_OF_MOVES_EASY;
		} else if (difficultyLevel.equalsIgnoreCase("m") || difficultyLevel.equalsIgnoreCase("medium")) {
			return NO_OF_MOVES_MEDIUM;
		} else if (difficultyLevel.equalsIgnoreCase("d") || difficultyLevel.equalsIgnoreCase("difficult")) {
			return NO_OF_MOVES_DIFFICULT;
		} else {
			return 0;
		}
	}
	
	// get the player mode user desires for
	// TODO error checking and validation part remains
	public static Mode getGameMode() {
		System.out.println("Play as a (P)layer or (C)omputer?: ");
		String mode = scanner.nextLine();
		if (mode.equalsIgnoreCase("p") || mode.equalsIgnoreCase("Player")) {
			return Mode.Player;
		} else if (mode.equalsIgnoreCase("c") || mode.equalsIgnoreCase("Computer")) {
			return Mode.Computer;
		} else {
			return Mode.Undefined;
		}
	}

	public enum Mode {
		Player, Computer, Undefined;
	}

	// TODO organize the difficulty level for the game nicely
	public enum Difficulty {
		Easy, Medium, Difficult;
	}
}

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
			
			// a loop here to get the values from the user and to keep checking it until they have attempts left
			
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
      boolean incorrectColor=false;
		System.out.print("Please provide combination of 4 colors, valid choices are: "+ Arrays.toString(Ball.colors));
		String color = scanner.nextLine();
      while (color.length()!=4){
         System.out.println("Invalid input. Please choose exactly 4 colors.");
         color=changeString(color);
      }
      for (int i = 0 ; i<4 ; i++) {
         while(color.charAt(i)!='b' && color.charAt(i)!='g' && color.charAt(i)!='r' && color.charAt(i)!='y' && color.charAt(i)!='p' && color.charAt(i)!='o'){
            System.out.println("Invalid input. Please choose 4 colors out of: " + Arrays.toString(Ball.colors));
            color=changeString(color);
         }
      }
     return color;
	}

   private static String changeString(String s){
      return scanner.nextLine();
   }
   
	// get the difficulty level user desires for
	// TODO error checking and validation part remains
	public static int getDifficultyLevel() {
		System.out.print("Choose difficulty level (E)asy, (M)edium or (D)ifficult: ");
		String difficultyLevel = scanner.nextLine();
      while(difficultyLevel!="e" && difficultyLevel!="m" && difficultyLevel!="d"){
   		if (difficultyLevel.equalsIgnoreCase("e") || difficultyLevel.equalsIgnoreCase("easy")) {
   			return NO_OF_MOVES_EASY;
   		} else if (difficultyLevel.equalsIgnoreCase("m") || difficultyLevel.equalsIgnoreCase("medium")) {
   			return NO_OF_MOVES_MEDIUM;
   		} else if (difficultyLevel.equalsIgnoreCase("d") || difficultyLevel.equalsIgnoreCase("difficult")) {
   			return NO_OF_MOVES_DIFFICULT;
   		} else {
   			System.out.print("Invalid input. Please choose (E)asy, (M)edium or (D)ifficult: ");
            difficultyLevel=changeString(difficultyLevel);
   		}
      }
      return 0;
	}
	
	// get the player mode user desires for
	// TODO error checking and validation part remains
	public static Mode getGameMode() {
		System.out.print("Play as a (P)layer or (C)omputer?: ");
		String mode = scanner.nextLine();
      while(mode!="p" || mode!="Player" || mode!="c" || mode!="Computer"){
   		if (mode.equalsIgnoreCase("p") || mode.equalsIgnoreCase("Player")) {
   			return Mode.Player;
   		} else if (mode.equalsIgnoreCase("c") || mode.equalsIgnoreCase("Computer")) {
   			return Mode.Computer;
   		} else {
            System.out.print("Invalid input. Please select (P)layer or (C)omputer: ");
   			mode=changeString(mode);
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
}

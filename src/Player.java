import java.util.ArrayList;

/**
 * A class dedicated for user stats
 * @author Bijay Koirala
 *
 */

public class Player {
	
	private String name;
	private int numberOfAttempts;
	private static ArrayList<Integer> userStats = new ArrayList<>();
	private static ArrayList<Integer> computerStats = new ArrayList<>();
	private static int numberOfPlayerGames = 0;
	private static int numberOfComputerGames = 0;
	
	public Player(String name){
		this.name = name;
		numberOfAttempts = 0;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public void increaseNumberOfAttempts() {
		numberOfAttempts++;
	}
	
	public static void addToUserStats(int times){
		userStats.add(times);
		numberOfPlayerGames += 1;
	}
	
	public static void addToComputerStats(int times){
		computerStats.add(times);
		numberOfComputerGames += 1;
		}
	
}

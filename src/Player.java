import java.util.ArrayList;

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
	
	public static void averageUserRecord(){
		int total = 0;
		for (Integer i : userStats){
			total += i;
		}
		//System.out.println( (1.0 * total) / (1.0 * numberOfPlayerGames));
	}
	
	public static void averageComputerRecord(){
		int total = 0;
		for (Integer i : computerStats){
			total += i;
		}
		//System.out.println( (1.0 * total) / (1.0 * numberOfComputerGames));
	}
	
}


public class Player {
	
	private String name;
	private int numberOfAttempts;
	
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
}

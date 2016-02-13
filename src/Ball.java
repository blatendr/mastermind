import java.util.Random;

public class Ball {
	private String currentBalls = "";
	
	//public static String[] colors = {"B", "G", "R", "Y", "P", "O" };
	public static String[] colors = {"1","2","3","4","5","6"};
	
	public String getRandomPegs(){
		String tempPattern = "";
		for (int i = 0; i < 4; i++){
			int rnd = new Random().nextInt(colors.length);
			tempPattern += colors[rnd];
		}
		currentBalls = tempPattern;
		return currentBalls;
	}
	
	public String getPegs(){
		return currentBalls;
	}
	
	/**
	 * This is going to set the balls as per user selection
	 * @param balls the combination of balls the user wants to give
	 */
	public void setBalls(String balls){
		currentBalls = balls;
	}
	
	public String getHint(Ball balls){
		// give a random position out
		return "Hint will be determined and given here";
	}
	
	public String giveUp(Ball balls){
		return "The solution was "+ balls.getPegs();
	}
	
	public static String checkAnswer(Ball answer, Ball userResponse){
		return "right or not";
	}
	

}

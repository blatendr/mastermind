import java.util.Random;

public class Ball {
	private String currentBalls = "";
	
	public static String[] colors = {"B", "G", "R", "Y", "P", "O" };
	
	public String getPegs(){
		String tempPattern = "";
		for (int i = 0; i < 4; i++){
			int rnd = new Random().nextInt(colors.length);
			tempPattern += colors[rnd];
		}
		currentBalls = tempPattern;
		return currentBalls;
	}
	
	public void setBalls(String balls){
		currentBalls = balls;
	}
	
	public String getHint(Ball balls){
		return "Hint will be determined and given here";
	}
	
	public String giveUp(Ball balls){
		return "The solution was "+ balls.getPegs();
	}
	
	public String checkAnswer(Ball answer, Ball userResponse){
		return "right or not";
	}
	

}

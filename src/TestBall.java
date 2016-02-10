
public class TestBall {
	
	public static void main(String[] main){
		Ball bAnswerBall = new Ball();
		bAnswerBall.setBalls("BGYW");
		
		Ball userBall = new Ball();
		userBall.setBalls("BBBB");
		
		String response = Ball.checkAnswer(bAnswerBall, userBall);
		System.out.println(response);
		
	}

}
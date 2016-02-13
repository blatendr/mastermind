
public class TestBall {
	
	public static void main(String[] main){
		Ball bAnswerBall = new Ball();
		bAnswerBall.setBalls("pssp");
		
		Ball userBall = new Ball();
		userBall.setBalls("sasp");
		
		String response = checkAnswer(bAnswerBall.getPegs(), userBall.getPegs());
		System.out.println(response);
		
	}
	
	
	public static String checkAnswer(String bAnswerBall, String userBall){
		int numberOfBlacks = 0;
		int numberOfWhites = 0;
		
		StringBuilder sbAnswer = new StringBuilder(bAnswerBall.trim().toString());
		StringBuilder sbUserAns = new StringBuilder(userBall.trim().toString());
		
		StringBuilder sbAnswerPhase1 = new StringBuilder();
		StringBuilder sbUserAnswerPhase1 = new StringBuilder();
		
		//System.out.println(sbAnswer);
		//System.out.println(sbUserAns);
		
		// if both of them contain the same balls, replace both of them and add one to the number of black counts
		for (int i = 0; i < sbUserAns.length(); i++){
			if (sbUserAns.charAt(i) == sbAnswer.charAt(i)){
				numberOfBlacks ++;
			}
			else{
				sbUserAnswerPhase1.append(sbUserAns.charAt(i));
				sbAnswerPhase1.append(sbAnswer.charAt(i));
			}
		}
		
		//System.out.println("Real answer after removing the blacks" +sbAnswerPhase1);
		//System.out.println("User answer after removing the blacks " +sbUserAnswerPhase1);
		
		
		
		// if each color of user's answer is in the real answer, add white count and remove both of them 
		for (int i =0; i < sbUserAnswerPhase1.length(); i++){
			char pegUnderConsideration = sbUserAnswerPhase1.charAt(i);
			for (int j =0; j < sbAnswerPhase1.length(); j++){
				char comparisionPeg = sbAnswerPhase1.charAt(j);
				// System.out.println("Comparing "+pegUnderConsideration+" with "+ comparisionPeg+" Answer is : "+sbAnswerPhase1+" User gave: "+sbUserAnswerPhase1);
				if (pegUnderConsideration == comparisionPeg){ // meaning it was found
					numberOfWhites++;
					sbAnswerPhase1.setCharAt(j, 'A');
					break; // break out if you already found an answer, no need for further comparisions
				}
			}
			
		}
		
		//System.out.println(sbAnswer);
		//System.out.println(sbUserAns);
		
		return numberOfBlacks+"b"+numberOfWhites+"w";
		
	}

}
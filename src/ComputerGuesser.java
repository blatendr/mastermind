import java.util.ArrayList;

public class ComputerGuesser {
	private static ArrayList<String> ballCombinations;
	public static String knownSoFar = "";

	public static void generateAllCombinations() {
		ballCombinations = new ArrayList<>();
		for (String first : Ball.colors) {
			for (String second : Ball.colors) {
				for (String third : Ball.colors) {
					for (String fourth : Ball.colors) {
						String pattern = first + second + third + fourth;
						ballCombinations.add(pattern);
					}
				}
			}
		}
	}

	public ArrayList<String> getCombinations() {
		return ballCombinations;
	}

	public static void main(String[] args) {
		generateAllCombinations();
		//String answerBall = StartPlaying.getInputFromUser();
		String answerBall = "1212";
		
	    String guess1 = "1111";
	    String result1 = TestBall.checkAnswer(answerBall, guess1);
	    System.out.println(result1);
	    int numberOf1s = Integer.parseInt(result1.substring(0,1));
	    
	    removeChoicesThatDoNotHaveInAllPlaces(numberOf1s, "1");
	    displayChoices();
	    numberOfChoicesLeft();
	    
	    String guess2 = "2222";
	    String result2 = TestBall.checkAnswer(answerBall, guess2);
	    System.out.println(result2);
	    int numberOf2s = Integer.parseInt(result1.substring(0,1));
	    
	    removeChoicesThatDoNotHaveInAllPlaces(numberOf2s, "2");
	    displayChoices();
	    numberOfChoicesLeft();
	    
//	    String guess3 = "3333";
//	    String result3 = TestBall.checkAnswer(answerBall, guess3);
//	    System.out.println(result3);
//	    int numberOf3s = Integer.parseInt(result1.substring(0,1));
//	    
//	    removeChoicesThatDoNotHaveInAllPlaces(numberOf3s, "3");
//	    displayChoices();
//	    numberOfChoicesLeft();
//	    
//	    String guess4 = "4444";
//	    String result4 = TestBall.checkAnswer(answerBall, guess4);
//	    System.out.println(result4);
//	    int numberOf4s = Integer.parseInt(result1.substring(0,1));
//	    
//	    removeChoicesThatDoNotHaveInAllPlaces(numberOf4s, "4");
//	    displayChoices();
//	    numberOfChoicesLeft();
//	    
//	    String guess5 = "5555";
//	    String result5 = TestBall.checkAnswer(answerBall, guess5);
//	    System.out.println(result5);
//	    int numberOf5s = Integer.parseInt(result5.substring(0,1));
//	    
//	    removeChoicesThatDoNotHaveInAllPlaces(numberOf5s, "5");
//	    displayChoices();
//	    numberOfChoicesLeft();
	    
//	    String guess6 = "6666";
//	    String result6 = TestBall.checkAnswer(answerBall, guess6);
//	    System.out.println(result6);
//	    int numberOf6s = Integer.parseInt(result6.substring(0,6));
//	    
//	    removeChoicesThatDoNotHaveInAllPlaces(numberOf6s, "6");
//	    displayChoices();
//	    numberOfChoicesLeft();
	    
	}

	public static void displayChoices() {
		for (String eachChoice : ballCombinations) {
			System.out.print(eachChoice+",");
		}
	}

	public static void removeChoicesThatHas(String identified) {
		ArrayList<String> toRemove = new ArrayList<>();
		for (String eachChoice : ballCombinations) {
			if (eachChoice.contains(identified)) {
				toRemove.add(eachChoice);
			}
		}
		ballCombinations.removeAll(toRemove);
	}
	
	// TODO UNTESTED METHOD
	public static void removeChoicesThatDoNotHaveInFirstTwoPlaces(int number, String letterUnderConsideration){
		ArrayList<String> toAdd = new ArrayList<>();
		for(String eachChoice : ballCombinations){
			String result = eachChoice.substring(0,2).replaceAll(letterUnderConsideration, "");
			if (result.trim().length() == (2-number)){
				toAdd.add(eachChoice);
			}
		}
		ballCombinations.clear();
		ballCombinations = toAdd;
	}
	
	// TODO UNTESTED METHOD
	public static void removeChoicesThatDoNotHaveInLastTwoPlaces(int number, String letterUnderConsideration){
		ArrayList<String> toAdd = new ArrayList<>();
		for(String eachChoice : ballCombinations){
			String result = eachChoice.substring(3).replaceAll(letterUnderConsideration, "");
			if (result.trim().length() == (2-number)){
				toAdd.add(eachChoice);
			}
		}
		ballCombinations.clear();
		ballCombinations = toAdd;
	}
	
	public static void removeChoicesThatDoNotHaveInAllPlaces(int number, String letterUnderConsideration){
		ArrayList<String> toAdd = new ArrayList<>();
		for(String eachChoice : ballCombinations){
			String result = eachChoice.replaceAll(letterUnderConsideration, "");
			if (result.trim().length() == (4-number)){
				toAdd.add(eachChoice);
			}
		}
		ballCombinations.clear();
		ballCombinations = toAdd;
	}
	
	public static void removeChoicesThatDoesnotHave(String identified) {
		ArrayList<String> toRemove = new ArrayList<>();
		for (String eachChoice : ballCombinations) {
			if (eachChoice.contains(identified)) {
				toRemove.add(eachChoice);
			}
		}
		ballCombinations.clear();
		ballCombinations = toRemove;
	}
	
	public static void numberOfChoicesLeft(){
		System.out.println("\nChoices Left "+ballCombinations.size());
	}

}

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
		removeChoicesThatHas("1");
	    displayChoices();
	    numberOfChoicesLeft();
	    
	    removeChoicesThatHas("2");
	    displayChoices();
	    numberOfChoicesLeft();
	    
	    removeChoicesThatHas("3");
	    displayChoices();
	    numberOfChoicesLeft();
	    
	    removeChoicesThatDoesnotHave("4");
	    displayChoices();
	    numberOfChoicesLeft();
	    
	    removeChoicesThatDoesnotHave("5");
	    displayChoices();
	    numberOfChoicesLeft();

	    removeChoicesThatDoesnotHave("66");
	    displayChoices();
	    numberOfChoicesLeft();
	    
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

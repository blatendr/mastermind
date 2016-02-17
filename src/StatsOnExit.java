import java.util.ArrayList;

/**
 * This class deals with all the stats the game goes through after each run
 * @author Bijay Koirala
 *
 */
public class StatsOnExit {
	
	public static ArrayList<Integer> allGames = new ArrayList<>();
	
	
	public static ArrayList<Integer> getGameStats(){
		return allGames;
	}
	
	public static void addToStats(int aNumber){
		allGames.add(aNumber);
	}
	
	public static void resetCounter(){
		allGames.clear();
	}
	
	public static void printAverage(){
		Integer sum = 0;
		double average = sum; // will be replaced later if the arraylist is not empty
		  if(!allGames.isEmpty()) {
		    for (Integer aGameStat : allGames) {
		        sum += aGameStat;
		    }
		    average = (sum.doubleValue() /(1.0 * allGames.size()));
		    System.out.println("Average after "+ allGames.size() +" games: "+average);
		  }
		
	}
	
	public static void addAndGetAverage(int aNumber){
		addToStats(aNumber);
		printAverage();
		//printAllStats();
	}
	
	public static void printAllStats(){
		for (int oneStat : allGames){
			System.out.println(oneStat);
		}
	}

	public static void main(String[] args){
		
		for (int i = 0; i < 1000; i++){
			int attemptsLeft = 100;
			String answerBall = new Pegs().getRandomPegs();
			ComputerGuesser.generateAllCombinations(); // first generate all 6^4 combinations
			ComputerGuesser.setNumberOfAttemptsAllowed(100); // set 100 attempts
			ComputerGuesser.firstPruneSearchSpace(answerBall, false); // prune search spaces based on the result of 1111
			
			ArrayList<String> ballCombinations = ComputerGuesser.getCombinations();
			int numberOfAttempts = ComputerGuesser.getNumberOfAttempts();
			boolean foundAnswer = ComputerGuesser.getFoundAnswer();
			
			while (ballCombinations.size() != 0 || foundAnswer) {
				if (attemptsLeft <= 0) {
					break;
				}
				String randomResponse = ballCombinations.get(0);
				attemptsLeft--;
				String result = PegsControler.checkAnswer(answerBall, randomResponse);
				//Pegs.printToConsole(randomResponse, result, attemptsLeft);

				numberOfAttempts++;
				if (Integer.parseInt(result.substring(0, 1)) == 4) { // computer wins only if there are 4 blacks
					foundAnswer = true;
					StatsOnExit.addAndGetAverage(numberOfAttempts);
					break;

				} else {
					ballCombinations.remove(randomResponse);
					
				}
			}
			
			
		}
	}
}

package Ver1;

import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class MM {
//version one of player Mastermind game

 public static void main(String[] args)  
 {
 int NUMTURNS = 12;
 int HAND_SIZE = 4;
 
 int[] playerArray = new int[4];//initialize player hand
 
  int[] compArray = new int[4]; //initialize comp hand-----
  
  Scanner sc = new Scanner(System.in);

  
  Random randomGenerator = new Random();
      
  for (int i=0;i<compArray.length;i++)
   {
   int randomInt = randomGenerator.nextInt(6);
     compArray[i] = randomInt+1;
   System.out.print(compArray[i]);
   }
    
   System.out.println("Welcome to Mastermind, enter four ints in range 1-6 to guess computer's hand");
   
   for (int j=0;j<NUMTURNS;j++) //For loop that runs 12 times, one for each turn
    {
   int bothCorrect=0;
   int correctColor=0;
   boolean value = false;
    for (int i=0; i < HAND_SIZE; i++)
     {
     System.out.println("guess number "+(j+1)+":"); //scanner that gets each guess
     System.out.println("Please enter ball "+(i+1)+":");
     int guess = sc.nextInt();
     playerArray[i]= guess;
       if (guess == compArray[i]) //if statement that counts black pegs
          {
          bothCorrect++;
          value = true;
          }
     
       for (int k =0;k<HAND_SIZE;k++)
       {
          //System.out.println(compArray[k]+"         "+playerArray[i]); #use to see what is being compaired for red pegs

          if (compArray[k] == playerArray[i] && value != true)
          {
                      correctColor++; 
          }
       }
     }
    // end statements, if there are four black pegs, the player is a winner
    if (bothCorrect <4)
    {
       System.out.println("Num of black pegs (correct position and color) is "+bothCorrect);
       System.out.println("Num of red pegs (correct color) is "+correctColor);
    }
    if (bothCorrect ==4)
    {
    System.out.println("You have guessed the right combo! you win!");
     System.exit(0);
    
    }
    
    }
 }
}
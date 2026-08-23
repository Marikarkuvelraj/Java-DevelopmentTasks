import java.util.Random;
import java.util.Scanner;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Random random=new Random();

        int attempts=5;
        int secretnumber=random.nextInt(100)+1;

        System.out.println("welcome to the number guessing game");
        System.out.println("Guess the number between 1 to 100");
        System.out.println("You have "+attempts+"attempts");

        while (attempts>0){
            System.out.println("Enter the guessing number:");
        
        if(!sc.hasNextInt()){
            System.out.println("Invalid input!,Please enter the valid input");

            sc.next();
            continue;

        }
        int guess=sc.nextInt();
        if(guess==secretnumber){
            System.out.println("congrats!!you find the corrct number");
            break;
        }else if(guess>secretnumber){
            System.out.println("too high");

        }
        else{
            System.out.println("too low");
        }
        attempts--;
    

    if(attempts>0){
        System.out.println("Attempts left="+ attempts);
    }else{
        System.out.println("Game over");
        System.out.println("The correct number is:"+secretnumber);

    }
}
}}

 
    


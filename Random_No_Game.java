import java.util.*;

class choice{
    Scanner sc = new Scanner(System.in);
    void Choice(int number, int guess){
        if(number == guess){
            System.out.println("Congratulations...!!!!! Your choice is correct");
            System.exit(0);
        }
        else if(number >= 2*guess){
            System.out.println("Your choice is incorrect and also it is too high than your choice");
        }
        else if(number <= guess/2){
            System.out.println("Your choice is incorrect and also it is too low than your choice");
        }
        else{
            System.out.println("Your choice is incorrect and you are very close");
        }
    }
}
public class Random_No_Game {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Random R = new Random();
        int number = R.nextInt(100);
        choice ch = new choice();
            System.out.println("Enter your choice:");
            System.out.println("1.Enter game");
            System.out.println("2.Exit");
            int c = sc.nextInt();
            boolean playGame = true;
        
           while (playGame) {
            switch (c) {
                case 1:
                    System.out.println("To guess the my number, pls enter your guessing number");
                    int guess = sc.nextInt();
                    ch.Choice(number,guess);
                    boolean continueGuess = true;
                    while (continueGuess) {
                        System.out.println("Enter your choice");
                        System.out.println("1.Give choice again: ");
                        System.out.println("2.Exit");
                        int a = sc.nextInt();
                        switch (a) {
                            case 1:
                                System.out.println("Enter new choice: ");
                                guess = sc.nextInt();
                                ch.Choice(number, guess);
                                break;
                            case 2:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Enter valid choice");
                                break;
                    }    
                }
                    break;
                case 2:
                    playGame = false;
                    break;
            
                default:
                    System.out.println("Enter valid choice");
                    break;
            }
           }
        }
    }
    


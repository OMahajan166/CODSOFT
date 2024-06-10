//CODSOFT task 2
// Student Grade Calculator



import java.util.Scanner;

class excp{
    void check(double mark){
        if(mark > 100){
            throw new ArithmeticException("Enter Marks out of 100");
        }
    }
    
}




public class Grade_Calculator {

    static double per(double marks){
        return marks/4;
    }

    static char grade(double result){
        if(result > 90){
            return 'O';
        }
        else if(result <=90 && result > 80){
            return 'A';
        }
        else if(result <=80 && result > 70){
            return 'B';
        }
        else if(result <=70 && result > 60){
            return 'C';
        }
        else if(result <=60 && result > 50){
            return 'D';
        }
        else if(result <=50 && result > 40){
            return 'E';
        }
        else{
            return 'F';
        }
    }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    excp ex = new excp();

    System.out.println("Enter the marks for following subjects out of 100");
    System.out.println("Enter the marks of Mathematics: ");
    double math = sc.nextDouble();
    ex.check(math);
    System.out.println("Enter the marks of Chemistry: ");
    double che = sc.nextDouble();
    ex.check(che);
    System.out.println("Enter the marks of Physics: ");
    double phy = sc.nextDouble();
    ex.check(phy);
    System.out.println("Enter the marks of Biology: ");
    double bio = sc.nextDouble();
    ex.check(bio);
    double result = per(math+che+phy+bio);

    




    System.out.println("Your report card");
    System.out.println();
    System.out.println("Physics   |   Chemistry   |   Mathematics   |   Biology");
    System.out.println(phy+"          "+che+"            "+math+"             "+bio);
    System.out.println();
    System.out.println("Total marks: ");
    System.out.println(phy+che+math+bio);
    System.out.println();
    System.out.println("Percentage you got: ");
    System.out.println(result);
    System.out.println();
    if(math < 40){
        System.out.println("You are failed in Mathematics.");
    }
    if(che < 40){
        System.out.println("You are failed in Chemistry.");
    }
    if(phy < 40){
        System.out.println("You are failed in Physcics.");
    }
    if(bio < 40){
        System.out.println("You are failed in Biology.");
    }

    System.out.println();
    System.out.println("Your grade:");
    System.out.println(grade(result));

  }  
}

//Task 4 - Atm Interface

package Tasks;
import java.util.*;

	class setandget{
	int custmerid;
    int custmerpin;
    public int setcustmerid(int custid){
        this.custmerid=custid;
        return custid;
    }
    public void setcustmerpin(int custpin){
        this.custmerpin=custpin;
    }
    public int getCustmerid(){
        return custmerid;
    }
    public int getCustmerpin(){
        return custmerpin;
    }
    
}

	
public class Task4_ATM {


    public static void main (String[] args){
        setandget s=new setandget();

        Scanner sc=new Scanner(System.in);
        System.out.println("Please Enter your login id: ");

        int id= sc.nextInt();
        s.setcustmerid(id);
        System.out.println("Please Enter your ATM PIN:");
        int pin=sc.nextInt();
        s.setcustmerpin(pin);

        System.out.println("Custmer ID is:"+s.getCustmerid());



        int balance=500000,withdraw=0,deposite=0,choice ,tacct=0,acctnum;
        while (true){
            System.out.println("\nWelcome ATM machine");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw ");
            System.out.println("3. Deposite");
            System.out.println("4. Check Balance");
            System.out.println("5. Quit");
            System.out.println("\nEnter you choice: ");
            choice=sc.nextInt();

            if(choice==1||choice==2||choice==3||choice==4||choice==5) {


                switch (choice) {

                    case 1:

                        System.out.println("current Withdraw ammount is :" + withdraw);
                        System.out.println("current Deposited amount is : " + deposite);
                        System.out.println("current Transfeered amount is: " + tacct);
                        System.out.println("Your Total Balance is: " + balance);


                        break;
                    case 2:
                        System.out.println("Enter amount you want to withdraw: ");
                        withdraw = sc.nextInt();
                        if (balance > withdraw) {
                            balance = balance - withdraw;
                            System.out.println("Amount is Withdrawed successfully");
                        } else {
                            System.out.println("Your balance is less");
                        }
                        break;
                        
                    case 3:
                        System.out.println("Enter amount you want to deposite");
                        deposite = sc.nextInt();
                        balance = balance + deposite;
                        System.out.println("Amount is Deposited successfully");
                        break;

                    case 4:
                        System.out.println("Your Balance is:" + balance);
                        break;

                    case 5:
                    	System.out.println("Thank You!! Visit Again.");
                        System.exit(0);


                }
            }
           else {
               System.out.println("Please Enter appropriate choice");
               System.out.println("Thank You!!");
               return;
           }


        }

    }

}

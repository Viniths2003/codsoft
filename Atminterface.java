import java.util.Scanner;   // Atm interface program

class Bankaccount{
    double balance;

    public  Bankaccount(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        balance+=amount;
        System.out.println(amount+" DEPOSITED");
    }
    public boolean withdrawl(double amount){
        if(balance>amount){
            balance-=amount;
            System.out.println(amount+"WITHDRAWED");
            return true;
        }
        else {
            System.out.println("Insufficient balance");
            return false;
        }
    }
}

class Atm{
    private Bankaccount bankaccount;
    private Scanner scanner;

    public Atm(Bankaccount bankaccount){
        this.bankaccount = bankaccount;
        this.scanner = new Scanner(System.in);
    }
    public void displaymenu(){
        System.out.println("\n ATM menu");
        System.out.println("1.Check balance");
        System.out.println("2.Deposit");
        System.out.println("3.Withdraw");
        System.out.println("4.exit");
    }
    public void checkbalance(){
        System.out.println("Current Balance :"+bankaccount.getBalance());
    }
    public void deposit(){
        System.out.println("Enter The Amount To Be Deposited:");
        double amount = scanner.nextDouble();
        bankaccount.deposit(amount);

    }
    public void withdraw(){
        System.out.println("Enter The Withdrawl Amount:");
        double amount = scanner.nextDouble();
        bankaccount.withdrawl(amount);
    }
    public void option(int options){
        switch (options){
            case 1:
                checkbalance();
                break;
            case 2:
                deposit();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                System.out.println("Exiting ATM");
                System.exit(0);
                break;
            default:
                System.out.println("Enter the valid option");
        }
    }
}

class Main{
    public static void main(String[]args){
        Bankaccount bankaccount = new Bankaccount(1000);// initial balance
        Atm atm = new Atm(bankaccount);
        Scanner scanner = new Scanner(System.in);

        while(true){
            atm.displaymenu();
            System.out.println("Choose an Option:");
            int option = scanner.nextInt();
            atm.option(option);
        }
    }
}

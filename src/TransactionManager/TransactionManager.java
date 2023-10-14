package TransactionManager;

import java.util.Scanner;

public class TransactionManager {
    TransactionManager(){
    }
    public void run(){
        AccountDatabase account = new AccountDatabase();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Transaction Manager is running.");
        while (true){
            String command = scanner.next();
            String input = scanner.nextLine();
            if (command.equals("Q")){
                System.out.println("Transaction Manager is terminated.");
                break;
            }
            switch (command){
                case "O":
                    account.open(openAccount(input));
                    break;
                case "C":
                    account.close(closeAccount(input));
                    break;
                case "D":
                    account.deposit(deposit(input));
                    break;
                case "W":
                    account.withdraw(withdraw(input));
                    break;
                case "P":
                    account.printSorted();
                    break;
                case "PI":
                    account.printFeesAndInterests();
                    break;
                case "UB":
                    account.printUpdatedBalances();
                    break;
            }
        }
    }
    public Account openAccount(String input){
        String[] inputArray = input.trim().split("\\s+");
        String accountName = inputArray[0];
        String fname = inputArray[1];
        String lname = inputArray[2];
        Date dob = new Date(inputArray[3]);
        Profile profile = new Profile(fname, lname, dob);
        double bal = Double.parseDouble(inputArray[4]);
        boolean loyalty = false;
        switch(accountName) {
            case ("C"):
                return new Checking(profile, bal);
            case ("CC"):
                int campusCode = Integer.parseInt(inputArray[5]);
                return new CollegeChecking(profile, bal, campusCode);
            case ("S"):
                if (bal >= 2000) {
                    loyalty = true;
                }
                return new Savings(profile, bal, loyalty);
            case ("MM"):
                return new MoneyMarket(profile, bal, loyalty, 0);
            default:
                System.out.println("Invalid Account"); //fix the invalid exception
                break;
        }
        return null; // return null?
    }

    public Account closeAccount(String input){
        String[] inputArray = input.trim().split("\\s+");
        String accountName = inputArray[0];
        String fname = inputArray[1];
        String lname = inputArray[2];
        Date dob = new Date(inputArray[3]);
        Profile profile = new Profile(fname, lname, dob);
        switch(accountName) {
            case ("C"):
                return new Checking(profile);
            case ("CC"):
                return new CollegeChecking(profile);
            case ("S"):
                return new Savings(profile);
            case ("MM"):
                return new MoneyMarket(profile);
            default:
                System.out.println("Invalid Account"); //fix the invalid exception
                break;
        }
        return null; //return null?

    }

    public Account deposit(String input){
        String[] inputArray = input.trim().split("\\s+");
        String accountName = inputArray[0];
        String fname = inputArray[1];
        String lname = inputArray[2];
        Date dob = new Date(inputArray[3]);
        Profile profile = new Profile(fname, lname, dob);
        double depo = Double.parseDouble((inputArray[4]));
        switch(accountName) {
            case ("C"):
                return new Checking(profile, depo);
            case ("CC"):
                return new CollegeChecking(profile, depo);
            case ("S"):
                return new Savings(profile, depo);
            case ("MM"):
                return new MoneyMarket(profile, depo);
            default:
                System.out.println("Invalid Account"); //fix the invalid exception
                break;
        }
        return null;
    }
    public Account withdraw(String input){
        String[] inputArray = input.trim().split("\\s+");
        String accountName = inputArray[0];
        String fname = inputArray[1];
        String lname = inputArray[2];
        Date dob = new Date(inputArray[3]);
        Profile profile = new Profile(fname, lname, dob);
        double withD = Double.parseDouble((inputArray[4]));
        switch(accountName) {
            case ("C"):
                return new Checking(profile, withD);
            case ("CC"):
                return new CollegeChecking(profile, withD);
            case ("S"):
                return new Savings(profile, withD);
            case ("MM"):
                return new MoneyMarket(profile, withD);
            default:
                System.out.println("Invalid Account"); //fix the invalid exception
                break;
        }
        return null;
    }
}


//O - opens account
//C - closes account
//D - deposits money
//W- widthdraw
//P - prints account
//PI - prints fees and interest
//UB - updated ballence and applys interest and fees
//Q - quits
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
            processCommand(account, command, input);
        }
    }

    public void processCommand (AccountDatabase account, String command, String input){
        switch (command){
            case "O":
                if (openAccount(input) != null) {
                    account.open(openAccount(input));
                }
                break;
            case "C":
                if (closeAccount(input) != null) {
                    account.close(closeAccount(input));
                }
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
            default:
                System.out.println("Invalid Command");
        }
    }
    public Account openAccount(String input) {
        String[] inputArray = input.trim().split("\\s+");
        try {
                String accountName = inputArray[0];
                boolean loyalty = false;
                switch (accountName) {
                    case ("C"):
                       return openChecking(input);
                    case ("CC"):
                        return openCollegeChecking(input);
                    case ("S"):
                        return openSavings(input);
                    case ("MM"):
                       return openMoneyMarket(input);
                    default:
                        System.out.println("Invalid Account"); //fix the invalid exception
                }
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Missing data for opening an account.");
            }
            return null; // return null?
        }

        public Account openChecking(String input) {
            try {
                String[] inputArray = input.trim().split("\\s+");
                String fname = inputArray[1];
                String lname = inputArray[2];
                Date dob = new Date(inputArray[3]);
                Profile profile = new Profile(fname, lname, dob);
                double bal = Double.parseDouble(inputArray[4]);
                return new Checking(profile, bal);
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Missing data for opening an account.");
            }
            catch (NumberFormatException e){
                System.out.println("Not a valid amount.");
            }
            return null;
        }

        public Account openCollegeChecking(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            String fname = inputArray[1];
            String lname = inputArray[2];
            Date dob = new Date(inputArray[3]);
            Profile profile = new Profile(fname, lname, dob);
            double bal = Double.parseDouble(inputArray[4]);
            int campusCode = Integer.parseInt(inputArray[5]);
            return new CollegeChecking(profile, bal, campusCode);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing data for opening an account.");
        }
        catch (NumberFormatException e){
            System.out.println("Not a valid amount.");
        }
        return null;
    }

    public Account openSavings(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            String fname = inputArray[1];
            String lname = inputArray[2];
            Date dob = new Date(inputArray[3]);
            Profile profile = new Profile(fname, lname, dob);
            double bal = Double.parseDouble(inputArray[4]);
            boolean savingLoyalty = false;
            if (inputArray[5].equals("1")) {
               savingLoyalty = true;
            }
            return new Savings(profile, bal, savingLoyalty);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing data for opening an account.");
        }
        catch (NumberFormatException e){
            System.out.println("Not a valid amount.");
        }

        return null;
    }

    public Account openMoneyMarket(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            String fname = inputArray[1];
            String lname = inputArray[2];
            Date dob = new Date(inputArray[3]);
            Profile profile = new Profile(fname, lname, dob);
            double bal = Double.parseDouble(inputArray[4]);
            return new MoneyMarket(profile, bal);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing data for opening an account.");
        }
        catch (NumberFormatException e){
            System.out.println("Not a valid amount.");
        }
        return null;
    }



    public Account closeAccount(String input){
        try {
            String[] inputArray = input.trim().split("\\s+");
            String accountName = inputArray[0];
            String fname = inputArray[1];
            String lname = inputArray[2];
            Date dob = new Date(inputArray[3]);
            Profile profile = new Profile(fname, lname, dob);
            switch (accountName) {
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
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing data for closing an account.");
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
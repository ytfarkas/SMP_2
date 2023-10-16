package TransactionManager;

import java.util.Scanner;

/**
 * TransactionManger class is the main class runs the program to receive and process commands
 * this class begins with the run(); method, which takes in all the commands and process them accordingly
 * Commands are as follows:
 * O - calls openAccount to open an account
 * C - calls closeAccount to close an account
 * D - Deposits money in holders account
 * W - Withdraws money from holders account
 * P - Prints account sorted by account type and name
 * PI - Prints sorted account with interest and fees
 * PU - Prints sorted accounts with updated balance from interest and fees
 * Q- Terminates Program
 * This class also can handle invalid commands as well
 *
 * @author Judah Farkas, David Rahabi
 */
public class TransactionManager {

    /**
     * empty constructor for creating a TransactionManager Object
     */
    TransactionManager() {
    }

    /**
     * Starts the program for reading the commands.
     */
    public void run() {
        AccountDatabase account = new AccountDatabase();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Transaction Manager is running.");
        while (true) {
            String command = scanner.next();
            String input = scanner.nextLine();
            if (command.equals("Q")) {
                System.out.println("Transaction Manager is terminated.");
                break;
            }
            processCommand(account, command, input);
        }
    }

    /**
     * Reads the first command and executes the correct action or prints error.
     *
     * @param account the account database
     * @param command the first command
     * @param input   the rest of the line to be read
     */
    public void processCommand(AccountDatabase account, String command, String input) {
        switch (command) {
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
                if (deposit(input) != null) {
                    account.deposit(deposit(input));
                }
                break;
            case "W":
                if (withdraw(input) != null) {
                    account.withdraw(withdraw(input));
                }
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
                System.out.println("Invalid command!");
        }
    }

    /**
     * takes in the string command and executes the correct open based on the account type.
     *
     * @param input the input string
     * @return the account to be opened or null if error occurred
     */
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
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
        }
        return null; // return null?
    }

    /**
     * Reads the input and creates a checking account.
     *
     * @param input the input string
     * @return the account to be opened or null if error occurred
     */
    public Account openChecking(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            Profile profile = new Profile(inputArray[1], inputArray[2], new Date(inputArray[3]));
            double bal = Double.parseDouble(inputArray[4]);
            return new Checking(profile, bal);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
        return null;
    }

    /**
     * Reads the input and creates a college checking account.
     *
     * @param input the input string
     * @return the account to be opened or null if error occurred
     */
    public Account openCollegeChecking(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            Profile profile = new Profile(inputArray[1], inputArray[2], new Date(inputArray[3]));
            double bal = Double.parseDouble(inputArray[4]);
            int campusCode = Integer.parseInt(inputArray[5]);
            return new CollegeChecking(profile, bal, campusCode);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
        return null;
    }

    /**
     * Reads the input and creates a Savings account.
     *
     * @param input the input string
     * @return the account to be opened or null if error occurred
     */
    public Account openSavings(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            Profile profile = new Profile(inputArray[1], inputArray[2], new Date(inputArray[3]));
            double bal = Double.parseDouble(inputArray[4]);
            boolean savingLoyalty = false;
            if (inputArray[5].equals("1")) {
                savingLoyalty = true;
            }
            return new Savings(profile, bal, savingLoyalty);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }

        return null;
    }

    /**
     * Reads the input and creates a MoneyMarket account.
     *
     * @param input the input string
     * @return the account to be opened or null if error occurred
     */
    public Account openMoneyMarket(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            Profile profile = new Profile(inputArray[1], inputArray[2], new Date(inputArray[3]));
            double bal = Double.parseDouble(inputArray[4]);
            return new MoneyMarket(profile, bal, true);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
        return null;
    }

    /**
     * Reads the input and creates an account to be closed.
     *
     * @param input the inout string
     * @return the account to be closed or null if error occurred
     */
    public Account closeAccount(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            Profile profile = new Profile(inputArray[1], inputArray[2], new Date(inputArray[3]));
            switch (inputArray[0]) {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for closing an account.");
        }
        return null; //return null?
    }

    /**
     * Reads the input and creates an account for a deposit.
     *
     * @param input the input string
     * @return the account to be deposited or null if error occurred
     */
    public Account deposit(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            Profile profile = new Profile(inputArray[1], inputArray[2], new Date(inputArray[3]));
            double depo = Double.parseDouble((inputArray[4]));
            switch (inputArray[0]) {
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
        } catch (NumberFormatException a) {
            System.out.println("Not a valid amount.");
        }
        return null;
    }

    /**
     * Reads the input and creates an account for a withdraw.
     *
     * @param input the input string
     * @return the account for the withdraw or null if error occurred
     */
    public Account withdraw(String input) {
        try {
            String[] inputArray = input.trim().split("\\s+");
            Profile profile = new Profile(inputArray[1], inputArray[2], new Date(inputArray[3]));
            double withD = Double.parseDouble((inputArray[4]));
            switch (inputArray[0]) {
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
        } catch (NumberFormatException a) {
            System.out.println("Not a valid amount.");
        }
        return null;
    }
}
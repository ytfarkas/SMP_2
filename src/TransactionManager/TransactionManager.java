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
                    if ()
            }

        }

    }
    public Account openAccount(String input){
        String[] inputArray = input.trim().split("\\s+");
        String fname = inputArray[1];
        String lname = inputArray[2];
        Date dob = new Date(inputArray[3]);
        Profile profile = new Profile(fname, lname, dob);
        double bal = Double.parseDouble(inputArray[4]);
        boolean loyalty = false;
        switch(inputArray[0]) {
            case ("C"):
                Checking checking = new Checking(profile, bal);
                break;
            case ("CC"):
                int campusCode = Integer.parseInt(inputArray[5]);
                CollegeChecking collegeChecking = new CollegeChecking(profile, bal, campusCode);
                break;
            case ("S"):
                if (bal >= 2000){
                    loyalty = true;
                }
                Savings saving = new Savings(profile, bal, loyalty);
                break;
            case ("MM"):
                MoneyMarket moneyMarket = new MoneyMarket(profile, bal, loyalty, 0);
        }
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
package TransactionManager;

public class AccountDatabase {
    private Account [] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array

    public AccountDatabase(){
        this.accounts = new Account[4];
        this.numAcct = 0;
    }


    private int find(Account account) { //search for an account in the array
        for (int i = 0; i < numAcct; i++){
            if (accounts[i].compareTo(account) == 0){
                return i; // return 1 or the place in the array?
            }
        }
        return -1; //Not found
    }


    private void grow(){ //increase the capacity by 4
       Account[] newAccounts = new Account[numAcct + 4];
        for(int i = 0; i < numAcct; i++){
            newAccounts[i] = accounts[i];
        }
        accounts = newAccounts;
    }


    public boolean contains(Account account){
        for (int i = 0; i < numAcct; i++){
            if (account.compareTo(accounts[i]) == 0){
                return true; // Account is in the array
            }
        }
        return false; //account is not in the array

    } //overload if necessary
    public boolean hasChecking(Account account){
        for (int i = 0; i < numAcct; i++){
            if (accounts[i].holder.compareTo(account.holder) == 0 && (accounts[i].printType().equals("(CC)") || accounts[i].printType().equals("(C)"))){
                return true;
            }
        }
        return false; //account is not in the array
    }
    public boolean open(Account account){
        if(validOpen(account)) {
            if (numAcct == accounts.length) {
                grow();
            }
            accounts[numAcct] = account;
            numAcct++;
            System.out.println(account.holder.toString() + account.printType() + " opened.");
            return true;
        }
        return false;
    } //add a new account

    /**
     * Checks if the open command is valid and prints the correct error if not
     *
     * @param account
     * @return true if valid, false if not
     */
    public boolean validOpen(Account account){
        if(account.balance <= 0){
            System.out.println("Initial deposit cannot be 0 or negative.");
            return false;
        }
        if(contains(account)){
            System.out.println(account.holder.toString() + account.printType() + " is already in the database.");
            return false;
        }
        if(account.printType().equals("(CC)") ) {
            CollegeChecking checking = (CollegeChecking) account;
            try {
                boolean checkCampCode = !checking.getCampus().isValid();
            } catch (NullPointerException e){
                System.out.println("Invalid campus code.");
                return false;
            }
        }
        if((account.printType().equals("(CC)") || account.printType().equals("(C)")) && hasChecking(account)){
            System.out.println(account.holder.toString() + account.printType() + " is already in the database.");
            return false;
        }
        if(!account.holder.getDOB().isValid()) {
            return false;
        }
        if(account.holder.getDOB().getAge() < 16){
            System.out.println("DOB invalid: " + account.holder.getDOB().toString() +  " under 16.");
            return false;
        }
        if((account.printType().equals("(CC)") && account.holder.getDOB().getAge() >= 24)){
            System.out.println("DOB invalid:" + account.holder.getDOB().toString() + " over 24.");
            return false;
        }
        if(account.printType().equals("(MM)") && account.balance < 2000){
            System.out.println("Minimum of $2000 to open a Money Market account.");
            return false;
        }
        return true;
    }

    public boolean close(Account account){
        if(account.holder.getDOB().isValid() && isInDatabase(account)){
            Account[] newAccounts = new Account[accounts.length];
            int count=0;
            for(int i = 0; i < numAcct; i++){
                if(account.compareTo(accounts[i]) == 0){
                    continue;
                }
                newAccounts[count] = accounts[i];
                count++;
            }
            accounts = newAccounts;
            numAcct--;
            System.out.println(account.holder.toString() + account.printType() + " has been closed.");
            return true;
        }
        return false;
    } //remove the given account

    public boolean isInDatabase(Account account){
        if(!contains(account)){
            System.out.println(account.holder.toString() + account.printType() + " is not in the database.");
            return false;
        }
        return true;
    }
    public boolean withdraw(Account account){
        //need check for if amount entered is not a number in transactionmanager i think

        if(account.balance <= 0){
            System.out.println("Withdraw - amount cannot be 0 or negative.");
            return false;
        }

        if(!account.holder.getDOB().isValid()){
            return false;
        }

        if(isInDatabase(account) && accounts[find(account)].balance > account.balance){
            accounts[find(account)].balance -= account.balance;
            if(accounts[find(account)].printType().equals("MM")){
                MoneyMarket update = (MoneyMarket) accounts[find(account)];
                update.updateWithdrawal();
                accounts[find(account)] = update;
            }

            System.out.println(account.holder.toString() + account.printType() + " Withdraw - balance updated.");
            return true;
        }
        System.out.println(account.holder.toString() + account.printType() + "Withdraw - insufficient fund.");
        return false;
    } //false if insufficient fund
    public void deposit(Account account){
        //need check for if amount entered is not a number in transactionmanager i think
        if(account.balance <= 0){
            System.out.println("Deposit - amount cannot be 0 or negative.");
        }else if(account.holder.getDOB().isValid() && isInDatabase(account)){
            accounts[find(account)].balance += account.balance;
            System.out.println(account.holder.toString() + account.printType() + " Deposit - balance updated.");
        }
    }
    public void printSorted(){
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
        }
        else{
            System.out.println("*Accounts sorted by account type and profile.");
            Account[] sortedChecking = sortChecking();
            Account[] sortedCollege = sortCollege();
            Account[] sortedMoneyMarket = sortMoneyMarket();
            Account[] sortedSavings = sortSavings();
            int count = 0;
            for(int i = 0; i < sortedChecking.length; i++){
                System.out.println(sortedChecking[i].toString());
                accounts[count] = sortedChecking[i];
                count++;
            }
            for(int i = 0; i < sortedCollege.length; i++){
                System.out.println(sortedCollege[i].toString());
                accounts[count] = sortedCollege[i];
                count++;
            }
            for(int i = 0; i < sortedMoneyMarket.length; i++){
                System.out.println(sortedMoneyMarket[i].toString());
                accounts[count] = sortedMoneyMarket[i];
                count++;
            }
            for(int i = 0; i < sortedSavings.length; i++){
                System.out.println(sortedSavings[i].toString());
                accounts[count] = sortedSavings[i];
                count++;
            }

            System.out.println("*end of list.");
        }
    } //sort by account type and profile

    public Account[] sortChecking(){
        Account[] newAccounts = new Account[numAcct];
        int count = 0;
        for(int i = 0; i < numAcct; i++){
            if(accounts[i].printType().equals("(C)")){
                newAccounts[count] = accounts[i];
                count++;
            }
        }
        for(int i = 0; i < count-1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if((newAccounts[j].holder.compareTo(newAccounts[j + 1].holder) > 0))
                {
                    Account temp = newAccounts[j];
                    newAccounts[j] = newAccounts[j + 1];
                    newAccounts[j + 1] = temp;
                }
            }
        }
        Account[] noNullAccounts = new Account[count];
        for(int i = 0; i < count; i++){
                noNullAccounts[i] = newAccounts[i];
        }

        return noNullAccounts;
    }

    public Account[] sortCollege(){
        Account[] newAccounts = new Account[numAcct];
        int count = 0;
        for(int i = 0; i < numAcct; i++){
            if(accounts[i].printType().equals("(CC)")){
                newAccounts[count] = accounts[i];
                count++;
            }
        }
        for(int i = 0; i < count-1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if((newAccounts[j].holder.compareTo(newAccounts[j + 1].holder) > 0))
                {
                    Account temp = newAccounts[j];
                    newAccounts[j] = newAccounts[j + 1];
                    newAccounts[j + 1] = temp;
                }
            }
        }
        Account[] noNullAccounts = new Account[count];
        for(int i = 0; i < count; i++){
            noNullAccounts[i] = newAccounts[i];
        }

        return noNullAccounts;
    }

    public Account[] sortMoneyMarket(){
        Account[] newAccounts = new Account[numAcct];
        int count = 0;
        for(int i = 0; i < numAcct; i++){
            if(accounts[i].printType().equals("(MM)")){
                newAccounts[count] = accounts[i];
                count++;
            }
        }
        for(int i = 0; i < count-1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if((newAccounts[j].holder.compareTo(newAccounts[j + 1].holder) > 0))
                {
                    Account temp = newAccounts[j];
                    newAccounts[j] = newAccounts[j + 1];
                    newAccounts[j + 1] = temp;
                }
            }
        }
        Account[] noNullAccounts = new Account[count];
        for(int i = 0; i < count; i++){
            noNullAccounts[i] = newAccounts[i];
        }
        return noNullAccounts;
    }

    public Account[] sortSavings(){
        Account[] newAccounts = new Account[numAcct];
        int count = 0;
        for(int i = 0; i < numAcct; i++){
            if(accounts[i].printType().equals("(S)")){
                newAccounts[count] = accounts[i];
                count++;
            }
        }
        for(int i = 0; i < count-1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if((newAccounts[j].holder.compareTo(newAccounts[j + 1].holder) > 0))
                {
                    Account temp = newAccounts[j];
                    newAccounts[j] = newAccounts[j + 1];
                    newAccounts[j + 1] = temp;
                }
            }
        }
        Account[] noNullAccounts = new Account[count];
        for(int i = 0; i < count; i++){
            noNullAccounts[i] = newAccounts[i];
        }
        return noNullAccounts;
    }

    public void printFeesAndInterests(){
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
        }
        else{
            System.out.println("*list of accounts with fee and monthly interest");
            for(int i=0; i < numAcct; i++){
                System.out.println(accounts[i].printWithFeesAndInterest());
            }
            System.out.println("*end of list.");
        }
    } //calculate interests/fees
    public void printUpdatedBalances(){
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
        }
        else{
            System.out.println("*list of accounts with fees and interests applied");
            for(int i=0; i < numAcct; i++){
                System.out.println(accounts[i].printUpdatedBalance());
            }
            System.out.println("*end of list.");
        }
    } //apply the interests/fees
}

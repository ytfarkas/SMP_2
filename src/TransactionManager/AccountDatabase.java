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
        for(int i = 0; i < newAccounts.length; i++){
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

        if(contains(account)){
            System.out.println(account.holder.toString() + account.printType() + " is already in the database.");
            return false;
        }
        if((account.printType().equals("(CC)") || account.printType().equals("(C)")) && hasChecking(account) ){
            System.out.println(account.holder.toString() + account.printType() + " is already in the database.");
            return false;
        }
        if(account.holder.getDOB().getAge() < 16){
            System.out.println("DOB invalid: " + account.holder.getDOB().toString() +  " under 16.");
            return false;
        }
         if(numAcct == accounts.length){
            grow();
        }
        accounts[numAcct] = account;
        numAcct++;
        System.out.println(account.holder.toString() + account.printType() + " opened.");
        return true; // are we ever supposed to
    } //add a new account

    public boolean close(Account account){
        if(isInDatabase(account)){
            Account[] newAccounts = new Account[accounts.length];
            int count=0;
            for(int i = 0; i < numAcct; i++){
                if(account.compareTo(accounts[i]) == 0){
                    continue;
                }
                newAccounts[i] = accounts[count];
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

        if(isInDatabase(account) && accounts[find(account)].balance > account.balance){
            accounts[find(account)].balance -= account.balance;
            System.out.println(account.holder.toString() + account.printType() + "Withdraw - balance updated.");
            return true;
        }
        System.out.println(account.holder.toString() + account.printType() + "Withdraw - insufficient fund.");
        return false;
    } //false if insufficient fund
    public void deposit(Account account){
        //need check for if amount entered is not a number in transactionmanager i think
        if(account.balance <= 0){
            System.out.println("Deposit- amount cannot be 0 or negative.");
        }else if(isInDatabase(account)){
            accounts[find(account)].balance -= account.balance;
            System.out.println(account.holder.toString() + account.printType() + "Deposit - balance updated.");
        }
    }
    public void printSorted(){
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
        }
        else{
            for(int i = 1; i < numAcct; i++){
                for(int j = 0; j < numAcct-1; j++){
                    if((accounts[j].holder.getLname().compareTo(accounts[j+1].holder.getLname()) > 0 && accounts[j].printType().compareTo(accounts[j+1].printType()) > 0) ||
                        (accounts[j].holder.getLname().compareTo(accounts[j+1].holder.getLname()) == 0 &&
                         accounts[j].holder.getFname().compareTo(accounts[j+1].holder.getFname()) > 0 &&  //checking the last and first name when the accounts are different
                         accounts[j].printType().compareTo(accounts[j+1].printType()) > 0 )   ||
                        (accounts[j].holder.getLname().compareTo(accounts[j+1].holder.getLname()) > 0 && accounts[j].printType().compareTo(accounts[j+1].printType()) == 0)   ||
                        (accounts[j].holder.getLname().compareTo(accounts[j+1].holder.getLname()) == 0 &&
                         accounts[j].holder.getFname().compareTo(accounts[j+1].holder.getFname()) > 0 &&  //checking the last and first name when the accounts are the same
                         accounts[j].printType().compareTo(accounts[j+1].printType()) == 0)
                    ){
                        Account temp = accounts[j];
                        accounts[j] = accounts[j+1];
                        accounts[j+1] = temp;
                    }
                }
            }
        }
    } //sort by account type and profile

    public void printFeesAndInterests(){
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
        }
    } //calculate interests/fees
    public void printUpdatedBalances(){
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
        }
    } //apply the interests/fees
}

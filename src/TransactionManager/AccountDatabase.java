package TransactionManager;

public class AccountDatabase {
    private Account [] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array

    public AccountDatabase(){
        this.size = 4;
        this.accounts = new Account[size];
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
            if (account.compareTo(accounts[i] == 0)){
                return true; // Account is in the array
            }
        }
        return false; //account is not in the array

    } //overload if necessary
    public boolean open(Account account){
         if(numAcct == accounts.length){
            grow();
        }
        accounts[numAcct] = account;
        numActt++;

        return true; // are we ever supposed to
    } //add a new account
    public boolean close(Account account){
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
        return true; // are we ever supposed to return false?
    } //remove the given account
    public boolean withdraw(Account account){} //false if insufficient fund
    public void deposit(Account account){}
    public void printSorted(){
        if (numAcct == 0){
            System.out.println("Account Database is empty!");
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

package TransactionManager;

public class AccountDatabase {
    private Account [] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array

    private int size; //can i use this here or it considered an instacne varrible?

    public AccountDatabase(){
        this.size = 4;
        this.accounts = new Account[size];
        this.numAcct = 0;
    }


    private int find(Account account) { //search for an account in the array
        for (int i = 0; i < numAcct; i++){
            if (accounts[i].equals(account)){
                return 1; // Found
            }
        }
        return -1; //Not found
    }


    private void grow(){ //increase the capacity by 4
        this.size = size + 4;
        Account[] grow = new Account[size];

        for (int i = 0; i < numAcct; i++){
            grow[i] = accounts[i];
        }
        this.accounts = grow;
    }


    public boolean contains(Account account){
        for (int i = 0; i < numAcct; i++){
            if (account.equals(accounts[i])){
                return true; // Account is in the array
            }
        }
        return false; //account is not in the array

    } //overload if necessary
    public boolean open(Account account){} //add a new account
    public boolean close(Account account){} //remove the given account
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
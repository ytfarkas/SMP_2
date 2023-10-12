package TransactionManager;

public class AccountDatabase {
    private Account [] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array
    private int find(Account account) {
        for(int i = 0; i < numAcct; i++){
            if(account.compareTo(accounts[i]) == 0){
                return i;
            }
        }
        return -1; //are we supposed to return -1 if not found?

    } //search for an account in the array

    private void grow(){
        Account[] newAccounts = new Account[numAcct + 4];
        for(int i = 0; i < newAccounts.length; i++){
            newAccounts[i] = accounts[i];
        }
        accounts = newAccounts;
    } //increase the capacity by 4
    public boolean contains(Account account){
        for(int i = 0; i < numAcct; i++){
            if(account.compareTo(accounts[i]) == 0){
                return true;
            }
        }
        return false;
    } //overload if necessary
    public boolean open(Account account){
        if(numAcct == accounts.length){
            grow();
        }
        accounts[numAcct] = account;

        return true; // are we ever supposed to return false?
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
        return true; // are we ever supposed to return false?

    } //remove the given account
    public boolean withdraw(Account account){

    } //false if insufficient fund
    public void deposit(Account account){}
    public void printSorted(){} //sort by account type and profile
    public void printFeesAndInterests(){} //calculate interests/fees
    public void printUpdatedBalances(){} //apply the interests/fees
}
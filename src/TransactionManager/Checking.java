package TransactionManager;

public class Checking extends Account {

    /**
     * Constructor for creating a Checking account.
     * @param profile the account holder profile
     * @param balance the balannce
     */
    public Checking(Profile profile, double balance){
        this.holder=profile;
        this.balance=balance;
    }

    /**
     * Second Constructor for checking using only the profile.
     * @param profile the account holder
     */
    public Checking (Profile profile){
        this.holder = profile;
    }

    /**
     * Calculates the monthly interest rate.
     * @return the monthly interest
     */
    @Override
    public double monthlyInterest() {
        return (balance * .01) / 12; // i dont think you use this to update the balance, you simply return what the interest is
    }

    /**
     * Calculates the monthly fee.
     *
     * @return the monthly fee
     */
    @Override
    public double monthlyFee() {
        if (balance >= 1000.00){
            return 0.0;
        }                           //same here, you dont return the balance, just the actual fee itself
        else{
            return 12.0;
        }
    }

    /**
     * creates a string with the type of account
     * @return a string containing the type of account abbreviated in parentheses
     */
    @Override
    public String printType(){
        return "(C)";
    }

    /**
     * creates a string to print the account
     * @return the string with the account values
     */
    @Override
    public String toString(){
        return "Checking::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance);
    }

    /**
     * creates a string to print the account with fees and interest
     * @return the string with the account values
     */
    @Override
    public String printWithFeesAndInterest(){
        return "Checking::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance) + "::fee $"+
                String.format("%,.2f", this.monthlyFee())  + "::monthly interest $" + String.format("%,.2f", this.monthlyInterest());
    }

    /**
     * creates a string to print the account with the updated balances based on the interest and fees.
     * @return the string with the account values
     */
    @Override
    public String printUpdatedBalance(){
        balance += this.monthlyInterest();
        balance -= this.monthlyFee();
        return "Checking::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance);

    }


}

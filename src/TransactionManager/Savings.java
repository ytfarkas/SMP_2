package TransactionManager;

public class Savings extends Account {
    protected boolean isLoyal; //loyal customer status //protected basically means it can be accessed anywhere in the package, and even in sublclasses outside the package
                                //so you dont need getters for them

    public Savings(Profile profile, double balance, boolean loyalty){
        this.holder=profile;
        this.balance=balance;
        this.isLoyal=loyalty;
    }
    public Savings(Profile profile){
        this.holder = profile;
    }
    public Savings (Profile profile, double depo){
        this.holder = profile;
        this.balance = depo;
    }

    /**
     * Calculates the monthly interest rate.
     * @return the monthly interest
     */
    @Override
    public double monthlyInterest(){
        if (isLoyal){
            return (balance * .0425) / 12;
        }
        return (balance * .04) / 12;
    }
    /**
     * Calculates the monthly fee.
     *
     * @return the monthly fee
     */
    @Override
    public double monthlyFee(){
        if (balance >= 500){
            return 0.0;
        }
        return 25.0;
    }

    /**
     * creates a string with the type of account
     * @return a string containing the type of account abbreviated in parentheses
     */
    @Override
    public String printType(){
        return "(S)";
    }

    /**
     * creates a string to print the account
     * @return the string with the account values
     */
    @Override
    public String toString(){
        if(isLoyal){
            return "Savings::" + this.holder.toString() + "::Balance $"
                    + String.format("%,.2f", this.balance) + "::is loyal";
        }
        return "Savings::" + this.holder.toString() + "::Balance $"
                + String.format("%,.2f", this.balance);

    }

    /**
     * creates a string to print the account with fees and interest
     * @return the string with the account values
     */
    @Override
    public String printWithFeesAndInterest(){
        if(isLoyal){
            return "Savings::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance)
                    + "::is loyal" +  "::fee $" + String.format("%,.2f", this.monthlyFee()) + "::monthly interest $"
                    + String.format("%,.2f", this.monthlyInterest());
        }
        return "Savings::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance)
                + "::fee $" + String.format("%,.2f", this.monthlyFee()) + "::monthly interest $" + String.format("%,.2f", this.monthlyInterest());
    }

    /**
     * creates a string to print the account with the updated balances based on the interest and fees.
     * @return the string with the account values
     */
    @Override
    public String printUpdatedBalance(){
        balance += this.monthlyInterest();
        balance -= this.monthlyFee();
        if(isLoyal){
            return "Savings::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance) + "::is loyal";
        }
        return "Savings::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance);
    }

    public void updateLoyalty(){
        if(this.balance >= 2000){
            this.isLoyal = true;
        }
        else{
            this.isLoyal = false;
        }
    }
}

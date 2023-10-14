package TransactionManager;

public class Savings extends Account {
    protected boolean isLoyal; //loyal customer status //protected basically means it can be accessed anywhere in the package, and even in sublclasses outside the package
                                //so you dont need getters for them

    public Savings(Profile profile, double balance, boolean loyalty){
        this.holder=profile;
        this.balance=balance;
        this.isLoyal=loyalty;
    }

    @Override
    public double monthlyInterest(){
        if (isLoyal){
            return (balance * .0425) / 12;
        }
        return (balance * .04) / 12;
    }
    @Override
    public double monthlyFee(){
        if (balance >= 500){
            return 0.0;
        }
        return 25.0;
    }

    @Override
    public String printType(){
        return "(S)";
    }

    @Override
    public String toString(){
        String loyal = "";
        if(isLoyal){
            loyal = "::is loyal";
        }
        return this.getClass() + "::" + this.holder.toString() + ":: Balance $" + this.balance + loyal;
    }

    @Override
    public String printWithFeesAndInterest(){
        return this.getClass() + "::" + this.holder.toString() + ":: Balance $" + String.format("%.2f", this.balance) + "::fee $"+
                String.format("%.2f", this.monthlyFee())  + "::monthly interest $" + String.format("%.2f", this.monthlyInterest());
    }

    @Override
    public String printUpdatedBalance(){
        String loyal = "";
        if(isLoyal){
            loyal = "::is loyal::";
        }
        balance += this.monthlyInterest();
        balance -= this.monthlyFee();
        return this.getClass() + "::" + this.holder.toString() + ":: Balance $" + String.format("%.2f", this.balance) + "::" + loyal;

    }
}

package TransactionManager;

public abstract class Savings extends Account {
    protected boolean isLoyal; //loyal customer status //protected basically means it can be accessed anywhere in the package, and even in sublclasses outside the package
                                //so you dont need getters for them

    public Savings(Profile profile, double balance, boolean loyalty){
        this.holder=profile;
        this.balance=balance;
        this.isLoyal=loyalty;
    }

    @Override
    public String printType(){
        return "(S)";
    }
}

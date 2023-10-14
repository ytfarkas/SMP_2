package TransactionManager;

public class Checking extends Account {

    public Checking(Profile profile, double balance){
        this.holder=profile;
        this.balance=balance;
    }


    @Override
    public double monthlyInterest() {
        return balance * .01; // i dont think you use this to update the balance, you simply return what the interest is
    }

    @Override
    public double monthlyFee() {
        if (balance >= 1000.00){
            return 0.0;
        }                           //same here, you dont return the balance, just the actual fee itself
        else{
            return 12.0;
        }
    }

    @Override
    public String printType(){
        return "(C)";
    }



}

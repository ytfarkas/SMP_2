package TransactionManager;

public abstract class Checking extends Account {
    @Override
    public double monthlyInterest() {
        return balance = balance*.01;
    }

    @Override
    public double monthlyFee() {
        if (balance >= 1000.00){
            return balance;
        }
        else{
            return balance - 12;
        }
    }

}

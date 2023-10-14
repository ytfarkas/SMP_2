package TransactionManager;

public class MoneyMarket extends Savings {
    private int withdrawal; //number of withdrawals

    public MoneyMarket(Profile profile, double balance, boolean loyalty, int withdrawal){
        super(profile, balance, loyalty); //super explanation in collegeChecking
        this.withdrawal=withdrawal;
    }
    @Override
    public double monthlyInterest(){
        if (isLoyal){
            return balance * .0475;
        }
        return balance * .045;
    }
    @Override
    public double monthlyFee(){
        if (balance >= 2000){
            return 0.0;
        }
        return 25.0;
    }

    @Override
    public String printType(){
        return "(MM)";
    }

}

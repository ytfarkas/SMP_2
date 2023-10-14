package TransactionManager;

public abstract class MoneyMarket extends Savings {
    private int withdrawal; //number of withdrawals

    public MoneyMarket(Profile profile, double balance, boolean loyalty, int withdrawal){
        super(profile, balance, loyalty); //super explanation in collegeChecking
        this.withdrawal=withdrawal;
    }

    @Override
    public String printType(){
        return "(MM)";
    }

}

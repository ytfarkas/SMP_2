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
            return (balance * .0475) / 12;
        }
        return (balance * .045) / 12;
    }
    @Override
    public double monthlyFee(){
        if (balance >= 2000){
            return 0.0;
        }
        if(withdrawal > 3){
            return 35.0;
        }
        return 25.0;
    }

    @Override
    public String printType(){
        return "(MM)";
    }

    @Override
    public String toString(){
        String loyal = "";
        if(isLoyal){
            loyal = "::is loyal::";
        }
        return this.getClass() + "::" + this.holder.toString() + ":: Balance $" + this.balance + "::" + loyal + "::withdrawal: " + withdrawal;
    }

    @Override
    public String printWithFeesAndInterest(){
        String loyal = "";
        if(isLoyal){
            loyal = "::is loyal::";
        }
        return this.getClass() + "::Savings::" + this.holder.toString() + ":: Balance $" + String.format("%.2f", this.balance) + "::" + loyal + "::withdrawal: " + withdrawal +
                "::fee $" + String.format("%.2f", this.monthlyFee()) + "::monthly interest $" + String.format("%.2f", this.monthlyInterest());
    }

    @Override
    public String printUpdatedBalance(){
        String loyal = "";
        if(isLoyal){
            loyal = "::is loyal::";
        }
        balance += this.monthlyInterest();
        balance -= this.monthlyFee();
        return this.getClass() + "::" + this.holder.toString() + ":: Balance $" + String.format("%.2f", this.balance) + "::" + loyal + "::withdrawal: " + withdrawal;

    }

}

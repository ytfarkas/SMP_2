package TransactionManager;

public class MoneyMarket extends Savings {
    private int withdrawal; //number of withdrawals

    public MoneyMarket(Profile profile, double balance, boolean loyalty, int withdrawal){
        super(profile, balance, loyalty); //super explanation in collegeChecking
        this.withdrawal=withdrawal;
    }
    public MoneyMarket(Profile profile){
        super(profile);
    }
    public MoneyMarket(Profile profile, double depo){
        super(profile, depo);
    }
    public MoneyMarket(Profile profile, double depo, boolean loyal){
        super(profile, depo, loyal);
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
        if(isLoyal){
            return "Money Market" + "::Savings::" + this.holder.toString() + "::Balance $"
                    + String.format("%.2f", this.balance) + "::is loyal::" + "withdrawal: " + withdrawal;
        }
        return "Money Market" + "::Savings::" + this.holder.toString() + "::Balance $"
                + String.format("%.2f", this.balance) + "::withdrawal: " + withdrawal;

    }

    @Override
    public String printWithFeesAndInterest(){
        if(isLoyal){
            return "Money Market" + "::Savings::" + this.holder.toString() + "::Balance $" + String.format("%.2f", this.balance)
                    + "::is loyal" + "::withdrawal: " + withdrawal + "::fee $" + String.format("%.2f", this.monthlyFee()) + "::monthly interest $"
                    + String.format("%.2f", this.monthlyInterest());
        }
        return "Money Market" + "::Savings::" + this.holder.toString() + "::Balance $" + String.format("%.2f", this.balance)
                + "::withdrawal: " + withdrawal + "::fee $" + String.format("%.2f", this.monthlyFee()) + "::monthly interest $" + String.format("%.2f", this.monthlyInterest());
    }

    @Override
    public String printUpdatedBalance(){
        balance += this.monthlyInterest();
        balance -= this.monthlyFee();
        if(isLoyal){
            return "Money Market" + "::Savings::" + this.holder.toString() + "::Balance $" + String.format("%.2f", this.balance) + "::is loyal::" + "withdrawal: " + withdrawal;
        }
        return "Money Market" + "::Savings::" + this.holder.toString() + "::Balance $" + String.format("%.2f", this.balance) + "::withdrawal: " + withdrawal;
    }

    public void updateWithdrawal(){
        this.withdrawal++;
        if (balance < 2000){
            isLoyal = false;
        }
    }

}

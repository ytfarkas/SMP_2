package TransactionManager;

public class Checking extends Account {

    public Checking(Profile profile, double balance){
        this.holder=profile;
        this.balance=balance;
    }


    @Override
    public double monthlyInterest() {
        return (balance * .01) / 12; // i dont think you use this to update the balance, you simply return what the interest is
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

    @Override
    public String toString(){
        return this.getClass() + "::" + this.holder.toString() + ":: Balance $" + this.balance;
    }

    @Override
    public String printWithFeesAndInterest(){
        return this.getClass() + "::" + this.holder.toString() + ":: Balance $" + String.format("%.2f", this.balance) + "::fee $"+
                String.format("%.2f", this.monthlyFee())  + "::monthly interest $" + String.format("%.2f", this.monthlyInterest());
    }

    @Override
    public String printUpdatedBalance(){
        balance += this.monthlyInterest();
        balance -= this.monthlyFee();
        return this.getClass() + "::" + this.holder.toString() + ":: Balance $" + String.format("%.2f", this.balance);

    }


}

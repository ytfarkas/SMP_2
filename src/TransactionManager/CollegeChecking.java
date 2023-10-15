package TransactionManager;

    public class CollegeChecking extends Checking {

        private Campus campus; //campus code

        public CollegeChecking(Profile holder, double balance, int campus) {
            super(holder, balance); /* calls the Checking constructor:
                                     cannot simply do this.holder = holder and this.balance=balance
                                    you must actually use the parent class's constructor bc those instance variables
                                     are not directly associated with this class, the only instance variable in this class is campus
                                     */

            for(Campus c : Campus.values()){
                if(c.campusNumber == campus){  //sets campus
                    this.campus = c;
                }
            }
        }
        public CollegeChecking(Profile profile){
            super(profile);
        }
        public CollegeChecking(Profile profile, double depo){
            super(profile, depo);
        }

        public Campus getCampus() {
            return campus;
        }

        @Override
        public double monthlyInterest() {
            return (balance * 0.01) / 12;
        }
        @Override
        public double monthlyFee() {
            return 0.0;
        }

        @Override
        public String printType(){
            return "(CC)";
        }

        @Override
        public String toString(){
            return "College Checking::" + this.holder.toString() + "::Balance $" + String.format("%.2f", this.balance) + "::" + this.campus;
        }

        @Override
        public String printWithFeesAndInterest(){
            return "College Checking::" + this.holder.toString() + "::Balance $" + String.format("%.2f", this.balance) + "::" + this.campus + "::fee $"+
                    String.format("%.2f", this.monthlyFee())  + "::monthly interest $" + String.format("%.2f", this.monthlyInterest());
        }

        @Override
        public String printUpdatedBalance(){
            balance += this.monthlyInterest();
            balance -= this.monthlyFee();
            return "College Checking::" + this.holder.toString() + "::Balance $" + String.format("%.2f", this.balance) + "::" + this.campus;
        }



    }



// 0 - New Brunswick
// 1 - Newark
// 2 - Camden

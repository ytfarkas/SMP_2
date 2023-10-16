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

        /**
         * Calculates the monthly interest rate.
         * @return the monthly interest
         */
        @Override
        public double monthlyInterest() {
            return (balance * 0.01) / 12;
        }

        /**
         * Calculates the monthly fee.
         *
         * @return the monthly fee
         */
        @Override
        public double monthlyFee() {
            return 0.0;
        }

        /**
         * creates a string with the type of account
         * @return a string containing the type of account abbreviated in parentheses
         */
        @Override
        public String printType(){
            return "(CC)";
        }

        /**
         * creates a string to print the account
         * @return the string with the account values
         */
        @Override
        public String toString(){
            return "College Checking::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance) + "::" + this.campus;
        }

        /**
         * creates a string to print the account with fees and interest
         * @return the string with the account values
         */
        @Override
        public String printWithFeesAndInterest(){
            return "College Checking::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance) + "::" + this.campus + "::fee $"+
                    String.format("%,.2f", this.monthlyFee()) + "::monthly interest $" + String.format("%,.2f", this.monthlyInterest());
        }


        /**
         * creates a string to print the account with the updated balances based on the interest and fees.
         * @return the string with the account values
         */
        @Override
        public String printUpdatedBalance(){
            balance += this.monthlyInterest();
            balance -= this.monthlyFee();
            return "College Checking::" + this.holder.toString() + "::Balance $" + String.format("%,.2f", this.balance) + "::" + this.campus;
        }



    }



// 0 - New Brunswick
// 1 - Newark
// 2 - Camden

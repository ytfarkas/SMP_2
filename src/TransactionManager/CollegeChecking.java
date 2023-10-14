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

        public Campus getCampus() {

            return campus;
        }

        @Override
        public double monthlyInterest() {
            return 0.0;         // i dont think you use this to update the balance, you simply return what the interest is
        }

        @Override
        public String printType(){
            return "(CC)";
        }

    }



// 0 - New Brunswick
// 1 - Newark
// 2 - Camden

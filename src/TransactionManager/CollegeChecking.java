package TransactionManager;

public enum Campus {
    NEW_BRUNSWICK(0),
    NEWARK(1),
    CAMDEN(2);

    private Campus campus; //campus code

    Campus(int i){
        if (i == 0){
            this.campus = i;
        } else if (i == 1) {
            this.campus == NEWARK;

        } else if (i == 2) {
            this.campus == Campus.CAMDEN;

        }else
    }
}
    public abstract class CollegeChecking extends Checking {



        public CollegeChecking(Campus campus) {
            this.campus = campus;
        }

        public Campus getCampus() {
            return campus;
        }

        @Override
        public double monthlyInterest() {
            return 0.0;
        }

    }



// 0 - New Brunswick
// 1 - Newark
// 2 - Camden

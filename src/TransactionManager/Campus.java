package TransactionManager;

public enum Campus {
    NEW_BRUNSWICK(0),
    NEWARK(1),
    CAMDEN(2);


    int campusNumber;
    Campus(int campus){
        this.campusNumber = campus;

    }

    public int getCampusNumber() {
        return campusNumber;
    }
}

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
    public boolean isValid() {
        for (Campus campus : Campus.values()) {
            if (campus.campusNumber == this.campusNumber) {
                return true;
            }
        }
        return false;
    }
}

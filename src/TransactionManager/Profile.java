package TransactionManager;
public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    @Override
    public int compareTo(Profile profile) {
        if (this.fname.equals(profile.fname) && this.lname.equals(profile.lname)){
            if (this.dob.equals(profile.dob)) {
                return 0; //if profile equal
            }
        }
    return 1;
    }

    @Override
    public String toString(){
        return fname + " " + lname + " " + dob.toString();
    }

    public Date getDOB(){
        return dob;
    }
    public String getFname(){
        return fname;
    }

    public String getLname(){
        return lname;
    }
}
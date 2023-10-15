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
        String thisFName = this.fname.toLowerCase();
        String thisLName = this.lname.toLowerCase();
        String profileFName = profile.fname.toLowerCase();
        String profileLName = profile.lname.toLowerCase();
        if (thisFName.equals(profileFName) && thisLName.equals(profileLName) && this.dob.equals(profile.dob)){
                return 0; //if profile equal
        }
        if((thisLName.compareTo(profileLName) > 0) || ((thisLName.compareTo(profileLName)) == 0 &&
            thisFName.compareTo(profileFName) > 0)){
            return 1;
        }
        if((thisLName.compareTo(profileLName) == 0 && thisFName.compareTo(profileFName) == 0) &&
            this.dob.compareTo(profile.dob) > 0){
            return 1;
        }
    return -1;
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
package edu.android.teamproject;

/**
 * Created by STU on 2017-09-19.
 */

public class ModelMember {

    private String id;
    private String password;
    private String myPhoneNum;
    private String yourPhoneNum;
    private String startDay;

    public ModelMember(){}

    public ModelMember(String id, String password, String myPhoneNum, String yourPhoneNum, String startDay) {
        this.id = id;
        this.password = password;
        this.myPhoneNum = myPhoneNum;
        this.yourPhoneNum = yourPhoneNum;
        this.startDay = startDay;
    }

    public String getStartDay() {return startDay;}

    public void setStartDay(String startDay) {this.startDay = startDay;}

    public String getMyPhoneNum() {
        return myPhoneNum;
    }

    public void setMyPhoneNum(String myPhoneNum) {
        this.myPhoneNum = myPhoneNum;
    }

    public String getYourPhoneNum() {
        return yourPhoneNum;
    }

    public void setYourPhoneNum(String yourPhoneNum) {
        this.yourPhoneNum = yourPhoneNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

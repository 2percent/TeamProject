package edu.android.teamproject;

/**
 * Created by STU on 2017-09-19.
 */

public class MemberInfo {

    private int MemberNum;
    private String id;
    private String password;
    private String myPhoneNum;
    private String yourPhoneNum;

    public MemberInfo(String id, String password) {
        this.id = id;
        this.password = password;
    }

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

    public int getMemberNum() {
        return MemberNum;
    }

    public void setMemberNum(int memberNum) {
        MemberNum = memberNum;
    }


}

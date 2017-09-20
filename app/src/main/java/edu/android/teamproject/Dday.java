package edu.android.teamproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by STU on 2017-09-19.
 */

public class Dday {

    private int MemberNum;
    private int AnniverNum;
    Date firstMeeting;
    List<String> anniversary = new ArrayList<>();

    private static Dday instance;
    public static Dday getInstace(){
        if(instance == null){
            instance = new Dday();
        }
        return instance;
    }

    public int getMemberNum() {
        return MemberNum;
    }

    public void setMemberNum(int memberNum) {
        MemberNum = memberNum;
    }

    public int getAnniverNum() {
        return AnniverNum;
    }

    public void setAnniverNum(int anniverNum) {
        AnniverNum = anniverNum;
    }

    public Date getFirstMeeting() {
        return firstMeeting;
    }

    public void setFirstMeeting(Date firstMeeting) {
        this.firstMeeting = firstMeeting;
    }

    public List<String> getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(List<String> anniversary) {
        this.anniversary = anniversary;
    }


}

package edu.android.teamproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by STU on 2017-09-19.
 */

public class DiaryLab {

    static List<Diary> lab;

    private static DiaryLab instance;
    public static DiaryLab getInstance(){
        if(instance == null){
            instance = new DiaryLab();
            lab = new ArrayList<>();
        }
        return instance;
    }
}

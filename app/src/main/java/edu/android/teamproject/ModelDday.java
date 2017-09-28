package edu.android.teamproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by STU on 2017-09-19.
 */

// Model
public class ModelDday {

    private String Anniversary;
    private String id;

    public ModelDday(){}
    public ModelDday(String anniversary, String id) {
        this.Anniversary = anniversary;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnniversary() {
        return Anniversary;
    }

    public void setAnniversary(String anniversary) {
        Anniversary = anniversary;
    }
}

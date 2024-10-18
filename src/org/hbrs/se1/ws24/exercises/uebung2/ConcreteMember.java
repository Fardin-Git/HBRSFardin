package org.hbrs.se1.ws24.exercises.uebung2;

import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {
    private Integer ID;
    public ConcreteMember(int ID){
        this.ID = ID;
    }
    public Integer getID(){
        return ID;
    }
    @Override
    public String toString(){
        return "Member (ID = " + ID + ")";
    }
}

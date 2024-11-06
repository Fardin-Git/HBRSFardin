package org.hbrs.se1.ws24.exercises.uebung4;


import org.hbrs.se1.ws24.exercises.uebung2.Member;


import java.io.Serializable;

public class UserStoryMember implements Member, Serializable, Comparable<UserStoryMember> {
    String[] userStory;
    @Override
    public Integer getID() {
        return Integer.parseInt(userStory[0]);
    }
    public UserStoryMember(String[] userStory){
        this.userStory = userStory;
    }
    @Override
    public String toString(){
        return           "\n\u001B[31m\u001B[4m\u001B[1mUser Story (ID = " +  userStory[0] + "):\u001B[0m"

                + "\n" + "\tTitel: "+             userStory[1]
                + "\n" + "\tAkzeptanzkriterium: "+userStory[2]
                + "\n" + "\tRelativer Mehrwert: "+userStory[3]
                + "\n" + "\tRelative Strafe: "+   userStory[4]
                + "\n" + "\tRelatives Risiko: "+  userStory[5]
                + "\n" + "\tRelativer Aufwand: "+ userStory[6]
                + "\n" + "\tProjekt Zuordnung: "+ userStory[7]
                + "\n" + "\tPrio: "+              userStory[8];
    }
    public double getPrio() {
        return Double.parseDouble(userStory[8]);
    }

    @Override
    public int compareTo(UserStoryMember o) {
        return Double.compare(o.getPrio(), this.getPrio());
    }
}

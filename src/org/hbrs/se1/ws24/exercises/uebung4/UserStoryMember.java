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

                + "\n" + "Titel: "+             userStory[1]
                + "\n" + "Akzeptanzkriterium: "+userStory[2]
                + "\n" + "Relativer Mehrwert: "+userStory[3]
                + "\n" + "Relative Strafe: "+   userStory[4]
                + "\n" + "Relatives Risiko: "+  userStory[5]
                + "\n" + "Relativer Aufwand: "+ userStory[6]
                + "\n" + "Projekt Zuordnung: "+ userStory[7]
                + "\n" + "Prio: "+              userStory[8];
    }
    public double getPrio(){
        return Double.parseDouble(userStory[8]);
    }

    @Override
    public int compareTo(UserStoryMember o) {
        return Double.compare(o.getPrio(), this.getPrio());
    }
}

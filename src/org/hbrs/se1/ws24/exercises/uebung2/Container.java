package org.hbrs.se1.ws24.exercises.uebung2;

import java.util.ArrayList;

public class Container {
    ArrayList<Member> memberList = new ArrayList<>();

    public void addMember(Member member) throws ContainerException{
        for (Member oldMem: memberList){
            if(oldMem.getID().equals(member.getID())) throw new ContainerException(member.getID());
        }
        memberList.add(member);
    }
    public String deleteMember(Integer id){
        for (int i = 0; i < memberList.size(); ++i){
            //Aus memberList passenden Index finden und mit get auf Member zugreifen
            if (id.equals(memberList.get(i).getID())){
                memberList.remove(i);
                return "Member erfolgreich entfernt.";
            }
        }
        return "Kein Member mit entsprechender ID vorhanden.";
    }
    public void dump(){
        String memberIDList = "";
        for(Member mem: memberList){
            memberIDList += mem.toString() + "\n";
        }
        System.out.println(memberIDList);
    }
    public int size(){
        return memberList.size();
    }
}

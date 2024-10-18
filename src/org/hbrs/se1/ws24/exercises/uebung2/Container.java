package org.hbrs.se1.ws24.exercises.uebung2;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> memberList = new ArrayList<>();
    public PersistenceStrategyStream<Member> stream = new PersistenceStrategyStream<>();

    private Container(){}
    private static class ContainerHelper{
        private static final Container container = new Container();
    }
    public static Container getContainer(){
        return ContainerHelper.container;
    }
    public void addMember(Member member) throws ContainerException{
        for (Member oldMem: memberList){
            if (oldMem.getID().equals(member.getID())) throw new ContainerException(member.getID());
        }
        memberList.add(member);
    }
    public String deleteMember(Integer id){
        for (int i = 0; i < memberList.size(); ++i) {
            //Aus memberList passenden Index finden und mit get auf Member zugreifen
            if (id.equals(memberList.get(i).getID())){
                memberList.remove(i);
                return "Member erfolgreich entfernt.";
            }
        }
        return "Kein Member mit entsprechender ID vorhanden.";
    }
    public int size(){
        return memberList.size();
    }
    public void store() throws PersistenceException {
        stream.save(memberList);
    }
    public void load() throws PersistenceException {
        if (stream.load() == null){

        } else {
            memberList = stream.load();
        }
    }
    public List<Member> getCurrentList(){
        return memberList;
    }
}

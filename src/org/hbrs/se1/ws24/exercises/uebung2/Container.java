package org.hbrs.se1.ws24.exercises.uebung2;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> memberList = new ArrayList<>();
    private PersistenceStrategy<Member> strategy;
    private static Container container = null;
    private Container(){ }

    public static synchronized Container getContainer(){
        if (container == null){
            container = new Container();
        }
        return container;
    }
    public void setStrategy(PersistenceStrategy<Member> s){
        strategy = s;
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
        try {
            strategy.save(memberList);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Strategie nicht implementiert");
        }
    }
    public void load() throws PersistenceException {
        try {
            memberList = strategy.load();
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Strategie nicht implementiert");
        }
    }
    public List<Member> getCurrentList(){
        return memberList;
    }
}

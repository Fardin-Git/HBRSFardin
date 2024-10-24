package org.hbrs.se1.ws24.exercises.uebung2;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

public class Main {
    public static void main(String[] args) throws ContainerException, PersistenceException {
        Container container = Container.getContainer();
        PersistenceStrategyStream<Member> m = new PersistenceStrategyStream<>();
        m.setLocation("a");
        container.setStrategy(m);
        container.store();
        Client client = new Client();
        for (int i = 1; i < 21; ++i){
            client.setMember(new ConcreteMember(i));
            container.addMember(client.getMember());
        }
        MemberView.dump(container.getCurrentList());
        container.load();
    }
}

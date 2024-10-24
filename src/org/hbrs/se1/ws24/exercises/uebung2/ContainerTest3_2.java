package org.hbrs.se1.ws24.exercises.uebung2;

import static org.junit.jupiter.api.Assertions.*;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.Test;

class ContainerTest3_2 {
    Container container = Container.getContainer();
    @Test
    void nullTest()  {
        PersistenceStrategyStream<Member> m = new PersistenceStrategyStream<>();
        m.setLocation("object20.ser");
        container.setStrategy(m);
        assertThrows(PersistenceException.class, ()-> container.load());
    }
    @Test
    void mongoDBTest() {
        PersistenceStrategyMongoDB<Member> m = new PersistenceStrategyMongoDB();
        container.setStrategy(m);
        assertThrows(PersistenceException.class, ()-> container.store());
    }
    @Test
    void directoryTest() {
        PersistenceStrategyStream<Member> p = new PersistenceStrategyStream();
        p.setLocation("wrong/directory");
        container.setStrategy(p);
        assertThrows(PersistenceException.class, ()-> container.store());
    }
    @Test
    void roundTripTest() throws ContainerException, PersistenceException {
        PersistenceStrategyStream<Member> m = new PersistenceStrategyStream<>();
        m.setLocation("object2.ser");
        container.setStrategy(m);

        container.addMember(new ConcreteMember(1));
        assertEquals(1, container.size());
        container.store();
        container.deleteMember(1);
        assertEquals(0, container.size());
        container.load();
        assertEquals(1, container.size());
        container.addMember(new ConcreteMember(2));
        container.addMember(new ConcreteMember(3));
        assertEquals(3, container.size());
        container.load();
        assertEquals(1, container.size());
        container.deleteMember(1);
        container.deleteMember(2);
        container.deleteMember(3);
        container.store();
        container.addMember(new ConcreteMember(3));
        container.load();
        assertEquals(0, container.size());
    }
}
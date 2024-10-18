package org.hbrs.se1.ws24.exercises.uebung2;

import static org.junit.jupiter.api.Assertions.*;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ContainerTest3_2 {
    Container container = Container.getContainer();
    @Test
    void nullTest() throws PersistenceException {
        container.stream.setLocation("object20.ser");
        assertNull(container.stream.load());
    }
    @Test
    void mongoDBTest() throws UnsupportedOperationException {
        PersistenceStrategyMongoDB m = new PersistenceStrategyMongoDB();
        assertThrows(UnsupportedOperationException.class, ()-> m.load());
    }
    @Test
    void directoryTest() throws PersistenceException {
        PersistenceStrategyStream p = new PersistenceStrategyStream();
        p.setLocation("wrong_directory");
        assertThrows(NullPointerException.class, (Executable) p.load());
    }
    @Test
    void roundTripTest() throws ContainerException, PersistenceException {
        container.stream.setLocation("object2.ser");

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
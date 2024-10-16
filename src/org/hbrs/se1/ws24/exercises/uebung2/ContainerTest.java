package org.hbrs.se1.ws24.exercises.uebung2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    Member m1;
    Member m2;
    Container containerAdd;
    Container containerDel;
    @BeforeEach
    void setup(){
        m1 = new ConcreteMember(1);
        m2 = new ConcreteMember(2);
        containerAdd = new Container();
        containerDel = new Container();
    }
    @Test
    void addMemberTest() throws ContainerException {
        containerAdd.addMember(m1);
        assertEquals(containerAdd.size(), 1);
        assertThrows(ContainerException.class, ()-> containerAdd.addMember(m1));
        containerAdd.addMember(m2);
        assertEquals(containerAdd.size(), 2);
    }
    @Test
    void deleteMemberTest() throws ContainerException {
        assertEquals("Kein Member mit entsprechender ID vorhanden.", containerDel.deleteMember(1));
        containerDel.addMember(m1);
        containerDel.deleteMember(1);
        assertEquals(containerDel.size(), 0);
        containerDel.addMember(m2);
        assertEquals("Kein Member mit entsprechender ID vorhanden.", containerDel.deleteMember(1));
        containerDel.addMember(m1);
        assertEquals("Kein Member mit entsprechender ID vorhanden.", containerDel.deleteMember(3));
        containerDel.deleteMember(2);
        assertEquals(containerDel.size(), 1);
        containerDel.deleteMember(1);
        assertEquals(containerDel.size(), 0);
        assertEquals("Kein Member mit entsprechender ID vorhanden.", containerDel.deleteMember(1));
    }
}
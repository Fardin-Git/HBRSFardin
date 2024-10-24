package org.hbrs.se1.ws24.exercises.uebung4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryMemberTest {
    UserStoryMember userStoryMember;
    @BeforeEach
    void setup(){
        String[] testString = {"1", "2", "3", "4",  "5",  "6", "7" , "8","9" };
        userStoryMember = new UserStoryMember(testString);
    }
    @Test
    void prioTest(){
        assertEquals(9, userStoryMember.getPrio());
    }
    @Test
    void IDTest(){
        assertEquals(1, userStoryMember.getID());
    }
    @Test
    void compareTest(){
        String[] testString = {"1", "2", "3", "4",  "5",  "6", "7" , "8","8" };
        UserStoryMember userStoryMember1 = new UserStoryMember(testString);
        assertEquals(-1, userStoryMember.compareTo(userStoryMember1));
    }
}
package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;

public class Main {
    public static void main(String[] args) throws ContainerException, PersistenceException {
        //UserStoryControl.userStoryTerminal();
        dosomething(
                //Methodenkopf und implementierung der Interface Methode addieren
                (a, b) -> {
                    return a+b;
                }
        );

    }
    public static void dosomething(Haus h){
        int add = h.addieren(5, 100);
        System.out.println(add);
    }
    
}

package org.hbrs.se1.ws24.exercises.uebung2;

public class ContainerException extends Exception {
    public ContainerException(Integer ID){
        super("Das Member-Objekt mit der ID " +ID+ " ist bereits vorhanden!");
    }
}

package org.hbrs.se1.ws24.exercises.uebung8;

public class Hotelsuche {
    newHotelsuche search = new Adapter();
    public Suchergebnis suche(String s){
        SuchAuftrag s2= sucheEingeben(s);
        return search.suche(s2);
    }
    public SuchAuftrag sucheEingeben(String s){
        return new SuchAuftrag();
    }

}

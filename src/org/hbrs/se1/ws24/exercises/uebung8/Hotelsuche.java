package org.hbrs.se1.ws24.exercises.uebung8;

public class Hotelsuche {
    newHotelsuche search = new Adapter();
    public Suchergebnis suchen(SuchAuftrag s){
        return search.suche(s);
    }
}

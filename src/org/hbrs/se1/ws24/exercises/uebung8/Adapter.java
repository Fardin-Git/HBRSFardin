package org.hbrs.se1.ws24.exercises.uebung8;

public class Adapter implements newHotelsuche{
    Reiseanbieter rAnbieter;
    public Suchergebnis suche(SuchAuftrag s){
        QueryResult o = rAnbieter.executeQuery(convertAuftrag(s));
        return convertResult(o);
    }
    private QueryObject convertAuftrag(SuchAuftrag s){
        return new QueryObject();
    }
    private Suchergebnis convertResult(QueryResult r){
        return new Suchergebnis();
    }
}

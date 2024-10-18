package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import org.hbrs.se1.ws24.exercises.uebung2.Member;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E extends Member> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help!
     */
    public void save(List<E> member) throws PersistenceException {
        try {
            File memberObj = new File(location);
            FileOutputStream file = new FileOutputStream(memberObj);
            ObjectOutputStream objout = new ObjectOutputStream(file);
            objout.writeObject(member);
            objout.close();
        } catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.FileNotFound,
                    "unzulässiges Speichern" );
        } catch (IOException e){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Fehler beim Speichern" );
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        try {
            File memberObj = new File(location);
            FileInputStream fis = new FileInputStream(memberObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                return (List) obj;
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,
                    "Fehler beim Speichern");
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Klasse nicht gefunden");
        }
    }
}

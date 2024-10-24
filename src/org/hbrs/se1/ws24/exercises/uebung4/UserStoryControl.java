package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

import java.util.Scanner;

public class UserStoryControl {
    public static void userStoryTerminal() throws ContainerException, PersistenceException {
        Container container = Container.getContainer();
        PersistenceStrategyStream<Member> p = new PersistenceStrategyStream<>();
        container.setStrategy(p);
        Scanner sc = new Scanner(System.in);
        System.out.println("Mögliche Befehle:\n 'befehl enter'\n 'befehl store'\n 'befehl load'\n 'befehl dump'\n 'befehl exit'\n ");

        while(sc.hasNext()){
            String eingabe = sc.nextLine().trim();
            switch (eingabe) {
                case "befehl enter" -> {
                    container.addMember(enter());
                    System.out.println("\nUser Story hinzugefügt, bitte geben Sie ihre nächste Eingabe an:\n");
                }
                case "befehl store" -> {
                    System.out.println("\nGeben sie den Dateinamen zum Speichern an: \n");
                    p.setLocation(sc.nextLine());
                    container.store();
                    System.out.println("\nUser Story erfolgreich gespeichert, bitte geben Sie ihre nächste Eingabe an: \n");
                }
                case "befehl load" -> {
                    System.out.println("\nGeben sie den Dateinamen zum Laden an: \n");
                    p.setLocation(sc.nextLine());
                    container.load();
                    System.out.println("\nUser Story erfolgreich geladen, bitte geben Sie ihre nächste Eingabe an: \n");
                }
                case "befehl dump" -> container.getCurrentList().stream().sorted().forEach(System.out::println);
                case "befehl exit" ->{
                    sc.close();
                    System.out.println("\nProgramm erfolgreich beendet.");
                    return;
                }
                case "befehl help" -> System.out.println("\nMögliche Befehle:\n 'befehl enter'\n 'befehl store'\n 'befehl load'\n 'befehl dump'\n 'befehl exit'\n ");
                default -> System.out.println("\nUngültige Eingabe, siehe 'befehl help'.\n");
            }
        }
    }

    private static UserStoryMember enter(){
        String[] userStory = new String[9];
        Scanner sc = new Scanner(System.in);
        System.out.print("\nUser Story ID: "); userStory[0] = sc.nextLine();
        System.out.print("Titel: "); userStory[1] = sc.nextLine();
        System.out.print("Akzeptanzkriterium: "); userStory[2] = sc.nextLine();
        System.out.print("Relativer Mehrwert: "); double mehrw = sc.nextDouble();
        if(mehrw > 5 || mehrw < 1) throw new IllegalArgumentException("Nur Zahlen zwischen 1 und 5 erlaubt");
        System.out.print("Relative Strafe: "); double strafe  = sc.nextDouble();
        if(strafe > 5 || strafe < 1) throw new IllegalArgumentException("Nur Zahlen zwischen 1 und 5 erlaubt");
        System.out.print("Relatives Risiko: "); double risiko = sc.nextDouble();
        if(risiko > 5 || risiko < 1) throw new IllegalArgumentException("Nur Zahlen zwischen 1 und 5 erlaubt");
        System.out.print("Relativer Aufwand: "); double aufwand = sc.nextDouble();
        System.out.print("Projekt Zuordnung: "); userStory[7] = sc.next();
        double prio = (mehrw+strafe) / (aufwand+risiko);
        userStory[3] = String.valueOf(mehrw);
        userStory[4] = String.valueOf(strafe);
        userStory[5] = String.valueOf(risiko);
        userStory[6] = String.valueOf(aufwand);
        userStory[8] = String.valueOf(prio);
        return new UserStoryMember(userStory);
    }
}

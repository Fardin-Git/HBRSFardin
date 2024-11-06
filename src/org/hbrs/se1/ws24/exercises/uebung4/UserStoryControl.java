package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import java.util.Scanner;

//MVC Pattern: Model View Control
public class UserStoryControl {
    public static void userStoryTerminal() throws ContainerException, PersistenceException {
        Container container = Container.getContainer();
        PersistenceStrategyStream<Member> p = new PersistenceStrategyStream<>();
        container.setStrategy(p);
        Scanner sc = new Scanner(System.in);
        System.out.println("Mögliche Befehle:\n 'enter'\n 'store'\n 'load'\n 'dump'\n 'exit'");

        while(true){
            System.out.print( "\n> "  );
            String eingabe = sc.nextLine().trim();

            switch (eingabe) {
                case "enter" -> {
                    container.addMember(enter());
                    System.out.println("\nUser Story hinzugefügt, bitte geben Sie ihre nächste Eingabe an:");
                }
                case "store" -> {
                    System.out.println("\nGeben sie den Dateinamen zum Speichern an: \n");
                    p.setLocation(sc.nextLine());
                    container.store();
                    System.out.println("\nUser Story erfolgreich gespeichert, bitte geben Sie ihre nächste Eingabe an:");
                }
                case "load" -> {
                    System.out.println("\nGeben sie den Dateinamen zum Laden an: \n");
                    p.setLocation(sc.nextLine());
                    container.load();
                    System.out.println("\nUser Story erfolgreich geladen, bitte geben Sie ihre nächste Eingabe an:");
                }
                case "dump" -> container.getCurrentList().stream().sorted().forEach(System.out::println);
                case "exit" ->{
                    sc.close();
                    System.out.println("\nProgramm erfolgreich beendet.");
                    return;
                }
                case "help" -> System.out.println("\nMögliche Befehle:\n 'enter'\n 'store'\n 'load'\n 'dump'\n 'exit'");
                default -> System.out.println("\nUngültige Eingabe, siehe 'help'.");
            }
        }
    }

    private static UserStoryMember enter(){
        String[] userStory = new String[9];
        Scanner sc = new Scanner(System.in);
        System.out.print("\nUser Story ID: "); userStory[0] = sc.nextLine();
        System.out.print("Titel: "); userStory[1] = sc.nextLine();
        System.out.print("Akzeptanzkriterium: "); userStory[2] = sc.nextLine();

        double mehrw = 0;
        double strafe = 0;
        double risiko = 0;
        boolean mehrwFalse = true;
        boolean strafeFalse = true;
        boolean risikoFalse = true;
        while(true){
            if(mehrwFalse) {
                System.out.print("Relativer Mehrwert: ");
                mehrw = sc.nextDouble();
                if (mehrw > 5 || mehrw < 1) {
                    System.out.println("\033[31mNur Zahlen zwischen 1 und 5 erlaubt!\033[0m");
                    continue;
                } else {
                    mehrwFalse = false;
                }
            }
            if(strafeFalse) {
                System.out.print("Relative Strafe: ");
                strafe = sc.nextDouble();
                if (strafe > 5 || strafe < 1) {
                    System.out.println("\033[31mNur Zahlen zwischen 1 und 5 erlaubt!\033[0m");
                    continue;
                } else {
                    strafeFalse = false;
                }
            }
            if (risikoFalse) {
                System.out.print("Relatives Risiko: ");
                risiko = sc.nextDouble();
                if (risiko > 5 || risiko < 1) {
                    System.out.println("\033[31mNur Zahlen zwischen 1 und 5 erlaubt!\033[0m");
                    continue;
                } else {
                    risikoFalse = false;
                }
            }
            break;
        }

        System.out.print("Relativer Aufwand: "); double aufwand = sc.nextDouble();
        sc.nextLine();
        System.out.print("Projekt Zuordnung: "); userStory[7] = sc.nextLine();
        double prio = (mehrw+strafe) / (aufwand+risiko);
        userStory[3] = String.valueOf(mehrw);
        userStory[4] = String.valueOf(strafe);
        userStory[5] = String.valueOf(risiko);
        userStory[6] = String.valueOf(aufwand);
        userStory[8] = String.valueOf(prio);
        return new UserStoryMember(userStory);
    }
}

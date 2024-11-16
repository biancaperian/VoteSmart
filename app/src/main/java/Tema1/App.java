package Tema1;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    private Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
        // Implementați aici cerințele din enunț
        // Pentru citirea datelor de la tastatura se folosește câmpul scanner.
        Alegeri alegeri = new Alegeri();
        while (true) {
            int nrTask = scanner.nextInt();
            scanner.nextLine();
            if (nrTask == 0) {
                String id = scanner.next(); // citeste id ul
                String nume = scanner.nextLine(); // citeste numele de unde s a terminat id ul
                System.out.println(alegeri.adaugareAlegere(alegeri.listaAlegeri, id, nume));
            }

            if (nrTask == 1) {
                String id = scanner.nextLine();
                System.out.println(alegeri.verificarePornireAlegere(alegeri.listaAlegeri, id ));
            }

            if (nrTask == 2) {
                String id = scanner.next();
                String nume = scanner.next();
                String regiune = scanner.nextLine();
                id = id.trim();
                nume = nume.trim();
                regiune = regiune.trim();
                System.out.println(alegeri.verificareIdAdaugareCircumscriptie(alegeri.listaAlegeri, id, nume, regiune));
            }

            if (nrTask == 3) {
                String id = scanner.next();
                String nume = scanner.next();
                id = id.trim();
                nume = nume.trim();
                System.out.println(alegeri.verificareIdEliminareCircumscriptie(alegeri.listaAlegeri, id, nume));
            }

            if (nrTask == 4) {
                String id = scanner.next();
                String CNP = scanner.next();
                int varsta = scanner.nextInt();
                String nume = scanner.nextLine();
                System.out.println(alegeri.adaugareCandidat(alegeri.listaAlegeri, id, CNP, varsta, nume));
            }

            if (nrTask == 5) {
                String id = scanner.next();
                String CNP = scanner.nextLine();
                CNP = CNP.trim();

                System.out.println(alegeri.eliminareCandidat(alegeri.listaAlegeri, id, CNP));
            }

            if (nrTask == 6) {
                String id = scanner.next();
                String circ = scanner.next();
                String CNP = scanner.next();
                int varsta = scanner.nextInt();
                String neindemanatic = scanner.next();
                String nume = scanner.nextLine();
                System.out.println(alegeri.adaugareVotant(alegeri.listaAlegeri, id, circ, CNP, varsta, neindemanatic, nume));
            }

            if (nrTask == 7) {
                String id = scanner.nextLine();
                alegeri.printareCandidatiDinAlegeri(alegeri.listaAlegeri, id);
            }

            if (nrTask == 8) {
                String id = scanner.next();
                String numeCirc = scanner.nextLine();
                alegeri.printareVotantiDinCircumscriptie(alegeri.listaAlegeri, id, numeCirc);
            }

            if (nrTask == 9) {
                String id = scanner.next();
                String numeCirc = scanner.next();
                String CNP_votant = scanner.next();
                String CNP_candidat = scanner.nextLine();
                System.out.println(alegeri.votare(alegeri.listaAlegeri, id, numeCirc, CNP_votant, CNP_candidat));
            }

            if (nrTask == 10) {
                String id = scanner.nextLine();
                System.out.println(alegeri.oprireAlegeri(alegeri.listaAlegeri, id));
            }

            if (nrTask == 11) {
                String id = scanner.next();
                String numeCirc = scanner.nextLine();
                alegeri.raportVoturi(alegeri.listaAlegeri, id, numeCirc);
            }

            if (nrTask == 12) {
                String id = scanner.next();
                alegeri.raportVoturiNationale(alegeri.listaAlegeri, id);

            }

            if (nrTask == 18) {
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}
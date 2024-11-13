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
            int nrTasks = scanner.nextInt();
            scanner.nextLine();
            if (nrTasks == 0) {
                String id = scanner.next(); // citeste id ul
                String nume = scanner.nextLine(); // citeste numele de unde s a terminat id ul
                System.out.println(alegeri.adaugareAlegere(alegeri.listaAlegeri, id, nume));
            }

            if (nrTasks == 1) {
                String id = scanner.nextLine();
                System.out.println(alegeri.verificarePornireAlegere(alegeri.listaAlegeri, id ));
            }

            if (nrTasks == 2) {
                String id = scanner.next();
                String nume = scanner.next();
                String regiune = scanner.nextLine();
                id = id.trim();
                nume = nume.trim();
                regiune = regiune.trim();
                System.out.println(alegeri.verificareIdAdaugareCircumscriptie(alegeri.listaAlegeri, id, nume, regiune));
            }

            if (nrTasks == 18) {
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
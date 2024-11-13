package Tema1;

import java.util.ArrayList;

public class Alegeri {
    ArrayList<Alegere> listaAlegeri = new ArrayList<Alegere>();

    public String adaugareAlegere(ArrayList<Alegere> listaAlegeri, String id, String nume) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                return "EROARE: Deja exista alegeri cu id " + id + "\n";
            }
        }
        Alegere alegere = new Alegere(id, nume, "NEINCEPUT");
        listaAlegeri.add(alegere);
        return "S-au creat alegerile" + nume + "\n";
    }

    public String verificarePornireAlegere(ArrayList<Alegere> listaAlegeri, String id) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                if (a.getCurent().equals("NEINCEPUT") == true) {
                    a.pornireAlegere();
                    return "Au pornit alegerile" + a.getNume();
                }
                else {
                    return "EROARE: Alegerile deja au inceput";
                }
            }
        }

        return "EROARE: Nu exista alegeri cu acest id";
    }

    public String verificareIdAdaugareCircumscriptie(ArrayList<Alegere> listaAlegeri, String id, String numeCirc, String numeRegiune) {
        for (Alegere a : listaAlegeri) {
            if (a.verificareId(id) == 1) {
                String rezultat = a.adaugareCircumscriptie(a, numeCirc, numeRegiune);
                return rezultat;
            }
        }
        return "EROARE: Nu exista alegeri cu acest id";
    }


}

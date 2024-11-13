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
        Alegere alegere = new Alegere(id, nume);
        listaAlegeri.add(alegere);
        return "S-au creat alegerile" + nume + "\n";
    }




}

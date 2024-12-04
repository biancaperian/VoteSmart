package Tema1;

import java.util.ArrayList;

/**
 * Clasa Analiza reprezinta o componenta pentru analizarea rezultatelor
 * dintr-o anumita regiune in cadrul unei alegeri. Include informatii
 * despre numele regiunii, lista candidatilor din regiune si lista voturilor.
 */


public class Analiza {
    private String numeRegiune;
    ArrayList<Candidat> listaCandidatiRegiune;
    ArrayList<Vot> listaVoturi = new ArrayList();


    Analiza(String numeRegiune) {
        this.numeRegiune = numeRegiune;
        listaCandidatiRegiune = new ArrayList<>();
    }

    Analiza() {
        this.numeRegiune = null;
        listaCandidatiRegiune = new ArrayList<>();
    }

    public String getNumeRegiune() {
        return numeRegiune;
    }

    public void setNumeRegiune(String numeRegiuna) {
        this.numeRegiune = numeRegiuna;
    }
}

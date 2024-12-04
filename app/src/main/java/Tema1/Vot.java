package Tema1;

/**
 *  Aceasta clasa reprezinta un singur vot si ofera informatii
 *  despre candidatul votat si numarul de voturi din circumscriptie
 *  pentru fiecare candidat.
 */

public class Vot implements Comparable<Vot>{
    Candidat candidat;
    private int nrVoturiCirc;

    /**
     * Metoda getter pentru obtinerea numarului de voturi din circumscriptie
     * @return numarul de voturi din circumscriptie.
     */
    public int getNrVoturiCirc() {
        return nrVoturiCirc;
    }

    /**
     * Constructor fara parametri.
     */
    Vot() {
        candidat = new Candidat();
        nrVoturiCirc = 0;
    }

    /**
     * Constructor cu parametri ce initializeaza un vot cu candidatul aferent si
     * numarul de voturi di circumscriptie 0.
     * @param candidat - candidatul asociat acestui vot
     */
    Vot(Candidat candidat) {
        this.candidat = candidat;
        nrVoturiCirc = 0;
    }

    /**
     * Metoda ce incrementeaza numarul de voturi din circumscriptie.
     */
    public void adaugaVotCirc() {
        this.nrVoturiCirc++;
    }

    /**
     * Compara doua voturi.
     * Comparatia se face mai intai dupa numarul de voturi ale fiecarui candidat,
     * iar, in caz de egalitate, dupa CNP-ul fiecaruia.
     * @param altVot the object to be compared.
     * @return un numar negativ, zero sau pozitiv daca acest obiect
     * este mai mic, egal sau mai mare decat altVot.
     */
    public int compareTo(Vot altVot) {
        int rezultat =  Integer.compare(this.nrVoturiCirc, altVot.nrVoturiCirc);
        if (rezultat == 0) {
            return this.candidat.getCNP().compareTo(altVot.candidat.getCNP());
        }
        return rezultat;
    }

}

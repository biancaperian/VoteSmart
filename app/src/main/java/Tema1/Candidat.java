package Tema1;

/**
 * Clasa Candidat extinde clasa abstracta Persoana si adauga caracteristici
 * specifice unui candidat, cum ar fi numarul de voturi stranse.
 */
public class Candidat extends Persoana implements Comparable<Candidat> {

    private int numarVoturi = 0;

    Candidat() {
        super();
    }

    Candidat(String nume, String CNP, int varsta) {
        super(nume, CNP, varsta);
    }

    public int getNrVoturi() {
        return this.numarVoturi;
    }

    /**
     * Metoda ce incrementeaza numarul de voturi ale candidatului.
     */
    public void crestereNrVoturi() {
        this.numarVoturi++;
    }

    /**
     * Compara acest obiect Candidat cu un alt obiect Candidat.
     * Mai intai se compara numarul de voturi, iar, in caz de egalitate,
     * dupa CNP-ul candidatului.
     * @param altCandidat the object to be compared.
     * @return un numar negativ, zero sau pozitiv daca acest obiect Candidat
     * mai mic, egal sau mai maere decat altCandidat.
     */
    public int compareTo(Candidat altCandidat) {
        int rezultat =  Integer.compare(this.numarVoturi, altCandidat.numarVoturi);
        if (rezultat == 0) {
            return this.getCNP().compareTo(altCandidat.getCNP());
        }
        return rezultat;
    }

}
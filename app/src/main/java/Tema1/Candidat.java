package Tema1;

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

    public void crestereNrVoturi() {
        this.numarVoturi++;
    }

    public int compareTo(Candidat altCandidat) {
        int rezultat =  Integer.compare(this.numarVoturi, altCandidat.numarVoturi);
        if (rezultat == 0) {
            return this.getCNP().compareTo(altCandidat.getCNP());
        }
        return rezultat;
    }

}
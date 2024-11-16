package Tema1;

public class Candidat extends Persoana {

    private static int numarVoturi = 0;

    Candidat() {
        super();
    }

    Candidat(String nume, String CNP, int varsta) {
        super(nume, CNP, varsta);
    }

    public int getnrVoturi() {
        return this.numarVoturi;
    }

    public void crestereNrVoturi() {
        this.numarVoturi++;
    }

}
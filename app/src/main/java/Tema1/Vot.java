package Tema1;

public class Vot implements Comparable<Vot>{
    Candidat candidat;
    private int nrVoturiCirc;

    public int getNrVoturiCirc() {
        return nrVoturiCirc;
    }

    Vot() {
        candidat = new Candidat();
        nrVoturiCirc = 0;
    }

    Vot(Candidat candidat) {
        this.candidat = candidat;
        nrVoturiCirc = 0;
    }

    public void adaugaVotCirc() {
        this.nrVoturiCirc++;
    }

    public int compareTo(Vot altVot) {
        int rezultat =  Integer.compare(this.nrVoturiCirc, altVot.nrVoturiCirc);
        if (rezultat == 0) {
            return this.candidat.getCNP().compareTo(altVot.candidat.getCNP());
        }
        return rezultat;
    }

}

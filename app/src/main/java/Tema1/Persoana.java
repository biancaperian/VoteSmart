package Tema1;

public abstract class Persoana {
    private String nume;
    private String CNP;
    private int varsta;

    Persoana() {
        this.nume = null;
        this.CNP = null;
        this.varsta = 0;
    }

    Persoana(String nume, String CNP, int varsta) {
        this.nume = nume;
        this.CNP = CNP;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCNP() {
        return CNP;
    }
    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getVarsta() {
        return varsta;
    }
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }


}

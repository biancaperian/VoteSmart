package Tema1;

public class Circumscriptie {
    private String nume;
    private String regiune;

    Circumscriptie() {
        this.nume = null;
        this.regiune = null;
    }

    Circumscriptie(String nume, String regiune) {
        this.nume = nume;
        this.regiune = regiune;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getRegiune() {
        return regiune;
    }

    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }



}


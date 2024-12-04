package Tema1;

/**
 * Clasa Votant extinde clasa abstracta Persoana si adauga caracteristici
 * specifice unui votant, cum ar fi un atribut "neindemanatic" si o stare
 * boolean care  indica daca a votat sau nu.
 */

public class Votant extends Persoana{
    private String neindemanatic;
    private boolean votat;

    Votant() {
        super();
        this.neindemanatic = null;
        this.votat = false;
    }

    Votant(String nume, String CNP, int varsta, String neindemanatic, boolean votat) {
        super(nume, CNP, varsta);
        this.neindemanatic = neindemanatic;
        this.votat = votat;
    }

    public void setVotat(boolean votat) {
        this.votat = true;
    }

    public boolean getVotat() {
        return this.votat;
    }

    public String getNeindemanatic() {
        return neindemanatic;
    }

    public void setNeindemanatic(String neindemanatic) {
        this.neindemanatic = neindemanatic;
    }
}

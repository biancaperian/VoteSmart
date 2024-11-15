package Tema1;

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
}

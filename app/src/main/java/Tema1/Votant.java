package Tema1;

public class Votant extends Persoana{
    private String neindemanatic;

    Votant() {
        super();
        this.neindemanatic = null;
    }

    Votant(String nume, String CNP, int varsta, String neindemanatic) {
        super(nume, CNP, varsta);
        this.neindemanatic = neindemanatic;
    }
}

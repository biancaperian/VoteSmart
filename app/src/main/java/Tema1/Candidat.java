package Tema1;

public class Candidat extends Persoana {
    Candidat() {
        super();
    }

    Candidat(String nume, String CNP, int varsta) {
        super(nume, CNP, varsta);
    }

    public int verificareCNP(String CNP) {
        if (CNP.length() != 13) {
            return 1; // CNP ul nu este valid
        }
        return 1; // CNP ul este valid
    }

    public int verificareVarsta(int varsta) {
        if (varsta < 35) {
            return 1; // varsta e prea mica
        }
        return 0; //  varsta e potrivita
    }


}
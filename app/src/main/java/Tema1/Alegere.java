package Tema1;

import java.util.ArrayList;

public class Alegere {
    private String id;
    private String nume;
    private String curent;

    ArrayList<Circumscriptie> listaCircumscriptii =  new ArrayList();

    public String getCurent() {
        return curent;
    }

    public void setCurent(String curent) {
        this.curent = curent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    Alegere() {
        this.id = null;
        this.nume = null;
        this.curent = "NEINCEPUT";
    }

    Alegere(String id, String nume, String curent) {
        this.id = id;
        this.nume = nume;
        this.curent = "NEINCEPUT";
    }

    public int verificareId(String id) {
        if (id.equals(this.id)) {
            return 1; // id ul nu este unic -> nu adaugam
        }
        return 0; // id ul este unic -> adaugam
    }

    public void pornireAlegere() {
        this.curent = "INCEPUT";
    }

    public int verificareCircumscriptie(String numeCircumsriptie) {
        for (Circumscriptie circ : listaCircumscriptii) {
            if (circ.getNume().equals(numeCircumsriptie) == true) {
                return 1; // circumscriptia exista -> nu adaugam
            }
        }
        return 0; //circumscriptia nu exista -> o adaugam
    }

    public String adaugareCircumscriptie(Alegere alegere, String numeCircumscriptie, String numeRegiune) {
        if (alegere.verificareCircumscriptie(numeCircumscriptie) == 1) {
            return "EROARE: Deja exista o circumscriptie cu numele " + numeCircumscriptie;
        }

        if (alegere.curent.equals("NEINCEPUT") == true) {
            return "EROARE: Nu este perioada de votare";
        }

        Circumscriptie circ =  new Circumscriptie(numeCircumscriptie, numeRegiune);
        alegere.listaCircumscriptii.add(circ);
        return "S-a adaugat circumscriptia " + numeCircumscriptie;
    }
}

package Tema1;
/**
 * Aceasta clasa reprezinta o singura alegere si contine informatii
 * despre Id-ul, numele si starea curenta a alegerii. De asemenea, fiecare
 * alegere are o lista de circumscriptii, de candidati si fraude.
 */

import java.util.ArrayList;

public class Alegere {
    private String id;
    private String nume;
    private String curent;

    ArrayList<Circumscriptie> listaCircumscriptii =  new ArrayList();
    ArrayList<Candidat> listaCandidati =  new ArrayList();
    ArrayList<Frauda> listaFraude =  new ArrayList();

    /**
     *  Metoda care returneaza starea curenta a alegerii.
     * @return stare curenta sub forma de string.
     */
    public String getCurent() {
        return curent;
    }

    /**
     * Seteaza starea curenta a alegerii.
     * @param curent - Starea curenta care trebuie setata.
     */
    public void setCurent(String curent) {
        this.curent = curent;
    }

    /**
     * Returneaza id-ul unic al alegerii.
     * @return id-ul unic al alegerii sub forma de String.
     */
    public String getId() {
        return id;
    }

    /**
     * Seteaza id-ul unic al alegerii.
     * @param id - id-ul unic care trebuie setat.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returneaza numele alegerii.
     *
     * @return Numele alegerii sub forma de String.
     */
    public String getNume() {
        return nume;
    }

    /**
     * Seteaza numele alegerii.
     *
     * @param nume - Numele care trebuie setat.
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Constructor fara parametri care seteaza id-ul si numele null,
     * iar starea ca "NEINCEPUT".
     */
    Alegere() {
        this.id = null;
        this.nume = null;
        this.curent = "NEINCEPUT";
    }

    /**
     * Constructor suprascris. Creeaza o alegere cu id-ul si numele specificate,
     * iar starea curenta o sa fie mereu "NEINCEPUT".
     *
     * @param id -  Id-ul unic al alegerii.
     * @param nume - Numele alegerii.
     * @param curent - Starea curenta, la inceput mereu setata "NEINCEPUT".
     */
    Alegere(String id, String nume, String curent) {
        this.id = id;
        this.nume = nume;
        this.curent = "NEINCEPUT";
    }

    /**
     * Verifica daca un id este unic, comparat cu celelalte id-uri deja existente.
     *
     * @param id - id-ul care trebuie verificat.
     * @return 1 daca id-ul nu este unic, 0 altfel.
     */
    public int verificareId(String id) {
        if (id.equals(this.id)) {
            return 1; // id ul nu este unic -> nu adaugam
        }
        return 0; // id ul este unic -> adaugam
    }

    /**
     * Porneste o alegere => seteaza starea curenta a alegerii la "In_CURS"
     */
    public void pornireAlegere() {
        this.curent = "IN_CURS";
    }

    /**
     * Verifica unicitatea unei circumscriptii in lista de circumscriptii.
     *
     * @param numeCircumsriptie - Numele circumscriptiei care trebuie verificata.
     * @return 1 daca circumscriptia exista, 0 altfel.
     */
    public int verificareCircumscriptie(String numeCircumsriptie) {
        for (Circumscriptie circ : listaCircumscriptii) {
            if (circ.getNume().equals(numeCircumsriptie) == true) {
                return 1; // circumscriptia exista -> nu adaugam
            }
        }
        return 0; //circumscriptia nu exista -> o adaugam
    }

    /**
     * Adauga o circumscriptie in lista de circumscriptii daca aceasta
     * nu exista deja si daca alegerile sunt in stagiul "IN_CURS".
     *
     * @param alegere - Alegerea in care dorim sa adaugam circumscriptia.
     * @param numeCircumscriptie - Numele circumscriptiei pe care vrem sa il adaugam.
     * @param numeRegiune - Numele regiunii aferente circumcriptiei
     * @return Un mesaj care indica o eroare sau daca circumscriptia a fost adaugata.
     */
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

    /**
     * Elimina o circumscriptie din lista de circumscriptii daca aceasta
     * exista si daca alegerile sunt in stagiul "IN_CURS".
     * @param alegere - Alegerea din care vrem sa eliminam circumscriptis.
     * @param numeCirc - Numele circumscriptiei pe care dorim sa o eliminam.
     * @return Un mesaj care confirma stergerea circumscriptiei sau un mesaj
     * de eroare in caz contrar.
     */
    public String eliminareCircumscriptie(Alegere alegere, String numeCirc) {

        if (alegere.verificareCircumscriptie(numeCirc) == 1 && alegere.curent.equals("IN_CURS") == true) {
            alegere.listaCircumscriptii.remove(alegere);
            return "S-a sters circumscriptia " + numeCirc;
        }

        if (alegere.curent.equals("IN_CURS") == false) {
            return "EROARE: Nu este perioada de votare";
        }

        return "EROARE: Nu exista o circumscriptie cu numele " + numeCirc;
    }

    /**
     * Calculeaza numarul de voturi totale dintr-o circumscriptie
     * @return Numarul total de voturi adunate de toti canditatii.
     */
    public int totalVoturiNationale() {
        int numarVoturi = 0;
        for (Candidat candidat : listaCandidati) {
            numarVoturi += candidat.getNrVoturi();
        }
        return numarVoturi;
    }
}
